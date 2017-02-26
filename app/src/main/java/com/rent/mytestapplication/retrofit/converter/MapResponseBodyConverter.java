package com.rent.mytestapplication.retrofit.converter;

import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;

import com.alibaba.fastjson.JSONObject;
import com.rent.mytestapplication.retrofit.bean.MapResult;
import com.rent.mytestapplication.retrofit.bean.Result;

import java.util.Map;

/**
 *
 * Created by RenTao on 17/1/12.
 */
final class MapResponseBodyConverter extends BeanResponseBodyConverter<Map<String, Object>> {

    @Override
    protected Result newInstance() {
        return new MapResult();
    }

    @Override
    protected Map<String, Object> parse(@NonNull JSONObject jsonObject) {
        JSONObject json = jsonObject.getJSONObject(Result.KEY_RESULT);
        if (json == null || json.isEmpty()) {
            return null;
        }
        Map<String, Object> map = new ArrayMap<>(json.size());
        for (Map.Entry<String, Object> entry : json.entrySet()) {
            if (entry.getValue() != null && !String.valueOf(entry.getValue()).trim().equals("")) {
                map.put(entry.getKey(), entry.getValue());
            }
        }
        return map;
    }
}
