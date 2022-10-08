package com.pei.learn.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class T01_01_Lock_ReentrantLock {

    public static void main(String[] args) {
        char[] a1 = "1234567".toCharArray();
        char[] a2 = "ABCDEFG".toCharArray();

        ReentrantLock lock = new ReentrantLock();
        Condition conditionT1 = lock.newCondition();
        Condition conditionT2 = lock.newCondition();

        CountDownLatch latch = new CountDownLatch(1);

        new Thread(() -> {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            try {
                for (char c : a2) {
                    System.out.print(c);
                    conditionT2.signal();
                    conditionT1.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {

            lock.lock();
            try {
                for (char c : a1) {
                    System.out.print(c);
                    latch.countDown();
                    conditionT1.signal(); // 叫醒另一个线程
                    conditionT2.await();
                }
            } catch (InterruptedException   e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }
}
