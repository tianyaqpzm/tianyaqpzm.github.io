/*
 * @lc app=leetcode.cn id=647 lang=java
 *
 * [647] 回文子串
 */

// @lc code=start
class Solution {

    /**
     * 暴力
     */
    public int countSubstrings(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (isPalindrome(s, i, j)) {
                    res += 1;
                }
            }
        }
        return res;
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
            if (i == j - 1) {
                return true;
            }
            i++;
            j--;
        }
        return i == j ? true : false;
    }

    public int countSubstrings2(String s) {
        int len, ans = 0;
        if (s == null || (len = s.length()) < 1) {
            return 0;
        }
        // dp[i][j]：s字符串下标i到下标j的字串是否是一个回文串，即s[i, j]
        boolean[][] dp = new boolean[len][len];
        for (int j = 0; j < dp.length; j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }
        // 遍历每一个字串，统计回文串个数
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (dp[i][j])
                    ans++;
            }
        }
        return ans;
    }

    /**
     * 首先确定回文串，就是找中心然后想两边扩散看是不是对称的就可以了。
     * 在遍历中心点的时候，要注意中心点有两种情况。
     * 一个元素可以作为中心点，两个元素也可以作为中心点。
     * 
     * @param s
     * @return
     */
    public int countSubstrings3(String s) {
        int len, ans = 0;
        if (s == null || (len = s.length()) < 1) {
            return 0;
        }

        // 总共有2 * len - 1个中心点
        for (int i = 0; i < 2 * len - 1; i++) {
            // 通过遍历每个回文中心，向两边扩散，并判断是否回文字串
            // 有两种情况，left == right，right = left + 1，这两种回文中心是不一样的
            int left = i / 2, right = left + i % 2;
            while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                // 如果当前是一个回文串，则记录数量
                ans++;
                left--;
                right++;
            }
        }
        return ans;
    }

    // public static void main(String[] args) {
    // System.out.println(new Solution647().countSubstrings("aaa"));
    // }

    // 【综合笔试题】难度 2.5/5，结合了「DP」和「回溯」的经典回文串题目
    // https://mp.weixin.qq.com/s?__biz=MzU4NDE3MTEyMA==&mid=2247487047&idx=1&sn=117c48f20778868442fce44e100d2ea8&chksm=fd9ca558caeb2c4eb1bff4f0878ff796feabe523657c2aafea0b2d1c7026e1c0572ab1e6d205&token=635532356&lang=zh_CN#rd

}
// @lc code=end
