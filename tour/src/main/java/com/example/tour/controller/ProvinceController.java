package com.example.tour.controller;

import com.example.tour.entity.Province;
import com.example.tour.result.Result;
import com.example.tour.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/province")
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;
    @GetMapping("/list")
    public Result<List<Province>> allProvince(){
        List<Province> provinceList= provinceService.getAll();
        return Result.success(provinceList);
    }

}
