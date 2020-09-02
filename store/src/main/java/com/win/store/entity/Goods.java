package com.win.store.entity;

/**
 * 商品数据
 */
public class Goods extends BaseEntity {

  private static final long serialVersionUID = 8436437434360991326L;

  private long id;
  private long categoryId;
  private String itemType;
  private String title;
  private String sellPoint;
  private long price;
  private Integer num;
  private String barcode;
  private String image;
  private Integer status;
  private Integer priority;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(long categoryId) {
    this.categoryId = categoryId;
  }

  public String getItemType() {
    return itemType;
  }

  public void setItemType(String itemType) {
    this.itemType = itemType;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getSellPoint() {
    return sellPoint;
  }

  public void setSellPoint(String sellPoint) {
    this.sellPoint = sellPoint;
  }

  public long getPrice() {
    return price;
  }

  public void setPrice(long price) {
    this.price = price;
  }

  public Integer getNum() {
    return num;
  }

  public void setNum(Integer num) {
    this.num = num;
  }

  public String getBarcode() {
    return barcode;
  }

  public void setBarcode(String barcode) {
    this.barcode = barcode;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Integer getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  @Override
  public String toString() {
    return "TGoods{" +
            "id=" + id +
            ", categoryId=" + categoryId +
            ", itemType='" + itemType + '\'' +
            ", title='" + title + '\'' +
            ", sellPoint='" + sellPoint + '\'' +
            ", price=" + price +
            ", num=" + num +
            ", barcode='" + barcode + '\'' +
            ", image='" + image + '\'' +
            ", status=" + status +
            ", priority=" + priority +
            '}';
  }
}
