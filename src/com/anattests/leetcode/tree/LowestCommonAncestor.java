package com.anattests.leetcode.tree;

/**
 * LeetCode 236. Lowest Common Ancestor of a Binary Tree (Medium)
 *
 * PROBLEM
 *   Given a binary tree (NOT a BST) and two nodes p and q in it, return their lowest
 *   common ancestor: the deepest node that has both p and q as descendants
 *   (a node counts as a descendant of itself).
 *
 * HOW TO RECOGNIZE IT
 *   Most tree problems are "ask both children, combine their answers" - a post-order
 *   DFS. Decide what the recursive call RETURNS, and the code writes itself.
 *
 * IDEA
 *   lca(node) returns p or q if it finds one of them in this subtree, the LCA if it
 *   finds both, and null if it finds neither.
 *     - node is null, p, or q      -> return node
 *     - both children found something -> p and q are on different sides, node is the LCA
 *     - only one side found something -> pass that result up
 *
 * COMPLEXITY
 *   Time O(n), space O(h) for the recursion stack (h = height, O(n) if skewed).
 *
 * TALKING POINTS / PITFALLS
 *   - If it were a BST (LeetCode 235) you'd just walk down: both smaller -> go left,
 *     both bigger -> go right, otherwise you're at the LCA. Interviewers like asking this.
 *   - Relies on p and q both existing. Ask about it; if not guaranteed you must also
 *     confirm the one found node actually has the other below it.
 *   - Very deep trees can overflow the Java stack - mention an iterative version with
 *     parent pointers as a follow-up.
 */
public class LowestCommonAncestor {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }
}
