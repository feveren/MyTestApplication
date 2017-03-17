package com.rent.mytestapplication.utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by RenTao on 2017/3/15.
 */
public class ListUtil {
    public static <T> List<T> valueOf(T model) {
        List<T> list = new ArrayList<>(1);
        list.add(model);
        return list;
    }
}
