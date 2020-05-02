package com.bawei.dianshangjinweek02.util;

import com.bawei.dianshangjinweek02.bean.CartlistBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Converter复杂类转换
 * PropertyConverter<List<CartlistBean>, String>：写转换后的最后一级
 */
public class GoodsConverter implements PropertyConverter<List<CartlistBean>, String> {
    @Override
    public List<CartlistBean> convertToEntityProperty(String databaseValue) {
        if (databaseValue == null) {
            return null;
        }
        Type type = new TypeToken<List<CartlistBean>>() {
        }.getType();
        return new Gson().fromJson(databaseValue, type);
    }
    @Override
    public String convertToDatabaseValue(List<CartlistBean> entityProperty) {
        if (entityProperty == null) {
            return null;
        }
        return new Gson().toJson(entityProperty);
    }
}