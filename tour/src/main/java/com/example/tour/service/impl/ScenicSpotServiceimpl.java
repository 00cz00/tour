package com.example.tour.service.impl;

import com.example.tour.dto.ScenicSpotPageQueryDTO;
import com.example.tour.entity.ScenicSpot;
import com.example.tour.entity.ScenicSpotPic;
import com.example.tour.mapper.SceneSpotPicMapper;
import com.example.tour.mapper.ScenicSpotMapper;
import com.example.tour.service.ScenicSpotService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScenicSpotServiceimpl implements ScenicSpotService {
    @Autowired
    private ScenicSpotMapper scenicSpotMapper;
    @Autowired
    private SceneSpotPicMapper sceneSpotPicMapper;

    @Override
    public List<ScenicSpot> page(ScenicSpotPageQueryDTO scenicSpotPageQueryDTO) {

        int offset=(scenicSpotPageQueryDTO.getPage()-1)*scenicSpotPageQueryDTO.getPageSize();
        scenicSpotPageQueryDTO.setOffset(offset);

            //PageHelper.startPage(scenicSpotPageQueryDTO.getPage(),scenicSpotPageQueryDTO.getPageSize());
        List<ScenicSpot> scenicSpotList= scenicSpotMapper.page(scenicSpotPageQueryDTO);

            //List<ScenicSpot> scenicSpotList=page.getResult();

           /* //判断是最热还是最新
            if(scenicSpotPageQueryDTO.getSearchBy().equals("hot")){
                ScenicSpot temper;

                for (int i=0;i<scenicSpotList.size()-1;i++){
                    for (int j=i+1;j<scenicSpotList.size();j++){

                        if(scenicSpotList.get(i).getLike()<scenicSpotList.get(j).getLike()){
                            temper=scenicSpotList.get(i);
                            scenicSpotList.set(i,scenicSpotList.get(j));
                            scenicSpotList.set(j,temper);

                        }
                    }
                }
            }*/
        return scenicSpotList;
        }
    //根据景点id查询详情
    @Override
    public List<ScenicSpotPic> getDetial(String id) {
        List<ScenicSpotPic> scenicSpotPicList=sceneSpotPicMapper.getBySceneSpotId(id);

        return scenicSpotPicList;

    }


}
