package com.anattests.leetcode.design;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 146. LRU Cache (Medium) - probably the most asked design question
 *
 * PROBLEM
 *   A cache with a fixed capacity. get(key) returns the value or -1. put(key, value)
 *   inserts or updates; when full, evict the Least Recently Used key.
 *   Both operations must be O(1).
 *
 * HOW TO RECOGNIZE IT
 *   "Design a data structure where every operation is O(1)" -> COMBINE structures: each
 *   one covers a weakness of the other.
 *     HashMap            -> find a key in O(1), but has no order
 *     Doubly linked list -> keeps usage order, move/remove a node in O(1), but slow lookup
 *   The map stores key -> list node, so you get both.
 *
 * IDEA
 *   The list goes from most recent (right after head) to least recent (right before
 *   tail). Any access moves the node to the front. Eviction removes the node before tail.
 *
 * COMPLEXITY
 *   get and put O(1), space O(capacity).
 *
 * TALKING POINTS / PITFALLS (this is where senior candidates stand out)
 *   - Sentinel head/tail nodes remove all null checks from insert and remove.
 *   - Nodes store the KEY too, so after evicting a node you can remove it from the map.
 *   - In real code: new LinkedHashMap<>(cap, 0.75f, true) + override removeEldestEntry.
 *     Mention it, but interviewers want the version below.
 *   - Follow-ups: thread safety (one lock is simple, lock striping or ConcurrentHashMap +
 *     approximate LRU scales better), TTL expiry, LFU (LeetCode 460).
 */
public class LRUCache {

    private static class Node {
        final int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> nodes = new HashMap<>();
    private final Node head = new Node(0, 0);
    private final Node tail = new Node(0, 0);

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = nodes.get(key);
        if (node == null) {
            return -1;
        }
        moveToFront(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = nodes.get(key);
        if (node != null) {
            node.value = value;
            moveToFront(node);
            return;
        }

        if (nodes.size() == capacity) {
            Node leastRecent = tail.prev;
            unlink(leastRecent);
            nodes.remove(leastRecent.key);
        }
        node = new Node(key, value);
        nodes.put(key, node);
        addFirst(node);
    }

    private void moveToFront(Node node) {
        unlink(node);
        addFirst(node);
    }

    private void unlink(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addFirst(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }
}
