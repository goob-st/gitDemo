package com.win.store.service;

import com.win.store.entity.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品数据的业务层接口
 * @Date 2020/7/22 15:30
 */
public interface IGoodsService {
    /**
     * 根据商品分类,查询商品列表
     * @param categoryId 商品分类的id
     * @param offset 偏移量
     * @param count 获取数据的最大数量
     * @Data 2020/7/22
     * @return 商品列表
     */
    List<Goods> getByCategory(Long categoryId,Integer offset,Integer count);

    /**
     * 根据id查询商品详情
     * @param id 商品id
     * @return 返回商品详情数据
     */
    Goods getById(Long id);

    /**
     * 根据优先级或许商品信息
     * @param count 最多查看多少条数据
     * @return 放回优先级最高的商品信息列表
     */
    List<Goods> getByPriority(Integer count);
}
