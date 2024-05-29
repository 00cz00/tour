package com.example.tour.controller;

import com.example.tour.dto.*;
import com.example.tour.entity.*;
import com.example.tour.entity.Collections;
import com.example.tour.mapper.*;
import com.example.tour.result.Result;
import com.example.tour.service.ArticleService;
import com.example.tour.service.BannerService;
import com.example.tour.service.UserService;
import com.example.tour.utils.AliYunOssUtils;
import com.example.tour.utils.JwtUtils;
import com.example.tour.vo.ArticlePageQueryVO;
import com.example.tour.vo.UserLoginVO;
import io.jsonwebtoken.Claims;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ArticleService articleService;
   @Autowired
    UserMapper userMapper;


    @Autowired
    private CollectionMapper collectionMapper;
    @Autowired
    private ArticleLikeMapper articleLikeMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ProvinceMapper provinceMapper;
    @Autowired
    private BannerService bannerService;
    //根据id查人
    @GetMapping("/userById")
    public User getUserById(String id){
       return userService.getUserById(id);
    }




    //注册
    @PostMapping("/reg")
    public Result reg(@RequestBody UserRegDTO userRegDTO){
        User user=userService.getByEmail(userRegDTO.getEmail());
        if(user!=null){
            return Result.error("该邮箱已被注册");
        }
        if(userRegDTO.getEmail()==null||userRegDTO.getPassword()==null||userRegDTO.getUsername()==null||userRegDTO.getUrl()==null){
            return Result.error("请填写完整注册信息");
        }

        userService.reg(userRegDTO.getEmail(),userRegDTO.getPassword(),userRegDTO.getUsername(),userRegDTO.getUrl());

        //User usernow=userService.login(userRegDTO.getEmail(),userRegDTO.getPassword());
        return Result.success();
    }

    //发送验证码
    @PostMapping("/send")
    public Result send(@RequestBody Map<String,String> params) {
        String email=params.get("email");
        User user=userService.getByEmail( email);
        if(user!=null){
            return Result.error("该邮箱已被注册");
        }

        return userService.send(email);
    }



    //登录
    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO){
        User user=userService.login(userLoginDTO.getEmail(),userLoginDTO.getPassword());
        System.out.println(user);
        if(user==null){
            System.out.println("账号密码错误");
            return Result.error("账号密码错误");
        }
        Map<String, Object> claims=new HashMap<>();
        claims.put("id",user.getId());
        claims.put("email",user.getEmail());
        claims.put("password",user.getPassword());
        claims.put("username",user.getUsername());
        claims.put("url",user.getUrl());
        String jwt = JwtUtils.createJwt(claims);
        UserLoginVO userLoginVO=new UserLoginVO();
        userLoginVO.setUser(user);
        userLoginVO.setJwt(jwt);
        return Result.success(userLoginVO);
    }





    //关注其他用户
    @PostMapping("/follow/{id}")
    public Result followee(ServletRequest servletRequest,@PathVariable String id){
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        String jwt = req.getHeader("jwt");
        Claims claims = JwtUtils.parserJwt(jwt);
        String userId = (String) claims.get("id");
        userService.followee(userId,id);
        return Result.success("关注成功");
    }




    //查询用户关注的用户
    @GetMapping("/followee/list")
    public Result<List<User>> selectFollowee(ServletRequest servletRequest){
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        String jwt = req.getHeader("jwt");
        Claims claims = JwtUtils.parserJwt(jwt);
        String userId = (String) claims.get("id");
       List<User> userList= userService.selectFollowee(userId);
        return Result.success(userList);
    }


    //取关用户
    @PostMapping("/unfollow/{id}")
    public Result deleteFollowee(ServletRequest servletRequest,@PathVariable String id){
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        String jwt = req.getHeader("jwt");
        Claims claims = JwtUtils.parserJwt(jwt);
        String userId = (String) claims.get("id");
        userService.deleteFollowee(id,userId);
        return Result.success("取关成功");
    }


    //用户删除
    @PostMapping("/deleteUser")
    public Result deleteUser(String id){
        userService.deleteUser(id);
        return Result.success("删除成功");
    }



    //查询用户收藏的文章
    @GetMapping("/article/myCollection")
    public Result<List<ArticlePageQueryVO>> selectCollection(ServletRequest servletRequest){
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        String jwt = req.getHeader("jwt");
        Claims claims = JwtUtils.parserJwt(jwt);
        String userId = (String) claims.get("id");
        List<Article> articleList=articleService.selectCollection(userId);

        List<ArticlePageQueryVO>  articlePageQueryVOList=new ArrayList<>();

        for(Article a:articleList){
            ArticlePageQueryVO articlePageQueryVO=new ArticlePageQueryVO();
            BeanUtils.copyProperties(a,articlePageQueryVO);

           //根据userId查询作者信息
            User user= userMapper.getById(a.getUserId());
            user.setPassword("****");
            articlePageQueryVO.setUser(user);

            //根据文章id查询其收藏数
            int collection= collectionMapper.countCollection(a.getId());
            articlePageQueryVO.setCollection(collection);

            //根据文章id查询其评论数
            int comment = commentMapper.countComment(a.getId());
            articlePageQueryVO.setComment(comment);

            //根据省份id查出其名称
            String provinceName=provinceMapper.getById(a.getProvinceId());
            articlePageQueryVO.setProvince(provinceName);

            //根据登录用户id和文章id判断是否收藏该文章
            Collections collection1=collectionMapper.isCollected(userId,a.getId());
            if(collection1!=null){
                articlePageQueryVO.setIsCollected(1);
            }

            //根据登录用户id和文章id判断是否点赞该文章
            articleLike articleLike=articleLikeMapper.isLike(userId,a.getId());
            if (articleLike!=null){
                articlePageQueryVO.setIsLiked(1);
            }

            articlePageQueryVOList.add(articlePageQueryVO);

        }
        return  Result.success(articlePageQueryVOList);
    }

    //查询我发布的文章
    @GetMapping("/article/my")
    public Result<List<ArticlePageQueryVO>> selectMyArticle(ServletRequest servletRequest){
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        String jwt = req.getHeader("jwt");
        Claims claims = JwtUtils.parserJwt(jwt);
        String userId = (String) claims.get("id");
        List<Article> articleList=articleService.selectMyArticle(userId);
        List<ArticlePageQueryVO>  articlePageQueryVOList=new ArrayList<>();

        for(Article a:articleList){
            ArticlePageQueryVO articlePageQueryVO=new ArticlePageQueryVO();
            BeanUtils.copyProperties(a,articlePageQueryVO);

            //根据userId查询作者信息
            User user= userMapper.getById(a.getUserId());
            user.setPassword("****");
            articlePageQueryVO.setUser(user);

            //根据文章id查询其收藏数
            int collection= collectionMapper.countCollection(a.getId());
            articlePageQueryVO.setCollection(collection);

            //根据文章id查询其评论数
            int comment = commentMapper.countComment(a.getId());
            articlePageQueryVO.setComment(comment);

            //根据省份id查出其名称
            String provinceName=provinceMapper.getById(a.getProvinceId());
            articlePageQueryVO.setProvince(provinceName);

            //根据登录用户id和文章id判断是否收藏该文章
            Collections collection1=collectionMapper.isCollected(userId,a.getId());
            if(collection1!=null){
                articlePageQueryVO.setIsCollected(1);
            }

            //根据登录用户id和文章id判断是否点赞该文章
            articleLike articleLike=articleLikeMapper.isLike(userId,a.getId());
            if (articleLike!=null){
                articlePageQueryVO.setIsLiked(1);
            }

            articlePageQueryVOList.add(articlePageQueryVO);

        }
        return  Result.success(articlePageQueryVOList);
    }

   /* //查询
    @GetMapping("/selectArticleByUserId")
    public Result<List<Article>> selectMyArticle(@RequestBody ArticlePageQueryDTO articlePageQueryDTO){
        List<Article> articleList=articleService.selectMyArticle(userId);
        return  Result.success(articleList);
    }*/




    //收藏文章
    @PostMapping("/collectArticle/{id}")
    public Result collectArticle(ServletRequest servletRequest,@PathVariable String id){
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        String jwt = req.getHeader("jwt");
        Claims claims = JwtUtils.parserJwt(jwt);
        String userId = (String) claims.get("id");
        articleService.collectArticle(userId,id);
        return Result.success("收藏文章成功");
    }

    //取消收藏文章
    @PostMapping("/abolishCollectArticle/{id}")
    public Result abolishCollect(ServletRequest servletRequest,@PathVariable String id){
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        String jwt = req.getHeader("jwt");
        Claims claims = JwtUtils.parserJwt(jwt);
        String userId = (String) claims.get("id");

        articleService.abolishCollect(userId,id);


        return Result.success("取消成功");
    }





    //评论文章
    @PostMapping("/comment")
    public Result comment(@RequestBody CommentDTO commentDTO, ServletRequest servletRequest){
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        String jwt = req.getHeader("jwt");
        Claims claims = JwtUtils.parserJwt(jwt);
        String userId = (String) claims.get("id");
        articleService.comment(commentDTO,userId);
        return  Result.success("评论成功");
    }

    //检测用户是否登录
    @GetMapping("/user/userInfo")
    public Result<User>  isLogin(ServletRequest servletRequest) {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String userId = "null";

        String jwt = req.getHeader("jwt");

        log.info("jwt撒大大:" + jwt);
        if (jwt != null && !jwt.equals("")) {
            try {
                Claims claims = JwtUtils.parserJwt(jwt);
                userId = (String) claims.get("id");
                val userById = userService.getUserById(userId);
                return Result.success(userById);

            } catch (Exception e) {
                return Result.error("登录已过期");

            }

        }
        return Result.error("未登录");

    }




    //上传图片到oss并返回url
    @PostMapping(value="/uploadImgToOSS")
    public String uploadImageToOSS(@RequestParam("imageFile") MultipartFile multipartFile) {
        String imageUrl = null;
        try {
            // 获取文件名
            String originalFilename = multipartFile.getOriginalFilename();
            // 获取文件字节数组
            byte[] bytes = multipartFile.getBytes();
//            // 构造OSS Object Name，可以使用文件名或者自定义的名称
//            String objectName = originalFilename;

            //截取原始文件名的后缀   dfdfdf.png
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            //构造新文件名称
            String objectName = UUID.randomUUID().toString() + extension;

            // 使用工具类上传图片
            imageUrl = AliYunOssUtils.upload(bytes, objectName);

            // 打印图片的URL
            System.out.println("Image uploaded URL: " + imageUrl);

            // 这里可以根据需要将图片URL保存到数据库或进行其他处理


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error occurred while uploading the image.");
        }
        return imageUrl;
    }
    //查询用户喜欢的文章
    @GetMapping("/article/myLike")
    public Result<List<ArticlePageQueryVO>> LikedArticle(ServletRequest servletRequest){
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        String jwt = req.getHeader("jwt");
        Claims claims = JwtUtils.parserJwt(jwt);
        String userId = (String) claims.get("id");

        List<ArticlePageQueryVO> articlePageQueryVOList=articleService.like(userId);

        return Result.success(articlePageQueryVOList);
    }

    //修改头像与用户名
    @PostMapping("/user/updateInfo")
    public Result userUpdateInfo(ServletRequest servletRequest,@RequestBody  UserUpdateInfoDTO userUpdateInfoDTO){
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        String jwt = req.getHeader("jwt");
        Claims claims = JwtUtils.parserJwt(jwt);
        String userId = (String) claims.get("id");
        userService.userUpdateInfo(userId,userUpdateInfoDTO);
        return Result.success("修改成功");
    }



    //修改用户登录密码
    @PostMapping("/user/updatePassword")
    public Result userUpdatePassword(ServletRequest servletRequest,@RequestBody UserUpdatePasswordDTO userUpdatePasswordDTO){
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        String jwt = req.getHeader("jwt");
        Claims claims = JwtUtils.parserJwt(jwt);
        String userId = (String) claims.get("id");
        String email= (String) claims.get("email");

        User user=userService.login(email,userUpdatePasswordDTO.getOldPassword());
        System.out.println(user);

        if(user==null){
            System.out.println("账号密码错误");
            return Result.error("密码错误");
        }
        userService.userUpdatePassword(userId,userUpdatePasswordDTO.getNewPassword());
        return Result.success("修改成功");
    }

    //查询轮播图
    @GetMapping("/banner/select")
    public Result<List<Banner>> bannerSelect(){
        List<Banner> list= bannerService.bannerSelect();
        return Result.success(list);
    }

}
