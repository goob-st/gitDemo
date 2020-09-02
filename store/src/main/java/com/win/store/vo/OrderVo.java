package com.win.store.vo;

import com.win.store.entity.OrderItem;

import java.io.Serializable;
import java.util.List;

/**
 * @Date 2020/8/1 16:17
 */
public class OrderVo implements Serializable {

    private static final long serialVersionUID = -3209355179469730877L;

    private Integer id;
    private Integer uid;
    private String recvName;
    private String recvPhone;
    private String recvDistrict;
    private String recvAddress;
    private Long pay;
    private Integer status;
    private List<OrderItem> items;

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

    public String getRecvName() {
        return recvName;
    }

    public void setRecvName(String recvName) {
        this.recvName = recvName;
    }

    public String getRecvPhone() {
        return recvPhone;
    }

    public void setRecvPhone(String recvPhone) {
        this.recvPhone = recvPhone;
    }

    public String getRecvDistrict() {
        return recvDistrict;
    }

    public void setRecvDistrict(String recvDistrict) {
        this.recvDistrict = recvDistrict;
    }

    public String getRecvAddress() {
        return recvAddress;
    }

    public void setRecvAddress(String recvAddress) {
        this.recvAddress = recvAddress;
    }

    public Long getPay() {
        return pay;
    }

    public void setPay(Long pay) {
        this.pay = pay;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "OrderVo{" +
                "id=" + id +
                ", uid=" + uid +
                ", recvName='" + recvName + '\'' +
                ", recvPhone='" + recvPhone + '\'' +
                ", recvDistrict='" + recvDistrict + '\'' +
                ", recvAddress='" + recvAddress + '\'' +
                ", pay=" + pay +
                ", status=" + status +
                ", items=" + items +
                '}';
    }
}
