package com.bawei.dianshangjinweek02.base;

import com.bawei.dianshangjinweek02.bean.DataBean;
import com.bawei.dianshangjinweek02.contact.IContact;
import com.bawei.dianshangjinweek02.util.RetrofitUtil;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Presenter基类
 */
public abstract class BasePresenter {
    //定义
    private IContact.IView iView;
    private IContact.IRequest iRequest;
    //封装
    public IContact.IRequest getiRequest() {
        return iRequest;
    }
    //构造
    public BasePresenter(IContact.IView iView) {
        this.iView = iView;
        //初始化iRequest
        iRequest = RetrofitUtil.getRetrofitUtil().create(IContact.IRequest.class);
    }
    //请求方法
    public void request(){
        getObservable()
                //把请求订阅到子线程中进行处理
                .subscribeOn(Schedulers.io())
                //把请求到的结果放入主线程中处理
                .observeOn(AndroidSchedulers.mainThread())
                //结果处理
                .subscribe(new Consumer<DataBean>() {
                    @Override
                    public void accept(DataBean dataBean) throws Exception {
                        //反馈
                        iView.onViewSuccess(dataBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //错误回调
                        throwable.printStackTrace();
                        iView.onViewFail(throwable.getMessage());
                    }
                });
    }
    //方法封装
    protected abstract Observable getObservable();
    //释放资源
    public void destroy(){
        if(iView != null){
            iView = null;
        }
    }
}
