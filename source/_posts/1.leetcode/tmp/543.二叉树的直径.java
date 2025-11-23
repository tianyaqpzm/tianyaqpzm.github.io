/*
 * @lc app=leetcode.cn id=543 lang=java
 *
 * [543] 二叉树的直径
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution543 {
    public int diameterOfBinaryTree(TreeNode root) {

        int[] maxDiameter = { 0 };
        depthDiameter(root, maxDiameter);
        return maxDiameter[0];
    }

    private int depthDiameter(TreeNode root, int[] maxDiameter) {
        if (root == null) {
            return 0;
        }
        int left = depthDiameter(root.left, maxDiameter);
        int right = depthDiameter(root.right, maxDiameter);
        // 根据计算直径的特点， 将两边相加即可，但最大直径可能在过程中，所以要在过程中收集
        maxDiameter[0] = Math.max(maxDiameter[0], left + right);
        // 此处左右节点 应选择当前深度最大的，这样才能更大
        return Math.max(left, right) + 1;
    }
}
// @lc code=end
