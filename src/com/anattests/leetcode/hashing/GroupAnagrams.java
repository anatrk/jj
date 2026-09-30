package com.anattests.leetcode.hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 49. Group Anagrams (Medium)
 *
 * PROBLEM
 *   Given an array of strings, group the anagrams together (any order).
 *   ["eat","tea","tan","ate","nat","bat"] -> [["eat","tea","ate"],["tan","nat"],["bat"]]
 *
 * HOW TO RECOGNIZE IT
 *   "Group items that are equivalent in some way" -> compute a canonical KEY for each
 *   item and bucket items by that key in a HashMap.
 *
 * IDEA
 *   Anagrams have identical letter counts. Turn the 26 counts into a string key
 *   ("1#0#0#...#") and collect words with the same key into the same list.
 *
 * COMPLEXITY
 *   Time O(n * k), space O(n * k)   (n = number of words, k = max word length)
 *
 * TALKING POINTS / PITFALLS
 *   - Simpler key: sort the characters of each word -> O(n * k log k). Fine to start
 *     with it, then offer the counting key as the optimization.
 *   - The '#' separator is required: counts [1, 11] and [11, 1] would both give "111".
 *   - computeIfAbsent replaces the "if (!map.containsKey(k)) map.put(k, new ...)" dance.
 */
public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();
        for (String s : strs) {
            groups.computeIfAbsent(key(s), k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(groups.values());
    }

    private String key(String s) {
        int[] counts = new int[26];
        for (char c : s.toCharArray()) {
            counts[c - 'a']++;
        }
        StringBuilder key = new StringBuilder();
        for (int count : counts) {
            key.append(count).append('#');
        }
        return key.toString();
    }
}
