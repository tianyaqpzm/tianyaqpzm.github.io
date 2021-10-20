import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=1033 lang=java
 *
 * [1033] 移动石子直到连续
 */

// @lc code=start
class Solution {
    public int[] numMovesStones(int a, int b, int c) {
        int max = (a > b ? a : b) > c ? (a > b ? a : b) : c;
        int min = (a < b ? a : b) < c ? (a < b ? a : b) : c;
        int mid = a + b + c - max - min;
        int[] t = new int[] { a, b, c };
        // 默认递增
        // Arrays.sort(a, new Comparable<Integer>() {
        // public int compare(Integer o1, Integer o2) {
        // return o1.compareTo(o2);
        // }
        // });

        // 1、当ab连续 或者bc 连续，最大移动次数abs(bc)距离或abs(ab)， 最小为1
        // 2、当都不连续，移动次数 abs(ac) 最小为2
        // 3、当小于2 那么为 0
        int[] res = new int[] { 0, 0 };
        // Arrays.sort(stones);
        if (max - min > 2) {
            return res;
        }
        res[1] = max - min - 2;
        if (mid - min <= 2 || max - mid <= 2) {
            res[0] = 1;
        } else {
            res[0] = 2;
        }
        return res;
    }
}
// @lc code=end
