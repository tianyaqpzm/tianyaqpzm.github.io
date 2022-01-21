package com.pei.learn.lock;


/**
 * 交叉输出A1B2C3D4
 * 解决方式： 容量为1的队列
 */
public class T01_04_Self_Def_CAS {

    enum ReadyToRun {T1, T2}

    /**
     * 必须保证可见
     */
    static volatile ReadyToRun r = ReadyToRun.T1;

    public static void main(String[] args) {
        final Object o = new Object();
        char[] a1 = "1234567".toCharArray();
        char[] a2 = "ABCDEFG".toCharArray();


        new Thread(() -> {
            for (char c : a1) {
                while (r != ReadyToRun.T1) {

                }
                System.out.print(c);
                r = ReadyToRun.T2;
            }

        }, "t1").start();
        new Thread(() -> {
            for (char c : a2) {
                while (r != ReadyToRun.T2) {
                }
                System.out.print(c);
                r = ReadyToRun.T1;
            }

        }, "t1").start();
    }
}
