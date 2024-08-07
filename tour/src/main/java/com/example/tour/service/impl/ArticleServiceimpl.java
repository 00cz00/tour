package com.example.tour.service.impl;


import com.example.tour.dto.AdminarticlePageQueryDTO;
import com.example.tour.dto.ArticleDTO;
import com.example.tour.dto.ArticlePageQueryDTO;
import com.example.tour.dto.CommentDTO;
import com.example.tour.entity.*;
import com.example.tour.mapper.*;

import com.example.tour.result.PageResult;
import com.example.tour.service.ArticleService;
import com.example.tour.vo.ArticleDetialVO;
import com.example.tour.vo.ArticlePageQueryVO;
import com.example.tour.vo.CommentDetialVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceimpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CollectionMapper collectionMapper;
    @Autowired
    private ArticleLikeMapper articleLikeMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ProvinceMapper provinceMapper;
    @Autowired
    private FolloweeMapper followeeMapper;

    //文章分页查询
    @Override
    public List<ArticlePageQueryVO> page(ArticlePageQueryDTO articlePageQueryDTO, String userId) {

        int offset = (articlePageQueryDTO.getPage() - 1) * articlePageQueryDTO.getPageSize();
        articlePageQueryDTO.setOffset(offset);

        List<Article> list = articleMapper.page(articlePageQueryDTO);

        List<ArticlePageQueryVO> articlePageQueryVOList = new ArrayList<>();

        for (Article a : list) {
            ArticlePageQueryVO articlePageQueryVO = new ArticlePageQueryVO();
            BeanUtils.copyProperties(a, articlePageQueryVO);

            //根据userId查询作者信息
            User user = userMapper.getById(a.getUserId());
            ///user.setPassword("****");
            articlePageQueryVO.setUser(user);

            //根据文章id查询其收藏数
            int collection = collectionMapper.countCollection(a.getId());
            articlePageQueryVO.setCollection(collection);

            //根据文章id查询其评论数
            int comment = commentMapper.countComment(a.getId());
            articlePageQueryVO.setComment(comment);

            //根据省份id查出其名称
            String provinceName = provinceMapper.getById(a.getProvinceId());
            articlePageQueryVO.setProvince(provinceName);

            //根据登录用户id和文章id判断是否收藏该文章
            Collections collection1 = collectionMapper.isCollected(userId, a.getId());
            if (collection1 != null) {
                articlePageQueryVO.setIsCollected(1);
            }

            //根据登录用户id和文章id判断是否点赞该文章
            ArticleLike articleLike = articleLikeMapper.isLike(userId, a.getId());
            if (articleLike != null) {
                articlePageQueryVO.setIsLiked(1);
            }
            articlePageQueryVOList.add(articlePageQueryVO);

        }

       /* //判断是最热还是最新
        if(articlePageQueryDTO.getSearchBy().equals("hot")){
             ArticlePageQueryVO temper;

            for (int i=0;i<articlePageQueryVOList.size()-1;i++){
                for (int j=i+1;j<articlePageQueryVOList.size();j++){

                    if(articlePageQueryVOList.get(i).getLike()<articlePageQueryVOList.get(j).getLike()){
                        temper=articlePageQueryVOList.get(i);
                        articlePageQueryVOList.set(i,articlePageQueryVOList.get(j));
                        articlePageQueryVOList.set(j,temper);
                    }
                }
            }
        }*/

        return articlePageQueryVOList;
    }

    @Override
    public List<Article> selectCollection(String userId) {

        return articleMapper.selectCollection(userId);
    }

    @Override
    public List<Article> selectMyArticle(String userId) {
        return articleMapper.selectMyArticle(userId);
    }

    @Override
    public void collectArticle(String userId, String id) {


        collectionMapper.collectArticle(userId, id);
    }

    @Override
    public void comment(CommentDTO commentDTO, String userId) {
        commentDTO.setCreateTime(LocalDateTime.now());
        commentMapper.comment(commentDTO, userId);
    }

    //根据文章id查询具体内容
    @Override
    public ArticleDetialVO getById(String id, String userId) {
        Article article = articleMapper.getBy(id);
        ArticleDetialVO articleDetialVO = new ArticleDetialVO();

        String createTime = article.getCreateTime().toString();
        createTime = createTime.replace("T", " ");
        BeanUtils.copyProperties(article, articleDetialVO);
        //  BeanUtils.copyProperties(a,articlePageQueryVO);
        articleDetialVO.setCreateTime(createTime);

        //根据userId查询作者信息
        User user = userMapper.getById(article.getUserId());
        user.setPassword("****");
        articleDetialVO.setUser(user);

        //根据文章id查询其收藏数
        int collection = collectionMapper.countCollection(article.getId());
        articleDetialVO.setCollection(collection);

        //根据文章id查询其评论数
        int comment = commentMapper.countComment(article.getId());
        articleDetialVO.setComment(comment);

        //根据省份id查出其名称
        String provinceName = provinceMapper.getById(article.getProvinceId());
        articleDetialVO.setProvince(provinceName);

        //根据登录用户id和文章id判断是否收藏该文章
        Collections collection1 = collectionMapper.isCollected(userId, article.getId());
        if (collection1 != null) {
            articleDetialVO.setIsCollected(1);
        }

        //根据登录用户id和文章id判断是否点赞该文章
        ArticleLike articleLike = articleLikeMapper.isLike(userId, article.getId());
        if (articleLike != null) {
            articleDetialVO.setIsLiked(1);
        }
        //根据用户id和文章作者id判断是否关注该作者
        Followee followee = followeeMapper.isFollowed(userId, article.getUserId());
        if (followee != null) {
            articleDetialVO.setIsFollowed(1);
        }

        //根据文章id查出其全部评论
        List<Comment> commentList = commentMapper.getByArticleId(id);
        List<CommentDetialVO> commentDetialVOS = new ArrayList<>();
        for (Comment comment1 : commentList) {
            User user1 = userMapper.getById(comment1.getUserId());
            CommentDetialVO commentDetialVO = new CommentDetialVO();
            BeanUtils.copyProperties(comment1, commentDetialVO);
            commentDetialVO.setUser(user1);
            commentDetialVOS.add(commentDetialVO);
        }

        articleDetialVO.setCommentContent(commentDetialVOS);


        return articleDetialVO;
    }

    public void ThumbsUp(String id, String userId) {
        articleMapper.ThumbsUp(id);
        articleLikeMapper.insert(id, userId);
    }

    public void delete(String id) {
        articleMapper.delete(id);
        collectionMapper.articleDelete(id);
        articleLikeMapper.deleteByArticleId(id);
        commentMapper.deleteByArticleId(id);


    }

    @Override
    public void disThumbsUp(String id, String userId) {
        articleMapper.disThumbsUp(id);
        articleLikeMapper.disThumbsUp(id, userId);
    }

    @Override
    public void abolishCollect(String userId, String articleId) {
        collectionMapper.abolishCollect(userId, articleId);
    }

    @Override
    public List<ArticlePageQueryVO> followeeArticle(ArticlePageQueryDTO articlePageQueryDTO, String userId) {
        int offset = (articlePageQueryDTO.getPage() - 1) * articlePageQueryDTO.getPageSize();
        articlePageQueryDTO.setOffset(offset);
        List<Article> articles = articleMapper.followeeArticle(articlePageQueryDTO, userId);
        List<ArticlePageQueryVO> articlePageQueryVOList = new ArrayList<>();
        for (Article a : articles) {

            ArticlePageQueryVO articlePageQueryVO = new ArticlePageQueryVO();
            BeanUtils.copyProperties(a, articlePageQueryVO);

            //根据userId查询作者信息
            User user = userMapper.getById(a.getUserId());
            user.setPassword("****");
            articlePageQueryVO.setUser(user);

            //根据文章id查询其收藏数
            int collection = collectionMapper.countCollection(a.getId());
            articlePageQueryVO.setCollection(collection);

            //根据文章id查询其评论数
            int comment = commentMapper.countComment(a.getId());
            articlePageQueryVO.setComment(comment);

            //根据省份id查出其名称
            String provinceName = provinceMapper.getById(a.getProvinceId());
            articlePageQueryVO.setProvince(provinceName);

            //根据登录用户id和文章id判断是否收藏该文章
            Collections collection1 = collectionMapper.isCollected(userId, a.getId());
            if (collection1 != null) {
                articlePageQueryVO.setIsCollected(1);
            }

            //根据登录用户id和文章id判断是否点赞该文章
            ArticleLike articleLike = articleLikeMapper.isLike(userId, a.getId());
            if (articleLike != null) {
                articlePageQueryVO.setIsLiked(1);
            }
            articlePageQueryVOList.add(articlePageQueryVO);

        }


        return articlePageQueryVOList;
    }

    //查询用户点赞的文章
    @Override
    public List<ArticlePageQueryVO> like(String id) {
        List<Article> articles = articleMapper.getById(id);
        List<ArticlePageQueryVO> articlePageQueryVOList = new ArrayList<>();
        for (Article a : articles) {

            ArticlePageQueryVO articlePageQueryVO = new ArticlePageQueryVO();
            BeanUtils.copyProperties(a, articlePageQueryVO);

            //根据userId查询作者信息
            User user = userMapper.getById(a.getUserId());
            user.setPassword("****");
            articlePageQueryVO.setUser(user);

            //根据文章id查询其收藏数
            int collection = collectionMapper.countCollection(a.getId());
            articlePageQueryVO.setCollection(collection);

            //根据文章id查询其评论数
            int comment = commentMapper.countComment(a.getId());
            articlePageQueryVO.setComment(comment);

            //根据省份id查出其名称
            String provinceName = provinceMapper.getById(a.getProvinceId());
            articlePageQueryVO.setProvince(provinceName);

            //根据登录用户id和文章id判断是否收藏该文章
            Collections collection1 = collectionMapper.isCollected(id, a.getId());
            if (collection1 != null) {
                articlePageQueryVO.setIsCollected(1);
            }

            //根据登录用户id和文章id判断是否点赞该文章
            ArticleLike articleLike = articleLikeMapper.isLike(id, a.getId());
            if (articleLike != null) {
                articlePageQueryVO.setIsLiked(1);
            }
            articlePageQueryVOList.add(articlePageQueryVO);

        }
        return articlePageQueryVOList;
    }

    //发布文章
    @Override
    public void publish(ArticleDTO articleDTO) {
        articleDTO.setLikes(0);
        articleDTO.setCreateTime(LocalDateTime.now());
        articleMapper.add(articleDTO);
    }

    @Override
    public List<Integer> selectByScenicSport(String id) {
        return articleMapper.selectByScenicSport(id);
    }

    //管理端分页查询
    @Override
    public PageResult adminArticlePage(AdminarticlePageQueryDTO adminarticlePageQueryDTO) {

        PageHelper.startPage(adminarticlePageQueryDTO.getPage(), adminarticlePageQueryDTO.getPageSize());
        Page<Article> page = articleMapper.adminPage(adminarticlePageQueryDTO);

        List<Article> articleList = page.getResult();
        List<ArticlePageQueryVO> articlePageQueryVOList = new ArrayList<>();
        for (Article a : articleList) {


            //  BeanUtils.copyProperties(a,articlePageQueryVO);
            ArticlePageQueryVO articlePageQueryVO = new ArticlePageQueryVO();
            BeanUtils.copyProperties(a, articlePageQueryVO);

            String createTime = a.getCreateTime().toString();
            createTime = createTime.replace("T", " ");
            articlePageQueryVO.setCreateTime(createTime);


            //根据userId查询作者信息
            User user = userMapper.getById(a.getUserId());
            user.setPassword("****");
            articlePageQueryVO.setUser(user);

            //根据文章id查询其收藏数
            int collection = collectionMapper.countCollection(a.getId());
            articlePageQueryVO.setCollection(collection);

            //根据文章id查询其评论数
            int comment = commentMapper.countComment(a.getId());
            articlePageQueryVO.setComment(comment);

            //根据省份id查出其名称
            String provinceName = provinceMapper.getById(a.getProvinceId());
            articlePageQueryVO.setProvince(provinceName);



            articlePageQueryVOList.add(articlePageQueryVO);

        }


        return new PageResult(page.getTotal(), articlePageQueryVOList);
    }
}
