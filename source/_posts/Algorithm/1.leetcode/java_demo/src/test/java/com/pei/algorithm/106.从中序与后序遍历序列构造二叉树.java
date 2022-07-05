import java.util.HashMap;

/*
 * @lc app=leetcode.cn id=106 lang=java
 *
 * [106] 106.从中序与后序遍历序列构造二叉树
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
public class Solution106 {
    // public static void main(String[] args) {
    // new Solution106().buildTree(new int[] { 9, 3, 15, 20, 7 }, new int[] { 9, 15,
    // 7, 20, 3 });
    // }

    HashMap<Integer, Integer> inOrderMap = new HashMap<Integer, Integer>();

    int postIndex = 0;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
            inOrderMap.put(inorder[i], i);
        }
        postIndex = postorder.length - 1;
        TreeNode rootNode = createNode(inorder, postorder, 0, postorder.length - 1);
        return rootNode;
    }

    private TreeNode createNode(int[] inorder, int[] postorder, int left, int right) {
        if (left > right) {
            return null;
        }
        Integer rootIndex = inOrderMap.get(postorder[postIndex]);
        TreeNode rootNode = new TreeNode(postorder[postIndex]);
        postIndex--;
        rootNode.right = createNode(inorder, postorder, rootIndex + 1, right);
        rootNode.left = createNode(inorder, postorder, left, rootIndex - 1);
        return rootNode;
    }

    // class TreeNode {
    // int val;
    // TreeNode left;
    // TreeNode right;

    // TreeNode() {
    // }

    // TreeNode(int val) {
    // this.val = val;
    // }

    // TreeNode(int val, TreeNode left, TreeNode right) {
    // this.val = val;
    // this.left = left;
    // this.right = right;
    // }
    // }
}
// @lc code=end
