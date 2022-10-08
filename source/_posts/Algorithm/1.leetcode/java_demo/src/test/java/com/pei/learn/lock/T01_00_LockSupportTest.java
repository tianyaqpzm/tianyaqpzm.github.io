package com.pei.learn.lock;


import java.util.concurrent.locks.LockSupport;

/**
 * 交叉输出A1B2C3D4
 * 
 */
public class T01_00_LockSupportTest {

    static Thread t1 = null;
    static Thread t2 = null;

    /**
     * 唤醒指定线程，前提知道对应线程
     * LockSupport  叫醒别人unpark  堵塞自己park
     *
     * @param args
     */
    public static void main(String[] args) {
        char[] a1 = "1234567".toCharArray();
        char[] a2 = "ABCDEFG".toCharArray();
        t1 = new Thread(() -> {
            for (char c : a1) {
                System.out.print(c);
                LockSupport.unpark(t2); // 叫醒第二个线程
                LockSupport.park(); // 让当前线程堵塞
            }
        }, "t1");
        t2 = new Thread(() -> {
            for (char c : a2) {
                LockSupport.park(); // 挂起自己，保证线程1 执行，被叫醒
                System.out.print(c);
                LockSupport.unpark(t1);
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
