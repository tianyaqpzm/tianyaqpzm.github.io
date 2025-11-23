/**
 * 详细计算 testDiameterNotThroughRoot 测试用例的直径
 */
public class DiameterCalculation {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
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
        System.out.println("🔍 详细分析 testDiameterNotThroughRoot 测试用例\n");

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
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        root.left.left.left = new TreeNode(6);
        root.right.right.right = new TreeNode(7);
        root.left.left.left.left = new TreeNode(8);
        root.right.right.right.right = new TreeNode(9);

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
        System.out.println();

        // 手动计算每个节点的直径贡献
        System.out.println("📊 手动计算每个节点的直径贡献：");

        // 节点1：左子树深度4 + 右子树深度4 = 8
        System.out.println("节点1: 左子树深度4 + 右子树深度4 = 8");

        // 节点2：左子树深度3 + 右子树深度0 = 3
        System.out.println("节点2: 左子树深度3 + 右子树深度0 = 3");

        // 节点3：左子树深度0 + 右子树深度3 = 3
        System.out.println("节点3: 左子树深度0 + 右子树深度3 = 3");

        // 节点4：左子树深度2 + 右子树深度0 = 2
        System.out.println("节点4: 左子树深度2 + 右子树深度0 = 2");

        // 节点5：左子树深度0 + 右子树深度2 = 2
        System.out.println("节点5: 左子树深度0 + 右子树深度2 = 2");

        // 节点6：左子树深度1 + 右子树深度0 = 1
        System.out.println("节点6: 左子树深度1 + 右子树深度0 = 1");

        // 节点7：左子树深度0 + 右子树深度1 = 1
        System.out.println("节点7: 左子树深度0 + 右子树深度1 = 1");

        // 节点8和9：叶子节点，贡献为0
        System.out.println("节点8,9: 叶子节点，贡献为0");

        System.out.println();
        System.out.println("最大直径 = max(8, 3, 3, 2, 2, 1, 1, 0, 0) = 8");

        int result = getDiameter(root);
        System.out.println("程序计算结果: " + result);

        System.out.println();
        System.out.println("✅ 结论：期望值应该是 8，不是 6！");
        System.out.println("路径：8 -> 6 -> 4 -> 2 -> 1 -> 3 -> 5 -> 7 -> 9");
        System.out.println("路径长度：8个节点，直径 = 8 - 1 = 7");
        System.out.println("等等...让我重新理解直径的定义");

        System.out.println();
        System.out.println("🤔 重新理解直径定义：");
        System.out.println("直径 = 任意两个节点间路径长度的最大值");
        System.out.println("路径长度 = 路径上的边数，不是节点数");
        System.out.println("8 -> 6 -> 4 -> 2 -> 1 -> 3 -> 5 -> 7 -> 9");
        System.out.println("这条路径有8个节点，7条边，所以直径 = 7");

        System.out.println();
        System.out.println("但是算法中：");
        System.out.println("左子树深度 + 右子树深度 = 4 + 4 = 8");
        System.out.println("这里的深度是指节点数，不是边数");
        System.out.println("所以算法返回的是节点数，需要减1才是边数");

        System.out.println();
        System.out.println("🔧 修正：");
        System.out.println("如果直径定义为边数，那么期望值应该是 7");
        System.out.println("如果直径定义为节点数，那么期望值应该是 8");
        System.out.println("LeetCode题目中直径定义为边数，所以期望值应该是 7");
    }
}