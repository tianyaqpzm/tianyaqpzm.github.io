import java.util.ArrayList;
import org.junit.Test;

class TestSolutionTest {
    public static void main(String[] args) {
        // char[][] grid = new char[][] {
        // { '1', '1', '1', '1', '0' },
        // { '1', '1', '0', '1', '0' },
        // { '1', '1', '0', '0', '0' },
        // { '0', '0', '0', '0', '0' }
        // };

        char[][] grid = new char[][] {
                { '1', '1', '1' },
                { '0', '1', '0' },
                { '1', '1', '1' }
        };
        // [["1","1","1"],["0","1","0"],["1","1","1"]]
        int numIslands = new Solution200().numIslands(grid);
        System.out.println(numIslands);

    }

    @Test
    public void test_usecase1() {
        System.out.println();

    }
}
/*
 * @lc app=leetcode.cn id=200 lang=java
 *
 * [200] 岛屿数量
 */

// @lc code=start
class Solution {

    public int numIslands(char[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (visited[i][j] || grid[i][j] == '0') {
                    continue;
                }
                res += 1;
                dfs(grid, i, j, visited);
            }
        }
        return res;
    }

    private void dfs(char[][] grid, int i, int j, boolean[][] visited) {
        ArrayList<Point> nextList = nextList(grid, i, j);
        for (int ii = 0; ii < nextList.size(); ii++) {
            Point point = nextList.get(ii);
            if (visited[point.x][point.y]) {
                continue;
            }
            visited[point.x][point.y] = true;
            dfs(grid, point.x, point.y, visited);
        }
    }

    private void dfs2(char[][] grid, int r, int c) {
        int nr = grid.length;
        int nc = grid[0].length;

        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
            return;
        }

        grid[r][c] = '0';
        dfs2(grid, r - 1, c);
        dfs2(grid, r + 1, c);
        dfs2(grid, r, c - 1);
        dfs2(grid, r, c + 1);
    }

    private ArrayList<Point> nextList(char grid[][], int i, int j) {
        ArrayList<Point> next = new ArrayList<>();
        if (i > 0 && grid[i - 1][j] == '1') {
            next.add(new Point(i - 1, j));
        }
        if (i + 1 < grid.length && grid[i + 1][j] == '1') {
            next.add(new Point(i + 1, j));
        }
        if (j > 0 && grid[i][j - 1] == '1') {
            next.add(new Point(i, j - 1));
        }
        if (j + 1 < grid[0].length && grid[i][j + 1] == '1') {
            next.add(new Point(i, j + 1));
        }
        return next;
    }

    class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
// @lc code=end
