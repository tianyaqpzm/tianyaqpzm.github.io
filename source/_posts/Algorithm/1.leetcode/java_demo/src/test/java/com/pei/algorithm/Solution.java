package com.pei.algorithm;

import java.util.PriorityQueue;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(stones.length, (a, b) -> b - a);
        for (int stone : stones) {
            maxHeap.add(stone);
        }
        while (maxHeap.size() >= 2) {
            Integer head1 = maxHeap.poll();
            Integer head2 = maxHeap.poll();
            if (head1.equals(head2)) {
                continue;
            }
            maxHeap.offer(head1 - head2);
        }
        if (maxHeap.isEmpty()) {
            return 0;
        }
        return maxHeap.poll();

    } 

    public static void main(String[] args) {
        int[] stones = new int[] { 2, 7, 4, 1, 8, 1 };
        Solution solution = new Solution();
        int lastStoneWeight = solution.lastStoneWeight(stones);
        System.out.println(lastStoneWeight);
    }


    @Test
    @DisplayName("ReDos攻击")
    public void test_binary_redos() {

    }
}