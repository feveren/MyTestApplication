package com.rent.mytestapplication.mvp;

/**
 * Created by RenTao on 16/11/16.
 */

public interface Callback {

    void onResult(int code, String message);

    void onError(int code);

}
