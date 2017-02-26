package com.rent.mytestapplication.retrofit.converter;

import com.rent.mytestapplication.retrofit.bean.JsonResult;
import com.rent.mytestapplication.retrofit.bean.ListResult;
import com.rent.mytestapplication.retrofit.bean.MapResult;
import com.rent.mytestapplication.utils.ClassUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 *
 * Created by RenTao on 17/1/9.
 */
public class JsonConverterFactory extends Converter.Factory {

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        // 获取最外层的类型，eg. Result<T> -> Result.class
        Class<?> rawType = ClassUtil.getRawType(type);
        if (rawType == MapResult.class) {
            return new MapResponseBodyConverter();
        }
        else if (rawType == JsonResult.class) {
            return new JsonResponseBodyConverter();
        }
        else {
            // 获取真实的类型，eg. Result<T> -> actualType就是T
            Type actualType = ClassUtil.getParameterUpperBound(0, (ParameterizedType) type);
            boolean isList = rawType == ListResult.class;
            return new BeanResponseBodyConverter<>(actualType, isList);
        }
    }
}