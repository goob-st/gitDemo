package com.win.store.service.impl;

import com.win.store.entity.Goods;
import com.win.store.mapper.GoodsMapper;
import com.win.store.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品数据的实现类
 * @Date 2020/7/22 15:32
 */
@Service
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> getByCategory(Long categoryId, Integer offset, Integer count) {
        return findByCategory(categoryId,offset,count);
    }

    @Override
    public Goods getById(Long id) {
        return findById(id);
    }

    @Override
    public List<Goods> getByPriority(Integer count) {
        return findByPriority(count);
    }

    /**
     * 根据商品分类,查询商品列表
     * @param categoryId 商品分类的id
     * @param offset 偏移量
     * @param count 获取数据的最大数量
     * @Data 2020/7/22
     * @return 商品列表
     */
   private List<Goods> findByCategory(Long categoryId,Integer offset,Integer count){
        return goodsMapper.findByCategory(categoryId,offset,count);
    }

    /**
     * 根据id查询商品详情
     * @param id 商品id
     * @return 返回商品详情数据
     */
    private Goods findById(Long id){
       return goodsMapper.findById(id);
    }

    /**
     * 根据优先级或许商品信息
     * @param count 最多查看多少条数据
     * @return 放回优先级最高的商品信息列表
     */
    private List<Goods> findByPriority(Integer count){
        return goodsMapper.findByPriority(count);
    }
}
