package com.bawei.dianshangjinweek02.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class CartlistBean {
    @Id
    private long id;
    @NotNull
    private long shopId;
    private String shopName;
    private String defaultPic;
    private long productId;
    private String productName;
    private String color;
    private String meal;
    private long price;
    private int count;
    private boolean checked;
    @Generated(hash = 1890091846)
    public CartlistBean(long id, long shopId, String shopName, String defaultPic,
            long productId, String productName, String color, String meal,
            long price, int count, boolean checked) {
        this.id = id;
        this.shopId = shopId;
        this.shopName = shopName;
        this.defaultPic = defaultPic;
        this.productId = productId;
        this.productName = productName;
        this.color = color;
        this.meal = meal;
        this.price = price;
        this.count = count;
        this.checked = checked;
    }
    @Generated(hash = 1785380587)
    public CartlistBean() {
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getShopId() {
        return shopId;
    }
    public void setShopId(long shopId) {
        this.shopId = shopId;
    }
    public String getShopName() {
        return shopName;
    }
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
    public String getDefaultPic() {
        return defaultPic;
    }
    public void setDefaultPic(String defaultPic) {
        this.defaultPic = defaultPic;
    }
    public long getProductId() {
        return productId;
    }
    public void setProductId(long productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getMeal() {
        return meal;
    }
    public void setMeal(String meal) {
        this.meal = meal;
    }
    public long getPrice() {
        return price;
    }
    public void setPrice(long price) {
        this.price = price;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public boolean isChecked() {
        return checked;
    }
    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    public boolean getChecked() {
        return this.checked;
    }
}