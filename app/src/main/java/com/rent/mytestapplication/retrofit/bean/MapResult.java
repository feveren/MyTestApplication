package com.rent.mytestapplication.retrofit.bean;

import com.rent.mytestapplication.utils.MapUtil;

import java.util.Map;

/**
 * 网络请求返回数据Bean，继承自{@link Result}，data的类型为Map&lt;String, Object&gt;
 * Created by RenTao on 17/1/12.
 */
public class MapResult extends Result<Map<String, Object>> {

    public Object get(String key) {
        return MapUtil.get(data, key);
    }

    public int getInt(String key) {
        return MapUtil.getInt(data, key);
    }

    public int getInt(String key, int defaultValue) {
        return MapUtil.getInt(data, key, defaultValue);
    }

    public double getDouble(String key) {
        return MapUtil.getDouble(data, key);
    }

    public double getDouble(String key, double defaultValue) {
        return MapUtil.getDouble(data, key, defaultValue);
    }

    public String getString(String key) {
        return MapUtil.getString(data, key);
    }

    public String getString(String key, String defaultValue) {
        return MapUtil.getString(data, key, defaultValue);
    }

    public Boolean getBoolean(String key) {
        return MapUtil.getBoolean(data, key);
    }

    public Boolean getBoolean(String key, boolean defaultValue) {
        return MapUtil.getBoolean(data, key, defaultValue);
    }

    @Override
    public boolean hasData() {
        return data != null && !data.isEmpty();
    }
}
