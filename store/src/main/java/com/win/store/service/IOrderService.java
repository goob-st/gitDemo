package com.win.store.service;

import com.win.store.entity.Order;
import com.win.store.service.serviceEx.InsertException;
import com.win.store.vo.OrderVo;

/**
 *订单与订单商品的业务层接口
 * @Date 2020/7/28 15:50
 */
public interface IOrderService {
    /**
     * 创建订单
     * @param uid 当前登录用户的id
     * @param username 当前登录的用户的用户名
     * @param addressId 所选择的收货地址的数据id
     * @param cartIds 订单中商品在购物车中的数据id
     * @return 成功创建的订单
     */
    Order createOrder(Integer uid,String username,
                      Integer addressId,Integer[] cartIds) throws InsertException;

    OrderVo getById(Integer id,Integer uid);
}
