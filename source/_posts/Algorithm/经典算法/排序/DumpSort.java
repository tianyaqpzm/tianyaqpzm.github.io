
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
            if (index + 1 < len) {
                if (list[index] < list[index + 1]) {
                    index = index + 1;
                }
            }
            if (list[index] > temp) {
                list[k] = list[index];
                k = index;
                index = 2 * k + 1;
            } else {
                break;
            }
        }
        list[k] = temp;
    }

}
