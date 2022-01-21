package com.pei.learn.lock;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * 交叉输出A1B2C3D4
 * 解决方式： 容量为1的队列
 */
public class T01_05_CAS {

    static AtomicInteger threadNo = new AtomicInteger(1);

    public static void main(String[] args) {
        final Object o = new Object();
        char[] a1 = "1234567".toCharArray();
        char[] a2 = "ABCDEFG".toCharArray();


        new Thread(() -> {
            for (char c : a1) {
                while (threadNo.get() != 1) {
                }
                System.out.print(c);
                threadNo.set(2);
            }

        }, "t1").start();
        new Thread(() -> {
            for (char c : a2) {
                while (threadNo.get() != 2) {
                }
                System.out.print(c);
                threadNo.set(1);
            }

        }, "t1").start();
    }
}
