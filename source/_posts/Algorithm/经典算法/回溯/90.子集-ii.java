import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
/*
 * @lc app=leetcode.cn id=90 lang=java
 *
 * [90] 子集 II
 */

// @lc code=start
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backTrack(res, new ArrayList<Integer>(), nums, 0);
        return res;
    }

    void backTrack(List<List<Integer>> res, List<Integer> tempList, int[] nums, int start) {
        res.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            tempList.add(nums[i]);
            backTrack(res, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
}
// @lc code=end
