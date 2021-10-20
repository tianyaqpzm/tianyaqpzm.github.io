package com.pei.learn.basic.proxy.dynamicmode;

import com.pei.learn.basic.proxy.UserService;
import com.pei.learn.basic.proxy.UserServiceImpl;

import java.lang.reflect.Proxy;

public class DynamicProxyDemo {
    public static void main(String[] args) {
        dynamicProxy();
    }

    private static void dynamicProxy() {
        // 设置变量可以保存动态代理类，默认名称以 $Proxy0 格式命名
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        UserServiceImpl userServiceImpl = new UserServiceImpl();

        UserService proxyInstance = (UserService) Proxy.newProxyInstance(UserServiceImpl.class.getClassLoader(),
                userServiceImpl.getClass().getInterfaces(), new DynamicProxyHandler(userServiceImpl));

        proxyInstance.login("jack", "456");
    }
}
