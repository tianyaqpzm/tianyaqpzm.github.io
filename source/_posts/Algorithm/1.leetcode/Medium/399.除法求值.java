package com.pei.algorithm;


import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * @lc app=leetcode.cn id=399 lang=java
 *
 * [399] 除法求值
 */

// @lc code=start
class Solution {

    // map保存 字母和 下标的键值对， 2、保存是否在并查集中，如果没有，则开启编号
    HashMap<String, Integer> map = new HashMap<>();

    class UnionFind {

        private int[] parents;
        private double[] weight;
        private int cnt = 0;

        public UnionFind(int size) {
            this.parents = new int[size];
            this.weight = new double[size];
            for (int i = 0; i < size; i++) {
                parents[i] = i;
                weight[i] = 1d;
            }
        }

        private int find(int x) {
            if (x != parents[x]) {
                int pre = parents[x];
                parents[x] = find(parents[x]);
                weight[x] *= weight[pre];
            }
            return parents[x];
        }

        private boolean contected(int x, int y) {
            return find(x) == find(y);
        }

        private void union(int x, int y, double wei) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            // rootX 挂在rootY上
            parents[rootX] = rootY;
            // w[x] * w[rootX] = wei * w[rootY]
            weight[rootX] = wei * weight[y] / weight[x];
            cnt--;
        }
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        UnionFind unionFind = new UnionFind(equations.size() * 2);
        int id = 0;
        for (int i = 0; i < equations.size(); i++) {
            String x = equations.get(i).get(0);
            String y = equations.get(i).get(1);
            if (!map.containsKey(x)) {
                map.put(x, id);
                id++;
            }
            if (!map.containsKey(y)) {
                map.put(y, id);
                id++;
            }
            unionFind.union(map.get(x), map.get(y), values[i]);
        }
        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String x = queries.get(i).get(0);
            String y = queries.get(i).get(1);
            if (!map.containsKey(x) || !map.containsKey(y)) {
                res[i] = -1;
                continue;
            }
            int rootX = unionFind.find(map.get(x));
            int rootY = unionFind.find(map.get(y));
            if (rootX != rootY) {
                res[i] = -1;
            } else {
                res[i] = unionFind.weight[map.get(x)] / unionFind.weight[map.get(y)];
            }
        }
        return res;
    }


    public static void main(String[] args) {
        List<List<String>> equations = new ArrayList<>();
        equations.add(Lists.newArrayList("a", "b"));
        equations.add(Lists.newArrayList("b", "c"));
        equations.add(Lists.newArrayList("c", "d"));
        equations.add(Lists.newArrayList("d", "e"));
//        double[] values = new double[]{2.0, 3.0};
        double[] values = {3.0, 4.0, 5.0, 6.0};
        List<List<String>> queries = new ArrayList<>();
        queries.add(Lists.newArrayList("a", "e"));
        queries.add(Lists.newArrayList("b", "a"));
        queries.add(Lists.newArrayList("a", "e"));
        queries.add(Lists.newArrayList("a", "a"));
        queries.add(Lists.newArrayList("x", "x"));
        Solution solution = new Solution();
        solution.calcEquation(equations, values, queries);
    }
}
// @lc code=end