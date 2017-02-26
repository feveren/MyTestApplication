package com.rent.mytestapplication.utils;

/**
 *
 * Created by RenTao on 17/1/13.
 */
public class ObjectUtil {
    public static int toInt(Object obj) {
        return toInt(obj, 0);
    }

    public static int toInt(Object obj, int valueIfNull) {
        return obj == null ? valueIfNull : Integer.parseInt(String.valueOf(obj));
    }
    public static long toLong(Object obj) {
        return toLong(obj, 0);
    }

    public static long toLong(Object obj, long valueIfNull) {
        return obj == null ? valueIfNull : Long.parseLong(String.valueOf(obj));
    }

    public static double toDouble(Object obj) {
        return toDouble(obj, 0);
    }

    public static double toDouble(Object obj, double valueIfNull) {
        return obj == null ? valueIfNull : Double.parseDouble(String.valueOf(obj));
    }

    public static String toString(Object obj) {
        return toString(obj, null);
    }

    public static String toString(Object obj, String valueIfNull) {
        return obj == null ? valueIfNull : String.valueOf(obj);
    }

    public static boolean tooBoolean(Object obj) {
        return toBoolean(obj, false);
    }

    public static boolean toBoolean(Object obj, boolean valueIfNull) {
        return obj == null ? valueIfNull : Boolean.valueOf(String.valueOf(obj));
    }
}
