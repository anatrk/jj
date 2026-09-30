# LeetCode solved examples

One solved problem per pattern. Each file starts with the problem, how to recognize the
pattern, the idea, the complexity, and talking points for the interview. Read the
comment first, try to picture the solution, then read the code.

About 4 minutes each, in this order:

| # | Pattern | Problem | File |
|---|---|---|---|
| 1 | Arrays and hashing | 49 Group Anagrams | `hashing/GroupAnagrams.java` |
| 2 | Two pointers | 15 3Sum | `twopointers/ThreeSum.java` |
| 3 | Sliding window | 3 Longest Substring Without Repeating Characters | `slidingwindow/LongestSubstringWithoutRepeating.java` |
| 4 | Monotonic stack | 739 Daily Temperatures | `stack/DailyTemperatures.java` |
| 5 | Binary search | 33 Search in Rotated Sorted Array | `binarysearch/SearchRotatedSortedArray.java` |
| 6 | Linked list | 143 Reorder List | `linkedlist/ReorderList.java` |
| 7 | Trees (DFS) | 236 Lowest Common Ancestor | `tree/LowestCommonAncestor.java` |
| 8 | Heap | 295 Find Median from Data Stream | `heap/MedianFinder.java` |
| 9 | Backtracking | 78 Subsets | `backtracking/Subsets.java` |
| 10 | Graphs (topological sort) | 207 Course Schedule | `graph/CourseSchedule.java` |
| 11 | Dynamic programming | 322 Coin Change | `dp/CoinChange.java` |
| 12 | Intervals | 56 Merge Intervals | `intervals/MergeIntervals.java` |
| 13 | Trie | 208 Implement Trie | `trie/Trie.java` |
| 14 | Design | 146 LRU Cache | `design/LRUCache.java` |

Tests: `test/com/anattests/leetcode/SolvedExamplesTest.java` runs each solution on the
examples from its comment.

## Interview routine (45 minutes)

1. Repeat the problem and ask about input size, edge cases, duplicates, negatives (3 min).
2. Say the brute-force solution and its complexity (2 min).
3. Name the pattern and the better approach before coding (5 min).
4. Code it, talking as you go (20 min).
5. Walk through one example by hand, then edge cases: empty, one element, all equal (5 min).
6. State the final time and space complexity; mention follow-ups (5 min).
