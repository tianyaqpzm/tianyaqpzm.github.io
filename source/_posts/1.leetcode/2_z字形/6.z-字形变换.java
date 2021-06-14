import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=6 lang=java
 *
 * [6] Z 字形变换
 */

// @lc code=start
class Solution {
    public String convert(String s, int numRows) {
        if(s==null || s.length()==0 || numRows<=1){
            return s;
        }
        StringBuilder[] sb = new StringBuilder[numRows];
       for(int i=0;i<numRows;i++){
         sb[i]=  new StringBuilder();
       }
       int dir =1;
       int index = 0;
        for(char c:s.toCharArray()){
            sb[index].append(c);
            index += dir;
            if(index ==0 ||index== numRows-1){
                dir =-dir;
            }
        }
        StringBuilder result = new StringBuilder();
        for(int i=0;i<sb.length;i++){
            result.append(sb[i]);
        }
        return result.toString();
    }
}
// @lc code=end

