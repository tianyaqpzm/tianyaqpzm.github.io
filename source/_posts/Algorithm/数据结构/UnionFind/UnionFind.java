/**
 * 并查集使用的是一种树型的数据结构，用于处理一些不交集（Disjoint Sets）的合并及查询问题。
 * 
 */
public class UnionFind {

    int[] parents;

    // 每个集合的元素数量，key为集合根节点标记
    Map<Integer, Integer> size = new HashMap<Integer, Integer>();

    // 并查集中集合的数量
    int cnt = 0;

    public UnionFind(int n) {
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
            size.put(i, 1);
            cnt++;
        }
    }

    /**
     * 确定元素属于哪一个子集。它可以被用来确定两个元素是否属于同一子集
     * 
     * @param x
     * @return
     */
    public int find(int x) {
        // 路径压缩
        if (x != parents[x]) {
            parents[x] = find(parents[x]);
        }
        return parents[x];
    }

    /**
     * 将两个子集合并成同一个集合。
     * 
     * @param x
     * @param y
     * @param dist
     */
    public void union(int x, int y, int dist) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY)
            return;
        if (size.get(rootX) < size.get(rootY)) {
            // 小树挂大树
            parents[rootX] = rootY;
            size.put(rootY, size.get(rootX) + size.get(rootY));
        } else {
            parents[rootY] = rootX;
            size.put(rootX, size.get(rootX) + size.get(rootY));
        }
        cnt -= 1;
    }

    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}