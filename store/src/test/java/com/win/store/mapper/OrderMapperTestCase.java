package com.win.store.mapper;

import com.win.store.entity.Order;
import com.win.store.entity.OrderItem;
import com.win.store.vo.OrderVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMapperTestCase {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void insertOrder(){
        Order order = new Order();
        order.setUid(2);
        order.setRecvName("同学");
        Integer rows = orderMapper.insertOrder(order);
        System.out.println(rows);
    }

    @Test
    public void insertOrderItem(){
        OrderItem orderItem = new OrderItem();
        orderItem.setOid(2);
        orderItem.setGoodsCount(2);
        Integer rows = orderMapper.insertOrderItem(orderItem);
        System.out.println(rows);
    }

    @Test
    public void findById(){
        Integer id = 34;
        OrderVo data = orderMapper.findById(id);
        System.err.println(data);
    }


}
