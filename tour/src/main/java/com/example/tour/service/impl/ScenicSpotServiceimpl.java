package com.example.tour.service.impl;

import com.example.tour.dto.ScenicSpotPageQueryDTO;
import com.example.tour.entity.ScenicSpot;
import com.example.tour.mapper.ScenicSpotMapper;
import com.example.tour.result.PageResult;
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

    @Override
    public PageResult page(ScenicSpotPageQueryDTO scenicSpotPageQueryDTO) {

            PageHelper.startPage(scenicSpotPageQueryDTO.getPage(),scenicSpotPageQueryDTO.getPageSize());
            Page<ScenicSpot> page= scenicSpotMapper.page(scenicSpotPageQueryDTO);

            //判断是最热还是最新
            if(scenicSpotPageQueryDTO.getSearchBy().equals("最热")){
                ScenicSpot temper;

                List<ScenicSpot> scenicSpotList=page.getResult();

                for (int i=0;i<scenicSpotList.size()-1;i++){
                    for (int j=i+1;j<scenicSpotList.size();j++){

                        if(scenicSpotList.get(i).getLike()<scenicSpotList.get(j).getLike()){
                            temper=scenicSpotList.get(i);
                            scenicSpotList.set(i,scenicSpotList.get(j));
                            scenicSpotList.set(j,temper);

                        }
                    }
                }

                return new PageResult(page.getTotal(), scenicSpotList);
            }
            else {
                return new PageResult(page.getTotal(),page.getResult());
            }
        }


}
