






**算法的复杂度瓶颈在字符串查找，并且字符串有很多公共前缀，就可以用前缀树优化**。



前缀树就是一个树。前缀树一般是将一系列的单词记录到树上， 如果这些单词没有公共前缀，则和直接用数组存没有任何区别。而如果有公共前缀， 则公共前缀仅会被存储一次。



https://leetcode-solution-leetcode-pp.gitbook.io/leetcode-solution/thinkings/trie



com.pei.algorithm.Trie.Trie