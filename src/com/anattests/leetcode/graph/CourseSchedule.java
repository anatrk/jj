package com.anattests.leetcode.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode 207. Course Schedule (Medium)
 *
 * PROBLEM
 *   numCourses courses, prerequisites[i] = [a, b] means "take b before a".
 *   Can you finish all courses?
 *   2, [[1,0]]        -> true   (take 0, then 1)
 *   2, [[1,0],[0,1]]  -> false  (each needs the other: a cycle)
 *
 * HOW TO RECOGNIZE IT
 *   Dependencies / ordering / "before" -> directed graph + TOPOLOGICAL SORT.
 *   "Is it possible?" == "Is there no cycle?"
 *
 * IDEA (Kahn's algorithm, BFS)
 *   in-degree = number of unmet prerequisites. Start with every course whose in-degree
 *   is 0. Taking a course lowers the in-degree of the courses it unlocks. If we manage
 *   to take all courses, there is no cycle. Courses in a cycle never reach 0.
 *
 * COMPLEXITY
 *   Time O(V + E), space O(V + E).
 *
 * TALKING POINTS / PITFALLS
 *   - Build the adjacency list first - most graph bugs are in building the graph.
 *     Watch the edge direction: [a, b] is an edge b -> a.
 *   - Alternative: DFS with 3 states (unvisited / visiting / done); reaching a
 *     "visiting" node means a cycle.
 *   - Course Schedule II (210) asks for the order itself: record courses as you poll.
 *   - Other graph tools to know: BFS for shortest path in unweighted graphs, Dijkstra
 *     (PriorityQueue) for weighted, Union-Find for connectivity.
 */
public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> unlocks = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            unlocks.add(new ArrayList<>());
        }
        int[] inDegree = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int required = prerequisite[1];
            unlocks.get(required).add(course);
            inDegree[course]++;
        }

        Deque<Integer> ready = new ArrayDeque<>();
        for (int course = 0; course < numCourses; course++) {
            if (inDegree[course] == 0) {
                ready.offer(course);
            }
        }

        int taken = 0;
        while (!ready.isEmpty()) {
            int course = ready.poll();
            taken++;
            for (int next : unlocks.get(course)) {
                if (--inDegree[next] == 0) {
                    ready.offer(next);
                }
            }
        }
        return taken == numCourses;
    }
}
