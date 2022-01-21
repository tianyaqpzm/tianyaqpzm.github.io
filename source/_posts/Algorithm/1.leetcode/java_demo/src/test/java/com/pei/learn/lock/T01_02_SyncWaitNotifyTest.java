package com.pei.learn.lock;


/**
 * 交叉输出A1B2C3D4
 */
public class T01_02_SyncWaitNotifyTest {


    public static void main(String[] args) {
        final Object o = new Object();
        char[] a1 = "1234567".toCharArray();
        char[] a2 = "ABCDEFG".toCharArray();
        new Thread(() -> {
            synchronized (o) {
                for (char c : a1) {
                    System.out.print(c);
                    try {
                        o.notify();  // CFS: 完全公平算法， 线程调度，叫醒哪一个线程
                        o.wait(); // 让出锁，必须持有锁能执行
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify(); //
            }

        }, "t1").start();

        new Thread(() -> {
            synchronized (o) {
                for (char c : a2) {
                    System.out.print(c);
                    try {
                        o.notify();  // CFS: 完全公平算法， 线程调度，叫醒哪一个线程
                        o.wait(); // 让出锁，必须持有锁能执行
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify(); //
            }

        }, "t1").start();
    }
}
