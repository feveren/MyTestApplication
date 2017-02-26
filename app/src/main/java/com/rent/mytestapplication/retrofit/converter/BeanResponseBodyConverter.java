package com.rent.mytestapplication.retrofit.converter;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rent.mytestapplication.retrofit.bean.ListResult;
import com.rent.mytestapplication.retrofit.bean.Result;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 *
 * Created by RenTao on 17/1/12.
 */
class BeanResponseBodyConverter<T> implements Converter<ResponseBody, Result<T>> {
    private Type mType;
    private boolean mIsList;

    BeanResponseBodyConverter() {}

    BeanResponseBodyConverter(Type type, boolean isList) {
        mType = type;
        mIsList = isList;
    }

    @Override
    public Result<T> convert(ResponseBody value) throws IOException {
        try {
            Result response = newInstance();
            String json = value.string();
            JSONObject jsonObject = JSONObject.parseObject(json);
            if (jsonObject == null) {
                return null;
            }
            response.code = jsonObject.getIntValue(Result.KEY_CODE);
            response.message = jsonObject.getString(Result.KEY_MESSAGE);
            response.lastQueryTime = jsonObject.getLongValue(Result.KEY_LAST_QUERY_TIME);
            response.data = parse(jsonObject);
            return response;
        } finally {
            value.close();
        }
    }

    protected Result newInstance() {
        return mIsList ? new ListResult<>() : new Result<>();
    }

    protected T parse(@NonNull JSONObject jsonObject) {
        if (mIsList) {
            return (T) JSON.parseArray(jsonObject.getString(Result.KEY_ARRAY_RESULT), (Class) mType);
        } else {
            return JSON.parseObject(jsonObject.getString(Result.KEY_RESULT), mType, JSON.DEFAULT_PARSER_FEATURE);
        }
    }
}
