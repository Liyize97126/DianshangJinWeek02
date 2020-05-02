package com.bawei.dianshangjinweek02.contact;

import com.bawei.dianshangjinweek02.bean.DataBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * 契约类
 */
public interface IContact {
    //视图
    interface IView {
        void onViewSuccess(DataBean result);
        void onViewFail(String err);
    }
    //请求
    interface IRequest {
        //购物车数据
        @GET("api/mall/shoppingcart.json")
        Observable<DataBean> shoppingcart();
    }
}
