/*
 * @lc app=leetcode.cn id=695 lang=java
 *
 * {695} 岛屿的最大面积
 */

// @lc code=start
public class Solution {
    int row = 0;
    int col = 0;

    public static void main(String[] args) {
        // int[][] grid = new int[][] {
        // { 1, 0, 1, 0, 0 },
        // { 1, 1, 1, 0, 0 },
        // { 0, 0, 1, 0, 0 }
        // };
        int[][] grid = new int[][] {
                { 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
                { 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0 },
                { 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0 } };

        // int maxAreaOfIsland = new Solution695().maxAreaOfIsland(grid);
        // System.out.println(maxAreaOfIsland);
    }

    public int maxAreaOfIsland(int[][] grid)

    {
        row = grid.length;
        col = grid[0].length;
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    res = Math.max(res, dfs(grid, i, j));
                }
            }
        }
        return res;
    }

    int dfs(int[][] grid, int i, int j) {
        grid[i][j] = 2;
        int count = 1;
        // 向右
        if (j + 1 < col && grid[i][j + 1] == 1) {
            count += dfs(grid, i, j + 1);
        }
        // 向左
        if (j - 1 >= 0 && grid[i][j - 1] == 1) {
            count += dfs(grid, i, j - 1);
        }
        // 向下
        if (i + 1 < row && grid[i + 1][j] == 1) {
            count += dfs(grid, i + 1, j);
        }
        // 向上
        if (i - 1 >= 0 && grid[i - 1][j] == 1) {
            count += dfs(grid, i - 1, j);
        }
        return count;
    }

}
// @lc code=end
