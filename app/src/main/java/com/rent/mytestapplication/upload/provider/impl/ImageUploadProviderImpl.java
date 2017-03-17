package com.rent.mytestapplication.upload.provider.impl;

import com.rent.mytestapplication.upload.bean.UploadBean;
import com.rent.mytestapplication.upload.provider.ImageUploadProvider;

/**
 *
 * Created by RenTao on 2017/3/16.
 */
public class ImageUploadProviderImpl extends UploadProviderImpl implements ImageUploadProvider {

    @Override
    protected int getUploadType() {
        return UploadBean.IMAGE;
    }
}
