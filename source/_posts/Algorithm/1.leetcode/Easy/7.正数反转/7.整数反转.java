/*
 * @lc app=leetcode.cn id=7 lang=java
 *
 * [7] 整数反转
 */

// @lc code=start
class Solution {
    public int reverse(int x) {
        int flag = n>0?1:-1;      
        if(x>Integer.MIN_VALUE) return 0;
        x *= flag;
        int res=0;
        int tmp;
        while(x>0){
            int n = ret;
            n*=10;
            n+=x%10;
            x /=10;
            if(n/10 != ret){
                return 0;
            }
            res = n;
        }
    }
}
// @lc code=end

