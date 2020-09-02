package com.win.store.mapper;

import com.win.store.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 出来用户数据的持久层
 */
public interface UserMapper {

    /**
     * 插入用户数据
     *
     * @param user 用户数据
     * @return 受影响行数
     */
    Integer addnew(User user);


    /**
     * 根据用户名查询用户数据
     *
     * @param username 用户名
     * @return 匹配用户数据，如果没有匹配的数据，则返回null
     */
    User findByUsername(String username);


    /**
     * 根据ID查询用户信息
     * @param id 用户id
     * @return 匹配用户数据，如果没有匹配的数据，则返回null
     */
    User findById(Integer id);


    /**
     * 更新密码
     * @param uid 用户id
     * @param password 用户要修改的密码
     * @param modifiedUser  修改人
     * @param modifiedTime  修改时间
     * @return 受影响的行数
     */
    Integer updatePassword(
            @Param("uid") Integer uid, @Param("password") String password,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime")Date modifiedTime
    );

    /**
     * 根据ID查询，用户信息
     * @param user 用户信息
     * @return  用户资料
     */
    Integer updateInfo(User user);

    /**
     * 根据ID查询用户信息，并且修改用户头像
     * @param uid   用户ID
     * @param avatar    用户头像
     * @param modifiedUser  信息修改人
     * @param modifiedTime  信息修改时间
     * @return 受影响的行数
     */
    Integer updateAvatar(
            @Param("uid") Integer uid,
            @Param("avatar") String avatar,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime
    );



}
