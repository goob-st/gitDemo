package com.win.store.mapper;


import com.win.store.entity.Cart;
import com.win.store.vo.CartVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartMapperTestCase {

    @Autowired
    private CartMapper cartMapper;

    @Test
    public void addnew() {
        Cart cart = new Cart();
        cart.setUid(2);
        cart.setPrice(3002L);
        cart.setCount(2);
        cart.setGid(100000001L);
        Integer rows = cartMapper.addnew(cart);
        System.err.println(rows);
    }

    @Test
    public void findByUidAndGid(){
        Integer uid = 2;
        Long goodsId =100000001L;
        Cart cart = cartMapper.findByUidAndGid(uid,goodsId);
        System.err.println(cart);
    }

    @Test
    public void updateCount(){
        String modifiedUser = "A";
        Date modifiedTime = new Date();
        Integer id = 1;
        Integer count = 3;
        Integer rows = cartMapper.updateCount(id,count,modifiedUser,modifiedTime);
        System.err.println(rows);
    }

    @Test
    public void finByUid(){
        List<CartVo> list = cartMapper.finByUid(1);
        for (CartVo cart: list
             ) {
            System.err.println(cart);
        }
    }

    @Test
    public void findById(){
        Integer id = 6;
        Cart cart = cartMapper.findById(id);
        System.err.println(cart);
    }

    @Test
    public void findByIds(){
        Integer[] ids = {5,6,7};
        List<CartVo> list = cartMapper.findByIds(ids);
        for (CartVo c : list) {
            System.err.println(c);
        }
    }


}
