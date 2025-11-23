/*
 * @lc app=leetcode.cn id=22 lang=java
 *
 * [22] 括号生成
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Solution22 {
    public List<String> generateParenthesis(int n) {

        List<String> res = new ArrayList<String>();

        // StringBuilder temp = new StringBuilder();
        backtrack(res, new StringBuilder(), n * 2);
        return res;
    }

    private void backtrack(List<String> res, StringBuilder sb, int n) {
        if (n == 0) {
            if (isValid(sb.toString())) {
                res.add(sb.toString());
                return;
            }
            return;
        }

        sb.append('(');
        backtrack(res, sb, n - 1);
        sb.deleteCharAt(sb.length() - 1);

        sb.append(')');
        backtrack(res, sb, n - 1);
        sb.deleteCharAt(sb.length() - 1);
    }

    private boolean isValid(String str) {
        Stack<Character> stack = new Stack<>();
        boolean flag = false;
        for (int i = 0; i < str.length(); i++) {
            char target = str.charAt(i);
            if (target == ')' && stack.size() > 0 && stack.peek() == '(') {
                stack.pop();
            } else {
                stack.add(target);
            }
        }
        if (stack.isEmpty()) {
            flag = true;
        }
        return flag;
    }

    /**
     * Helper method to validate if a string has valid parentheses
     */
    private boolean isValidParentheses(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
                if (count < 0) {
                    return false; // More closing than opening at this point
                }
            }
        }
        return count == 0; // Should have equal opening and closing
    }

}
// @lc code=end
