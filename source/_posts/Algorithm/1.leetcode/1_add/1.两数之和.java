/*
 * @lc app=leetcode.cn id=1 lang=java
 *
 * [1] 两数之和
 */

import java.util.*;

// @lc code=start
public class Solution {
    /**
     * 利用字典 key 保存数字，value 保存索引 寻找第一次能满足 两数之和为指定值
     * 
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        Map map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                res[0] = (int) map.get(target - nums[i]);
                res[1] = i;
                return res;
            }
            map.put(nums[i], i);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("test");
    }

}
// @lc code=end
