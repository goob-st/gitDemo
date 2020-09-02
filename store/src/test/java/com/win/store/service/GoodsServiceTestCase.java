package com.win.store.service;

import com.win.store.entity.Goods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsServiceTestCase {
    @Autowired
    private IGoodsService iGoodsService;

    @Test
    public void getByCategory(){
        long categoryId = 163L;
        Integer offset = 0;
        Integer count = 2;
        List<Goods> list = iGoodsService.getByCategory(categoryId,offset, count);
        for (Goods g1: list
        ) {
            System.err.println(g1);
        }
    }

    @Test
    public void getById(){
        Long id = 10000017L;
        Goods goods = iGoodsService.getById(id);
        System.out.println(goods);
    }

    @Test
    public void getByPriority(){
        Integer count = 4;
        List<Goods> list = iGoodsService.getByPriority(count);
        for (Goods g1: list
        ) {
            System.err.println(g1);
        }
    }


}
