package com.pei.algorithm._547.省份数量;

import java.util.LinkedList;
import java.util.Queue;

public
/*
 * @lc app=leetcode.cn id=547 lang=java
 *
 * [547] 省份数量
 */

// @lc code=start
class Solution2 {

    public int findCircleNum(int[][] isConnected) {
        int length = isConnected.length;
        boolean[] visit = new boolean[length];
        int cnt = 0;
        for (int i = 0; i < length; i++) {
            if (!visit[i]) {
                dfs(isConnected, visit, length, i);
            }

        }
        return cnt;
    }

    private void dfs(int[][] isConnected, boolean[] visit, int legth, int i) {
        for (int j = 0; j < legth; j++) {
            if (isConnected[i][j] == 1 && !visit[j]) {
                visit[j] = true;
                dfs(isConnected, visit, legth, j);
            }
        }
    }

    public int findCircleNumBfs(int[][] isConnected) {
        int provinces = isConnected.length;
        boolean[] visited = new boolean[provinces];
        int circles = 0;
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < provinces; i++) {
            if (!visited[i]) {
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int j = queue.poll();
                    visited[j] = true;
                    for (int k = 0; k < provinces; k++) {
                        if (isConnected[j][k] == 1 && !visited[k]) {
                            queue.offer(k);
                        }
                    }
                }
                circles++;
            }
        }
        return circles;
    }

    public static void main(String[] args) {
        int[][] isConnected = { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } };
        Solution2 solution = new Solution2();
        solution.findCircleNum(isConnected);
    }

}
// @lc code=end
