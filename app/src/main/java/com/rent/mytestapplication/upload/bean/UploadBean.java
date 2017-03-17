package com.rent.mytestapplication.upload.bean;

/**
 * form-data; name={name}; filename={fileName}
 * Created by aa on 2017/3/13.
 */
public class UploadBean {
    public final static int IMAGE = 1;
    public final static int AUDIO = 2;
    public final static int VIDEO = 3;
    /** 文件 */
    public final static int MISC = 4;

    public final static String UPLOAD_NAME = "FILE";

    public String contentType;
    public String name = UPLOAD_NAME;
    public String fileName;
    /** 本地路径 */
    public String path;
    /** 网络路径 */
    public String url;

    public UploadBean() {}

    public UploadBean(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }
}

