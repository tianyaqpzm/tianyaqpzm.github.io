public class UnionFindWei {

    int[] parents;
    // 每个集合的元素数量，key为集合根节点标记
    Map<Integer, Integer> size = new HashMap<Integer, Integer>();

    // 每个集合的边的权重，key为集合根节点标记
    // weight[a] = 1 表示 a 到其父节点的权重是 1
    Map<Integer, Integer> weight = new HashMap<Integer, Integer>();

    // 并查集中集合的数量
    int cnt = 0;

    public UnionFindWei(int n) {
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
            size.put(i, 1);
            weight.put(i, 0);
            cnt++;
        }
    }

    // public int find(int x) {
    //     int temp = 0;
    //     // root <-- a <-- b
    //     while (x != parents[x]) {
    //         temp = weight.get(x);
    //         x = parents[x];
    //         // 路径压缩
    //         parents[x] = parents[x];
    //         weight.put(x, weight.get(x) + temp);
    //     }
    //     return x;
    // }

    private int find(int x) {
        if (x != parents[x]) {
            int pre = parents[x];
            parents[x] = find(parents[x]);
            weight[x] *= weight[pre];
        }
        return parents[x];
    }

    public void union(int x, int y, int dist) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY)
            return;
        if (size.get(rootX) < size.get(rootY)) {
            // 将 x 挂载到Y上
            parents[rootX] = rootY;
            // 更新 大树的大小
            size.put(rootY, size.get(rootX) + size.get(rootY));

            // 更新 rootX 到其父节点rootY的权重是
            // 原先： w[x] + w[rootX] = dest + w[y]
            weight.put(rootX, dist + weight.get(y) - weight.get(x));
        } else {
            parents[rootY] = rootX;
            size.put(rootX, size.get(rootX) + size.get(rootY));
            weight.put(rootY, dist + weight.get(x) - weight.get(y));
        }
        cnt -= 1;
    }

    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}