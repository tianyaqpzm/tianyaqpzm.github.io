public class Binary {
    public int binarySearch(final int[] nums, final int target) {
        // 左右都闭合都区间
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            final int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                left = mid + 1;
            }
            if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static void main(final String[] args) {
        final int[] nums = new int[] { 1, 2, 3, 4, 5, 6, 6 };
        Binary binary = new Binary();
        int res = binary.binarySearch(nums, 4);
        System.out.println(res);

    }

}