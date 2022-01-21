package com.pei.learn.basic.multiThread;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.concurrent.atomic.AtomicLong;

public class T01_00_ThreadPerformance {
    private static AtomicLong count = new AtomicLong(0);

    @Test
    @DisplayName("测试单线程累加性能")
    public void test_multi_thread_perform_add() throws Exception {
        long startTime = System.currentTimeMillis();
        Runnable task = new Runnable() {
            public void run() {
                for (int i = 0; i < 1e8; ++i) {
                    count.incrementAndGet();
                }
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

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
