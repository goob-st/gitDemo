package com.win.store.vo;

import java.io.Serializable;

/**
 * 购物车列表实体类
 * @Date 2020/7/25 21:37
 */
public class CartVo implements Serializable {

    private static final long serialVersionUID = -3492309637720583864L;

    private Integer id;
    private Integer uid;
    private Long gid;
    private String title;
    private String image;
    private Integer count;
    private Long oldPrice;
    private Long newPrice;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Long oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Long getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(Long newPrice) {
        this.newPrice = newPrice;
    }

    @Override
    public String toString() {
        return "CartVo{" +
                "id=" + id +
                ", uid=" + uid +
                ", gid=" + gid +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", count=" + count +
                ", oldPrice=" + oldPrice +
                ", newPrice=" + newPrice +
                '}';
    }
}
