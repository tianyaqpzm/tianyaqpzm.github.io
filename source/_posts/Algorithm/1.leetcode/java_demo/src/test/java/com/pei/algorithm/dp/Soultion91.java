package com.pei.algorithm.dp;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.assertj.core.api.Assertions;

public class Soultion91 {
    public int numDecodings(String s) {
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        for (int i = 1; i <= s.length(); i++) {
            if (s.charAt(i - 1) != '0') {
                dp[i] = dp[i - 1];
            }
            if (i > 1 && s.charAt(i - 2) != '0' && Integer.valueOf(s.substring(i - 2, i)) - 26 <= 0) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[s.length()];
    }

    public Soultion91 soultion91 = new Soultion91();

    @Test
    @DisplayName("")
    public void test_numDecodings() {
        Assertions.assertThat(soultion91.numDecodings("226")).isEqualTo(3);
        Assertions.assertThat(soultion91.numDecodings("10011")).isEqualTo(0);
    }
}
