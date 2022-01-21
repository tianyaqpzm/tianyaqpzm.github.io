package com.pei.learn.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class T02_00_ABCABC {

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition cA = lock.newCondition();
    private static Condition cB = lock.newCondition();
    private static Condition cC = lock.newCondition();

    private static CountDownLatch latchB = new CountDownLatch(1);
    private static CountDownLatch latchC = new CountDownLatch(1);


    /*
    三个线程，三个不同的队列，A唤醒B  B唤醒C
     */
    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.print("A");
                    cB.signal();
                    if (i == 0) {
                        latchB.countDown();
                    }
                    cA.await();
                }
                cB.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "Thread A");


        Thread threadB = new Thread(() -> {
            // 等待唤醒
            try {
                latchB.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.print("B");
                    cC.signal();
                    if (i == 0) {
                        latchC.countDown();
                    }
                    cB.await();
                }
                cC.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "Thread B");


        Thread threadC = new Thread(() -> {
            // 等待唤醒
            try {
                latchC.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.print("C");
                    cA.signal();
                    cC.await();
                }
                cA.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "Thread C");

        threadA.start();
        threadB.start();
        threadC.start();

    }
}
