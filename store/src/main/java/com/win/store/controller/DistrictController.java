package com.win.store.controller;

import com.win.store.entity.District;
import com.win.store.service.IDistrictService;
import com.win.store.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/district")
public class DistrictController extends BaseController{

    @Autowired
    private IDistrictService districtService;

    @RequestMapping("/list/{parent}")
    public ResponseResult<List<District>> getListByParent(@PathVariable("parent") String parent){
        List<District> list = districtService.getListByParent(parent);
        return new ResponseResult<List<District>>(SUCCESS,list);
    }
}
