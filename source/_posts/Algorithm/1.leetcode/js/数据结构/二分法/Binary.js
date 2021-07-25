"use strict";
function binarySearch(nums, target) {
    var left = 0;
    var right = nums.length - 1;
    while (left < right) {
        var mid = Math.floor(left + (right - left) / 2);
        if (nums[mid] === target)
            return mid;
        if (nums[mid] < target) {
            left = mid + 1;
        }
        if (nums[mid] > target) {
            right = mid - 1;
        }
    }
    return -1;
}
