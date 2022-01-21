
import java.util.*;

public class NCE509牛能和牛可乐的礼物 {
    private static NCE509牛能和牛可乐的礼物 nce509牛能和牛可乐的礼物;

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 贪心的做法： 9/13 组用例通过 运行时间 18ms 占用内存 9916KB </br>
     * 失败的用例： [41, 467, 334, 0, 169, 224, 478, 358, 462, 464, 205]
     * 
     * @param presentVec int整型一维数组 每个礼物的价值
     * @return int整型
     */
    public int maxPresent2(int[] presentVec) {
        // write code here
        Arrays.sort(presentVec);
        int firstSum = 0;
        int secondSum = 0;
        for (int i = presentVec.length - 1; i >= 0; i--) {
            if (firstSum > secondSum) {
                secondSum += presentVec[i];
                continue;
            }
            firstSum += presentVec[i];
        }
        return firstSum - secondSum;
    }

    public int maxPresent(int[] presentVec) {

        int sum = 0;
        for (int i = 0; i < presentVec.length; i++) {
            sum += presentVec[i];
        }
        int len = sum >> 1;
        int[] dp = new int[len + 1];
        for (int j = 0; j < presentVec.length; j++) {
            for (int i = len; i >= 1; i--) {
                if (i >= presentVec[j]) {
                    dp[i] = Math.max(dp[i - presentVec[j]] + presentVec[j], dp[i]);
                }
            }
        }
        return sum - 2 * dp[len];
    }

    public static void main(String[] args) {
        // int[] presentVec = new int[] { 41, 467, 334, 0, 169, 224, 478, 358, 462, 464,
        // 205 };
        int[] presentVec = new int[] { 1, 3, 5 };
        new NCE509牛能和牛可乐的礼物().maxPresent(presentVec);
        System.out.println();
    }

}