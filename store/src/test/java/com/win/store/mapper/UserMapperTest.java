package com.win.store.mapper;

import com.win.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void addnew(){
        Date now = new Date();
        User user = new User();
        user.setUsername("spring");
        user.setPassword("1234");
        user.setGender(1);
        user.setPhone("13800138003");
        user.setEmail("root1@tedu.cn");
        user.setSalt("Hello,MD5!");
        user.setIsDelete(0);
        user.setCreatedUser("Admin");
        user.setModifiedUser("Admin");
        user.setCreatedTime(now);
        user.setModifiedTime(now);
        Integer rows = userMapper.addnew(user);
        System.err.println("rows=" + rows);
    }

    @Test
    public void findByUsername(){
        String username = "spring";
        User user = userMapper.findByUsername(username);
        System.err.println(user);
    }

    @Test
    public void updatePassword(){
        Integer uid = 5;
        String password = "111";
        String modifiedUser = "A";
        Date modifiedTime = new Date();
        Integer rows = userMapper.updatePassword(uid,password,modifiedUser,modifiedTime);
        System.out.println(rows);
    }

    @Test
    public void findById(){
        Integer id = 5;
        User user = userMapper.findById(id);
        System.err.println(user);
    }

    @Test
    public void updateInfo(){
        User user = new User();
        user.setId(6);
        user.setGender(1);
        user.setPhone("12133");
        user.setEmail("jaa@..1");
        user.setModifiedUser("Ch");
        user.setModifiedTime(new Date());
        Integer rows = userMapper.updateInfo(user);
        System.err.println(rows);
    }

    @Test
    public void updateAvatar(){
        Integer uid = 7;
        String avatar = "666";
        String modifiedUser = "P";
        Date modifiedTime = new Date();
        Integer rows = userMapper.updateAvatar(uid,avatar,modifiedUser,modifiedTime);
        System.out.println(rows);
    }
}
