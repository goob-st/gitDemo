package com.win.store.service.impl;

import com.win.store.entity.User;
import com.win.store.mapper.UserMapper;
import com.win.store.service.IUserService;
import com.win.store.service.serviceEx.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

//业务层实现类

@Service
public class UserServiceImpl implements IUserService {

    @Autowired private UserMapper userMapper;

    @Override
    public User reg(User user) throws DuplicateKeyException, InsertException {
        User data = findByUsername(user.getUsername());
        //判断获取的数据是否为null
        if (data == null){
            //是：用户名不存在，允许注册，则处理密码加密
            //{补充非用户提交的数据}
            user.setIsDelete(0);
            //4.日志补充
            Date now = new Date();
            user.setCreatedUser(user.getUsername());
            user.setCreatedTime(now);
            user.setModifiedUser(user.getUsername());
            user.setModifiedTime(now);
            //---------------------
            //{处理密码加密}
            //加密1：获取随机的UUID作为盐值
            String salt = UUID.randomUUID().toString();
            //加密2：获取用户提交的原始密码
            String srcPassword = user.getPassword();
            //加密3：基于原始密码和盐值执行加密，获取通过MD5加密过后的密码
            String md5Password = getMd5Password(srcPassword,salt);
            //加密4：将加密后的密码封装在user对象中
            user.setPassword(md5Password);
            //加密5：将盐值封装在user对象中
            user.setSalt(salt);
            //执行注册
            addnew(user);
            return user;
        }else {
            throw new DuplicateKeyException("用户名"+user.getUsername()+"已被占用");
        }
    }

    @Override
    public User login(String username, String password) throws UserNotFoundException, PasswordNotMatchException {
        // 根据参数username查询用户数据
        User data = findByUsername(username);
        // 判断用户数据是否为null
        if (data == null){
            // 是：为null，用户名不存在，则抛出UserNotFoundException
            throw new UserNotFoundException("用户名不存在");
        }

        // 否：非null，根据用户名找到了数据，取出盐值
        String salt = data.getSalt();
        //  对参数password执行加密
        String md5Password = getMd5Password(password,salt);

        //  判断密码是否匹配
        if (data.getPassword().equals(md5Password)){
            //  是：匹配，密码正确，则判断是否被删除
            if (data.getIsDelete() == 1){
                //  是：已被删除，则抛出UserNotFoundException或自定义“用户被删除异常”
                throw new UserNotFoundException("用户名不存在");
            }else {
                //      否：没被删除，则登录成功，将第1步查询的用户数据中的盐值和密码设置为null
                data.setSalt(null);
                data.setPassword(null);
                //      返回用户数据
                return data;
            }
        }else {
            //  否：不匹配，密码错误，则抛出PasswordNotMatchException
            throw new PasswordNotMatchException("密码错误");
        }

    }

    @Override
    public void changePassword(Integer uid, String oldPassword, String newPassword) throws UserNotFoundException, PasswordNotMatchException, UpdateException {
        User data = findById(uid);
        if (data == null){
            throw new UserNotFoundException("该用户不存在");
        }
        if (data.getIsDelete() == 1){
            throw new UserNotFoundException("该用户已被注销");
        }
        String salt = data.getSalt();
        //  对参数password执行加密
        String oldMd5Password = getMd5Password(oldPassword,salt);
        if (data.getPassword().equals(oldMd5Password)){
            String newMd5Password = getMd5Password(newPassword,salt);
            Date now = new Date();
            updatePassword(uid,newMd5Password,data.getUsername(),now);
        }else {
            throw new PasswordNotMatchException("原始密码错误");
        }

    }

    @Override
    public void changeInfo(User user) throws UserNotFoundException, UpdateException {
        User data = findById(user.getId());
        if (data ==null){
            throw new UserNotFoundException("无法查询此用户");
        }
        if (data.getIsDelete() == 1){
            throw new UserNotFoundException("该用户已被注销");
        }
        Date now = new Date();
        user.setModifiedUser(user.getUsername());
        user.setModifiedTime(now);
        updateInfo(user);
    }

    @Override
    public User getById(Integer id) {
        User data = findById(id);
        data.setPassword(null);
        data.setSalt(null);
        data.setIsDelete(null);
        return data;
    }

    @Override
    public void changeAvatar(Integer uid, String avatar) throws UserNotFoundException, UpdateException {
        // 根据参数uid查询用户数据
        User user = findById(uid);
        // 判断是否为null
        if (user == null){
            // 是：UserNotFoundException
            throw new UserNotFoundException("该用户不存在！");
        }
        // 判断isDelete==1
        if (user.getIsDelete()==1){
            // 是：UserNotFoundException
            throw new UserNotFoundException("该用户已被注销");
        }
        String modifiedUser = user.getUsername();
        Date modifiedTime = new Date();
        // 执行更新头像
        updateAvatar(uid,avatar,modifiedUser,modifiedTime);
    }

    /**
     * 密码加密
     * @param srcPassword 用户密码
     * @param salt  盐值
     * @return
     */
    private String getMd5Password(String srcPassword,String salt){
        //盐值+原密码+盐值+原密码
        String str = salt + srcPassword + salt + srcPassword;
        for (int i = 0; i < 10; i++) {
            str = DigestUtils.md5DigestAsHex(str.getBytes());
        }
        return str;
    }

    /**
     * 插入用户数据
     *
     * @param user 用户数据
     * @throws InsertException
     */
    private void addnew(User user){
        Integer rows = userMapper.addnew(user);
        if (rows != 1){
            throw new InsertException("增加用户时出现异常");
        }
    }

    /**
     * 根据用户名查询数据
     * @param username 用户名
     * @return 匹配用户数据，如果没有匹配的数据，则返回null
     */
    private User findByUsername(String username){
        return userMapper.findByUsername(username);
    }

    /**
     * 根据用户ID查询数据
     * @param id 用户ID
     * @return  匹配用户数据，如果没有匹配的数据，则返回null
     */
    private User findById(Integer id){
        return userMapper.findById(id);
    }

    /**
     * 用户修改密码
     * @param uid 用户ID
     * @param password 用户新密码
     * @param modifiedUser 修改人
     * @param modifiedTime 修改时间
     */
    private void updatePassword(Integer uid, String password,String modifiedUser, Date modifiedTime){
        Integer rows = userMapper.updatePassword(uid,password,modifiedUser,modifiedTime);
        if (rows != 1){
            throw new UpdateException("修改密码失败");
        }
    }

    /**
     * 用户修改资料
     * @param user 用户原始资料信息
     */
    private void updateInfo(User user){
        Integer rows = userMapper.updateInfo(user);
        if (rows != 1){
            throw new UpdateException("更新时出现错误");
        }
    }

    /**
     * 用户修改头像
     * @param uid 用户ID
     * @param avatar 用户要上传的头像
     * @param modifiedUser 信息修改人
     * @param modifiedTime 信息修改时间
     */
    private void updateAvatar(Integer uid,String avatar,String modifiedUser, Date modifiedTime ){
        Integer rows = userMapper.updateAvatar(uid,avatar,modifiedUser,modifiedTime);
        if (rows != 1){
            throw new UpdateException("上传失败，请重新尝试！");
        }
    }

}
