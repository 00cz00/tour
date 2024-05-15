package com.example.tour.service.impl;


import com.example.tour.dto.ArticlePageQueryDTO;
import com.example.tour.entity.Article;
import com.example.tour.entity.Collections;
import com.example.tour.entity.ScenicSpot;
import com.example.tour.entity.User;
import com.example.tour.mapper.*;

import com.example.tour.service.ArticleService;
import com.example.tour.vo.ArticlePageQueryVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.java.Log;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ArticleServiceimpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private  CollectionMapper collectionMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ProvinceMapper provinceMapper;
    //文章分页查询
    @Override
    public List<ArticlePageQueryVO> page( ArticlePageQueryDTO articlePageQueryDTO,String userId) {

        int offset=(articlePageQueryDTO.getPage()-1)*articlePageQueryDTO.getPageSize();
        articlePageQueryDTO.setOffset(offset);

        List<Article> list=articleMapper.page(articlePageQueryDTO);

        List<ArticlePageQueryVO> articlePageQueryVOList=new ArrayList<>();

        for (Article a:list){
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
}
