package com.rent.mytestapplication.upload.utils;

import java.util.Locale;

/**
 * <p> Created by RenTao on 2017/3/16.
 */
public class ImageExt {
    /**
     * 原图优化图
     */
    public final static int COMPRESS = 1;
    /**
     * 原比例缩放图 r{minWidth}x{minHeight}z{maxWidth}x{maxHeight}
     */
    public final static int SCALED = 2;
    /**
     * 指定尺寸图片 r{width}x{height}
     */
    public final static int SIZE = 3;
    /**
     * 原图
     */
    public final static int ORIGIN = 4;

    public static String add(String source, int bizType) {
        return add(source, SIZE, bizType, null);
    }

    public static String addScaled(String source, int bizType) {
        return add(source, SCALED, bizType, null);
    }

    public static String addCompress(String source, int bizType) {
        return add(source, COMPRESS, bizType, null);
    }

    public static String addOrigin(String source, String url) {
        return add(source, ORIGIN, 0, url);
    }

    private static String add(String source, int extType, int bizType, String url) {
        if (source == null || source.trim().isEmpty()) {
            return null;
        }
        return source + get(extType, bizType, url);
    }

    public static String get(int extType, int bizType) {
        return get(extType, bizType, null);
    }

    public static String get(int extType, int bizType, String url) {
        if (extType == SIZE) {
//            return String.format(Locale.getDefault(), "_r%dx%d", width, height);
        } else if (extType == COMPRESS) {
            return "_o";
        } else if (extType == SCALED) {
//            return String.format(Locale.getDefault(), "_r%dx%dz%dx%d", minWidth, minHeight, maxWidth, maxHeight);
        } else if (extType == ORIGIN) {
            return "_" + (url == null ? "" : url);
        }
        throw new IllegalStateException("extType must be ImageExt.COMPRESS, ImageExt.SCALE, ImageExt.SIZE or ImageExt.ORIGIN");
    }
}
