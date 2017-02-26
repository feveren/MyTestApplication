package com.rent.mytestapplication;

import org.junit.Test;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        new Child<List<Bean>>().printChild();
        new Child<List<Bean>>().printParent();
    }

    class Bean {

    }

    class Child<T> extends Parent<T> {
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