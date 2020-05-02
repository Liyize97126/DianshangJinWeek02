package com.bawei.dianshangjinweek02.bean;

import com.bawei.dianshangjinweek02.util.GoodsConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.List;
@Entity
public class OrderDataBean {
    @Id
    private long shopId;
    private String shopName;
    private boolean checked;
    //Convert
    @Convert(converter = GoodsConverter.class,columnType = String.class)
    private List<CartlistBean> cartlist;
    @Generated(hash = 1973362249)
    public OrderDataBean(long shopId, String shopName, boolean checked,
            List<CartlistBean> cartlist) {
        this.shopId = shopId;
        this.shopName = shopName;
        this.checked = checked;
        this.cartlist = cartlist;
    }
    @Generated(hash = 219276303)
    public OrderDataBean() {
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
    public List<CartlistBean> getCartlist() {
        return cartlist;
    }
    public void setCartlist(List<CartlistBean> cartlist) {
        this.cartlist = cartlist;
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