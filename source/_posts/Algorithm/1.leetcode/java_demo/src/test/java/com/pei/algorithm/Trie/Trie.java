package com.pei.algorithm.Trie;

import lombok.Data;

@Data
class TrieNode {
    int count;
    int preCount;
    TrieNode[] children = new TrieNode[26];

}

/**
 * Trie
 */
public class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    /**
     * 插入新单词的时候就从根结点出发一个字符一个字符插入，
     * 有对应的字符节点就更新对应的属性，没有就创建一个！
     * 
     * @param word
     * @return
     */
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            TrieNode tmp = node.children[word.charAt(i) - 'a'];
            if (tmp == null) {
                tmp = new TrieNode();
            }
            node = tmp;
            node.preCount++;
        }
        node.count++;
    }

    /**
     * 若中途有个字符没有对应节点 →Trie 不含该单词
     * 若字符串遍历完了，都有对应节点，但最后一个字符对应的节点并不是粉色的，也就不是一个单词 →Trie 不含该单词
     * 
     * @param word
     * @return
     */
    public boolean search(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            TrieNode tmp = node.children[word.charAt(i) - 'a'];
            if (tmp == null) {
                return false;
            }
            node = tmp;
        }
        return node.count > 0;
    }

    public boolean startWith(String prefix) {
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            TrieNode tmp = node.children[prefix.charAt(i) - 'a'];
            if (tmp == null) {
                return false;
            }
            node = tmp;
        }
        return node.preCount > 0;
    }
}
