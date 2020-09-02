package com.win.store.service;

import com.win.store.entity.Address;
import com.win.store.service.serviceEx.DeleteException;
import com.win.store.service.serviceEx.InsertException;
import com.win.store.service.serviceEx.UpdateException;
import com.win.store.service.serviceEx.UserNotFoundException;

import java.util.List;

/**
 * 收货地址的业务层接口
 */
public interface IAddressService {
    /**
     *  创建新收货地址
     * @param username 当前执行人
     * @param address 收货地址信息
     * @return 受影响的行数
     * @throws InsertException
     */
    Address create(String username , Address address) throws InsertException;

    /**
     * 获取某用户的收货地址
     * @param uid 用户ID
     * @return 该用户的收货地址数据
     */
    List<Address> getListByUid(Integer uid);

    /**
     * 根据ID查询收货地址数据
     * @param id 收获地址的ID
     * @return 匹配的收货的数据,如果没有则返回null
     */
    Address getById(Integer id);

    /**
     * 设置收货地址的默认选项
     * @param uid 收获地址归属的用户ID
     * @param id 将要修改为默认地址的数据ID
     */
    void setDefault(Integer uid,Integer id,String modifiedUser);

    /**
     * 根据id删除收货地址
     * @param uid 收货地址归属的用户id
     * @param id 收货地址数据的id
     * @throws DeleteException
     */
    void delete(Integer uid,Integer id,String modifiedUser)throws DeleteException;

    /**
     *  修改用户收货地址
     * @param address 收货地址数据
     * @throws UserNotFoundException
     * @throws UpdateException
     */
    void changeAdd(Address address,Integer id,String modifiedUser) throws UserNotFoundException, UpdateException;

    /**
     * 根据收货地址id查询数据
     * @param id 收货地址id
     * @return 返回收货地址数据
     */
    Address addGetById(Integer id);
}
