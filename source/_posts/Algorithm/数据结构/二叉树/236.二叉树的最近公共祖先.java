import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=236 lang=java
 *
 * [236] 二叉树的最近公共祖先
 */

// @lc code=start
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {

        ArrayList<TreeNode> pathP = new ArrayList<TreeNode>();
        ArrayList<TreeNode> pathQ = new ArrayList<TreeNode>();
        dfs(root, pathP, p);
        dfs(root, pathQ, q);
        pathP = suc.get(0);
        pathQ = suc.get(1);
        int minCount = pathP.size();
        if (pathQ.size() < minCount) {
            minCount = pathQ.size();
        }
        TreeNode res = root;

        for (int i = 0; i < minCount; i++) {
            if (pathP.get(i).val != pathQ.get(i).val) {
                return res;
            }
            res = pathP.get(i);
        }
        return res;
    }

    /**
     * lowestCommonAncestor这个函数不要理解为找公共祖先，而就理解为帮两个节点找祖先 传入的值是root, p,
     * q，帮p和q找到一个祖先就行，找到两个就更好了，如果找不到就返回NULL
     * 在root->left里面找一次，root->right里面再找一次，如果某一边返回值是NULL， 那么说明两个值都在另一边
     * 由于找的时候，一定是找的最近的祖先返回，所以这里直接返回前面的返回值就行了，可以保证是最近的公共祖先 <br>
     * 如果左右的返回值都不是NULL，那说明p和q分别在两边，则当前节点就是最近公共祖先 左右都找不到就直接返回NULL
     * 
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root.val == p.val || root.val == q.val) {
            return root;
        }
        TreeNode left = null;
        TreeNode right = null;
        if (root.left != null) {
            left = lowestCommonAncestor(root.left, p, q);
        }
        if (root.right != null) {
            right = lowestCommonAncestor(root.right, p, q);
        }
        if (left != null && right != null) {
            return root;
        }
        if (right != null) {
            return right;
        }
        return left;
    }

    private List<ArrayList<TreeNode>> suc = new ArrayList<ArrayList<TreeNode>>();

    /**
     * path代表当前遍历的路径，每递归一次 添加当前元素，在末位恢复现场
     * 
     * @param root
     * @param path
     * @param target
     */
    private void dfs(TreeNode root, ArrayList<TreeNode> path, TreeNode target) {
        if (root == null) {
            return;
        }
        if (root.val == target.val) {
            path.add(target);
            ArrayList<TreeNode> arrayList = new ArrayList<TreeNode>(path);
            suc.add(arrayList);
            return;
        }
        path.add(root);
        dfs(root.left, path, target);
        dfs(root.right, path, target);
        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        TreeNode treeNode2 = new TreeNode(2);
        treeNode2.left = new TreeNode(7);
        treeNode2.right = new TreeNode(4);
        TreeNode treeNode6 = new TreeNode(6);

        TreeNode treeNode5 = new TreeNode(5);
        treeNode5.left = treeNode6;
        treeNode5.right = treeNode2;

        TreeNode treeNode0 = new TreeNode(0);
        TreeNode treeNode8 = new TreeNode(8);

        TreeNode treeNode1 = new TreeNode(1);
        treeNode1.left = treeNode0;
        treeNode1.right = treeNode8;

        TreeNode treeNode3 = new TreeNode(3);
        treeNode3.left = treeNode5;
        treeNode3.right = treeNode1;

        // Solution236 solution236 = new Solution236();
        // solution236.lowestCommonAncestor(treeNode3, treeNode6, treeNode8);

    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
// @lc code=end
