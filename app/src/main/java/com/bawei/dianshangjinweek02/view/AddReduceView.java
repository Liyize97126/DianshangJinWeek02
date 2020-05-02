package com.bawei.dianshangjinweek02.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bawei.dianshangjinweek02.R;

public class AddReduceView extends FrameLayout {
    private TextView countText;
    private ChangeCount changeCount;
    public void setChangeCount(ChangeCount changeCount) {
        this.changeCount = changeCount;
    }
    private int count;
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
        countText.setText(String.valueOf(count));
    }
    public AddReduceView(@NonNull Context context) {
        super(context);
        initView();
    }
    public AddReduceView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }
    public AddReduceView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    private void initView(){
        View view = View.inflate(getContext(), R.layout.view_reduce_item, this);
        countText = view.findViewById(R.id.count_text);
        view.findViewById(R.id.add_btn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                countText.setText(String.valueOf(count));
                if(changeCount != null){
                    changeCount.changeCount(AddReduceView.this);
                }
            }
        });
        view.findViewById(R.id.reduce_btn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count > 1){
                    count--;
                    countText.setText(String.valueOf(count));
                } else {
                    Toast.makeText(getContext(),"数量不能少于1个！",Toast.LENGTH_LONG).show();
                }
                if(changeCount != null){
                    changeCount.changeCount(AddReduceView.this);
                }
            }
        });
    }
    public interface ChangeCount{
        void changeCount(AddReduceView view);
    }
}
