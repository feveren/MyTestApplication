package com.rent.mytestapplication.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.StyleableRes;

/**
 *
 * Created by RenTao on 2016/9/15.
 */
public class TypeArrayUtil {

    public static int getDimension(Context context, TypedArray typedArray, @StyleableRes int resId) {
        return getDimension(context, typedArray, resId, 0);
    }

    public static int getDimension(Context context, TypedArray typedArray, @StyleableRes int resId, int defaultValue) {
        int value = typedArray.getResourceId(resId, 0);
        if (value == 0) {
            return typedArray.getDimensionPixelSize(resId, defaultValue);
        } else {
            return context.getResources().getDimensionPixelOffset(value);
        }
    }

    public static String getString(Context context, TypedArray typedArray, @StyleableRes int resId) {
        int value = typedArray.getResourceId(resId, 0);
        if (value == 0) {
            return typedArray.getString(resId);
        } else {
            return context.getResources().getString(value);
        }
    }

    public static int getColor(Context context, TypedArray typedArray, @StyleableRes int resId) {
        return getColor(context, typedArray, resId, 0);
    }

    public static int getColor(Context context, TypedArray typedArray, @StyleableRes int resId, int defaultValue) {
        int value = typedArray.getResourceId(resId, 0);
        if (value == 0) {
            return typedArray.getColor(resId, defaultValue);
        } else {
            return context.getResources().getColor(value);
        }
    }

}
