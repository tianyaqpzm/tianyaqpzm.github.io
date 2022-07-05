/*
 * @lc app=leetcode.cn id=5 lang=java
 *
 * [5] 最长回文子串
 */

// @lc code=start
class Solution {
    public String longestPalindrome(String s) {

        return "";
    }

    /**
     * 
     * @param s
     * @param i
     * @param j
     * @return
     */
    boolean isPalindrome(String s, int i, int j) {
        while (i < j && s.charAt(i) == s.charAt(j)) {
            i++;
            j--;
        }
        return i == j ? true : false;
    }

}
// @lc code=end
