

# 单调栈




## [42.接雨水](./42.接雨水.java)

### 思路

- 双指针法：如果按照列来计算的话，宽度一定是1了，我们再把每一列的雨水的高度求出来就可以了。
- 动态规划：优化双指针中 左右高度的重复计算，递推公式：即从左向右遍历：
  $$maxLeft[i] = max(height[i], maxLeft[i - 1]);$$
- 单调栈:按照行方向来计算雨水，寻找凹槽(单调递增栈)


### 关键点

$$
  negative[i]=\left\{
  \begin{aligned}
  negative[i-1] + 1 &  & negative[i-1] > 0 \\
  0 & & negative[i-1] = 0 \\
  \end{aligned}
  \right.
$$
    

