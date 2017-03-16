package com.rent.mytestapplication.reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 给你一个Person类。请用反射的形式:
 * 1.打印出Person类中所有内容（包括：类名、属性、构造方法）
 * 2.打印出Person中所有方法（修饰符+返回值+方法名称+参数类型+抛出的异常）
 * 3.构造出Person对象，调用set方法为name 和 age 赋值
 * 4.调用sayHello() 方法
 *
 * 示例如下：
 * 1. 类名：reflect.Person
 * 属性：private java.lang.String name;
 * private int age;
 * private java.lang.String sex;
 *
 * 构造方法：
 * public reflect.Person(java.lang.String,int)
 * public reflect.Person(int)
 * public reflect.Person()
 *
 * 2.打印所有方法：
 * public java.lang.String toString ( )
 * public java.lang.String getName ( )
 * public void setName (java.lang.String arg0 )
 * public java.lang.String getSex ( )
 * public int getAge ( )
 * public void sayNameAge (java.lang.String arg0 , int arg1 )
 * public void setSex (java.lang.String arg0 )
 * public void setAge (int arg0 )
 * public final void wait ( ) throws java.lang.InterruptedException
 * public final void wait (long arg0 , int arg1 ) throws java.lang.InterruptedException
 * public final native void wait (long arg0 ) throws java.lang.InterruptedException
 * public boolean equals (java.lang.Object arg0 )
 * public native int hashCode ( )
 * public final native java.lang.Class getClass ( )
 * public final native void notify ( )
 * public final native void notifyAll ( )
 */
public class Daily {
    @Test
    public void question1() {
        Class clazz = Person.class;
        System.out.println(clazz.getName());
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            print(field.getModifiers(),
                    field.getType(),
                    field.getName());
        }
        Constructor[] constructors = clazz.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            print(constructor.getModifiers(),
                    constructor.getDeclaringClass().getName(),
                    "(" + classesToString(constructor.getParameterTypes()) + ")");
        }
    }

    @Test
    public void question2() {
        Class clazz = Person.class;
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            String exception = classesToString(method.getExceptionTypes());
            print(method.getModifiers(),
                    method.getReturnType().getName(),
                    method.getName(),
                    "(" + classesToString(method.getParameterTypes()) + ")",
                    exception.isEmpty() ? "" : "throws " + exception);
        }
    }

    @Test
    public void question3() throws Exception {
        Class clazz = Person.class;
        Constructor constructor = clazz.getConstructor();
        Object person = constructor.newInstance();

        Method setNameMethod = clazz.getMethod("setName", String.class);
        setNameMethod.invoke(person, "ren");
        Method setAgeMethod = clazz.getMethod("setAge", int.class);
        setAgeMethod.invoke(person, 18);

        System.out.println(clazz.getMethod("getAge").invoke(person));
        System.out.println(clazz.getMethod("getName").invoke(person));
    }

    @Test
    public void question4() throws Exception {
        Class clazz = Person.class;
        Constructor constructor = clazz.getConstructor();
        Object person = constructor.newInstance();

        Method method = clazz.getDeclaredMethod("sayHello");
        method.setAccessible(true);
        method.invoke(person);
    }

    private void print(int modifier, Object... values) {
        System.out.print(Modifier.toString(modifier));
        for (Object obj : values) {
            System.out.print(" " + obj);
        }
        System.out.println();
    }

    private String classesToString(Class[] classes) {
        if (classes != null && classes.length > 0) {
            StringBuilder result = new StringBuilder();
            for (Class clazz : classes) {
                result.append("," + clazz.getName());
            }
            result.deleteCharAt(0);
            return result.toString();
        }
        return "";
    }
}
