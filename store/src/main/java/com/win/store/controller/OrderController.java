package com.win.store.controller;

import com.win.store.entity.Order;
import com.win.store.service.IOrderService;
import com.win.store.util.ResponseResult;
import com.win.store.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @Date 2020/7/29 1:17
 */
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {
    @Autowired
    private IOrderService orderService;

    @RequestMapping("/create")
    public ResponseResult<Order> createOrder(
            HttpSession session, @RequestParam("address") Integer addressId,@RequestParam("cart_id") Integer[] cartIds) {
        Integer uid = getUidFromSession(session);
        String username = session.getAttribute("username").toString();
        Order order = orderService.createOrder(uid,username,addressId,cartIds);
        return new ResponseResult<Order>(SUCCESS,order);
    }

    @GetMapping("details/{id}")
    public ResponseResult<OrderVo> getById(@PathVariable("id")Integer id,HttpSession session){
        Integer uid = getUidFromSession(session);
        OrderVo data = orderService.getById(id,uid);
        return new  ResponseResult<OrderVo>(SUCCESS,data);
    }
}
