package com.rent.mytestapplication.utils;

import java.util.Map;

/**
 *
 * Created by RenTao on 17/1/12.
 */
public class MapUtil {

    public static <K, V> V get(Map<K, V> map, K key) {
        return map == null ? null : map.get(key);
    }

    public static <K, V> int getInt(Map<K, V> map, K key) {
        return getInt(map, key, 0);
    }

    public static <K, V> int getInt(Map<K, V> map, K key, int defaultValue) {
        return ObjectUtil.toInt(get(map, key), defaultValue);
    }

    public static <K, V> double getDouble(Map<K, V> map, K key) {
        return getDouble(map, key, 0);
    }

    public static <K, V> double getDouble(Map<K, V> map, K key, double defaultValue) {
        return ObjectUtil.toDouble(get(map, key), defaultValue);
    }

    public static <K, V> String getString(Map<K, V> map, K key) {
        return getString(map, key, null);
    }

    public static <K, V> String getString(Map<K, V> map, K key, String defaultValue) {
        return ObjectUtil.toString(get(map, key), defaultValue);
    }

    public static <K, V> boolean getBoolean(Map<K, V> map, K key) {
        return getBoolean(map, key, false);
    }

    public static <K, V> boolean getBoolean(Map<K, V> map, K key, boolean defaultValue) {
        return ObjectUtil.toBoolean(get(map, key), defaultValue);
    }

}
