package com.rent.mytestapplication.homework;

import org.junit.Test;

/**
 * Created by RenTao on 2017/3/30.
 */
public class TestBuilder {

    @Test
    public void test() {
        Homework homework = new Homework.Builder()
                .index(1)
                .name("Day 30")
                .desc("Builder 模式")
                .githubUrl("https://github.com")
                .quote("builder.build")
                .addCompletedName("RenT")
                .build();
        System.out.println(homework);
    }
}
