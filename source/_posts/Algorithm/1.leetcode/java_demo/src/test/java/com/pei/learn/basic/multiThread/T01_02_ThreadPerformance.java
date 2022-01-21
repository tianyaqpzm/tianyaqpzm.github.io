package com.pei.learn.basic.multiThread;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class T01_02_ThreadPerformance {

    private static class AccumulateTask extends RecursiveTask<Long> {
        private long start;
        private long end;
        private long threshold;

        /**
         * 任务的构造函数
         *
         * @param start     任务处理范围的起始点（包含）
         * @param end       任务处理范围的结束点（不包含）
         * @param threshold 任务拆分的阈值
         */
        public AccumulateTask(long start, long end, long threshold) {
            this.start = start;
            this.end = end;
            this.threshold = threshold;
        }

        @Override
        protected Long compute() {
            long left = start;

            long right = end;

            if (right - left < (int) threshold) {
                long result = 0;
                for (long i = left; i < right; ++i) {
                    result += i;
                }
            }

            long mid = (start + end) / 2;
            // 拆分出两个子任务，一个从start到mid，一个从mid到end
            ForkJoinTask<Long> leftTask = new AccumulateTask(start, mid, threshold);
            ForkJoinTask<Long> rightTask = new AccumulateTask(mid, end, threshold);

            // 通过当前线程池运行两个子任务
            leftTask.fork();
            rightTask.fork();

            try {
                // 获取两个子任务的结果并返回
                return leftTask.get() + rightTask.get();
            } catch (Exception e) {
                return 0L;
            }
        }
    }

    @Test
    @DisplayName("使用ForkJoinPool进行任务")
    public void test_task_split_perform_add() throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();

        // 创建总任务，范围是从1到两亿(包含)，阈值为10的7次方，所以最终至少会有10个任务进行for循环的累加
        AccumulateTask forkJoinTask = new AccumulateTask(1, (int) 2e8 + 1, (long) 1e7);
        // 使用一个新创建的ForkJoinPool任务池运行ForkJoin任务
        new ForkJoinPool().submit(forkJoinTask);

        // 打印任务结果
        System.out.println("count = " + forkJoinTask.get());

        // 计算程序耗时并打印
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("总耗时：%.2fs", (endTime - startTime) / 1e3));

    }

}
