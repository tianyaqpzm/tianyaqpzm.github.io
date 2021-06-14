"use strict";
function twoSum(nums, target) {
    var map = new Map();
    for (var i = 0; i < nums.length; i++) {
        var diff = target - nums[i];
        if (map.has(diff)) {
            return [map.get(diff), i];
        }
    }
}
