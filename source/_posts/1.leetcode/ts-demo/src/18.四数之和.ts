/*
 * @lc app=leetcode.cn id=18 lang=typescript
 *
 * [18] 四数之和
 */

// @lc code=start
function fourSum(nums: number[], target: number): number[][] {
    const res: number[][] = [];
    nums.sort((a, b) => a - b);
    for (let index = 0; index < nums.length; index++) {
        // 首位去除重复元素
        if (index > 0 && nums[index] === nums[index - 1]) {
            continue;
        }
        for (let j = index + 1; j < nums.length; j++) {
            // 防止超过起始位置
            if ((j - index) > 1 && nums[j] === nums[j - 1]) {
                continue;
            }
            let left = j + 1;
            let right = nums.length - 1;
            while (left < right) {
                const sum = nums[index] + nums[j] + nums[left] + nums[right];
                if (sum > target) {
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    res.push([nums[index], nums[j], nums[left], nums[right]]);
                    while (left <= right && nums[left] === nums[left + 1]) {
                        left++;
                    }
                    while (left <= right && nums[right - 1] === nums[right]) {
                        right--;
                    }
                    left++;
                    right--;
                }
            }

        }

    }
    return res;
};
// @lc code=end

