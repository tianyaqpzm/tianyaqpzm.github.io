/*
 * @lc app=leetcode.cn id=17 lang=java
 *
 * [17] 电话号码的字母组合
 */

// @lc code=start

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.isEmpty()) {
            return new ArrayList<>();
        }
        Map<Integer, String> map = new HashMap();
        // 可以使用数组 前面补齐0
        map.put(0, "");
        map.put(1, "");

        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");

        List<String> res = new ArrayList<String>();
        StringBuilder sbTemp = new StringBuilder();
        backTrack(map, digits, res, sbTemp, 0);
        return res;
    }

    private void backTrack(Map<Integer, String> map, String digits, List<String> res, StringBuilder temp, int start) {
        if (start == digits.length()) {
            res.add(temp.toString());
            return;
        }
        // 没有外层循环，否则假设“23” 遍历2后继续以3为起点了
        // for (int i = start; i < digits.length(); i++) {
        String str = map.get(Integer.valueOf(digits.charAt(start)) - 48);
        for (int j = 0; j < str.length(); j++) {
            temp.append(str.charAt(j));
            backTrack(map, digits, res, temp, start + 1);
            temp.deleteCharAt(temp.length() - 1);
        }
        // }

    }

}
// @lc code=end
