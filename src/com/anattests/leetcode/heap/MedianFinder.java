package com.anattests.leetcode.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LeetCode 295. Find Median from Data Stream (Hard)
 *
 * PROBLEM
 *   Design a class with addNum(int) and findMedian() over a stream of numbers.
 *   add 1, add 2 -> median 1.5;  add 3 -> median 2.0
 *
 * HOW TO RECOGNIZE IT
 *   "Top k / smallest k / k-th / median over changing data" -> HEAP (PriorityQueue).
 *
 * IDEA
 *   Keep the smaller half in a MAX-heap (lower) and the larger half in a MIN-heap (upper).
 *   Rules: every number in lower <= every number in upper, and lower has the same size
 *   as upper or one more. Then the median is lower's top, or the average of both tops.
 *
 * COMPLEXITY
 *   addNum O(log n), findMedian O(1), space O(n).
 *
 * TALKING POINTS / PITFALLS
 *   - Java's PriorityQueue is a MIN-heap. Max-heap: new PriorityQueue<>(Comparator.reverseOrder()).
 *     Avoid (a, b) -> b - a: it overflows for large values.
 *   - Cast to long before adding the two middles, or the sum can overflow.
 *   - Follow-ups: "all numbers are in 0..100" -> counting array, O(1) add.
 *     "Sliding window median" -> need removal, use two TreeMaps / multisets.
 */
public class MedianFinder {

    private final PriorityQueue<Integer> lower = new PriorityQueue<>(Comparator.reverseOrder());
    private final PriorityQueue<Integer> upper = new PriorityQueue<>();

    public void addNum(int num) {
        // Pass the number through lower so the biggest of the small half moves up.
        lower.offer(num);
        upper.offer(lower.poll());
        // Rebalance so lower is never smaller than upper.
        if (upper.size() > lower.size()) {
            lower.offer(upper.poll());
        }
    }

    public double findMedian() {
        if (lower.size() > upper.size()) {
            return lower.peek();
        }
        return ((long) lower.peek() + upper.peek()) / 2.0;
    }
}
