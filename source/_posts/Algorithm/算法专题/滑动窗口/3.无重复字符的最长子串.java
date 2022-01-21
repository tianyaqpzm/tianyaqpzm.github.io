
import java.util.HashSet;

/*
 * @lc app=leetcode.cn id=3 lang=java
 *
 * [3] 无重复字符的最长子串
 * 一旦发现set存在，从左侧i开始去除元素，直到不再重复
 */

// @lc code=start
class Solution {

    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> hashSet = new HashSet<>();
        int j = 0;
        int i = 0;
        int res = 0;
        while (j < s.length()) {
            if (!hashSet.contains(s.charAt(j))) {
                hashSet.add(s.charAt(j++));
                res = Math.max(hashSet.size(), res);
            } else {
                hashSet.remove(s.charAt(i++));
            }
        }
        return res;

    }

    // public static void main(String[] args) {
    // Solution3 solution = new Solution3();
    // solution.lengthOfLongestSubstring(" ");
    // }

}
// @lc code=end
