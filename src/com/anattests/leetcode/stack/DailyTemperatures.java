package com.anattests.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 739. Daily Temperatures (Medium)
 *
 * PROBLEM
 *   For each day, how many days until a warmer temperature? 0 if never.
 *   [73,74,75,71,69,72,76,73] -> [1,1,4,2,1,1,0,0]
 *
 * HOW TO RECOGNIZE IT
 *   "Next greater / next smaller element" -> MONOTONIC STACK.
 *
 * IDEA
 *   Keep a stack of indices of days still waiting for a warmer day. Their temperatures
 *   are decreasing from bottom to top. When today is warmer than the day on top, today
 *   is that day's answer: pop it and record the distance. Repeat, then push today.
 *
 * COMPLEXITY
 *   Time O(n) - each index is pushed once and popped at most once, even though there is
 *   a loop inside a loop. Space O(n).
 *
 * TALKING POINTS / PITFALLS
 *   - Store INDICES, not temperatures: you need the index to compute the distance and to
 *     know where to write the answer.
 *   - Use ArrayDeque, not java.util.Stack (Stack is synchronized and a legacy class).
 *   - Same trick: Next Greater Element, Largest Rectangle in Histogram, Stock Span.
 */
public class DailyTemperatures {

    public int[] dailyTemperatures(int[] temperatures) {
        int[] answer = new int[temperatures.length];
        Deque<Integer> waiting = new ArrayDeque<>();

        for (int day = 0; day < temperatures.length; day++) {
            while (!waiting.isEmpty() && temperatures[day] > temperatures[waiting.peek()]) {
                int colderDay = waiting.pop();
                answer[colderDay] = day - colderDay;
            }
            waiting.push(day);
        }
        return answer; // days still on the stack never got warmer, so they stay 0
    }
}
