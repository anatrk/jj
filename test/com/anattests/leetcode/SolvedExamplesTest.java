package com.anattests.leetcode;

import com.anattests.leetcode.backtracking.Subsets;
import com.anattests.leetcode.binarysearch.SearchRotatedSortedArray;
import com.anattests.leetcode.design.LRUCache;
import com.anattests.leetcode.dp.CoinChange;
import com.anattests.leetcode.graph.CourseSchedule;
import com.anattests.leetcode.hashing.GroupAnagrams;
import com.anattests.leetcode.heap.MedianFinder;
import com.anattests.leetcode.intervals.MergeIntervals;
import com.anattests.leetcode.linkedlist.ReorderList;
import com.anattests.leetcode.linkedlist.ReorderList.ListNode;
import com.anattests.leetcode.slidingwindow.LongestSubstringWithoutRepeating;
import com.anattests.leetcode.stack.DailyTemperatures;
import com.anattests.leetcode.tree.LowestCommonAncestor;
import com.anattests.leetcode.tree.LowestCommonAncestor.TreeNode;
import com.anattests.leetcode.trie.Trie;
import com.anattests.leetcode.twopointers.ThreeSum;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SolvedExamplesTest {

    @Test
    void groupAnagrams() {
        List<List<String>> groups = new GroupAnagrams().groupAnagrams(
                new String[] {"eat", "tea", "tan", "ate", "nat", "bat"});

        Set<Set<String>> actual = new HashSet<>();
        for (List<String> group : groups) {
            actual.add(new HashSet<>(group));
        }
        assertEquals(Set.of(Set.of("eat", "tea", "ate"), Set.of("tan", "nat"), Set.of("bat")), actual);
    }

    @Test
    void threeSum() {
        assertEquals(List.of(List.of(-1, -1, 2), List.of(-1, 0, 1)),
                new ThreeSum().threeSum(new int[] {-1, 0, 1, 2, -1, -4}));
        assertEquals(List.of(List.of(0, 0, 0)), new ThreeSum().threeSum(new int[] {0, 0, 0, 0}));
        assertEquals(List.of(), new ThreeSum().threeSum(new int[] {0, 1, 1}));
    }

    @Test
    void longestSubstringWithoutRepeating() {
        LongestSubstringWithoutRepeating solution = new LongestSubstringWithoutRepeating();
        assertEquals(3, solution.lengthOfLongestSubstring("abcabcbb"));
        assertEquals(1, solution.lengthOfLongestSubstring("bbbbb"));
        assertEquals(3, solution.lengthOfLongestSubstring("pwwkew"));
        assertEquals(2, solution.lengthOfLongestSubstring("abba"));
        assertEquals(0, solution.lengthOfLongestSubstring(""));
    }

    @Test
    void dailyTemperatures() {
        assertArrayEquals(new int[] {1, 1, 4, 2, 1, 1, 0, 0},
                new DailyTemperatures().dailyTemperatures(new int[] {73, 74, 75, 71, 69, 72, 76, 73}));
    }

    @Test
    void searchRotatedSortedArray() {
        SearchRotatedSortedArray solution = new SearchRotatedSortedArray();
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        for (int i = 0; i < nums.length; i++) {
            assertEquals(i, solution.search(nums, nums[i]));
        }
        assertEquals(-1, solution.search(nums, 3));
        assertEquals(-1, solution.search(new int[] {1}, 0));
        assertEquals(1, solution.search(new int[] {3, 1}, 1));
    }

    @Test
    void reorderList() {
        assertEquals(List.of(1, 4, 2, 3), reorder(1, 2, 3, 4));
        assertEquals(List.of(1, 5, 2, 4, 3), reorder(1, 2, 3, 4, 5));
        assertEquals(List.of(1), reorder(1));
    }

    private List<Integer> reorder(int... values) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int value : values) {
            tail.next = new ListNode(value);
            tail = tail.next;
        }
        new ReorderList().reorderList(dummy.next);

        List<Integer> result = new ArrayList<>();
        for (ListNode node = dummy.next; node != null; node = node.next) {
            result.add(node.val);
        }
        return result;
    }

    @Test
    void lowestCommonAncestor() {
        //        3
        //      /   \
        //     5     1
        //    / \   / \
        //   6   2 0   8
        //      / \
        //     7   4
        TreeNode root = new TreeNode(3);
        TreeNode n5 = root.left = new TreeNode(5);
        TreeNode n1 = root.right = new TreeNode(1);
        n5.left = new TreeNode(6);
        TreeNode n2 = n5.right = new TreeNode(2);
        n2.left = new TreeNode(7);
        TreeNode n4 = n2.right = new TreeNode(4);
        n1.left = new TreeNode(0);
        n1.right = new TreeNode(8);

        LowestCommonAncestor solution = new LowestCommonAncestor();
        assertSame(root, solution.lowestCommonAncestor(root, n5, n1));
        assertSame(n5, solution.lowestCommonAncestor(root, n5, n4));
    }

    @Test
    void medianFinder() {
        MedianFinder finder = new MedianFinder();
        finder.addNum(1);
        finder.addNum(2);
        assertEquals(1.5, finder.findMedian());
        finder.addNum(3);
        assertEquals(2.0, finder.findMedian());

        MedianFinder large = new MedianFinder();
        large.addNum(Integer.MAX_VALUE);
        large.addNum(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, large.findMedian());
    }

    @Test
    void subsets() {
        assertEquals(
                List.of(List.of(), List.of(1), List.of(1, 2), List.of(1, 2, 3),
                        List.of(1, 3), List.of(2), List.of(2, 3), List.of(3)),
                new Subsets().subsets(new int[] {1, 2, 3}));
    }

    @Test
    void courseSchedule() {
        CourseSchedule solution = new CourseSchedule();
        assertTrue(solution.canFinish(2, new int[][] {{1, 0}}));
        assertFalse(solution.canFinish(2, new int[][] {{1, 0}, {0, 1}}));
        assertTrue(solution.canFinish(4, new int[][] {{1, 0}, {2, 0}, {3, 1}, {3, 2}}));
    }

    @Test
    void coinChange() {
        CoinChange solution = new CoinChange();
        assertEquals(3, solution.coinChange(new int[] {1, 2, 5}, 11));
        assertEquals(-1, solution.coinChange(new int[] {2}, 3));
        assertEquals(0, solution.coinChange(new int[] {1}, 0));
        assertEquals(2, solution.coinChange(new int[] {1, 3, 4}, 6));
    }

    @Test
    void mergeIntervals() {
        MergeIntervals solution = new MergeIntervals();
        assertArrayEquals(new int[][] {{1, 6}, {8, 10}, {15, 18}},
                solution.merge(new int[][] {{1, 3}, {2, 6}, {8, 10}, {15, 18}}));
        assertArrayEquals(new int[][] {{1, 5}}, solution.merge(new int[][] {{1, 4}, {4, 5}}));
        assertArrayEquals(new int[][] {{1, 10}}, solution.merge(new int[][] {{2, 3}, {1, 10}}));
    }

    @Test
    void trie() {
        Trie trie = new Trie();
        trie.insert("apple");
        assertTrue(trie.search("apple"));
        assertFalse(trie.search("app"));
        assertTrue(trie.startsWith("app"));
        trie.insert("app");
        assertTrue(trie.search("app"));
        assertFalse(trie.startsWith("b"));
    }

    @Test
    void lruCache() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        assertEquals(1, cache.get(1));  // 1 is now most recent
        cache.put(3, 3);                // evicts 2
        assertEquals(-1, cache.get(2));
        cache.put(4, 4);                // evicts 1
        assertEquals(-1, cache.get(1));
        assertEquals(3, cache.get(3));
        assertEquals(4, cache.get(4));
        cache.put(4, 40);               // update keeps size
        assertEquals(40, cache.get(4));
    }
}
