package com.pei.learn.lock.T03_Disorder;

import com.pei.learn.utils.CommonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class T03_00_AliQuestion {

    private static class Boss extends Thread {

        private List<Worker> task = new ArrayList<>();

        public void run() {
            task.stream().forEach(t -> t.start());

        }

        public void end(Worker worker) {
            if (worker.getResult() == Result.FAILED) {
                cancel(worker);
            }
        }

        private void cancel(Worker worker) {
            for (Worker w : task) {
                if (w != worker) {
                    w.cancel();
                }
            }
        }

        public boolean addTask(Worker worker) {
            return task.add(worker);
        }
    }

    private static enum Result {
        NOTEND, SUCCESSED, FAILED
    }

    public static void main(String[] args) throws IOException {
        Boss boss = new Boss();
        Worker t1 = new Worker(boss, "t1", 3, true);
        Worker t2 = new Worker(boss, "t2", 3, true);
        Worker t3 = new Worker(boss, "t3", 3, true);

        boss.addTask(t1);
        boss.addTask(t2);
        boss.addTask(t3);

        boss.start();
        System.in.read();
    }


    private static class Worker extends Thread {

        private Result result = Result.NOTEND;
        private boolean cancelling = false;

        private Boss boss;
        private String name;
        private int timeInseconds;

        private boolean success;

        public Worker(Boss boss, String name, int timeInseconds, boolean success) {
            this.result = result;
            this.boss = boss;
            this.name = name;
            this.success = success;
            this.timeInseconds = timeInseconds;
        }


        public void run() {
            CommonUtil.sleep(this.timeInseconds, TimeUnit.SECONDS);
            System.out.println(name + " 任务结束！");
            result = success ? Result.SUCCESSED : Result.FAILED;
            boss.end(this);


        }

        public void cancel() {
//            线程如何优雅结束？  interrupt 、stop都不行
            cancelling = true;

        }

        public Result getResult() {
            return result;
        }
    }
}
