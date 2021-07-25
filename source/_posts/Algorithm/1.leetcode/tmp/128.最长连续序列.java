import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * @lc app=leetcode.cn id=128 lang=java
 *
 * [128] 最长连续序列
 */

// @lc code=start
class Solution {
    Set<Integer> checkNums = new HashSet<>();
    // 剪枝，遍历4的时候： dfs(3) dfs(2) 使用备忘录
    Map<Integer, Integer> m = new HashMap<>();

    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        for (int i = 0; i < nums.length; i++) {
            checkNums.add(nums[i]);
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            // 遍历搜索比它小1 的元素
            res = Math.max(res, dfs(nums[i]));
        }
        return res;
    }

    public int dfs(int cur) {
        if (m.containsKey(cur)) {
            return m.get(cur);
        }
        int len = 1;
        if (checkNums.contains(cur - 1)) {
            len += dfs(cur - 1);
        }
        return len;
    }
}
// @lc code=end
