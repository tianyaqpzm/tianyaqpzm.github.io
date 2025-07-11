
package com.pei.algorithm.dp;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class Solution528 {

    /**
     * 求组合数
     * 
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, 0);
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            System.out.println("-----------i = " + i);
            for (int j = coins[i]; j < amount + 1; j++) {
                dp[j] += dp[j - coins[i]];
                System.out.print("j= " + j + ": " + dp[j] + " ");
            }
            System.out.println();
        }
        return amount;
    }

    /**
     * 求排序数
     * 
     * @param coins
     * @param amount
     * @return
     */
    public int coinChangePaiXu(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, 0);
        dp[0] = 1;
        // 先遍历背包
        for (int j = 0; j < amount + 1; j++) {
            System.out.println("-----------j = " + j);
            // 再遍历物品
            for (int i = 0; i < coins.length; i++) {
                if (j - coins[i] >= 0) {
                    dp[j] += dp[j - coins[i]];
                    System.out.print("i= " + coins[i] + "; " + dp[j] + " ");
                }
            }
            System.out.println();
        }
        return amount;
    }

    @Test
    @DisplayName("将所有行写入文件")
    public void test_coinChange() {
        this.coinChange(new int[] { 1, 2, 5 }, 11);
        // assertThat().containsExactly("alpha", "beta", "gamma", "prod");
    }

    @Test
    @DisplayName("将所有行写入文件")
    public void test_coinChangePaiXu() {
        this.coinChangePaiXu(new int[] { 1, 2, 5 }, 11);
        // assertThat().containsExactly("alpha", "beta", "gamma", "prod");
    }
}
