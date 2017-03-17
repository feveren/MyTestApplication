package com.rent.mytestapplication.upload.provider;

import android.support.annotation.IntRange;

import com.rent.mytestapplication.retrofit.bean.JsonResult;
import com.rent.mytestapplication.retrofit.observable.CallObservable;
import com.rent.mytestapplication.upload.bean.UploadBean;

import java.util.List;

/**
 *
 * Created by RenTao on 2017/3/15.
 */
public interface UploadProvider {

    CallObservable<JsonResult> upload(UploadBean upload);

    CallObservable<JsonResult> upload(List<UploadBean> uploads);

    CallObservable<JsonResult> upload(@IntRange(from = 1, to = 4) int type, UploadBean upload);

    CallObservable<JsonResult> upload(@IntRange(from = 1, to = 4) int type, List<UploadBean> uploads);
}
