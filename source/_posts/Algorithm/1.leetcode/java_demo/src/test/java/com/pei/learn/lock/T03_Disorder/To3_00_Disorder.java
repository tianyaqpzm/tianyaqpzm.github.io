package com.pei.learn.lock.T03_Disorder;

import java.util.concurrent.CountDownLatch;

public class To3_00_Disorder {

    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < Long.MAX_VALUE; i++) {
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            CountDownLatch latch = new CountDownLatch(2);
            Thread one = new Thread(new Runnable() {
                @Override
                public void run() {
                    a = 1;
                    x = b;
                    latch.countDown();
                }
            });


            Thread other = new Thread(new Runnable() {
                @Override
                public void run() {
                    b = 1;
                    y = a;
                    latch.countDown();
                }
            });
            one.start();
            other.start();
            latch.await();
            if (x == 0 && y == 0) {
                System.err.println("第几次x=0 && y=0: " + i);
            }

        }
    }
}
