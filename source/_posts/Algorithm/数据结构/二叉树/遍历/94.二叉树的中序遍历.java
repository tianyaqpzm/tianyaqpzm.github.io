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
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        while (root != null) {
            // 先遍历完左侧的树
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            // 取出最后一颗二叉树， 刚刚变量添加进去了，现在要对其进行处理
            stack.pop();
            res.add(root.val);
            System.out.println(root.val);
            root = root.right;
        }

    }

    /**
     * 与 inorderTraversal 相同
     * 
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal3(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        while (root != null || !stack.empty()) {
            // 先遍历完左侧的树
            if (root != null) {
                stack.add(root);
                root = root.left;
            } else {
                // 取出最后一颗二叉树， 刚刚变量添加进去了，现在要对其进行处理
                stack.pop();
                res.add(root.val);
                System.out.println(root.val);
                root = root.right;
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
