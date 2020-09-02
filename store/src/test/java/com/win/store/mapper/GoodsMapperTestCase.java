package com.win.store.mapper;

import com.win.store.entity.Goods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 *
 * @Date 2020/7/21 11:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsMapperTestCase {
    @Autowired
    private GoodsMapper goodsMapper;

    //List<Goods> findByCategory(@Param("categoryId") Long categoryId,@Param("offset") Integer offset,@Param("count") Integer count);
    @Test
    public void findByCategory(){
        long categoryId = 163L;
        Integer offset = 0;
        Integer count = 10;
        List<Goods> list = goodsMapper.findByCategory(categoryId,offset,count);
        for (Goods g1: list
             ) {
            System.err.println(g1);
        }
    }

    @ Test
    public void findById(){
        Long id = 10000001L;
        Goods goods = goodsMapper.findById(id);
        System.out.println(goods);
    }

    @Test
    public void findByPriority(){
        Integer count = 4;
        List<Goods> list = goodsMapper.findByPriority(count);
        for (Goods g1: list
        ) {
            System.err.println(g1);
        }
    }
}
