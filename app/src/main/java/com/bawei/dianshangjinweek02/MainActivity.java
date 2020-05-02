package com.bawei.dianshangjinweek02;

import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.dianshangjinweek02.adapter.GoodsListAdapter;
import com.bawei.dianshangjinweek02.base.BaseActivity;
import com.bawei.dianshangjinweek02.bean.DataBean;
import com.bawei.dianshangjinweek02.bean.OrderDataBean;
import com.bawei.dianshangjinweek02.contact.IContact;
import com.bawei.dianshangjinweek02.dao.DaoMaster;
import com.bawei.dianshangjinweek02.dao.OrderDataBeanDao;
import com.bawei.dianshangjinweek02.presenter.GoodsPresenter;
import com.bawei.dianshangjinweek02.util.RetrofitUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    //定义
    @BindView(R.id.expanlistv)
    protected ExpandableListView expanlistv;
    @BindView(R.id.check_all)
    protected CheckBox checkAll;
    @BindView(R.id.total_price)
    protected TextView totalPrice;
    private GoodsListAdapter goodsListAdapter;
    private GoodsPresenter goodsPresenter;
    //数据库
    //private CartlistBeanDao cartlistBeanDao;
    private OrderDataBeanDao orderDataBeanDao;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
    @Override
    protected void initView() {
        goodsListAdapter = new GoodsListAdapter();
        expanlistv.setAdapter(goodsListAdapter);
        goodsListAdapter.setOnPriceChangeListener(new GoodsListAdapter.OnPriceChangeListener() {
            @Override
            public void priceChange(double totalMoney, boolean isAll) {
                totalPrice.setText("合计：￥" + totalMoney);
                checkAll.setChecked(isAll);
            }
        });
        //数据库实例化
        //cartlistBeanDao = DaoMaster.newDevSession(this,CartlistBeanDao.TABLENAME).getCartlistBeanDao();
        orderDataBeanDao = DaoMaster.newDevSession(this,OrderDataBeanDao.TABLENAME).getOrderDataBeanDao();
        goodsPresenter = new GoodsPresenter(new IContact.IView() {
            @Override
            public void onViewSuccess(DataBean result) {
                if(result.getCode() == 200){
                    goodsListAdapter.getList().addAll(result.getOrderData());
                    goodsListAdapter.notifyDataSetChanged();
                    //保存数据
                    orderDataBeanDao.insertOrReplaceInTx(result.getOrderData());
                }

            }
            @Override
            public void onViewFail(String err) {
                Toast.makeText(MainActivity.this, "请求失败\n" + err, Toast.LENGTH_LONG).show();
            }
        });
        //发起请求
        if(RetrofitUtil.getRetrofitUtil().isNet(this)){
            goodsPresenter.request();
        } else {
            Toast.makeText(MainActivity.this, "已加载缓存数据", Toast.LENGTH_LONG).show();
            List<OrderDataBean> orderDataBeans = orderDataBeanDao.loadAll();//查询所有数据
            goodsListAdapter.getList().addAll(orderDataBeans);
            goodsListAdapter.notifyDataSetChanged();
        }
    }
    @OnClick(R.id.check_all)
    protected void checkAll(){
        goodsListAdapter.checkAll(checkAll.isChecked());
    }
    @Override
    protected void initDestroy() {
        //释放资源
        if(goodsListAdapter != null){
            goodsListAdapter.getList().clear();
            goodsListAdapter = null;
        }
        if(goodsPresenter != null){
            goodsPresenter.destroy();
            goodsPresenter = null;
        }
    }
}
