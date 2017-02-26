package com.rent.mytestapplication.retrofit.functions;

import com.alibaba.fastjson.JSONObject;
import com.rent.mytestapplication.retrofit.bean.JsonResult;
import com.rent.mytestapplication.retrofit.bean.Result;

import io.reactivex.functions.Function;

/**
 *
 * Created by RenTao on 17/1/24.
 */
public abstract class Json2ResultFunciton<T> implements Function<JsonResult, Result<T>> {

    @Override
    public Result<T> apply(JsonResult jsonResult) throws Exception {
        Result<T> result = new Result<>();
        jsonResult.copy(result);
        result.data = parse(jsonResult.data);
        return result;
    }

    protected abstract T parse(JSONObject json);
}
