package com.win.store.mapper;

import com.win.store.entity.GoodsCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryMapperTestCase {

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void findByParent(){
        Long parentId = 162L;
        List<GoodsCategory> list = categoryMapper.findByParent(parentId);
        System.out.println("开始");
        for (GoodsCategory g1 : list) {
            System.out.println(g1);
        }
        System.out.println("结束");
    }


}
