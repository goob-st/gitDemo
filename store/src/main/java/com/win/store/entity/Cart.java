package com.win.store.entity;

/**
 * 购物车实体类
 * @Date 2020/7/24 12:47
 */
public class Cart extends BaseEntity{

    private static final long serialVersionUID = 7414246216383749477L;

    Integer id;
    Integer uid;
    Long gid;
    Long price;
    Integer count;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", uid=" + uid +
                ", gid=" + gid +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
