package com.win.store.service;

import com.win.store.entity.Address;
import com.win.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrcictServiceTestCase {
    @Autowired
    private IDistrictService iDistrictService;

    @Test
    public void getListByParent(){
        String parent = "86";
        List<District> result = iDistrictService.getListByParent(parent);
        System.err.println("BEGIN.");
        for (District district : result
                ) {
            System.err.println(district);
        }
        System.err.println("ENO.");
    }
    @Test
    public void getByCode(){
        String code = "450000";
        District district = iDistrictService.getByCode(code);

        System.err.println(district);
    }

}
