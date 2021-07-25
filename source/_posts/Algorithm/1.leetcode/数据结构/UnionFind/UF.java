package com.pei.unionfind;

/**
 * 并查集 https://blog.csdn.net/weixin_40400177/article/details/103570722
 * 用于解决动态连通性问题，能动态连接两个点，并且判断两个点是否连通
 */
public class UF {
    // id[0] =1 --> 0节点的 父节点为 1
    protected int[] id;
    // 保存节点的数量信息
    private int[] sz;

    public UF(int N) {
        id = new int[N];
        this.sz = new int[N];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
        for (int i = 0; i < N; i++) {
            this.sz[i] = 1;
        }
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // 快速判断两个节点是否连通
    // 因为同一个连通分量的节点 id 值不同，
    // id 值只是用来指向另一个节点。因此需要一直向上查找操作，直到找到最上层的节点。
    public int find(int p) {
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            id[pRoot] = qRoot;
        }
        if (sz[i] < zs[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }

    }
}