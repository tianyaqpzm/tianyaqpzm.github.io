import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=78 lang=java
 *
 * [78] 子集
 */

// @lc code=start
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backTrack(res, new ArrayList<Integer>(), nums, 0);
        return res;
    }

    void backTrack(List<List<Integer>> res, List<Integer> tempList, int[] nums, int start) {
        res.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            backTrack(res, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    // public static void main(String[] args) {
    // int[] nums = new int[] { 1, 2, 3 };
    // Solution2 solution = new Solution2();
    // solution.subsets(nums);

    // }
}
// @lc code=end
