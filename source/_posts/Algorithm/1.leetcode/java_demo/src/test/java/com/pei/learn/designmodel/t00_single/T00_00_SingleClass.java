package com.pei.learn.designmodel.t00_single;

/**
 * 静态初始化, 没有延迟初始化
 * 饿汉式：
 * 类加载到内存后，就实例化一个单例，JVM保证线程安全
 * 简单实用
 * 不管用到与否，类加载时就完成实例化
 */
public class T00_00_SingleClass {
    private static T00_00_SingleClass INSTANCE = new T00_00_SingleClass();


    public static T00_00_SingleClass getInstance() {
        return INSTANCE;
    }

    private T00_00_SingleClass() {

    }

    public static void main(String[] args) {
        T00_00_SingleClass instance1 = T00_00_SingleClass.getInstance();
        T00_00_SingleClass instance2 = T00_00_SingleClass.getInstance();
        System.out.println(instance1 == instance2);

    }

}




