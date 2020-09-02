package com.win.store.service.impl;

import com.win.store.entity.Cart;
import com.win.store.mapper.CartMapper;
import com.win.store.service.ICartService;
import com.win.store.service.serviceEx.*;
import com.win.store.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 购物车实现类
 * @Date 2020/7/25 11:12
 */
@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public void addToCart(String username,Cart cart) throws InsertException, UpdateException {
        // 根据参数cart中的uid和gid查询数据
        Integer uid = cart.getUid();
        Long gid = cart.getGid();
        Cart data = findByUidAndGid(uid, gid);
        //获取当前操作时间
        Date modifiedTime = new Date();
        // 判断查询结果是否为null
        if (data == null) {
            // 是：该用户尚未在购物车中添加该商品，则执行新增
            cart.setCreatedUser(username);
            cart.setCreatedTime(modifiedTime);
            cart.setModifiedUser(username);
            cart.setModifiedTime(modifiedTime);
            addnew(cart);
        } else{
            // 否：该用户已经在购物车中添加该商品，则取出此前查询到的数据中的id和count
            Integer dataId = data.getId();
            Integer oldCount = data.getCount();
            // -- 根据上一步取出的count与参数cart中的count（此次用户提交的count），相加得到新的count
            Integer newCount = oldCount + cart.getCount();
            // -- 执行更新
            updateCount(dataId,newCount, username,modifiedTime);
         }
    }

    @Override
    public List<CartVo> getByUid(Integer uid) {
        return finByUid(uid);
    }

    @Override
    public void addCount(Integer id, Integer uid,String username) throws CartNoFoundException, AccessDeniedException, UpdateException {
        // 根据id查询数据
        Cart data = findById(id);
        // 判断数据是否为null
        if (data == null) {
            // 是：抛出异常：CartNotFoundException
            throw new CartNoFoundException("该数据不存在！");
        }
        // 判断数据归属是否不匹配
        if (data.getUid() != uid) {
            // 是：抛出异常：AccessDeniedException
            throw new AccessDeniedException("用户信息不匹配");
        }
        // 获取原来的数量
        Integer count = data.getCount();
        // 将数量+1
        count ++;
        // 更新购物车数据中的数量:updateCount(id, count)
        Date modifiedTime = new Date();
        updateCount(id,count,username,modifiedTime);
    }

    @Override
    public void cutCount(Integer id, Integer uid, String username) throws CartNoFoundException, AccessDeniedException, UpdateException {
        // 根据id查询数据
        Cart data = findById(id);
        // 判断数据是否为null
        if (data == null) {
            // 是：抛出异常：CartNotFoundException
            throw new CartNoFoundException("该数据不存在！");
        }
        // 判断数据归属是否不匹配
        if (!data.getUid().equals(uid)) {
            // 是：抛出异常：AccessDeniedException
            throw new AccessDeniedException("修改商品数量失败！访问权限验证不通过");
        }
        //判断商品总数是否小于一
        if (data.getCount() == 1){
            //是：抛出DeleteException
            throw new DeleteException("删除该数据");
        }
        Integer count = data.getCount();
        count = count - 1;
        // 更新购物车数据中的数量:updateCount(id, count)
        Date modifiedTime = new Date();
        updateCount(id,count,username,modifiedTime);
    }

    @Override
    public List<CartVo> getByIds(Integer[] ids) {
        return findByIds(ids);
    }

    /**
     * 根据用户id和商品id查询购物车数据
     * @param uid 用户id
     * @param goodsId 商品id
     * @return 购物车数据
     */
    private Cart findByUidAndGid(Integer uid, Long goodsId){
        return cartMapper.findByUidAndGid(uid,goodsId);
    }

    /**
     * 新增购物车数据
     * @param cart 商品数据
     */
    private void addnew(Cart cart){
       Integer rows = cartMapper.addnew(cart);
       if (rows !=1){
           throw new InsertException("创建购物车数据时发生错误！");
       }
    }

    /**
     * 更新购物车中商品的数量
     * @param id 商品id
     * @param count 商品数量
     * @param modifiedUser 最后修改人
     * @param modifiedTime 最后修改时间
     */
    private void updateCount(Integer id,Integer count,String modifiedUser,Date modifiedTime){
        Integer rows = cartMapper.updateCount(id,count,modifiedUser,modifiedTime);
        if (rows != 1){
            throw new UpdateException("数据更新失败！");
        }
    }

    /**
     * 根据用户id查询用户添加购物车的商品数据
     * @param uid 用户id
     * @return
     */
    private List<CartVo> finByUid(Integer uid){
       return cartMapper.finByUid(uid);
    }

    /**
     * 根据id获取购物车数据
     * @param id 购物车数据的id
     * @return 匹配的购物车数据，如果没有这返回null
     */
    private Cart findById(Integer id){
        return cartMapper.findById(id);
    }

    /**
     * 根据若干个id查询匹配的购物车数据的集合
     * @param ids 购物车中的数据
     * @return 购物车中的数据
     */
    private List<CartVo> findByIds(Integer[] ids){
        return cartMapper.findByIds(ids);
    }
}
