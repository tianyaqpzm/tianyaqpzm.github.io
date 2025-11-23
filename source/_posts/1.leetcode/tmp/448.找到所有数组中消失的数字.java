/*
 * @lc app=leetcode.cn id=448 lang=java
 *
 * [448] 找到所有数组中消失的数字
 */

// @lc code=start

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class Solution448 {

    /**
     * 最简单思路：遍历一次，遇到 构造TreeSet 每次从中去掉出现的，后面转为list
     * 
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers2(int[] nums) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 1; i <= nums.length; i++) {
            hashSet.add(i);
        }
        for (int i = 0; i < nums.length; i++) {
            hashSet.remove(nums[i]);
        }
        return List.copyOf(hashSet);
    }

    /**
     * 思考优化：如果只有一个数字的话 且不重复，那构造[1,...,n] 异或后 即可找出不存在的数
     * 
     * 看最优答案：核心思想：利用数组索引作为标记，将出现过的数字对应的位置标记为负数。
     * 这是一个非常巧妙的算法，通过利用数组本身的空间来记录信息，避免了使用额外的数据结构。
     * 
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        if (nums == null & nums.length == 0) {
            return new ArrayList<>();
        }
        int idx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                idx = nums[i] - 1;
            } else {
                // 因为之前将该数置反， 需要取反 后-1，获取对应的下标。
                idx = -1 * nums[i] - 1;
            }
            if (nums[idx] > 0) {
                nums[idx] *= -1;
            }
        }
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                res.add(i + 1);
            }
        }
        return res;
    }

}
// @lc code=end
