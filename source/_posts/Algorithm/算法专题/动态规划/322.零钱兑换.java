import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=322 lang=java
 *
 * [322] 零钱兑换
 */

// @lc code=start
class Solution {
    public HashMap<Integer, Integer> cache = new HashMap<>();

    private int MAX_VALUE;

    public int coinChangeDfs(int[] coins, int amount) {
        MAX_VALUE = amount + 1;
        int ans = dfs(coins, amount);
        return ans == MAX_VALUE ? -1 : ans;
    }

    /**
     * 遍历硬币的组合， 1，2，5 硬币列表，当前兑换金额</br>
     * 
     * @param coins
     * @param amount
     * @return
     */
    private int dfs(int[] coins, int amount) {
        if (this.cache.containsKey(amount)) {
            return this.cache.get(amount);
        }
        if (amount < 0) {
            return MAX_VALUE;
        }
        if (amount == 0) {
            return 0;
        }
        int ans = MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            ans = Math.min(ans, 1 + dfs(coins, amount - coins[i]));
            this.cache.put(amount, ans);
        }
        return ans;
    }

    /**
     * 排列数：背包在外层<br>
     * 与硬币次序无关
     * 
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange1(int[] coins, int amount) {
        // int[] dp = new int[amount + 1];
        // Arrays.fill(dp, max);

        // 存在amount为0的情况 coins为0的情况
        int[][] dp = new int[amount + 1][coins.length + 1];
        for (int i = 1; i < amount + 1; i++) {
            for (int j = 0; j < coins.length + 1; j++) {
                dp[i][j] = amount + 1;
            }
        }
        Arrays.fill(dp[0], 0);

        for (int i = 1; i < amount + 1; i++) {
            for (int j = 1; j < coins.length + 1; j++) {
                if (i - coins[j - 1] >= 0) {
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i - coins[j - 1]][j] + 1);
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[amount][coins.length] == amount + 1 ? -1 : dp[amount][coins.length];
    }

    /**
     * 组合数：物品在外层<br>
     * 内部循环，获取到所有硬币的组合数
     * 
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange2(int[] coins, int amount) {
        // int[] dp = new int[amount + 1];
        // Arrays.fill(dp, max);

        // 存在amount为0的情况 coins为0的情况
        int[][] dp = new int[coins.length + 1][amount + 1];

        for (int i = 0; i < coins.length + 1; i++) {
            for (int j = 0; j < amount + 1; j++) {
                dp[i][j] = amount + 1;
            }
        }

        for (int i = 0; i < coins.length + 1; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i < coins.length + 1; i++) {
            for (int j = 1; j < amount + 1; j++) {
                if (j - coins[i - 1] >= 0) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - coins[i - 1]] + 1);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[coins.length][amount] == amount + 1 ? -1 : dp[coins.length][amount];
    }

    /**
     * dp[j]：凑足总额为j所需钱币的最少个数为dp[j]. <br>
     * 递推公式：dp[j] = min(dp[j - coins[i]] + 1, dp[j]);
     * 
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        // int[] dp = new int[amount + 1];
        // Arrays.fill(dp, max);

        // 存在amount为0的情况 coins为0的情况
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i < coins.length + 1; i++) {
            for (int j = 1; j < amount + 1; j++) {
                if (j - coins[i - 1] >= 0) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i - 1]] + 1);
                }
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    /**
     * 
     * @param coins
     * @param amount
     * @return
     */
    public int coinChangeError(int[] coins, int amount) {
        // int[] dp = new int[amount + 1];
        // Arrays.fill(dp, max);

        // 存在amount为0的情况 coins为0的情况
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int j = 1; j < amount + 1; j++) {
            for (int i = 1; i < coins.length + 1; i++) {
                if (j - coins[i - 1] >= 0) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i - 1]] + 1);
                }
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = new int[] { 1, 2, 5 };
        Solution322 solution322 = new Solution322();
        solution322.coinChangeError(coins, 5);
    }

}
// @lc code=end
