package com.anattests.leetcode.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 56. Merge Intervals (Medium)
 *
 * PROBLEM
 *   Merge all overlapping intervals.
 *   [[1,3],[2,6],[8,10],[15,18]] -> [[1,6],[8,10],[15,18]]
 *   [[1,4],[4,5]]                -> [[1,5]]   (touching counts as overlapping)
 *
 * HOW TO RECOGNIZE IT
 *   Anything with [start, end] pairs -> SORT BY START first. After sorting, an interval
 *   can only overlap the one just before it (or the merged block it belongs to).
 *
 * IDEA
 *   Sort by start. Walk through: if the interval starts before the last merged one ends,
 *   extend that one's end; otherwise start a new merged interval.
 *
 * COMPLEXITY
 *   Time O(n log n) for the sort, space O(n) for the output.
 *
 * TALKING POINTS / PITFALLS
 *   - Use max for the new end: [1,10] then [2,3] must stay [1,10], not become [1,3].
 *   - Integer.compare instead of a[0] - b[0]: subtraction overflows for extreme values.
 *   - Copy intervals instead of changing the caller's arrays - good habit to mention.
 *   - Meeting Rooms II (253, "how many rooms"): sort starts and ends separately, or use
 *     a min-heap of end times.
 */
public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        int[][] sorted = intervals.clone();
        Arrays.sort(sorted, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();
        for (int[] interval : sorted) {
            if (!merged.isEmpty() && interval[0] <= merged.getLast()[1]) {
                int[] last = merged.getLast();
                last[1] = Math.max(last[1], interval[1]);
            } else {
                merged.add(new int[] {interval[0], interval[1]});
            }
        }
        return merged.toArray(new int[0][]);
    }
}
