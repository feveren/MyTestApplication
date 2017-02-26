package com.rent.mytestapplication.retrofit.bean;

import java.util.List;

/**
 * Created by RenTao on 17/1/12.
 */
public class ListResult<T> extends Result<List<T>> {
    @Override
    public boolean hasData() {
        return data != null && !data.isEmpty();
    }
}
