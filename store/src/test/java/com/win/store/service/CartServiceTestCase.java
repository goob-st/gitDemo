package com.win.store.service;

import com.win.store.entity.Cart;
import com.win.store.service.serviceEx.ServiceException;
import com.win.store.vo.CartVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Date 2020/7/25 12:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceTestCase {

    @Autowired
    private ICartService iCartService;

    @Test
    public void addToCart(){
        String username = "A";
        Cart cart = new Cart();
        cart.setUid(3);
        cart.setGid(10000008L);
        cart.setPrice(10000L);
        cart.setCount(1);
        iCartService.addToCart(username,cart);
    }

    @Test
    public void getByUid(){
        Integer uid = 1;
        List<CartVo> list = iCartService.getByUid(uid);
        for (CartVo cart : list) {
            System.err.println(cart);
        }
    }

    @Test
    public void addCount(){
        Integer id = 6;
        Integer uid = 1;
        String username = "Spring";
        iCartService.addCount(id,uid,username);
    }

    @Test
    public void cutCount(){
        try {
            Integer id = 6;
            Integer uid = 1;
            String username = "Spring";
            iCartService.cutCount(id, uid, username);
        }catch (ServiceException e){
            System.err.println("错误类型：" + e.getClass().getName());
            System.err.println("错误描述：" + e.getMessage());
        }
    }

    @Test
    public void getByIds(){
        Integer[] ids = {5,6,7};
        List<CartVo> list = iCartService.getByIds(ids);
        for (CartVo vo : list) {
            System.err.println(vo);
        }
    }

}
