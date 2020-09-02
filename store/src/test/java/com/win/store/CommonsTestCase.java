package com.win.store;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonsTestCase {
    @Test
    public void uuid(){
        String salt = "ed9b1db0-14c0-457c-a4c6-e9626b766661";
        String str = salt + "123" + salt + "123";
        String a = DigestUtils.md5DigestAsHex(str.getBytes());
        System.out.println(a);

        //47ae5f99e3e296909daa73677f96b4a6
        //ed9b1db0-14c0-457c-a4c6-e9626b766661
        //salt + srcPassword + salt + srcPassword

    }
}
