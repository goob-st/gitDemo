package com.win.store.controller;


import com.win.store.entity.Cart;
import com.win.store.service.ICartService;
import com.win.store.util.ResponseResult;
import com.win.store.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 购物车数据的控制器类
 * @Date 2020/7/25 14:38
 */
@RestController
@RequestMapping("/cart")
public class CartController extends BaseController{
    @Autowired
    private ICartService cartService;

    @PostMapping("/add_to_cart")
    public ResponseResult<Void> addToCart(HttpSession session, Cart cart){
        String username = session.getAttribute("username").toString();
        Integer uid = getUidFromSession(session);
        cart.setUid(uid);
        cartService.addToCart(username,cart);
        return new ResponseResult<>(SUCCESS);
    }

    @RequestMapping("/list")
    public ResponseResult<List<CartVo>> getByUid(HttpSession session){
        Integer uid = getUidFromSession(session);
        List<CartVo> list =  cartService.getByUid(uid);
        return new ResponseResult<List<CartVo>>(SUCCESS,list);
    }

    @GetMapping("/add_count")
    public ResponseResult<Void> addCount(@RequestParam("id")Integer id,HttpSession session){
        //获取用户名
        String username = session.getAttribute("username").toString();
        //获取uid
        Integer uid = getUidFromSession(session);
        cartService.addCount(id,uid,username);
        return new ResponseResult<>(SUCCESS);
    }

    @GetMapping("/cut_count")
    public ResponseResult<Void> cutCount(@RequestParam("id")Integer id,HttpSession session){
        //获取用户名
        String username = session.getAttribute("username").toString();
        //获取uid
        Integer uid = getUidFromSession(session);
        cartService.cutCount(id,uid,username);
        return new ResponseResult<>(SUCCESS);
    }

    @GetMapping("/get_by_ids")
    public ResponseResult<List<CartVo>> getByIds(@RequestParam("cart_id")Integer[] ids){
        List<CartVo> list = cartService.getByIds(ids);
        return new ResponseResult<List<CartVo>>(SUCCESS,list);
    }


}
