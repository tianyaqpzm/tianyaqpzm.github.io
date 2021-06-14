import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        while (root != null) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            stack.pop();
            res.add(root.val);
            System.out.println(root.val);
            root = root.right;
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
