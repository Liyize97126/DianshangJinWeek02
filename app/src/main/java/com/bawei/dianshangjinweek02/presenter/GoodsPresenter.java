package com.bawei.dianshangjinweek02.presenter;

import com.bawei.dianshangjinweek02.base.BasePresenter;
import com.bawei.dianshangjinweek02.contact.IContact;

import io.reactivex.Observable;

public class GoodsPresenter extends BasePresenter {
    public GoodsPresenter(IContact.IView iView) {
        super(iView);
    }
    @Override
    protected Observable getObservable() {
        return getiRequest().shoppingcart();
    }
}
