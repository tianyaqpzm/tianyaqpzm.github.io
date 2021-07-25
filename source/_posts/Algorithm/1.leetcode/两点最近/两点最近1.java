package com.pei.demo;

class Solution {
    /**
     * 用数组 代表点， 第一列: 代表x，points[x][0], 第二列: 代表y ： point[y][1]
     * 
     * @param points
     * @return
     */
    public static double getByBruteFoce(double[][] points) {
        int head = 0;
        int tail = points.length - 1;
        double minDist = getDistance(points, head, head + 1);// 初始化最小距离
        int[] minIndex = new int[] { head, head + 1 };// 初始化最小点对序号
        for (int i = head; i <= tail - 1; i++) {// 遍历求距离
            for (int j = i + 1; j <= tail; j++) {
                if (getDistance(points, i, j) < minDist) {
                    minDist = getDistance(points, i, j);// 更新点对最小距离
                    minIndex[0] = i;// 更新点对序号
                    minIndex[1] = j;
                }
            }
        }
        return minDist;
    }

    // 计算点i和点j的距离
    private static double getDistance(double[][] points, int i, int j) {
        double dis = Math.sqrt(Math.pow(points[i][0] - points[j][0], 2) + Math.pow(points[i][1] - points[j][1], 2));
        // System.out.println(dis);//测试输出
        return dis;
    }
}