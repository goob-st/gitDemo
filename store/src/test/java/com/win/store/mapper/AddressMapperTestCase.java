package com.win.store.mapper;

import com.win.store.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressMapperTestCase {

    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void addnew() {
        Address address = new Address();
        address.setUid(3);
        address.setName("TT");
        Integer rows = addressMapper.addnew(address);
        System.err.println("rows=" + rows);
        System.err.println(address);
    }

    @Test
    public void getCountByUid() {
        Integer uid = 1;
        Integer count
                = addressMapper.getCountByUid(uid);
        System.err.println("count=" + count);
    }

    @Test
    public void findByUid() {
        Integer uid = 1;
        List<Address> list = addressMapper.findByUid(uid);
        System.out.println("BEGIN");
        for (Address address :
                list) {
            System.err.println(address);
        }
        System.out.println("END");
    }

    @Test
    public void updateNonDefault(){
        Integer uid = 1;
        addressMapper.updateNonDefault(uid);
        System.out.println("执行成功");
    }

    @Test
    public void updateDefault(){
        Integer id = 57;
        String modifiedUser = "java";
        Date modifiedTime = new Date();
        Integer row = addressMapper.updateDefault(id,modifiedUser,modifiedTime);
        System.out.println(row);
    }

    @Test
    public void findById(){
        Integer id = 62;
        Address address = addressMapper.findById(id);
        System.out.println(address);
    }

    @Test
    public void deleteById(){
        addressMapper.deleteById(63);
    }

    @Test
    public void findLastModified(){
        Address address = addressMapper.findLastModified(2);
        System.out.println(address);
    }

    @Test
    public void updateAddnew(){
        Address address = new Address();
        address.setName("路飞");
        address.setId(67);
        Integer rows = addressMapper.updateAddnew(address);
        System.out.println(address);
        System.out.println(rows);
    }


}
