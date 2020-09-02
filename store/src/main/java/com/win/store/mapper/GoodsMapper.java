package com.win.store.mapper;

import com.win.store.entity.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品数据的持久层接口
 */
public interface GoodsMapper {

    /**
     * 根据商品分类,查询商品列表
     * @param categoryId 商品分类的id
     * @param offset 偏移量
     * @param count 获取数据的最大数量
     * @Data 2020/7/21
     * @return 商品列表
     */
    List<Goods> findByCategory(
            @Param("categoryId") Long categoryId,@Param("offset") Integer offset,
            @Param("count") Integer count);

    /**
     * 根据id查询商品详情
     * @param id 商品id
     * @return 返回商品详情数据
     */
    Goods findById(Long id);

    /**
     * 根据优先级或许商品信息
     * @param count 最多查看多少条数据
     * @return 放回优先级最高的商品信息列表
     */
    List<Goods> findByPriority(Integer count);

}
