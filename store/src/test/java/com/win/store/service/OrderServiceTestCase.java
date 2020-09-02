package com.win.store.service;

import com.win.store.entity.Order;
import com.win.store.service.serviceEx.ServiceException;
import com.win.store.vo.OrderVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTestCase {
    @Autowired
    private IOrderService iOrderService;

    @Test
    public void a(){
        try {
            Integer uid = 1;
            String username = "spring";
            Integer addressId = 75;
            Integer[] cartIds = {11,12,13};
            Order order = iOrderService.createOrder(uid,username,addressId,cartIds);
            System.err.println(order);
        }catch (ServiceException e){
            System.err.println("错误类型：" + e.getClass().toString());
            System.err.println("错误描述：" + e.getMessage());
        }
    }

    @Test
    public void b (){
        Integer id = 33;
        Integer uid = 1;
        OrderVo data = iOrderService.getById(id,uid);
        System.out.println(data);
    }



}
