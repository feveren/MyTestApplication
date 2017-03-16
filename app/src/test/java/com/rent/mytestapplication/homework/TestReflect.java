package com.rent.mytestapplication.homework;

import android.support.annotation.NonNull;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;

/**
 * Created by Administrator on 2017/3/14.
 */

public class TestReflect {

    @Test
    public void test() {
        Class clazz = Child.class;

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(Modifier.toString(field.getModifiers()) + " " + field.getName());
        }

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
            for (Class cls : method.getParameterTypes()) {
                System.out.println(Modifier.toString(cls.getModifiers()) + " " + cls);
            }
        }

        System.out.println(Parent.class.isAssignableFrom(Child.class));
        getComponentType(clazz.getGenericSuperclass());
//        System.out.println(((ParameterizedType) clazz.getGenericSuperclass()).getOwnerType());
//        System.out.println(((ParameterizedType) clazz.getGenericSuperclass()).getRawType());
    }

    private Class getComponentType(Type type) {
        Class componentType = null;
        if (type instanceof ParameterizedType) {
            //getActualTypeArguments()返回表示此类型实际类型参数的 Type 对象的数组。
            Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
            if (actualTypeArguments != null && actualTypeArguments.length > 0) {
                componentType = (Class) actualTypeArguments[0];
            }
            System.out.println("ParameterizedType: " + Arrays.toString(((ParameterizedType) type).getActualTypeArguments()));
        } else if (type instanceof GenericArrayType) {
            // 表示一种元素类型是参数化类型或者类型变量的数组类型
            componentType = (Class) ((GenericArrayType) type).getGenericComponentType();
            System.out.println("GenericArrayType: " + ((GenericArrayType) type).getGenericComponentType());
        } else if (type instanceof TypeVariable) {
            System.out.println("TypeVariable: " + ((TypeVariable) type).getBounds());
        } else if (type instanceof WildcardType) {
            System.out.println("TypeVariable: " + ((WildcardType) type).getUpperBounds() + " " + ((WildcardType) type).getUpperBounds());
        }else {
            componentType = (Class) type;
        }
        return componentType;
    }
}

class Child extends Parent<String> {
    public int age;

    @Override
    protected void override() {
        System.out.println("child override");
    }
}

class Parent<T> {
    public final static float PI = 3.14f;

    private int id;
    public String name;

    public static void print(String message) {
        System.out.println("print: " + message);
    }

    private void count(int count) {
        System.out.println("count: " + count);
    }

    protected void override() {
        System.out.println("parent override");
    }
}

class C<T> implements Comparable {

    @Override
    public int compareTo(@NonNull Object another) {
        return 0;
    }
}
