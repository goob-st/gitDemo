package com.win.store.service;

import com.win.store.entity.Address;
import com.win.store.entity.GoodsCategory;
import com.win.store.service.serviceEx.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTestCase {
    @Autowired
    private ICategoryService iCategoryService;

   @Test
    public void findByParent(){
       Long parentId = 162L;
       List<GoodsCategory> list = iCategoryService.getByParent(parentId);
       for (GoodsCategory g1 : list
            ) {
           System.err.println(g1);
       }
   }


}
