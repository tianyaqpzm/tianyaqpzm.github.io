package com.pei.learn.lock;


import java.util.concurrent.LinkedTransferQueue;

/**
 * 交叉输出A1B2C3D4
 * 解决方式： 容量为1的队列
 */
public class T01_03_TransferQueue {


    public static void main(String[] args) {
        final Object o = new Object();
        char[] a1 = "1234567".toCharArray();
        char[] a2 = "ABCDEFG".toCharArray();

        LinkedTransferQueue<Character> queue = new LinkedTransferQueue<>();

        new Thread(() -> {
            try {
                for (char c : a1) {
                    System.out.print(queue.take()); // 从 单队列取值
                    queue.transfer(c);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();
        new Thread(() -> {
            try {
                for (char c : a2) {
                    queue.transfer(c); // 放入值
                    System.out.print(queue.take());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "t1").start();
    }
}
