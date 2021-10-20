/*
 * @lc app=leetcode.cn id=96 lang=java
 *
 * [96] 不同的二叉搜索树
 */

// @lc code=start
class SolutionBST {
    /**
     * 递归
     * 
     * @param n
     * @return
     */
    public int numTrees(int n) {
        if (n <= 1) {
            return 1;
        }
        int res = 0;
        for (int i = 1; i <= n; i++) {
            res = numTrees(i) * numTrees(n - i - 1);
        }
        return res;
    }

    /**
     * 迭代
     * 
     * @param n
     * @return
     */
    public int countBst(int n) {
        int[] res = new int[n + 1];
        res[0] = 1;
        for (int index = 1; index < res.length; index++) {
            int tmp = 0;
            for (int j = 0; j < index; j++) {
                tmp += res[j] * res[index - j - 1];
            }
            res[index] = tmp;
        }
        return res[n];
    }

    public static void main(String[] args) {
        SolutionBST test = new SolutionBST();
        test.countBst(2);
    }

}
// @lc code=end
