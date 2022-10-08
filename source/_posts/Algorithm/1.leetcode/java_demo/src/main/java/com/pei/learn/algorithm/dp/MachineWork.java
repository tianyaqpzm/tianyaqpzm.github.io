package com.pei.learn.algorithm.dp;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MachineWork {

    static class Machine {
        public int timepoint = 0;
        public  int  workTime = 0;

        public Machine(int timepoint, int workTime) {
            this.timepoint = timepoint;
            this.workTime = workTime;
        }
    }
    static class MachineComparator implements Comparator<Machine> {

        @Override
        public int compare(Machine o1, Machine o2) {
            return (o1.timepoint + o1.workTime) - (o2.timepoint + o2.workTime);
        }
    }

    public static int minTime2(int[] arr, int n, int a, int b){
        PriorityQueue<Machine> heap = new PriorityQueue<Machine>(new MachineComparator());
        for (int i = 0; i < arr.length ; i++) {
            heap.add(new Machine(0, arr[i]));
        }
//        int[] drinks = new int[n];
//        for (int i = 0; i < n; i++) {
//            Machine cur = heap.poll();
//            cur.timepoint += cur.workTime;
//            drinks[i]= cur.timepoint;
//            heap.add(cur);
//        }
//        return dp(drinks, a, b);
        return 1;
    }

    /**
     *
     * @param drink  所有杯子可以开始洗的时间
     * @param wash  单杯洗干净单时间（串行）
     * @param air  挥发干净的时间
     * @param index
     * @param free  洗的机器什么时候可以用
     * @return  最早的结束时间
     */
    static int bestTime(int[] drink, int wash, int air, int index, int free){
        if(index == drink.length){
            return  0;
        }
        // index 号杯子 决定洗
        int selfClean1= Math.max(drink[index], free)+ wash;
        int restClean1= bestTime(drink, wash, air, index +1, selfClean1);
        int p1= Math.max(selfClean1, restClean1);

        // index 号杯子 决定等待
        int selfClean2= drink[index] + air;
        int restClean2= bestTime(drink, wash, air, index +1, free);

        int p2= Math.max(selfClean2, restClean2);
        return Math.min(p1, p2);
    }
}
