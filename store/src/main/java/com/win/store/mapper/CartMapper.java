package com.win.store.mapper;

import com.win.store.entity.Cart;
import com.win.store.vo.CartVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 购物车数据的持久层接口
 * @Date 2020/7/24 12:50
 */
public interface CartMapper {
    /**
     * 根据用户id和商品id查询购物车数据
     * @param uid 用户id
     * @param goodsId 商品id
     * @return 购物车数据
     */
    Cart findByUidAndGid(@Param("uid")Integer uid,@Param("goodsId") Long goodsId);

    /**
     * 根据id获取购物车数据
     * @param id 购物车数据的id
     * @return 匹配的购物车数据，如果没有这返回null
     */
    Cart findById(Integer id);

    /**
     * 新增购物车数据
     * @param cart 商品数据
     * @return 受影响的行数
     */
    Integer addnew(Cart cart);

    /**
     * 更新购物车中商品的数量
     * @param id 商品id
     * @param count 商品数量
     * @param modifiedUser 最后修改人
     * @param modifiedTime 最后修改时间
     */
    Integer updateCount(@Param("id")Integer id, @Param("count")Integer count,
                        @Param("modifiedUser") String modifiedUser,@Param("modifiedTime") Date modifiedTime);

    /**
     * 根据用户id查询用户添加购物车的数据列表
     * @param uid 用户id
     * @return 受影响的行数
     */
    List<CartVo> finByUid(Integer uid);

    /**
     * 根据若干个id查询匹配的购物车数据的集合
     * @param ids 购物车中的数据
     * @return 购物车中的数据
     */
    List<CartVo> findByIds(Integer[] ids);
}
