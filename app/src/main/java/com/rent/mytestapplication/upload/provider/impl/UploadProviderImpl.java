package com.rent.mytestapplication.upload.provider.impl;

import android.support.annotation.IntRange;

import com.rent.mytestapplication.retrofit.Requester;
import com.rent.mytestapplication.retrofit.bean.JsonResult;
import com.rent.mytestapplication.retrofit.observable.CallObservable;
import com.rent.mytestapplication.upload.bean.UploadBean;
import com.rent.mytestapplication.upload.provider.UploadProvider;
import com.rent.mytestapplication.upload.provider.service.UploadService;
import com.rent.mytestapplication.upload.utils.RequestBodyUtil;
import com.rent.mytestapplication.utils.ListUtil;

import java.util.List;

import okhttp3.RequestBody;

/**
 *
 * Created by RenTao on 2017/3/15.
 */
public class UploadProviderImpl implements UploadProvider {
    private UploadService uploadService;

    public UploadProviderImpl() {}

    public UploadProviderImpl(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @Override
    public CallObservable<JsonResult> upload(UploadBean upload) {
        return upload(ListUtil.valueOf(upload));
    }

    @Override
    public CallObservable<JsonResult> upload(List<UploadBean> uploads) {
        int type = getUploadType();
        if (type > 0) {
            return upload(type, uploads);
        }
        return null;
    }

    @Override
    public CallObservable<JsonResult> upload(@IntRange(from = 1, to = 4) int type, UploadBean upload) {
        return upload(type, ListUtil.valueOf(upload));
    }

    @Override
    public CallObservable<JsonResult> upload(@IntRange(from = 1, to = 4) int type, List<UploadBean> uploads) {
        CallObservable<JsonResult> obz = null;
        RequestBody body = RequestBodyUtil.parseBody(uploads);
        if (type == UploadBean.IMAGE) {
            obz = getUploadService().uploadImage(body);
        }
        else if (type == UploadBean.AUDIO) {
            obz = getUploadService().uploadAudio(body);
        }
        else if (type == UploadBean.VIDEO) {
            obz = getUploadService().uploadVideo(body);
        }
        else if (type == UploadBean.MISC) {
            obz = getUploadService().uploadMisc(body);
        }
        return obz;
    }

    protected UploadService getUploadService() {
        if (uploadService == null) {
            uploadService = Requester.get().create(UploadService.class);
        }
        return uploadService;
    }

    protected int getUploadType() {
        return 0;
    }
}
