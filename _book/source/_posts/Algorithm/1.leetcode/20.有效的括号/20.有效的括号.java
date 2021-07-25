/*
 * @lc app=leetcode.cn id=20 lang=java
 *
 * [20] 有效的括号
 */

// @lc code=start
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0;i <s.length();i++){
            char ch = s.charAt(i);
            if(ch =='('||ch=='{'|| ch=='['){
                stack.push(ch);}
            if (ch ==')'&& (stack.empty() || stack.pop()!='('))
                return false;
            if (ch ==']'&& (stack.empty() || stack.pop()!='['))
                return false;
            if (ch =='}'&& (stack.empty() || stack.pop()!='{'))
                return false;
        }
        if (!stack.empty())
            return false;
        return true;
    }
}
// @lc code=end

