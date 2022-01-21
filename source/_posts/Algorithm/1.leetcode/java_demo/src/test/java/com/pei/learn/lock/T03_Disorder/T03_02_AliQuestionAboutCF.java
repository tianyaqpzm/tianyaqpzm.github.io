package com.pei.learn.lock.T03_Disorder;

import com.pei.learn.utils.CommonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class T03_02_AliQuestionAboutCF {

    private static enum Result {
        NOTEND, SUCCESSED, FAILED, CANCELLED
    }

    static ArrayList<MyTask> tasks = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        MyTask task1 = new MyTask("task1", 3, Result.SUCCESSED);
        MyTask task2 = new MyTask("task2", 4, Result.SUCCESSED);
        MyTask task3 = new MyTask("task3", 1, Result.FAILED);

        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        for (MyTask task : tasks) {
            CompletableFuture.supplyAsync((() -> task.runTask())).thenAccept((result) -> callback(result, task));
        }
        CommonUtil.sleep(5, TimeUnit.SECONDS);
    }

    private static void callback(Result result, MyTask task) {
        if (Result.FAILED == result) {
            // 处理结束流程
//            通知其他线程结束
//            超时处理
            for (MyTask _task : tasks) {
                if (_task != task) {
                    _task.cancel();
                }

            }
        }

    }

    private static class MyTask {
        private Result result = Result.NOTEND;

        private boolean cancelling = false;
        volatile private boolean cancelled = false;

        private String name;
        private int timeInseconds;

        public MyTask(String name, int timeInseconds, Result ret) {
            this.name = name;
            this.result = ret;
            this.timeInseconds = timeInseconds * 1000;
        }

        public Result runTask() {
            int interval = 100;
            int total = 0;
            for (; ; ) {
                CommonUtil.sleep(interval, TimeUnit.MILLISECONDS);
                if (cancelling) {
                    continue;
                }
                total += interval;
                if (total >= timeInseconds) {
                    break;
                }
                if (cancelled) {
                    return Result.CANCELLED;
                }
            }
            System.out.println(name + " end!");
            return result;

        }

        public void cancel() {
            cancelling = true;
            synchronized (this) {
                System.out.println(name + " cancelling");
                CommonUtil.sleep(50, TimeUnit.MILLISECONDS);
                System.out.println(name + " cancelled");
            }
            cancelled = true;
        }


    }
}
