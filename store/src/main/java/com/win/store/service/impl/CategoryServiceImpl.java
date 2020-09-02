package com.win.store.service.impl;

import com.win.store.entity.GoodsCategory;
import com.win.store.mapper.CategoryMapper;
import com.win.store.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<GoodsCategory> getByParent(Long parentId) {
        return findByParent(parentId);
    }


    private List<GoodsCategory> findByParent(Long parentId){
        return categoryMapper.findByParent(parentId);
    }
}
