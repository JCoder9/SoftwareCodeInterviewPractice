/**
 * Knapsack DP - Coin Change (Minimum Coins)
 * 
 * The Rule: Find the MINIMUM number of coins to make a target amount.
 * 
 * Related LeetCode Problems:
 * - LC 322: Coin Change (Medium)
 * - LC 983: Minimum Cost For Tickets (Medium)
 * - LC 2547: Minimum Cost to Split an Array (Hard)
 * 
 * Time Complexity: O(n * amount)
 * Space Complexity: O(amount)
 */

import java.util.*;

public class CoinChangeMin {
    
    /**
     * LC 322: Coin Change - minimum coins.
     */
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // Use amount + 1 as "infinity"
        dp[0] = 0;
        
        for (int coin : coins) {
            for (int a = coin; a <= amount; a++) {
                dp[a] = Math.min(dp[a], dp[a - coin] + 1);
            }
        }
        
        return dp[amount] > amount ? -1 : dp[amount];
    }
    
    /**
     * 2D version (less space efficient but clearer).
     */
    public static int coinChange2D(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        
        // Initialize with infinity
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], amount + 1);
            dp[i][0] = 0;
        }
        
        for (int i = 1; i <= n; i++) {
            for (int a = 1; a <= amount; a++) {
                dp[i][a] = dp[i-1][a];
                
                if (coins[i-1] <= a) {
                    dp[i][a] = Math.min(dp[i][a], dp[i][a - coins[i-1]] + 1);
                }
            }
        }
        
        return dp[n][amount] > amount ? -1 : dp[n][amount];
    }
    
    public static void main(String[] args) {
        int[] coins1 = {1, 2, 5};
        int amount1 = 11;
        System.out.println("Min coins for " + amount1 + ": " + coinChange(coins1, amount1)); // 3
        
        int[] coins2 = {2};
        int amount2 = 3;
        System.out.println("Min coins for " + amount2 + ": " + coinChange(coins2, amount2)); // -1
        
        int[] coins3 = {1};
        int amount3 = 0;
        System.out.println("Min coins for " + amount3 + ": " + coinChange(coins3, amount3)); // 0
        
        System.out.println("\n2D version: " + coinChange2D(coins1, amount1)); // 3
    }
}
