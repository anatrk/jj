package com.anattests.leetcode.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 3. Longest Substring Without Repeating Characters (Medium)
 *
 * PROBLEM
 *   Length of the longest substring with no repeated character.
 *   "abcabcbb" -> 3 ("abc"),  "pwwkew" -> 3 ("wke"),  "" -> 0
 *
 * HOW TO RECOGNIZE IT
 *   "Longest/shortest CONTIGUOUS substring/subarray such that <condition>" -> sliding
 *   window. The window [left..right] always satisfies the condition.
 *
 * GENERAL TEMPLATE (memorize this - it solves most window problems)
 *   for (right = 0; right < n; right++) {
 *       add s[right] to the window
 *       while (window is invalid) remove s[left++]
 *       update the answer with the window (right - left + 1)
 *   }
 *
 * IDEA
 *   Remember the last index of each character. When s[right] was already seen INSIDE
 *   the window, jump left to just past that earlier copy - the window is valid again.
 *
 * COMPLEXITY
 *   Time O(n), space O(min(n, alphabet size)).
 *
 * TALKING POINTS / PITFALLS
 *   - The "prev >= left" check matters: a character seen before the window started
 *     must not pull left backwards ("abba" -> 2, not 3).
 *   - For ASCII input an int[128] is faster than a HashMap - worth mentioning.
 */
public class LongestSubstringWithoutRepeating {

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> lastSeen = new HashMap<>();
        int best = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            Integer prev = lastSeen.get(c);
            if (prev != null && prev >= left) {
                left = prev + 1;
            }
            lastSeen.put(c, right);
            best = Math.max(best, right - left + 1);
        }
        return best;
    }
}
