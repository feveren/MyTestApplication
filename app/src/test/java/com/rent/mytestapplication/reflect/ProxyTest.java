package com.rent.mytestapplication.reflect;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p> Created by RenTao on 2017/4/13.
 */
public class ProxyTest {

    @Test
    public void test() {
        final Service impl = new ServiceImpl();
        TestInvocationHandler invocationHandler = new TestInvocationHandler(impl);
        Service service = (Service) Proxy.newProxyInstance(Service.class.getClassLoader(), new Class[]{ Service.class },
                invocationHandler);
        service.print();

        service = (Service) Proxy.newProxyInstance(Service.class.getClassLoader(), new Class[]{Service.class},
                new ReplaceInvocationHandler(service));
        service.print();
    }

    private class TestInvocationHandler implements InvocationHandler {
        Service service;

        TestInvocationHandler(Service service) {
            this.service = service;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("before");
            method.invoke(service, args);
            System.out.println("after");
            return "aaaa";
        }
    }

    private class ReplaceInvocationHandler implements InvocationHandler {
        Service service;

        ReplaceInvocationHandler(Service service) {
            this.service = service;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("replaced");
            return Proxy.getInvocationHandler(service).invoke(proxy, method, args);
        }
    }

    interface Service {

        String print();
    }

    private static class ServiceImpl implements Service {

        @Override
        public String print() {
            System.out.println("ServiceImpl");
            return "bbb";
        }
    }
}
