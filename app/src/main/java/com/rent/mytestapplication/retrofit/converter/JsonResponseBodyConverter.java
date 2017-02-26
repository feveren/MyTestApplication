package com.rent.mytestapplication.retrofit.converter;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSONObject;
import com.rent.mytestapplication.retrofit.bean.JsonResult;
import com.rent.mytestapplication.retrofit.bean.Result;

/**
 *
 * Created by RenTao on 17/1/12.
 */
final class JsonResponseBodyConverter extends BeanResponseBodyConverter<JSONObject> {

    @Override
    protected Result<JSONObject> newInstance() {
        return new JsonResult();
    }

    @Override
    protected JSONObject parse(@NonNull JSONObject jsonObject) {
        return jsonObject;
    }
}
