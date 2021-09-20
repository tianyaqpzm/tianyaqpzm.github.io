/*
 * @lc app=leetcode.cn id=547 lang=java
 *
 * [547] 省份数量
 */

// @lc code=start
class Solution {
    public int findCircleNum(int[][] isConnected) {
        UnionFind unionFind = new UnionFind(isConnected.length);
        for (int i = 0; i < isConnected.length; i++) {
            for (int j = i + 1; j < isConnected[i].length; j++) {
                if (isConnected[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.cnt;
    }

    public static void main(String[] args) {
        int[][] isConnected = {
                {1, 1, 0}, {1, 1, 0}, {0, 0, 1}
        };
        Solution solution = new Solution();
        int circleNum = solution.findCircleNum(isConnected);
    }

    public class UnionFind {
        private int[] parent;
        private int cnt = 0;

        public UnionFind(int size) {
            this.parent = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                cnt++;
            }
        }

        private int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        private void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            parent[rootX] = rootY;
            cnt--;
        }
    }
}
// @lc code=end

