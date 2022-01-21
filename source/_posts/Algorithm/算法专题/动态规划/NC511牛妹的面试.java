
import java.util.*;

class SolutionNCE {
    private static SolutionNCE solutionNCE;

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 返回最大山峰序列长度
     * 
     * @param numberList int整型一维数组 给定的序列
     * @return int整型
     */
    public int mountainSequence(int[] numberList) {
        // write code here
        int[] dpUp = new int[numberList.length + 1];
        int[] dpDown = new int[numberList.length + 1];
        Arrays.fill(dpUp, 1);
        Arrays.fill(dpDown, 1);

        // dp[i] 到number[i]到递减子序列的长度
        for (int i = 0; i < numberList.length; i++) {
            for (int j = 0; j < i; j++) {
                if (numberList[i] > numberList[j]) {
                    dpUp[i] = Math.max(dpUp[i], dpUp[j] + 1);
                }
            }
        }
        for (int i = numberList.length - 1; i >= 0; i--) {
            for (int j = numberList.length - 1; j > i; j--) {
                if (numberList[i] > numberList[j]) {
                    dpDown[i] = Math.max(dpDown[i], dpDown[j] + 1);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < numberList.length; i++) {
            res = Math.max(res, dpUp[i] + dpDown[i] - 1);
        }

        return res;
    }

    public static void main(String[] args) {
        SolutionNCE solutionNCE = new SolutionNCE();
        solutionNCE.mountainSequence(new int[] { 1, 2, 3, 6, 1 });
        // solutionNCE.mountainSequence(new int[] { 4833, 1100, 4639, 9648, 2694, 9930,
        // 3972, 2306, 1658, 2376, 5021, 8735,
        // 6914, 9067, 6270, 5829, 6767 });
        System.out.println();
    }
}