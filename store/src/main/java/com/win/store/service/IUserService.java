package com.win.store.service;

import com.win.store.entity.User;
import com.win.store.service.serviceEx.*;

public interface IUserService {

    /**
     * 用户注册
     * @param user 用户注册信息
     * @return 成功注册的用户数据
     * @throws DuplicateKeyException
     * @throws InsertException
     */
    User reg(User user) throws DuplicateKeyException, InsertException;

    /**
     * 用户登录
     * @param username 用户名
     * @param password  用户密码
     * @return  用户登录成功
     * @throws UserNotFoundException
     * @throws PasswordNotMatchException
     */
    User login(String username,String password) throws UserNotFoundException, PasswordNotMatchException;

    /**
     * 修改密码
     * @param uid 用户ID
     * @param oldPassword 用户原始密码
     * @param newPassword 用户新密码
     * @throws UserNotFoundException
     * @throws PasswordNotMatchException
     * @throws UpdateException
     */
    void changePassword(Integer uid,String oldPassword,String newPassword)
            throws UserNotFoundException,PasswordNotMatchException, UpdateException;

    /**
     * 修改个人资料
     * @param user 用户数据
     * @throws UserNotFoundException
     * @throws UpdateException
     */
    void changeInfo(User user) throws UserNotFoundException,UpdateException;

    /**
     * 根据用户ID查询用户数据（不包括用户密码或者重要数据）
     * @param id
     * @return 匹配用户数据，如果没有匹配的数据，则返回null
     */
    User getById(Integer id);

    /**
     * 修改头像
     * @param uid 用户ID
     * @param avatar 头像路径
     * @throws UserNotFoundException
     * @throws UpdateException
     */
    void changeAvatar(Integer uid,String avatar) throws UserNotFoundException,UpdateException;
}
