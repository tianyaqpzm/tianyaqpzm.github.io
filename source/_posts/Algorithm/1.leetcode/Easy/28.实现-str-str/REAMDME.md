1、  暴力匹配，
利用双指针，替代 双层循环，见 python 解法

2、KMP
求 next 数组

abab

a b a b
0 0 1 2 最长相同前缀后缀
-1 0 0 1 未优化
-1 0 -1 0 优化
优化理由：因为不能出现 p[j] = p[ next[j ]]，所以当出现时需要继续递归，
例如在求模式串“abab”的第 2 个 a 的 next 值时，
如果是未优化的 next 值的话，第 2 个 a 对应的 next 值为 0，相当于第 2 个 a 失配时，下一步匹配模式串会用 p[0]处的 a 再次跟文本串匹配，必然失配。所以求第 2 个 a 的 next 值时，需要再次递归：next[2] = next[ next[2] ] = next[0] = -1

3、 go ：
strings.Index(haystack,needle)
needle == haystack[i: i + len(needle)]
