package com.win.store.controller;

import com.win.store.entity.GoodsCategory;
import com.win.store.service.ICategoryService;
import com.win.store.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController {

    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping("/list/{parent}")
    public ResponseResult<List<GoodsCategory>> getByParent(@PathVariable("parent") Long parent){
        List<GoodsCategory> list = iCategoryService.getByParent(parent);
        return new ResponseResult<>(SUCCESS,list);
    }

}
