package com.anattests.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 78. Subsets (Medium)
 *
 * PROBLEM
 *   Return all subsets (the power set) of an array of distinct ints.
 *   [1,2,3] -> [[],[1],[1,2],[1,2,3],[1,3],[2],[2,3],[3]]
 *
 * HOW TO RECOGNIZE IT
 *   "Return ALL combinations / permutations / subsets / placements" -> BACKTRACKING.
 *   Output size is exponential, so exponential time is expected and fine.
 *
 * TEMPLATE (same shape for Combination Sum, Permutations, N-Queens, Word Search)
 *   backtrack(state):
 *       if state is a solution: record a COPY
 *       for each choice:
 *           choose      (add to state)
 *           backtrack   (explore)
 *           un-choose   (remove from state)
 *
 * IDEA
 *   Every node in the recursion tree is a valid subset, so record it on entry. Only look
 *   at elements after "start" so each subset is built once, in index order.
 *
 * COMPLEXITY
 *   Time O(n * 2^n) - 2^n subsets, O(n) to copy each. Space O(n) recursion + output.
 *
 * TALKING POINTS / PITFALLS
 *   - result.add(current) without copying is the classic bug: every entry ends up being
 *     the same (finally empty) list.
 *   - Permutations: loop from 0 and skip used elements (boolean[] used), instead of "start".
 *   - With duplicates (Subsets II): sort first, skip nums[i] == nums[i-1] when i > start.
 */
public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, int start, List<Integer> current, List<List<Integer>> result) {
        result.add(new ArrayList<>(current));
        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);
            backtrack(nums, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
}
