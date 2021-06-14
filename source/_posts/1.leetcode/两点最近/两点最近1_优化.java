package com.pei.demo;

// 算法设计与分析——分治法：详解二维最近点对问题
// https://blog.csdn.net/weixin_41710054/article/details/105515528?utm_medium=distribute.pc_relevant.none-task-blog-baidujs-1

class Solution {
    // 用数组 代表点， 第一列: 代表x，points[x][0], 第二列: 代表y ： point[y][1]

    // 数据预处理：将点按照横坐标由小到大排序
    // 快速排序
    private static double[][] quickSort(double[][] oldData) {
        double[][] newData = oldData.clone();
        QSRecursion(newData, 0, newData.length - 1);
        return newData;
    }

    private static void QSRecursion(double[][] newData, int i, int j) {
        if (i == j)
            return;
        int standardIndex = i + new java.util.Random().nextInt(j - i + 1);
        int pivot = partition(newData, i, j, standardIndex);// 保存基准元素的正确位置
        if (pivot != i)
            QSRecursion(newData, i, pivot - 1);// 左边
        if (pivot != j)
            QSRecursion(newData, pivot + 1, j);// 右边
    }

    private static int partition(double[][] newData, int i, int j, int standardIndex) {
        swap(newData, i, standardIndex);// 将序列首个元素和基准元素对换位置
        int left = i + 1;// i为头指针
        int right = j;// j为尾指针
        while (left < right) {
            while (newData[left][0] <= newData[i][0] & left < right) {
                left++;
            }
            while (newData[right][0] >= newData[i][0] & left < right) {
                right--;
            }
            if (left < right) {
                swap(newData, right, left);// 对换位置,保证左小右大
            }
        }
        // 因为选取主元是随机的，那么判断
        if (newData[right][0] > newData[i][0]) {
            swap(newData, right - 1, i);
            standardIndex = right - 1;
        } else {
            swap(newData, j, i);
            standardIndex = j;
        }
        return standardIndex;
    }

    private static void swap(double[][] newData, int a, int b) {// 对换位置
        double[] temp = newData[a];
        newData[a] = newData[b];
        newData[b] = temp;
    }

    // 分治法求解
    public static double getByDivideConquer(double[][] points) {
        if (points.length == 1 | points.length == 0) {
            return 0;
        }
        double[][] pointsSorted = quickSort(points);
        // for(int i=0;i<pointsSorted.length;i++)//检查排序是否正确
        // {
        // System.out.println(pointsSorted[i][0]);
        //
        // }
        int head = 0;
        int tail = pointsSorted.length - 1;
        int[] minIndex = getByDivideConquer(pointsSorted, head, tail);
        double minDist = getDistance(pointsSorted, minIndex[0], minIndex[1]);
        return minDist;
    }

    /////////////////////////////////////////////
    private static int[] getByDivideConquer(double[][] points, int head, int tail) {
        double minDist = getDistance(points, head, head + 1);// 初始化最小距离
        int[] minIndex = new int[] { head, head + 1 };// 初始化最小点对序号
        if (tail - head + 1 <= 4) {// 点数小于等于4时
            for (int i = head; i <= tail - 1; i++) {// 遍历求距离
                for (int j = i + 1; j <= tail; j++) {
                    if (getDistance(points, i, j) < minDist) {
                        minDist = getDistance(points, i, j);
                        minIndex[0] = i;
                        minIndex[1] = j;
                    }
                }
            }
        } else {
            int[] minIndexLeft = getByDivideConquer(points, head, head + (tail - head) / 2);
            int[] minIndexRight = getByDivideConquer(points, head + (tail - head) / 2 + 1, tail);
            double minDisLeft = getDistance(points, minIndexLeft[0], minIndexLeft[1]);
            double minDisRight = getDistance(points, minIndexRight[0], minIndexRight[1]);
            double minDisTwoSide = Math.min(minDisLeft, minDisRight);// 即d
            if (minDisLeft >= minDisRight) {
                minDist = minDisRight;
                minIndex = minIndexRight;
            } else {
                minDist = minDisLeft;
                minIndex = minIndexLeft;
            }
            double middleAxis = (points[head + (tail - head) / 2][0] + points[head + (tail - head) / 2 + 1][0]) / 2;// 设置中间线，该变量为中间线的x轴坐标
            int i = head + (tail - head) / 2 + 1;// 中间线右边的点
            while (i <= tail && (points[i][0] - middleAxis < minDisTwoSide)) {// i点没越界且和中间线的x轴距离小于d
                {
                    int count = 0;
                    for (int j = head + (tail - head) / 2; j >= head && (points[j][0] - middleAxis < minDisTwoSide)
                            && (count <= 6); j--) {// j点没越界且符合条件的个数小于6
                        if (Math.abs(points[j][1] - points[j][1]) < minDisTwoSide) {// 找出d*2d矩形的点
                            if (getDistance(points, i, j) < minDist) {
                                minDist = getDistance(points, i, j);
                                minIndex[0] = i;
                                minIndex[1] = j;
                            }
                            count++;
                        }
                    }
                    i++;
                }
            }
        }
        return minIndex;
    }

    //////////////////////////////////////////////////
    // 计算点i和点j的距离
    private static double getDistance(double[][] points, int i, int j) {
        double dis = Math.sqrt(Math.pow(points[i][0] - points[j][0], 2) + Math.pow(points[i][1] - points[j][1], 2));
        // System.out.println(dis);//测试输出
        return dis;
    }

}