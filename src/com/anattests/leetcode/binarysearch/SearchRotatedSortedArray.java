package com.anattests.leetcode.binarysearch;

/**
 * LeetCode 33. Search in Rotated Sorted Array (Medium)
 *
 * PROBLEM
 *   A sorted array of distinct ints was rotated at an unknown pivot. Return the index of
 *   target, or -1, in O(log n).
 *   nums = [4,5,6,7,0,1,2], target = 0 -> 4
 *
 * HOW TO RECOGNIZE IT
 *   "Sorted" (even partly) + "O(log n)" -> binary search. The question is always: which
 *   half can I safely throw away?
 *
 * IDEA
 *   Cut at mid. At least one half, [lo..mid] or [mid..hi], is still normally sorted.
 *   Find that half. If target lies within its range, search there, otherwise search the
 *   other half.
 *
 * COMPLEXITY
 *   Time O(log n), space O(1).
 *
 * TALKING POINTS / PITFALLS
 *   - mid = lo + (hi - lo) / 2 avoids int overflow of (lo + hi).
 *   - "nums[lo] <= nums[mid]" needs <= : when lo == mid the left half is one element.
 *   - Loop condition lo <= hi (inclusive bounds) with hi = mid - 1 / lo = mid + 1.
 *     Pick one bound style and stay consistent - mixing styles causes infinite loops.
 */
public class SearchRotatedSortedArray {

    public int search(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[lo] <= nums[mid]) { // left half is sorted
                if (nums[lo] <= target && target < nums[mid]) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            } else { // right half is sorted
                if (nums[mid] < target && target <= nums[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }
        return -1;
    }
}
