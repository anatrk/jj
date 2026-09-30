package com.anattests.leetcode.dp;

import java.util.Arrays;

/**
 * LeetCode 322. Coin Change (Medium)
 *
 * PROBLEM
 *   Fewest coins that add up to amount (unlimited coins of each kind), or -1.
 *   coins = [1,2,5], amount = 11 -> 3 (5 + 5 + 1)
 *   coins = [2],     amount = 3  -> -1
 *
 * HOW TO RECOGNIZE IT
 *   "Minimum / maximum / number of ways" + choices that build on smaller versions of the
 *   same problem -> DYNAMIC PROGRAMMING.
 *
 * HOW TO BUILD ANY DP (say these steps out loud)
 *   1. State:      dp[a] = fewest coins that make amount a
 *   2. Transition: dp[a] = min over coins c <= a of dp[a - c] + 1   (last coin used is c)
 *   3. Base case:  dp[0] = 0
 *   4. Order:      a from 1 up, so dp[a - c] is ready before dp[a]
 *   5. Answer:     dp[amount]
 *
 * COMPLEXITY
 *   Time O(amount * coins), space O(amount).
 *
 * TALKING POINTS / PITFALLS
 *   - Greedy (largest coin first) is WRONG: coins [1,3,4], amount 6 -> greedy 4+1+1 = 3
 *     coins, best is 3+3 = 2. Mention it - it shows why DP is needed.
 *   - "Infinity" is amount + 1, not Integer.MAX_VALUE: MAX_VALUE + 1 overflows to negative.
 *   - Common path in the interview: recursion -> add memo (top-down) -> table (bottom-up).
 *   - Coin Change II (518, number of ways): loop coins OUTSIDE amounts so each
 *     combination is counted once, not once per order.
 */
public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int a = 1; a <= amount; a++) {
            for (int coin : coins) {
                if (coin <= a) {
                    dp[a] = Math.min(dp[a], dp[a - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
