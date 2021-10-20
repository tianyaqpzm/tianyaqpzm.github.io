package com.pei.learn.basic.proxy.dynamiccglib;

import net.sf.cglib.proxy.Enhancer;

public class DynamicProxyDemo {
    public static void main(String[] args) {
        cglibProxy();
    }

    private static void cglibProxy() {
        DynamicInterceptor interceptor = new DynamicInterceptor();

        Enhancer enhancer = new Enhancer();
        // 设置超类，cglib是通过继承来实现的
        enhancer.setSuperclass(UserServiceWithoutImpl.class);
        enhancer.setCallback(interceptor);

        UserServiceWithoutImpl userService = (UserServiceWithoutImpl) enhancer.create();
        userService.login("mary", "789");
    }
}
