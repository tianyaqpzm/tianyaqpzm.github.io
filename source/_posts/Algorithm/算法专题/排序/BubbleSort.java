// package com.pei.sort;

//  定义int型一维数组arr={ 32, 25, 48, 12, 56, 17}, 采用冒泡排序方法对该数组进行升序排序。

public class BubbleSort {
    // 定义int型一维数组arr={ 32, 25, 48, 12, 56, 17}, 采用冒泡排序方法对该数组进行升序排序
    public static void main(String[] args) {
        int[] arr = { 32, 25, 48, 12, 56, 17 };
        for (int i = arr.length - 1; i > 0; i--) {

            for (int j = 0; j < i; j++) {
                // 保证最小的放在最前面
                if (arr[j] > arr[j + 1]) {
                    // 如果前面的大，那么互换
                    int tem = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tem;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}