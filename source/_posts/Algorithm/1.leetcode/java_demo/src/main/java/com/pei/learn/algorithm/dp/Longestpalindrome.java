package com.pei.learn.algorithm.dp;

public class Longestpalindrome {


    public static void main(String[] args) {

    }

    public static int lpsl(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        return fun(s.toCharArray(), 0, s.length() - 1);
    }

    private static int fun(char[] str, int L, int R) {
        if (L == R) {
            return 1;
        }
        if (L == R - 1) {
            return str[L] == str[R] ? 2 : 1;
        }
        int p1 = fun(str, L + 1, R - 1);
        int p2 = fun(str, L, R - 1);
        int p3 = fun(str, L + 1, R);
        int p4 = str[L] != str[R] ? 0 : 2 + fun(str, L + 1, R - 1);
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }

    public static int lspl2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int N = s.length();
        char[] str = s.toCharArray();
        int[][] dp = new int[N][N];
        dp[N - 1][N - 1] = 1;
        //
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }
        for (int L = N - 3; L > 0; L--) {
            for (int R = L + 2; R < N; R++) {
                int p1 = dp[L + 1][R - 1];
                int p2 = dp[L][R - 1];
                int p3 = dp[L + 1][R];
                int p4 = str[L] != str[R] ? 0 : 2 + fun(str, L + 1, R - 1);
                dp[L][R] = Math.max(Math.max(p1, p2), Math.max(p3, p4));
            }
        }
        return dp[0][N - 1];
    }

    public static int lspl3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int N = s.length();
        char[] str = s.toCharArray();
        int[][] dp = new int[N][N];
        dp[N - 1][N - 1] = 1;
        //
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }
        for (int L = N - 3; L > 0; L--) {
            for (int R = L + 2; R < N; R++) {
                dp[L][R] = Math.max(dp[L][R - 1], dp[L + 1][R]);
                if(str[L] == str[R]){
                    dp[L][R] = Math.max(dp[L][R], 2+ dp[L+1][R-1]);
                }
            }
        }
        return dp[0][N - 1];
    }

}
