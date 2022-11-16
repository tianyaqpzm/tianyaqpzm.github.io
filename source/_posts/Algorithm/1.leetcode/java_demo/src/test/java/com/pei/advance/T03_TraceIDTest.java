package com.pei.advance;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import com.alibaba.ttl.threadpool.TtlExecutors;
import com.pei.learn.utils.CommonUtil;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class T03_TraceIDTest {
    public static final TransmittableThreadLocal<Integer> THREAD_INHERI = new TransmittableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor pool =
                new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        THREAD_INHERI.set(1);

        for (int i = 0; i < 5; i++) {
            pool.submit(TtlRunnable.get(()->{
                System.out.println(Thread.currentThread().getName() + "i" + THREAD_INHERI.get());
                CommonUtil.sleep(100, TimeUnit.MICROSECONDS);
            }));
        }
        THREAD_INHERI.set(2);

        for (int i = 0; i < 5; i++) {
            pool.submit(TtlRunnable.get(()->{
                System.out.println(Thread.currentThread().getName() + "i" + THREAD_INHERI.get());
                CommonUtil.sleep(100, TimeUnit.MICROSECONDS);
            }));
        }
    }
}
