/*
 * @lc app=leetcode.cn id=46 lang=javascript
 *
 * [46] 全排列
 */

// @lc code=start
/** 不含重复元素
 * @param {number[]} nums
 * @return {number[][]}
 */
var permute = function(nums) {
    const list=[];
    backtrack(list, [],nums, []) ;
    return list;
};

function backtrack2(list, templist, nums) {
    if(templist.length === nums.length) {
        return list.push([...templist]);
    }
    for(let i =0;i<nums.length;i++){
        if(templist.includes(nums[i]))
            continue;
        templist.push(nums[i]);
        backtrack(list, templist,nums);
        templist.pop();
    }
}

/**
 * 使用used[]标记法
 * @param {*} list 
 * @param {*} templist 
 * @param {*} nums 
 * @returns 
 */
function backtrack(list, templist, nums, used) {
    if(templist.length === nums.length) {
        return list.push([...templist]);
    }
    for(let i =0;i<nums.length;i++){
        if(used[i] === true){
            continue;
        }
        used[i] = true;
        templist.push(nums[i]);
        backtrack(list, templist,nums,used);
        templist.pop();
        used[i] = false;
    }
}
// @lc code=end

