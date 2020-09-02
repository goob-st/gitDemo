package com.win.store.controller;

import com.win.store.entity.Goods;
import com.win.store.service.IGoodsService;
import com.win.store.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Date 2020/7/22 15:47
 */
@RestController
@RequestMapping("/goods")
public class GoodsController extends BaseController {
    @Autowired
    private IGoodsService iGoodsService;

    @GetMapping("/list/{categoryId}")
    public ResponseResult<List<Goods>> getByParent(@PathVariable("categoryId") Long categoryId){
        List<Goods> list = iGoodsService.getByCategory(categoryId,0,20);
        return new ResponseResult<>(SUCCESS,list);
    }

    @GetMapping("/details/{id}")
    public ResponseResult<Goods> getById(@PathVariable("id") Long id){
        Goods goods = iGoodsService.getById(id);
        return new ResponseResult<>(SUCCESS,goods);
    }

    @GetMapping("/hot")
    public ResponseResult<List<Goods>> getHotGoods(){
        List<Goods> list = iGoodsService.getByPriority(4);
        return new ResponseResult<>(SUCCESS,list);
    }
}
