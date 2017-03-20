package com.rent.mytestapplication;

import org.junit.Test;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
//        new Child<List<Bean>>().printChild();
//        new Child<List<Bean>>().printParent();

        System.out.println(checkTargetExisted("50|70", "10|80"));
        System.out.println(checkTargetExisted("10|70", "10|80"));
        System.out.println(checkTargetExisted("20->50|70", "10|80"));
        System.out.println(checkTargetExisted("20->50|70", "20|80"));
        System.out.println(checkTargetExisted("20->50|70", "10->30|80"));
        System.out.println(checkTargetExisted("20->50|70", "60->80|90"));

        new Child<>();
    }

    private boolean checkTargetExisted(String str1, String str2) {
        String[] arr1 = str1.split("\\|");
        String[] arr2 = str2.split("\\|");
        for (String outer : arr1) {
            for (String inner : arr2) {
                if (outer.equals(inner)) {
                    return true;
                } else if (outer.contains("->") && inner.contains("->")) {
                    return checkRange(outer, inner);
                } else if (outer.contains("->")) {
                    return checkTargetInRange(outer, inner);
                } else if (inner.contains("->")) {
                    return checkTargetInRange(inner, outer);
                }
            }
        }
        return false;
    }

    private boolean checkTargetInRange(String range, String target) {
        int[] arr = parseRange(range);
        int num = Integer.parseInt(target);
        return num >= arr[0] && num <= arr[1];
    }

    private boolean checkRange(String range1, String range2) {
        int[] arr1 = parseRange(range1);
        int[] arr2 = parseRange(range2);
        return !(arr1[0] > arr2[1] || arr2[0] > arr1[1]);
    }

    private int[] parseRange(String range) {
        String[] arr = range.split("->");
        int min = Integer.parseInt(arr[0]);
        int max = Integer.parseInt(arr[1]);
        return new int[] { min, max };
    }

    class Bean {

    }

    class Child<T> extends Parent<T> {
        public Child() {
            System.out.println("child");
        }

        void printChild() {
            Type type = this.getClass().getGenericSuperclass();
            System.out.println(type);
            ParameterizedType pt = (ParameterizedType) type;
            System.out.println(pt.getRawType());
            Type actualType = pt.getActualTypeArguments()[0];
            System.out.println(actualType);
            System.out.println(actualType instanceof ParameterizedType);
            System.out.println(actualType instanceof WildcardType);
            System.out.println(actualType instanceof GenericArrayType);
            System.out.println(actualType instanceof TypeVariable);
        }
    }

    class Parent<T> {
        T data;

        public Parent() {
            System.out.println("parent");
        }

        void printParent() {
            Type type = this.getClass().getComponentType();
            System.out.println(type);
            ParameterizedType pt = (ParameterizedType) type;
            System.out.println(pt.getRawType());
            Class clazz = (Class<?>)pt.getActualTypeArguments()[0];
            System.out.println(clazz);
        }
    }
}