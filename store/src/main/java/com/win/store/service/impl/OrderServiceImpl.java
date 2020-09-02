package com.win.store.service.impl;

import com.win.store.entity.Address;
import com.win.store.entity.Order;
import com.win.store.entity.OrderItem;
import com.win.store.mapper.OrderMapper;
import com.win.store.service.IAddressService;
import com.win.store.service.ICartService;
import com.win.store.service.IOrderService;
import com.win.store.service.serviceEx.AccessDeniedException;
import com.win.store.service.serviceEx.AddressNotFoundException;
import com.win.store.service.serviceEx.InsertException;
import com.win.store.vo.CartVo;
import com.win.store.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单与订单商品的业务层实现类
 * @Date 2020/7/28 15:50
 */
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private IAddressService addressService;
    @Autowired
    private ICartService cartService;

    @Override
    @Transactional
    public Order createOrder(Integer uid, String username, Integer addressId, Integer[] cartIds) throws InsertException {
        // 创建Date对象
        Date now = new Date();
        // 声明pay变量
        Long pay = 0L;
        // List<CartVO> cartService.getByIds(ids)
        List<CartVo> carts = cartService.getByIds(cartIds);
        // 创建List<OrderItem> orderItems
        List<OrderItem> orderItems = new ArrayList<>();
        // 遍历集合，过程中，计算总价pay
        for (CartVo cartVo : carts){
            //计算总价pay
            pay += cartVo.getNewPrice() * cartVo.getCount();
            // -- 创建OrderItem
            OrderItem item = new OrderItem();
            // -- item属性：goods_5，OK
            item.setGoodsId(cartVo.getGid());
            item.setGoodsTitle(cartVo.getTitle());
            item.setGoodsImage(cartVo.getImage());
            item.setGoodsPrice(cartVo.getNewPrice());
            item.setGoodsCount(cartVo.getCount());
            // -- item属性：4个日志，OK
            item.setCreatedUser(username);
            item.setCreatedTime(now);
            item.setModifiedUser(username);
            item.setModifiedTime(now);
            //将item添加到集合中
            orderItems.add(item);
        }
        // 创建Order对象
        Order order = new Order();
        // order属性：uid，OK
        order.setUid(uid);
        // order属性：pay，OK
        order.setPay(pay);
        // 通过addressService.getById()得到收货地址数据
        Address address = addressService.getById(addressId);
        //判断书否查询到address数据
        if (address == null)
            throw new AddressNotFoundException("创建订单失败！收货地址数据有误！");
        // order属性：recv_4，OK
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvDistrict(address.getDistrict());
        order.setRecvAddress(address.getAddress());
        // order属性：order_time，OK
        order.setOrderTime(now);
        // order属性：status，OK，值为0
        order.setStatus(0);
        // order属性：4个日志，OK
        order.setCreatedUser(username);
        order.setCreatedTime(now);
        order.setModifiedUser(username);
        order.setModifiedTime(now);
        // 插入订单数据并获取oid：insertOrder(order)
        insertOrder(order);
        // 遍历orderItems
        for (OrderItem orderItem : orderItems){
            // item属性：oid
            orderItem.setOid(order.getId());
            // 插入订单商品数据
            insertOrderItem(orderItem);
        }
        return order;
    }

    @Override
    public OrderVo getById(Integer id,Integer uid) {
        OrderVo data = findById(id);
        if (!data.getUid().equals(uid)){
            System.out.println(data.getUid());
            throw new AccessDeniedException("访问异常");
        }
        return data;
    }

    /**
     * 插入订单数据
     * @param order 订单数据
     */
    private void insertOrder(Order order){
        Integer rows = orderMapper.insertOrder(order);
        if (rows != 1){
            throw new InsertException("插入订单数据时发生未知错误");
        }
    }

    /**
     * 插入订单商品数据
     * @param orderItem 订单商品数据

     */
    private void insertOrderItem(OrderItem orderItem){
        Integer rows = orderMapper.insertOrderItem(orderItem);
        if (rows != 1){
            throw new InsertException("插入订单商品数据时发生未知错误");
        }
    }

    /**
     * 根据id查询订单详情
     * @param id 订单id
     * @return 匹配的订单详情，没有则返回null
     */
    private OrderVo findById(Integer id){
       return orderMapper.findById(id);
    }
}
