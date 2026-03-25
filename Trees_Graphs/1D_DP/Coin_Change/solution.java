/**
 * 1D Dynamic Programming - Coin Change Pattern
 * 
 * Related LeetCode Problems:
 * - LC 322: Coin Change (Medium) - minimum coins
 * - LC 518: Coin Change II (Medium) - count combinations
 * - LC 377: Combination Sum IV (Medium) - count permutations
 * - LC 983: Minimum Cost For Tickets (Medium)
 * 
 * Time Complexity: O(n × amount)
 * Space Complexity: O(amount)
 */

import java.util.*;

public class CoinChange {
    
    // LC 322: Coin Change (minimum coins)
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        
        for (int amt = 1; amt <= amount; amt++) {
            for (int coin : coins) {
                if (coin <= amt) {
                    dp[amt] = Math.min(dp[amt], dp[amt - coin] + 1);
                }
            }
        }
        
        return dp[amount] > amount ? -1 : dp[amount];
    }
    
    // LC 518: Coin Change II (count combinations)
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        
        for (int coin : coins) {
            for (int amt = coin; amt <= amount; amt++) {
                dp[amt] += dp[amt - coin];
            }
        }
        
        return dp[amount];
    }
    
    // LC 377: Combination Sum IV (count permutations)
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        
        for (int amt = 1; amt <= target; amt++) {
            for (int num : nums) {
                if (num <= amt) {
                    dp[amt] += dp[amt - num];
                }
            }
        }
        
        return dp[target];
    }
    
    // LC 983: Minimum Cost For Tickets
    public int mincostTickets(int[] days, int[] costs) {
        Set<Integer> daySet = new HashSet<>();
        for (int day : days) {
            daySet.add(day);
        }
        
        int maxDay = days[days.length - 1];
        int[] dp = new int[maxDay + 1];
        
        for (int day = 1; day <= maxDay; day++) {
            if (!daySet.contains(day)) {
                dp[day] = dp[day - 1];
            } else {
                int cost1 = dp[Math.max(0, day - 1)] + costs[0];
                int cost7 = dp[Math.max(0, day - 7)] + costs[1];
                int cost30 = dp[Math.max(0, day - 30)] + costs[2];
                dp[day] = Math.min(cost1, Math.min(cost7, cost30));
            }
        }
        
        return dp[maxDay];
    }
    
    // Test
    public static void main(String[] args) {
        CoinChange solution = new CoinChange();
        
        int[] coins1 = {1, 2, 5};
        System.out.println("Min coins for 11: " + solution.coinChange(coins1, 11));
        
        System.out.println("Combinations for 5: " + solution.change(5, coins1));
        
        int[] nums = {1, 2, 3};
        System.out.println("Permutations for 4: " + solution.combinationSum4(nums, 4));
        
        int[] days = {1, 4, 6, 7, 8, 20};
        int[] costs = {2, 7, 15};
        System.out.println("Min ticket cost: " + solution.mincostTickets(days, costs));
    }
}
