package com.win.store.service;

import com.win.store.entity.User;
import com.win.store.service.serviceEx.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsernameTest {
    @Autowired
    private IUserService iUserService;

    @Test
    public void reg(){
        try {
            Date date = new Date();
            User user = new User();
            user.setUsername("b");
            user.setPassword("111");
            user.setGender(1);
            user.setPhone("139777");
            user.setEmail("java@tedu.cn");
            User result = iUserService.reg(user);
            System.err.println("result=" + result);
            System.out.println(date);
        }catch (ServiceException e){
            System.err.println("错误类型："+e.getClass().getName());
            System.err.println("错误描述："+e.getMessage());
        }

    }
    @Test
    public void login() {
        try {
            String username = "b";
            String password= "222";
            User result = iUserService.login(username, password);
            System.err.println("result=" + result);
        } catch (ServiceException e) {
            System.err.println("错误类型：" + e.getClass().getName());
            System.err.println("错误描述：" + e.getMessage());
        }
    }

    @Test
    //public void changePassword(Integer uid, String oldPassword, String newPassword)
    public void changePassword(){
        try {
            Integer uid = 2;
            String oldPassword = "222";
            String newPassword = "111";
            iUserService.changePassword(uid,oldPassword,newPassword);
            System.out.println("OK");
        }catch (ServiceException e){
            System.err.println("错误类型：" + e.getClass().getName());
            System.err.println("错误描述：" + e.getMessage());
        }
    }

    @Test
    public void changeInfo(){
        try {
            User user = new User();
            user.setId(2);
            user.setUsername("O");
            user.setGender(0);
            user.setPhone("138888");
            user.setEmail("spring@..3");
            iUserService.changeInfo(user);
            System.err.println("OK");
        }catch (ServiceException e){
            System.err.println("错误类型：" + e.getClass().getName());
            System.err.println("错误描述：" + e.getMessage());
        }
    }

    @Test
    public void changeAvatar(){
        try {
            Integer uid = 7;
            String avatar = "upload/2020.jpg";
            iUserService.changeAvatar(uid,avatar);
            System.out.println("OK");
        }catch (ServiceException e){
            System.err.println("错误类型：" + e.getClass().getName());
            System.err.println("错误描述：" + e.getMessage());
        }
    }
}
