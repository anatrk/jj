package com.anattests.leetcode.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 15. 3Sum (Medium)
 *
 * PROBLEM
 *   Return all UNIQUE triplets [a, b, c] from nums with a + b + c == 0.
 *   [-1,0,1,2,-1,-4] -> [[-1,-1,2], [-1,0,1]]
 *
 * HOW TO RECOGNIZE IT
 *   Pairs/triplets that hit a target sum, plus "no duplicates" -> SORT, then walk two
 *   pointers inward. Sorting makes both the search and the de-duplication easy.
 *
 * IDEA
 *   Sort. Fix the first number nums[i]. In the rest of the array, left starts right
 *   after i and right starts at the end:
 *     sum too small -> left++ (need a bigger number)
 *     sum too big   -> right-- (need a smaller number)
 *     sum == 0      -> record it, move both, skip repeated values.
 *
 * COMPLEXITY
 *   Time O(n^2), extra space O(1) (ignoring the output and the sort).
 *
 * TALKING POINTS / PITFALLS
 *   - Duplicates are skipped in two places: the fixed number (i) and after a match (left).
 *   - Early exit: once nums[i] > 0, every remaining number is positive, so no sum is 0.
 *   - A brute-force O(n^3) answer + Set for uniqueness is a good first thing to say
 *     out loud, then improve it.
 */
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // same first number would produce the same triplets
            }

            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum < 0) {
                    left++;
                } else if (sum > 0) {
                    right--;
                } else {
                    result.add(List.of(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                }
            }
        }
        return result;
    }
}
