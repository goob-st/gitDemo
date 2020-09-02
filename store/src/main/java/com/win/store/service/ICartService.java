package com.win.store.service;

import com.win.store.entity.Cart;
import com.win.store.service.serviceEx.AccessDeniedException;
import com.win.store.service.serviceEx.CartNoFoundException;
import com.win.store.service.serviceEx.InsertException;
import com.win.store.service.serviceEx.UpdateException;
import com.win.store.vo.CartVo;

import java.util.List;

/**
 * 购物车业务层接口
 * @Date 2020/7/24 12:51
 */
public interface ICartService {
    /**
     * 将商品添加到购物车
     * @param username 当前操作的执行人
     * @param cart 购物车数据
     * @throws InsertException
     * @throws UpdateException
     */
    void addToCart(String username,Cart cart) throws InsertException, UpdateException;

    /**
     * 根据用户id查询用户添加购物车的数据列表
     * @param uid 用户id
     * @return 受影响的行数
     */
    List<CartVo> getByUid(Integer uid);

    /**
     * 增加购物车中的商品数据
     * @param id 购物车商品id
     * @param uid 用户id
     * @param username 最后修改人
     * @throws CartNoFoundException
     * @throws AccessDeniedException
     * @throws UpdateException
     */
    void addCount(Integer id,Integer uid,String username) throws CartNoFoundException, AccessDeniedException,UpdateException;

    /**
     * 删减购物车中的商品数据
     * @param id
     * @param uid
     * @param username
     * @throws CartNoFoundException
     * @throws AccessDeniedException
     * @throws UpdateException
     */
    void cutCount(Integer id,Integer uid,String username) throws CartNoFoundException, AccessDeniedException,UpdateException;

    /**
     * 根据若干个id查询匹配的购物车数据的集合
     * @param ids 购物车中的数据
     * @return 购物车中的数据
     */
    List<CartVo> getByIds(Integer[] ids);
}
