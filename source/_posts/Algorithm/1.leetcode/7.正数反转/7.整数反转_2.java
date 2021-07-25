/*
 * @lc app=leetcode.cn id=7 lang=java
 *
 * [7] 整数反转
 */

// @lc code=start
class Solution {
    public int reverse(int x) {
        if(x>Integer.MAX_VALUE)return 0;
        long x1=x;
        int flag = x > 0 ? 1 : -1;
        StringBuffer sb = new StringBuffer(String.valueOf(Math.abs(x1)));
        long res = Long.parseLong(sb.reverse().toString());
        if(res>Integer.MAX_VALUE)return 0;
        return (int)res * flag;
    }
}
// @lc code=end

