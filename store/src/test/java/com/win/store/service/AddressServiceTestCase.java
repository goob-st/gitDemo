package com.win.store.service;

import com.win.store.entity.Address;
import com.win.store.service.serviceEx.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceTestCase {
    @Autowired
    private IAddressService addressService;

    @Test
    public void create(){
        String username = "Admin";
        Address address = new Address();
        address.setUid(1);
        address.setName("马化腾");
        address.setProvince("440000");
        address.setCity("440300");
        address.setArea("440305");
        Address result = addressService.create(username,address);
        System.err.println(result);
    }

    @Test
    public void a(){
        Integer uid = 1;
        List<Address> list = addressService.getListByUid(uid);
        for (Address address: list
             ) {
            System.out.println(address);
        }
    }

    @Test
    public void setDefault(){
        try {
            Integer uid = 2;
            Integer id = 51;
            String modifiedUser = "A";
            Date modifiedTime = new Date();
            addressService.setDefault(uid,id,modifiedUser);
        }catch (ServiceException e){
            System.err.println("错误类型："+e.getClass().getName());
            System.err.println("错误描述："+e.getMessage());
        }
    }

    @Test
    public void delete(){

        try {
            Integer uid = 1;
            Integer id = 65;
            String modifiedUser = "A";
            addressService.delete(uid,id,modifiedUser);
        }catch (ServiceException e){
            System.err.println("错误类型："+e.getClass().getName());
            System.err.println("错误描述："+e.getMessage());
        }
    }

    @Test
    public void changeAdd(){
        Integer id  = 67;
        Address address = new Address();
        address.setId(id);
        address.setName("乔巴");
        address.setProvince("440000");
        address.setCity("440300");
        address.setArea("440305");
        String modifiedUser = "WPS";
        addressService.changeAdd(address,id,modifiedUser);
        System.out.println("OK");
    }

    @Test
    public void addGetById(){
        Address address = addressService.addGetById(76);
        System.out.println(address);
    }


}
