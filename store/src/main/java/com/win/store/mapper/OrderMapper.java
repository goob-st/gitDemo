package com.win.store.mapper;

import com.win.store.entity.Order;
import com.win.store.entity.OrderItem;
import com.win.store.vo.OrderVo;

/**
 * 订单与订单商品数据的持久层接口
 * @Date 2020/7/28 13:52
 */
public interface OrderMapper {
    /**
     * 插入订单数据
     * @param order 订单数据
     * @return 受影响的行数
     */
    Integer insertOrder(Order order);

    /**
     * 插入订单商品数据
     * @param orderItem 订单商品数据
     * @return 受影响的行数
     */
    Integer insertOrderItem(OrderItem orderItem);

    /**
     * 根据id查询订单详情
     * @param id 订单id
     * @return 匹配的订单详情，没有则返回null
     */
    OrderVo findById(Integer id);
}
