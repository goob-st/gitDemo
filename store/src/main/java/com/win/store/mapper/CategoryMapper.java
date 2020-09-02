package com.win.store.mapper;

import com.win.store.entity.GoodsCategory;

import java.util.List;

/**
 * 商品分类数据的持久层接口
 */
public interface CategoryMapper {
    /**
     * 根据父级id获取子级的商品分类的数据的列表
     * @param parentId 父级商品分类的id
     * @return 子级的商品分类的数据的列表
     */
    List<GoodsCategory> findByParent(Long parentId);

}
