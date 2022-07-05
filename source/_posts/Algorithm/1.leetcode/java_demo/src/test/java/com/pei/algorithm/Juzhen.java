package com.pei.algorithm;

import java.util.Scanner;

public class Juzhen {
    // 矩阵最大值
    // 给定一个仅包含0和1的N*N二维矩阵，请计算二维矩阵的最大值，计算规则如下：
    // 1、每行元素按下标顺序组成一个二进制数（下标越大越排在低位），二进制数的值就是该行的值。矩阵各行值之和为矩阵的值。
    // 2、允许通过向左或向右整体循环移动每行元素来改变各元素在行中的位置。
    // 比如： [1,0,1,1,1]向右整体循环移动2位变为[1,1,1,0,1]，二进制数为11101，值为29。
    // [1,0,1,1,1]向左整体循环移动2位变为[1,1,1,1,0]，二进制数为11110，值30
    // 示例1
    // 输入
    // 5
    // 1,0,0,0,1
    // 0,0,0,1,1
    // 0,1,0,1,0
    // 1,0,0,1,1
    // 1,0,1,0,1
    // 输出
    // 122
    public static void main(String[] args) {
        // 2: 0000 0010 4 : 0000 0100
        // 3: 0000 0011 6: 0000 0110 5: 0000 0101  ==0000 0100  0000 0011

        // >> << | & 位运算符
        // >> 往右移动一位

        System.out.println(4 >> 1);
        System.out.println(2 << 1);
        System.out.println(6 & 5);
        int i1 = 0;
        int n = 6;
        while (n > 0) {
            n = n & (n - 1);
            i1++;
        }

        Scanner scanner = new Scanner(System.in);
        int N = Integer.parseInt(scanner.nextLine());
        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] temp = scanner.nextLine().split(",");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(temp[j]);
            }
        }

        int sum = 0;
        for (int i = 0; i < N; i++) {
            int num = arr[i][0];
            // 用for循环，使num的值等于数组第一行二进制的值
            for (int j = 1; j < N; j++) {
//                1 0 0 0 1  :   0000 0001-->  0000 0010 + 0000 00000 = 0000 0100 ==> 0001 0001
//                (1*2)*2
                num = arr[i][j] + (num << 1);
            }
            int max = num;
//            0,0,0,1,1
            // 往左移动  1 0 0 0 1 --》   0 0 0 1 1--》 0 0 1 1 0  ——》 11000

//            for (int j = 1; j < N; j++) {
//                int first = num & (int)Math.pow(2,N-1);//  1 0 0 0 1 - 10000     00001  ==   0 0 0 1 1
//                num -= first << (N - 1);
//                num = num << 1;
//                num += first;
//                max = Math.max(max, num);
//            }
//            1 0 0 0 1   01000 + 1*
            for (int j = 1; j < N; j++) {
                int end = num & 1;//  1 0 0 0 1 - 10000     00001  ==   0 0 0 1 1
                num = (num >> 1)  + (end << (N - 1));;
                max = Math.max(max, num);
            }


            sum += max;
        }
        System.out.println(sum);
    }

}
