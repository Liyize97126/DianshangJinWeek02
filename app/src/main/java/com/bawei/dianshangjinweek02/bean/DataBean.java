package com.bawei.dianshangjinweek02.bean;

import java.util.List;

public class DataBean {
    private int code;
    private List<OrderDataBean> orderData;
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public List<OrderDataBean> getOrderData() {
        return orderData;
    }
    public void setOrderData(List<OrderDataBean> orderData) {
        this.orderData = orderData;
    }
}
