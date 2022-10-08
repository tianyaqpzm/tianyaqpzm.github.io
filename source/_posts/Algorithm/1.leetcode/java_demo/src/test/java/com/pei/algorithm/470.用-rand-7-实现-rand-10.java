/*
 * @lc app=leetcode.cn id=470 lang=java
 *
 * [470] 用 Rand7() 实现 Rand10()
 */

// @lc code=start

class SolBase{
   int rand7(){
        return 1;
    }
}
/**
 * The rand7() API is already defined in the parent class SolBase.
 * public int rand7();
 * 
 * @return a random integer in the range 1 to 7
 */
class Solution extends SolBase {
    public int rand10() {
        int res = 0;
        // 1/10 = 1/2 * 1/5
        // 奇偶 + <5的概率
        int a = rand7();
        int b = rand7();
        while (a == 7) {
            a = rand7();
        }
        while (b > 5) {
            b = rand7();
        }
        return ((a & 1) == 0 ? 0 : 5) + b;
    }

    /**
     * 构造出二进制，表示任意数
     * 
     * @return
     */
    public int rand10_2() {
        int ans = rand2();
        for (int i = 0; i < 3; i++) {
            ans <<= 1;
            ans ^= rand2();
        }
        return (ans < 10 && ans > 0) ? ans : rand10_2();

    }

    public int rand2() {
        int ans = rand7();
        return ans == 7 ? rand2() : ans & 1;
    }

    // 拒绝采样，主要公式为 (rand(X)-1)*Y + randY() -----生成 [1,X*Y]区间，并且等概。 记住就完事了，xdm。

}
// @lc code=end
