package com.pei.learn.lock.deadlock;

import com.pei.learn.utils.CommonUtil;

import java.util.concurrent.TimeUnit;

public class T01_01_Philosopher {

    public static void main(String[] args) {
        ChopStick cs0 = new ChopStick();
        ChopStick cs1 = new ChopStick();
        ChopStick cs2 = new ChopStick();
        ChopStick cs3 = new ChopStick();
        ChopStick cs4 = new ChopStick();

        Philosopher p0 = new Philosopher("p0", 0, cs0, cs1);
        Philosopher p1 = new Philosopher("p1", 1, cs1, cs2);
        Philosopher p3 = new Philosopher("p2", 2, cs2, cs3);
        Philosopher p4 = new Philosopher("p3", 3, cs3, cs4);
        Philosopher p2 = new Philosopher("p4", 4, cs4, cs0);

        p0.start();
        p1.start();
        p2.start();
        p3.start();
        p4.start();

    }

    public static class Philosopher extends Thread {
        private ChopStick left, right;

        private int index;

        public Philosopher(String name, int index, ChopStick left, ChopStick right) {
            this.setName(name);
            this.index = index;
            this.left = left;
            this.right = right;
        }

        public void run() {
            if (index % 2 == 0) {
                synchronized (left) {
                    CommonUtil.sleep(index + 1, TimeUnit.SECONDS);
                    synchronized (right) {
                        CommonUtil.sleep(1, TimeUnit.SECONDS);
                        System.out.println(index + " 已经吃到。");
                    }
                }
            } else {
                synchronized (right) {
                    CommonUtil.sleep(index + 1, TimeUnit.SECONDS);
                    synchronized (left) {
                        CommonUtil.sleep(1, TimeUnit.SECONDS);
                        System.out.println(index + " 已经吃到。");
                    }
                }
            }

        }

    }
}
