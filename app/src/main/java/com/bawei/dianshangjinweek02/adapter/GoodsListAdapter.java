package com.bawei.dianshangjinweek02.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.dianshangjinweek02.R;
import com.bawei.dianshangjinweek02.bean.CartlistBean;
import com.bawei.dianshangjinweek02.bean.OrderDataBean;
import com.bawei.dianshangjinweek02.view.AddReduceView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class GoodsListAdapter extends BaseExpandableListAdapter {
    private List<OrderDataBean> list = new ArrayList<>();
    public List<OrderDataBean> getList() {
        return list;
    }
    //定义回调
    private OnPriceChangeListener onPriceChangeListener;
    public void setOnPriceChangeListener(OnPriceChangeListener onPriceChangeListener) {
        this.onPriceChangeListener = onPriceChangeListener;
    }
    @Override
    public int getGroupCount() {
        return list.size();
    }
    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getCartlist().size();
    }
    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getCartlist().get(childPosition);
    }
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }
    @Override
    public boolean hasStableIds() {
        return false;
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
    //加载组视图
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        MyGroupViewHouler myGroupViewHouler;
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_item,parent,false);
            myGroupViewHouler = new MyGroupViewHouler();
            myGroupViewHouler.groupCheck = convertView.findViewById(R.id.group_check);
            convertView.setTag(myGroupViewHouler);
        } else {
            myGroupViewHouler = (MyGroupViewHouler) convertView.getTag();
        }
        //设置数据
        OrderDataBean orderDataBean = list.get(groupPosition);
        myGroupViewHouler.groupCheck.setChecked(orderDataBean.isChecked());
        myGroupViewHouler.groupCheck.setTag(orderDataBean);
        myGroupViewHouler.groupCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderDataBean o = (OrderDataBean) v.getTag();
                CheckBox checkBox = (CheckBox) v;
                o.setChecked(checkBox.isChecked());
                List<CartlistBean> cartlist = o.getCartlist();
                for (int i = 0; i < cartlist.size(); i++) {
                    cartlist.get(i).setChecked(o.isChecked());
                }
                //总价计算
                calculateTotalPrice();
                //刷新适配器
                notifyDataSetChanged();
            }
        });
        myGroupViewHouler.groupCheck.setText(orderDataBean.getShopName());
        return convertView;
    }
    //加载子视图
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        //定义
        MyChildViewHouler myChildViewHouler;
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_item, parent, false);
            myChildViewHouler = new MyChildViewHouler();
            myChildViewHouler.childCheck = convertView.findViewById(R.id.child_check);
            myChildViewHouler.childImage = convertView.findViewById(R.id.child_image);
            myChildViewHouler.childName = convertView.findViewById(R.id.child_name);
            myChildViewHouler.childPrice = convertView.findViewById(R.id.child_price);
            myChildViewHouler.childAddReduce = convertView.findViewById(R.id.child_add_reduce);
            convertView.setTag(myChildViewHouler);
        } else {
            myChildViewHouler = (MyChildViewHouler) convertView.getTag();
        }
        //设置数据
        CartlistBean cartlistBean = list.get(groupPosition).getCartlist().get(childPosition);
        myChildViewHouler.childCheck.setChecked(cartlistBean.isChecked());
        myChildViewHouler.childCheck.setTag(cartlistBean);
        myChildViewHouler.childCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartlistBean c = (CartlistBean) v.getTag();
                CheckBox checkBox = (CheckBox) v;
                c.setChecked(checkBox.isChecked());
                for (int i = 0; i < list.size(); i++) {
                    OrderDataBean ord = list.get(i);
                    List<CartlistBean> cartlist = ord.getCartlist();
                    boolean isGoodsChecked = true;
                    for (int j = 0; j < cartlist.size(); j++) {
                        CartlistBean bean = cartlist.get(j);
                        isGoodsChecked = bean.isChecked() && isGoodsChecked;
                    }
                    ord.setChecked(isGoodsChecked);
                }
                //总价计算
                calculateTotalPrice();
                //刷新适配器
                notifyDataSetChanged();
            }
        });
        RequestOptions options = new RequestOptions()
                .fallback(R.drawable.zhan_pict)
                .error(R.drawable.zhan_pict)
                .placeholder(R.drawable.zhan_pict)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(5)));
        Glide.with(myChildViewHouler.childImage.getContext())
                .applyDefaultRequestOptions(options)
                .load(cartlistBean.getDefaultPic())
                .into(myChildViewHouler.childImage);
        myChildViewHouler.childName.setText(cartlistBean.getProductName());
        myChildViewHouler.childPrice.setText("￥" + cartlistBean.getPrice());
        //加减器设置
        myChildViewHouler.childAddReduce.setCount(cartlistBean.getCount());
        myChildViewHouler.childAddReduce.setTag(cartlistBean);
        myChildViewHouler.childAddReduce.setChangeCount(new AddReduceView.ChangeCount() {
            @Override
            public void changeCount(AddReduceView view) {
                int count = view.getCount();
                CartlistBean c = (CartlistBean) view.getTag();
                c.setCount(count);
                //总价计算
                calculateTotalPrice();
            }
        });
        return convertView;
    }
    //全选方法
    public void checkAll(boolean isAll){
        for (int i = 0; i < list.size(); i++) {
            OrderDataBean o = list.get(i);
            o.setChecked(isAll);
            for (int j = 0; j < o.getCartlist().size(); j++) {
                o.getCartlist().get(j).setChecked(isAll);
            }
        }
        //总价计算
        calculateTotalPrice();
        //刷新适配器
        notifyDataSetChanged();
    }
    //计算价格，而且最终决定是否全选和反选
    public void calculateTotalPrice() {
        boolean isAll = true;
        double sumPrice = 0;
        for (int i = 0; i < list.size(); i++) {
            OrderDataBean o = list.get(i);
            isAll = o.isChecked() && isAll;
            for (int j = 0; j < o.getCartlist().size(); j++) {
                CartlistBean cartlistBean = o.getCartlist().get(j);
                if(cartlistBean.isChecked()){
                    sumPrice += (cartlistBean.getPrice() * cartlistBean.getCount());
                }
            }
        }
        //回调页面
        if(onPriceChangeListener != null){
            onPriceChangeListener.priceChange(sumPrice,isAll);
        }
    }
    //寄存器
    class MyGroupViewHouler{
        CheckBox groupCheck;
    }
    class MyChildViewHouler{
        CheckBox childCheck;
        ImageView childImage;
        TextView childName,childPrice;
        AddReduceView childAddReduce;
    }
    //声明反馈接口
    public interface OnPriceChangeListener{
        void priceChange(double totalMoney,boolean isAll);
    }
}
