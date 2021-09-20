import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=131 lang=java
 *
 * [131] 分割回文串
 */

// @lc code=start
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        backTrack(res, new ArrayList<String>(), s, 0);
        return res;
    }

    void backTrack(List<List<String>> res, List<String> tempList, String s, int start) {
        if (start == s.length()) {
            res.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (isPalidrome(s, start, i)) {
                tempList.add(s.substring(start, i + 1));
                backTrack(res, tempList, s, i + 1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    boolean isPalidrome(String sub, int start, int end) {
        while (start <= end) {
            if (sub.charAt(start) != sub.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    // public static void main(String[] args) {
    // String s = "aab";
    // Solution_131 solution_131 = new Solution_131();
    // List<List<String>> partition = solution_131.partition(s);
    // System.out.println();
    // }
}
// @lc code=end
