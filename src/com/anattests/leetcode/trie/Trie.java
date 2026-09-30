package com.anattests.leetcode.trie;

/**
 * LeetCode 208. Implement Trie (Prefix Tree) (Medium)
 *
 * PROBLEM
 *   Implement insert(word), search(word) - exact word stored? - and startsWith(prefix).
 *   Words contain lowercase letters a-z.
 *
 * HOW TO RECOGNIZE IT
 *   Many words + questions about PREFIXES (autocomplete, "does any word start with",
 *   searching a grid for a dictionary of words) -> TRIE.
 *
 * IDEA
 *   Each node has up to 26 children, one per letter, and a flag marking that a word
 *   ends here. A word is a path from the root. Walk the path letter by letter.
 *
 * COMPLEXITY
 *   Each operation O(L) for a word of length L. Space O(total letters inserted * 26).
 *
 * TALKING POINTS / PITFALLS
 *   - search needs isWord: after inserting "apple", "app" is a prefix but not a word.
 *   - Larger alphabet (Unicode) -> Map<Character, Node> children instead of an array.
 *   - Word Search II (212) = Trie + backtracking on the grid; the Trie lets you stop
 *     a path early as soon as no word has that prefix.
 */
public class Trie {

    private static class Node {
        final Node[] children = new Node[26];
        boolean isWord;
    }

    private final Node root = new Node();

    public void insert(String word) {
        Node node = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (node.children[i] == null) {
                node.children[i] = new Node();
            }
            node = node.children[i];
        }
        node.isWord = true;
    }

    public boolean search(String word) {
        Node node = find(word);
        return node != null && node.isWord;
    }

    public boolean startsWith(String prefix) {
        return find(prefix) != null;
    }

    private Node find(String s) {
        Node node = root;
        for (char c : s.toCharArray()) {
            node = node.children[c - 'a'];
            if (node == null) {
                return null;
            }
        }
        return node;
    }
}
