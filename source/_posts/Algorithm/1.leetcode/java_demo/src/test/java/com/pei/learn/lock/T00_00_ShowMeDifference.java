package com.pei.learn.lock;

public class T00_00_ShowMeDifference {
}

final class Accumulator {
    // 线程不安全
    private double result = 0.0D;

    public void addAll(double[] values) {
        for (double value : values) {
            result += value;
        }
    }
}

final class Accumulator2 {
    // 线程不安全
    private double result = 0.0D;

    public void addAll(double[] values) {
        double sum = 0.0D;
        for (double value : values) {
            sum += value;
        }
        result += sum;
    }
}