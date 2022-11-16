package com.pei.advance;

import com.pei.learn.utils.CommonUtil;
import org.apache.commons.lang3.ThreadUtils;
import org.assertj.core.util.Lists;

import java.security.SecureRandom;
import java.sql.Time;
import java.util.concurrent.*;

public class T01_TraceIDTest {

    public static final ThreadLocal<Integer> THREAD_LOCAL = new ThreadLocal<>();

    public static final InheritableThreadLocal<Integer> THREAD_INHERI = new InheritableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        THREAD_LOCAL.set(1);
        THREAD_INHERI.set(1);

        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(THREAD_INHERI.get());
                System.out.println(THREAD_LOCAL.get());
            }
        });
        thread.start();
        thread.join();

//        JDK8 并发场景
        Lists.newArrayList(1,2,3,4,5)
                .parallelStream()
                .forEach(i-> System.out.println(Thread.currentThread().getName() + "i" + THREAD_INHERI.get()));

//        普通线程池
        ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>(5));
        for (int i = 0; i < 5; i++) {
            pool.submit(()->{
                System.out.println(Thread.currentThread().getName() + "i" + THREAD_INHERI.get());
                CommonUtil.sleep(100, TimeUnit.MICROSECONDS);
            });
        }
        THREAD_INHERI.set(2);

        for (int i = 0; i < 5; i++) {
            pool.submit(()->{
                System.out.println(Thread.currentThread().getName() + "i" + THREAD_INHERI.get());
                CommonUtil.sleep(100, TimeUnit.MICROSECONDS);
            });
        }
        CommonUtil.sleep(2000, TimeUnit.MICROSECONDS);

        // fork-join
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(new CustomerTask(5));
        CommonUtil.sleep(5,TimeUnit.SECONDS);
    }

    static class CustomerTask extends RecursiveTask<Long> {
        private  int index;
        public CustomerTask(int i) {
            this.index = i;
        }

        @Override
        protected Long compute() {
            if(index<0){
                return 0L;
            }
            CustomerTask customerTask = new CustomerTask(index - 1);
            customerTask.fork();
            System.out.println(Thread.currentThread().getName() + "i" + THREAD_INHERI.get());
            return customerTask.join()+1;
        }
    }

}


