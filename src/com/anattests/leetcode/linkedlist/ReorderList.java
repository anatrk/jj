package com.anattests.leetcode.linkedlist;

/**
 * LeetCode 143. Reorder List (Medium)
 *
 * PROBLEM
 *   Reorder L0 -> L1 -> ... -> Ln into L0 -> Ln -> L1 -> Ln-1 -> L2 -> ... in place.
 *   1->2->3->4 becomes 1->4->2->3,   1->2->3->4->5 becomes 1->5->2->4->3
 *
 * WHY THIS ONE
 *   It combines the three linked-list moves you must be able to write without thinking:
 *     1. find the middle with slow/fast pointers
 *     2. reverse a list in place
 *     3. merge two lists by relinking nodes
 *
 * IDEA
 *   Split the list in the middle, reverse the second half, then weave the two halves
 *   together, taking one node from each in turn.
 *
 * COMPLEXITY
 *   Time O(n), space O(1).
 *
 * TALKING POINTS / PITFALLS
 *   - Easy alternative: copy nodes into an ArrayList and use two indices - O(n) space.
 *     Say it, then do the O(1) version.
 *   - Cut the list (slow.next = null) or you create a cycle.
 *   - Save "next" pointers BEFORE relinking; drawing boxes and arrows helps.
 */
public class ReorderList {

    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        // 1. Find the end of the first half: slow moves 1 step, fast moves 2.
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. Cut after the middle and reverse the second half.
        ListNode second = reverse(slow.next);
        slow.next = null;

        // 3. Weave: first half is equal in length or one node longer.
        ListNode first = head;
        while (second != null) {
            ListNode firstNext = first.next;
            ListNode secondNext = second.next;
            first.next = second;
            second.next = firstNext;
            first = firstNext;
            second = secondNext;
        }
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}
