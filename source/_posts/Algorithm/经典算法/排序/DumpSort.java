
/**
 * https://www.cnblogs.com/chengxiao/p/6129630.html
 */
public class DumpSort {

    public static void main(String[] args) {
        int[] nums = { 16, 7, 3, 20, 17, 8 };
        headSort(nums);
        for (int num : nums) {
            System.out.println(num + " ");
        }
    }

    public static void headSort(int[] list) {
        for (int i = list.length / 2 - 1; i >= 0; i--) {
            headAdjust(list, list.length, i);
        }

        for (int i = list.length - 1; i >= 1; i--) {
            int temp = list[0];
            list[0] = list[i];
            list[i] = temp;
            headAdjust(list, i, 0);
        }
    }

    private static void headAdjust(int[] list, int len, int i) {
        int k = i, temp = list[i], index = 2 * k + 1;
        while (index < len) {
            // 左右子节点，取较大值
            if (index + 1 < len) {
                if (list[index] < list[index + 1]) {
                    index = index + 1;
                }
            }

            if (list[index] > temp) {
                // 子节点大于 目标值，替换后，检查子节点是否符合大顶堆
                list[k] = list[index];
                k = index;
                index = 2 * k + 1;
            } else {
                // 退出
                break;
            }
        }
        list[k] = temp;
    }

}
