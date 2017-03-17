package com.rent.mytestapplication.upload.functions;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rent.mytestapplication.upload.bean.UploadBean;
import com.rent.mytestapplication.retrofit.functions.BaseConverterFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * Json to List<UploadBean>
 * Created by RenTao on 2017/3/14.
 */
public class FileConverterFunction extends BaseConverterFunction<List<UploadBean>> {

    @Override
    protected List<UploadBean> parse(JSONObject json) {
        JSONArray jsonArray = json.getJSONArray("files");
        if (jsonArray == null) {
            return null;
        }
        List<UploadBean> list = new ArrayList<>(jsonArray.size());
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (jsonObject == null) {
                continue;
            }
            UploadBean upload = new UploadBean();
            upload.fileName = jsonObject.getString("name");
            upload.url = jsonObject.getString("url");
            list.add(upload);
        }
        return list;
    }
}
