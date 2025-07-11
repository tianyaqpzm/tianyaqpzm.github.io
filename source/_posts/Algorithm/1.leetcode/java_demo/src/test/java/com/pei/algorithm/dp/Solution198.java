package com.pei.algorithm.dp;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class Solution198 {

    public int rob(int[] nums) {
        int[] dp = new int[nums.length + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);
        }
        return dp[nums.length];
    }

    // private Solution198 solution = new Solution198();

    @Test
    @DisplayName("")
    public void test_rob() {
        Assertions.assertThat(this.rob(new int[] { 1, 2, 3, 1 })).isEqualTo(4);
    }
}
