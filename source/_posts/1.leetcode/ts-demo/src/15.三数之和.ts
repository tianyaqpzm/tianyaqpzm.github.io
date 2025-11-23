/*
 * @lc app=leetcode.cn id=15 lang=typescript
 *
 * [15] 三数之和
 */

// @lc code=start
function threeSum(nums: number[]): number[][] {
    const res: number[][] = [];
    nums.sort((a, b) => a - b);
    for (let i = 0; i < nums.length; i++) {


        if (nums[i] > 0) break;
        // 结果集中去重，[-1 -1 2]
        if (i > 0 && nums[i] === nums[i - 1]) {
            continue;
        }
        let left = i + 1;
        let right = nums.length - 1;
        while (left < right) {
            let sum = nums[i] + nums[left] + nums[right];
            if (sum > 0) right--;
            if (sum < 0) left++;
            if (sum == 0) {
                res.push([nums[i], nums[left], nums[right]]);
                // 继续去重，防止出现[-1,  -1, -1, 2, 2] 不然的话会得到重复的[-1,-1,2]结果
                // [-1,0,1,2,-1,-4] -》  -4 -1 -1 0 1 2
                while (left < right && nums[left] === nums[left + 1]) {
                    left++;
                }
                while (left < right && nums[right] === nums[right - 1]) {
                    right--;
                }
                left++; right--;
            }
        }
    }
    return res;

}

/**
 * 回溯法
 * @param nums 
 * @returns 
 */
function threeSum2(nums: number[]): number[][] {
    const res: number[][] = [];
    const used: boolean[] = [];
    const temp: number[] = [];
    // 此场景还需要考虑去重复  这里排序没有匿名函数，结果不对
    nums.sort();
    dfs(nums, res, used, temp);
    return res;
};

function dfs(nums: number[], res: number[][], used: boolean[], temp: number[]) {
    if (temp.length === 3) {
        // 不提供初始值，则第一个元素做初始值
        let sum = temp.reduce((acc, curr) => acc + curr);
        if (sum === 0) {
            // 如果使用回溯法的话 需要做去重
            res.push([...temp]);
        }
    }
    for (let i = 0; i < nums.length; i++) {
        if (temp.length > 3) {
            break;
        }
        // 这个和组合不一样，虽然相等 但是还是要用的，不能直接忽略  || i-1>=0  && nums[i-1 === nums[i]
        if (used[i]) {
            continue;
        }
        used[i] = true;
        temp.push(nums[i]);
        dfs(nums, res, used, temp);
        temp.pop();
        used[i] = false;
    }
}
// @lc code=end

