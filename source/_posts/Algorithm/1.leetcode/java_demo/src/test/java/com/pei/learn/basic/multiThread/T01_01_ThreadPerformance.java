package com.pei.learn.basic.multiThread;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class T01_01_ThreadPerformance {
    private static long count1 = 0;
    private static long count2 = 0;

    @Test
    @DisplayName("测试单线程累加性能优化版:" +
            "优化点：CAS重试和volatile写回内存两个操作所引起的开销是AtomicLong版本程序性能低下的罪魁祸首。")
    public void test_multi_thread_perform_add() throws Exception {
        long startTime = System.currentTimeMillis();
        Runnable task1 = new Runnable() {
            public void run() {
                for (int i = 0; i < 1e8; ++i) {
                    count1++;
                }
            }
        };
        Runnable task2 = new Runnable() {
            public void run() {
                for (int i = 0; i < 1e8; ++i) {
                    count2++;
                }
            }
        };

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();


        long count = count1 + count2;
        System.out.println("count = " + count);

        long endTime = System.currentTimeMillis();
        System.out.println(String.format("总耗时：%.2fs", (endTime - startTime) / 1e3));
    }

    @Test
    @DisplayName("测试单线程累加性能")
    public void test_single_thread_perform_add() {
        long startTime = System.currentTimeMillis();

        long count = 0;
        for (int i = 0; i < 2e8; ++i) {
            count += 1;
        }
        System.out.println("count = " + count);

        long endTime = System.currentTimeMillis();
        System.out.println(String.format("总耗时：%.2fs", (endTime - startTime) / 1e3));

//        UTHelper.invokeWithException(() -> list.add(4), UnsupportedOperationException.class,
//                "this list should not support and operation");
    }

}
