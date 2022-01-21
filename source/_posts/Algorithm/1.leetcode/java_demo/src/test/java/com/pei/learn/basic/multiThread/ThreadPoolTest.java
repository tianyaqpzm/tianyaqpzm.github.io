package com.pei.learn.basic.multiThread;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * https://zhuanlan.zhihu.com/p/60212693
 * 从0到1玩转线程池
 * <p>
 * https://www.jianguoyun.com/p/DVs_kkgQjraLChiumKEE
 */
public class ThreadPoolTest {

    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Runnable task = new Runnable() {


            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    synchronized (ThreadPoolTest.class) {
                        count += 1;
                    }
                }
            }
        };
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 1, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue());

        Executors.newCachedThreadPool();

        // 重要：向线程池提交两个任务
        threadPool.execute(task);
        threadPool.execute(task);

        // 等待线程池中的所有任务完成
        threadPool.shutdown();
        while (!threadPool.awaitTermination(1L, TimeUnit.MINUTES)) {
            System.out.println("Not yet. Still waiting for termination");
        }

        System.out.println("count = " + count);

    }


}
