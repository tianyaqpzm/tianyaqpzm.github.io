/**
 * 深度 vs 直径 对比示例
 * 通过具体例子说明两者的区别
 */
public class DepthVsDiameterExample {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 计算树的深度
    public static int getDepth(TreeNode root) {
        if (root == null)
            return 0;
        return Math.max(getDepth(root.left), getDepth(root.right)) + 1;
    }

    // 计算树的直径
    public static int getDiameter(TreeNode root) {
        int[] maxDiameter = { 0 };
        depthForDiameter(root, maxDiameter);
        return maxDiameter[0];
    }

    private static int depthForDiameter(TreeNode root, int[] maxDiameter) {
        if (root == null)
            return 0;

        int leftDepth = depthForDiameter(root.left, maxDiameter);
        int rightDepth = depthForDiameter(root.right, maxDiameter);

        // 更新最大直径：左子树深度 + 右子树深度
        maxDiameter[0] = Math.max(maxDiameter[0], leftDepth + rightDepth);

        // 返回当前节点的深度
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public static void main(String[] args) {
        System.out.println("🌳 深度 vs 直径 对比示例\n");

        // 示例1：简单情况
        System.out.println("📊 示例1：简单二叉树");
        /*
         * 1
         * / \
         * 2 3
         * / \
         * 4 5
         */
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);

        int depth1 = getDepth(root1);
        int diameter1 = getDiameter(root1);

        System.out.println("树结构：");
        System.out.println("      1");
        System.out.println("     / \\");
        System.out.println("    2   3");
        System.out.println("   / \\");
        System.out.println("  4   5");
        System.out.println("深度: " + depth1 + " (从根到最远叶子: 1->2->4 或 1->2->5)");
        System.out.println("直径: " + diameter1 + " (最长路径: 4->2->1->3 或 5->2->1->3)");
        System.out.println();

        // 示例2：左偏树
        System.out.println("📊 示例2：左偏树");
        /*
         * 1
         * /
         * 2
         * /
         * 3
         * /
         * 4
         */
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.left.left = new TreeNode(3);
        root2.left.left.left = new TreeNode(4);

        int depth2 = getDepth(root2);
        int diameter2 = getDiameter(root2);

        System.out.println("树结构：");
        System.out.println("  1");
        System.out.println(" /");
        System.out.println("2");
        System.out.println("/");
        System.out.println("3");
        System.out.println("/");
        System.out.println("4");
        System.out.println("深度: " + depth2 + " (从根到最远叶子: 1->2->3->4)");
        System.out.println("直径: " + diameter2 + " (最长路径: 4->3->2->1)");
        System.out.println();

        // 示例3：直径不经过根节点
        System.out.println("📊 示例3：直径不经过根节点");
        /*
         * 1
         * / \
         * 2 3
         * / \
         * 4 5
         * / \
         * 6 7
         * / \
         * 8 9
         */
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        root3.right = new TreeNode(3);
        root3.left.left = new TreeNode(4);
        root3.right.right = new TreeNode(5);
        root3.left.left.left = new TreeNode(6);
        root3.right.right.right = new TreeNode(7);
        root3.left.left.left.left = new TreeNode(8);
        root3.right.right.right.right = new TreeNode(9);

        int depth3 = getDepth(root3);
        int diameter3 = getDiameter(root3);

        System.out.println("树结构：");
        System.out.println("      1");
        System.out.println("     / \\");
        System.out.println("    2   3");
        System.out.println("   /     \\");
        System.out.println("  4       5");
        System.out.println(" /         \\");
        System.out.println("6           7");
        System.out.println("/             \\");
        System.out.println("8               9");
        System.out.println("深度: " + depth3 + " (从根到最远叶子: 1->2->4->6->8 或 1->3->5->7->9)");
        System.out.println("直径: " + diameter3 + " (最长路径: 8->6->4->2->1->3->5->7->9)");
        System.out.println();

        // 总结
        System.out.println("🔍 关键区别总结：");
        System.out.println("1. 深度：总是从根节点开始计算到最远叶子节点");
        System.out.println("2. 直径：任意两个节点间的最长路径，不一定经过根节点");
        System.out.println("3. 深度 ≤ 直径：直径可能等于深度，也可能大于深度");
        System.out.println("4. 当树是链状时，深度 = 直径");
        System.out.println("5. 当树有分支时，直径可能大于深度");
    }
}