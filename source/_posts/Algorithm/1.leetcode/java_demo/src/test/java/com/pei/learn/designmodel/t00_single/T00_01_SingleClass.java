package com.pei.learn.designmodel.t00_single;

/**
 * DCL double check lock 双重检查： 要不要volatile？？？需要
 * 发生指令重排， 实例化和赋值，顺序改变类， 第二个线程看你实用半初始化状态的对象，导致失败
 * 懒汉式：
 * 类加载到内存后，就实例化一个单例，JVM保证线程安全
 * 简单实用
 * 不管用到与否，类加载时就完成实例化
 */
public class T00_01_SingleClass {
    private static T00_01_SingleClass INSTANCE;


    public static T00_01_SingleClass getInstance() {
        // 外层检查为空，提高效率，不用每一个判断是否获取锁
        if (INSTANCE == null) {
            synchronized (T00_01_SingleClass.class) {
                if (INSTANCE == null) {
                    INSTANCE = new T00_01_SingleClass();
                }
            }
        }
        return INSTANCE;
    }

    private T00_01_SingleClass() {

    }

    public static void main(String[] args) {
        T00_01_SingleClass instance1 = T00_01_SingleClass.getInstance();
        T00_01_SingleClass instance2 = T00_01_SingleClass.getInstance();
        System.out.println(instance1 == instance2);

    }

}




