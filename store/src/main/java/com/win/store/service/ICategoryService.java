package com.win.store.service;

import com.win.store.entity.GoodsCategory;

import java.util.List;

/**
 * 商品分类数据的业务层接口
 */
public interface ICategoryService {

    List<GoodsCategory> getByParent (Long parentId);
}
