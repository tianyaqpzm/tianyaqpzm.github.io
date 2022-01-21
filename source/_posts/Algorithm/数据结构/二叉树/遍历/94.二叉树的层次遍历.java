import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/*
 * @lc app=leetcode.cn id=94 lang=java
 *
 * [94] 二叉树的中序遍历
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

// @lc code=start
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode() {} TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left
 * = left; this.right = right; } }
 */
class Solution {
    /**
     * 前序遍历：根节点->左子树->右子树（根->左->右）
     * 
     * 中序遍历：左子树->根节点->右子树（左->根->右） inorderTraversal
     * 
     * 后序遍历：左子树->右子树->根节点（左->右->根）
     * 
     * @param root
     * @return
     */

    public void levelOrderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val + "->");

            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    public static void main(String[] args) {

    }

    List<Integer> res = new LinkedList<>();

    /**
     * 因为返回的是List, 拼接数组浪费，定义为全局list
     * 
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        inorder(root);
        return res;
    }

    /**
     * 前序遍历，先左，输出中间，<br>
     * 后右 自顶向下，右下向上的输出
     * 
     * @param root
     */
    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        res.add(root.val);
        inorder(root.right);
    }

}
// @lc code=end
