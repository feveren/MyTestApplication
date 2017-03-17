package com.rent.mytestapplication.upload.utils;

import com.rent.mytestapplication.upload.bean.ImageBean;
import com.rent.mytestapplication.upload.bean.UploadBean;

import java.io.File;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * <p> Created by RenTao on 2017/3/17.
 */
public class RequestBodyUtil {

    public static void setUploadName(int extType, int bizType, ImageBean image) {
        image.name = ImageBean.UPLOAD_NAME + ImageExt.get(extType, bizType, image.name);
    }

    public static void setUploadNames(int extType, int bizType, List<ImageBean> images) {
        if (images == null || images.isEmpty()) {
            return;
        }
        for (ImageBean image : images) {
            setUploadName(extType, bizType, image);
        }
    }

    public static RequestBody parseBody(List<UploadBean> uploads) {
        return parseBody(null, uploads);
    }

    public static RequestBody parseBody(Map<String, String> params, List<UploadBean> uploads) {
        String clientId = "ae32c42e64434648af5cccb25ee1e906";
        String domain = "t.zm.gaiay.cn";

        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);

        // 增加公用参数
        builder.addFormDataPart("clientId", clientId);
        builder.addFormDataPart("domain", domain);

        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.addFormDataPart(entry.getKey(), entry.getValue());
            }
        }

        for (UploadBean upload : uploads) {
            if (upload.path == null || upload.path.trim().isEmpty()) {
                continue;
            }
            File file = new File(upload.path);
            if (!file.exists()) {
                continue;
            }
            builder.addFormDataPart(upload.getName(), file.getName(), RequestBody.create(null, file));
        }
        return builder.build();
    }

}
