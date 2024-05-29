package com.example.tour.service.impl;

import com.example.tour.dto.AdminScenicSpotUpdateDTO;
import com.example.tour.dto.ScenicSpotPageQueryDTO;
import com.example.tour.entity.ScenicSpotLike;
import com.example.tour.entity.ScenicSpot;
import com.example.tour.entity.ScenicSpotPic;
import com.example.tour.mapper.ProvinceMapper;
import com.example.tour.mapper.ScenicSpotLikeMapper;
import com.example.tour.mapper.ScenicSpotPicMapper;
import com.example.tour.mapper.ScenicSpotMapper;
import com.example.tour.service.ArticleService;
import com.example.tour.service.ScenicSpotService;
import com.example.tour.vo.ScenicSpotVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScenicSpotServiceimpl implements ScenicSpotService {
    @Autowired
    private ScenicSpotMapper scenicSpotMapper;
    @Autowired
    private ScenicSpotPicMapper sceneSpotPicMapper;
    @Autowired
    private ScenicSpotLikeMapper sceneSpotLikeMapper;
    @Autowired
    private ProvinceMapper provinceMapper;
    @Autowired
    private ScenicSpotLikeMapper scenicSpotLikeMapper;
    @Autowired
    ArticleService articleService;


    @Override
    public List<ScenicSpotVO> page(ScenicSpotPageQueryDTO scenicSpotPageQueryDTO, String userId) {

        int offset=(scenicSpotPageQueryDTO.getPage()-1)*scenicSpotPageQueryDTO.getPageSize();
        scenicSpotPageQueryDTO.setOffset(offset);

            //PageHelper.startPage(scenicSpotPageQueryDTO.getPage(),scenicSpotPageQueryDTO.getPageSize());
        List<ScenicSpot> scenicSpotList= scenicSpotMapper.page(scenicSpotPageQueryDTO);

        List<ScenicSpotVO> scenicSpotVOList=new ArrayList<>();
        for(ScenicSpot scenicSpot:scenicSpotList){
            ScenicSpotVO scenicSpotVO=new ScenicSpotVO();
            BeanUtils.copyProperties(scenicSpot,scenicSpotVO);
            //根据id查出对应省份名
            String provinceName=provinceMapper.getById(scenicSpot.getProvinceId());
            scenicSpotVO.setProvinceName(provinceName);

            //判断用户是否点赞该景点
            ScenicSpotLike scenicSpotLike=sceneSpotLikeMapper.getByBoth(scenicSpot.getId(),userId);

            if(scenicSpotLike!=null){
                scenicSpotVO.setIsLiked(1);
            }
            scenicSpotVOList.add(scenicSpotVO);

        }



        return scenicSpotVOList;
        }
    //根据景点id查询详情
    @Override
    public ScenicSpotVO getDetial(String id,String userId) {
        ScenicSpot scenicSpot=scenicSpotMapper.getById(id);
        ScenicSpotVO scenicSpotVO=new ScenicSpotVO();
        BeanUtils.copyProperties(scenicSpot,scenicSpotVO);
        ScenicSpotLike scenicSpotLike=sceneSpotLikeMapper.getByBoth(id,userId);
        if (scenicSpotLike!=null){
            scenicSpotVO.setIsLiked(1);
        }

        return scenicSpotVO;

    }

    //根据id删除景点
    @Override
    public void delete(String id) {
        //删除景点 景点图片 景点喜欢
        scenicSpotMapper.deleteById(id);
        sceneSpotPicMapper.deleteBySceneSpotId(id);
        sceneSpotLikeMapper.deleteBySceneSpotId(id);
        //删除景点相关文章
        List<Integer> articleList=articleService.selectByScenicSport(id);
        for (int a:
             articleList) {
            articleService.delete(String.valueOf(a));
        }

    }

    //根据id点赞景点
    @Override
    public void like(String sceneSpotId, String userId) {
        scenicSpotMapper.Like(sceneSpotId);
        sceneSpotLikeMapper.insert(sceneSpotId,userId);


    }

    @Override
    public void abolishLike(String sceneSpotId, String userId) {
        scenicSpotMapper.abolishLike(sceneSpotId);
        sceneSpotLikeMapper.deleteBySIdAndUId(sceneSpotId,userId);
    }

    @Override
    public List<ScenicSpot> getByPId(String id) {
        List<ScenicSpot> scenicSpotList=scenicSpotMapper.getByPId(id);

        return scenicSpotList;
    }

    @Override
    public void AdminScenicSpotUpdate(AdminScenicSpotUpdateDTO adminScenicSpotUpdateDTO) {
        scenicSpotMapper.AdminScenicSpotUpdate(adminScenicSpotUpdateDTO);
    }


}
