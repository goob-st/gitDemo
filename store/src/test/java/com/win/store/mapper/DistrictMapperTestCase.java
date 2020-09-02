package com.win.store.mapper;

import com.win.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictMapperTestCase {
    @Autowired
    private DistrictMapper districtMapper;

    @Test
    public void findByParent(){
        String parent = "86";
        List<District> result = districtMapper.findByParent(parent);
        System.out.println("Begin.");
        for (District district: result
             ) {
            System.err.println(district);
        }
        System.out.println("END.");
    }

    @Test
    public void findByCode(){
        String code = "450000";
        District district = districtMapper.findByCode(code);
        System.err.println(district);
    }

}
