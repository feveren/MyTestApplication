package com.rent.mytestapplication;

import org.junit.Test;

/**
 * Created by Administrator on 2017/3/16.
 */

public class TestStatic {

    @Test
    public void test() {
        SingleTon singleTon = SingleTon.getInstance();
        System.out.println("test count1: " + singleTon.count1);
        System.out.println("test count2: " + singleTon.count2);
        System.out.println(new SingleTon.Inner());
    }
}

class SingleTon {
    public static SingleTon singleTon = new SingleTon();
    public static int count1;
    public static int count2 = getCount2();

    static {
        System.out.println("static");
    }

    public SingleTon() {
        count1++;
        count2++;

        System.out.println("Constructor count1: " + count1);
        System.out.println("Constructor count2: " + count2);
    }

    private static int getCount2() {
        System.out.println("getCount2");
        return 0;
    }

    public static SingleTon getInstance() {
        System.out.println("getInstance");
        return singleTon;
    }

    static class Inner {
        static {
            System.out.println("Inner");
        }
    }
}
