package com.pei.algorithm.zijie;

import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * 来自字节
 * 
 * 输入:
 * 去重数组arr，里面的数只包含0~9
 * limit，一个数字
 * 
 * 返回:
 * 要求比limit小的情况下，能够用arr拼出来的最大数字
 */
public class MaxNumUnderLimit {

    static int tmp = 0;

    /**
     * 暴力破解，递减找能否拼接出来
     * 比如limit=246， 寻找245 243.。。。
     * 
     * @param arr
     * @param limit
     * @return
     */
    public static int maxNumber1(int[] arr, int limit) {
        Arrays.sort(arr);
        limit--;
        int offset = 1;
        while (offset <= limit / 10) {
            offset *= 10;
        }
        process1(arr, 0, offset, limit);
        if (tmp == 0) {
            // 如果找不到，就退而其次，拼接剩余的最大数
            int rest = 0;
            offset /= 10;
            while (offset > 0) {
                rest += arr[arr.length - 1] * offset;
                offset /= 10;
            }
            return rest;
        }
        return tmp;
    }

    /**
     * 通过arr 进行拼接形成： 222 226 取满足条件的最大值
     * 
     * @param arr
     * @param num
     * @param offset
     * @param limit
     */
    private static void process1(int[] arr, int num, int offset, int limit) {
        if (offset == 0) {
            if (num <= limit) {
                tmp = Math.max(tmp, num);
            }
        } else {
            for (int cur : arr) {
                process1(arr, num * 10 + cur, offset / 10, limit);
            }
        }
    }

    @Test
    @DisplayName("")
    public void test_MaxNumUnderLimit_baoli() {
        int maxNumber1 = maxNumber1(new int[] { 2, 6, 9 }, 269);
        Assertions.assertThat(maxNumber1).isEqualTo(266);
        int maxNumber2 = maxNumber2(new int[] { 2, 6, 9 }, 269);
        Assertions.assertThat(maxNumber2).isEqualTo(266);
        int maxNumber3 = maxNumber2(new int[] { 2, 6, 9 }, 222);
        Assertions.assertThat(maxNumber3).isEqualTo(96);

    }

    /**
     * 动态规划，用arr中的数字去拼 <limit ,尽量大
     * 如果不用offset 可以把limit搞成字符数组，增加index, 去掉期间的数字转换
     * int maxNumber2(int[] arr, int[] limit, int index)
     * 
     * @param arr
     * @param limit
     * @return
     */
    public static int maxNumber2(int[] arr, int limit) {
        Arrays.sort(arr);
        limit--;
        // limit : 657321
        // offset: 100000
        // 当前数：（limit/offset）%10--> 6
        // 下一个：
        // limit : 657321
        // offset : 10000
        // 当前数：（limit/offset）%10--> 5

        // 对齐offset ，
        int offset = 1;
        while (offset <= limit / 10) {
            offset *= 10;
        }
        // offset可能溢出
        // int offset = 1;
        // while (offset <= limit ) {
        // offset *= 10;
        // }
        // offset/=10;

        int ans = process2(arr, offset, limit);
        if (ans != -1) {
            return ans;
        } else {
            // 如果找不到，就退而其次，拼接剩余的最大数
            int rest = 0;
            offset /= 10;
            while (offset > 0) {
                rest += arr[arr.length - 1] * offset;
                offset /= 10;
            }
            return rest;
        }
    }

    /**
     * 拼出来的数字 和limit一样长， 返回尽量大， 如果做不到 返回-1
     * limit： 876530
     * offset : 1000
     * 说明 87这两位一定做追平，才会考虑6，如果offset为0，那么
     * 
     * @param arr
     * @param offset 用来取数字
     * @param limit
     * @return
     */
    private static int process2(int[] arr, int offset, int limit) {
        if (offset == 0) {
            return limit;
        }
        // 当前数字
        int cur = (limit / offset) % 10;
        // 场景1: arr中没有比6小的 返回-1 ，目标：给上一层看，然后变小，后续都最大
        // 场景2: arr中有6 继续递归
        // 场景3: arr中有比6小的数字4 返回比6小的数字4，直接拼接
        int near = near(arr, cur);
        if (near == -1) {
            return -1;
        } else if (arr[near] == cur) {
            // 当前位达成 [2 6 8]
            int ans = process2(arr, offset / 10, limit);
            if (ans != -1) {
                return ans;
            } else if (near > 0) {
                near--;
                return (limit / (offset * 10)) * offset * 10 + (arr[near] * offset) + rest(arr, offset / 10);
            } else {
                // 后续搞不定
                return -1;
            }
        } else {
            return (limit / (offset * 10)) * offset * 10 + (arr[near] * offset) + rest(arr, offset / 10);
        }
    }

    private static int rest(int[] arr, int offset) {
        int rest = 0;
        while (offset > 0) {
            rest += arr[arr.length - 1] * offset;
            offset /= 10;
        }
        return rest;
    }

    /**
     * 二分法：在一个有序数组中找到比num小的最大下标
     * 
     * @param arr
     * @param num
     * @return
     */
    private static int near(int[] arr, int num) {
        int l = 0;
        int r = arr.length - 1;
        int m = 0;
        int near = -1;
        while (l <= r) {
            m = (l + r) / 2;
            if (arr[m] <= num) {
                near = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return near;
    }

}
