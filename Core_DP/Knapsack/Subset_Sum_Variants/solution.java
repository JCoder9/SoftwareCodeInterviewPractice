/**
 * Knapsack Pattern - 0/1 and Unbounded
 * 
 * Time Complexity: O(n * capacity)
 * Space Complexity: O(capacity)
 */

public class Solution {
    
    public static int knapsack01(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[] dp = new int[capacity + 1];
        
        for (int i = 0; i < n; i++) {
            for (int w = capacity; w >= weights[i]; w--) {
                dp[w] = Math.max(dp[w], values[i] + dp[w - weights[i]]);
            }
        }
        
        return dp[capacity];
    }
    
    public static boolean subsetSum(int[] nums, int target) {
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        
        for (int num : nums) {
            for (int t = target; t >= num; t--) {
                dp[t] = dp[t] || dp[t - num];
            }
        }
        
        return dp[target];
    }
    
    public static boolean canPartition(int[] nums) {
        int totalSum = 0;
        for (int num : nums) totalSum += num;
        
        if (totalSum % 2 != 0) return false;
        
        int target = totalSum / 2;
        return subsetSum(nums, target);
    }
    
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        java.util.Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        
        for (int coin : coins) {
            for (int a = coin; a <= amount; a++) {
                dp[a] = Math.min(dp[a], dp[a - coin] + 1);
            }
        }
        
        return dp[amount] > amount ? -1 : dp[amount];
    }
    
    public static int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        
        for (int coin : coins) {
            for (int a = coin; a <= amount; a++) {
                dp[a] += dp[a - coin];
            }
        }
        
        return dp[amount];
    }

    public static void main(String[] args) {
        System.out.println("0/1 Knapsack: " + knapsack01(new int[]{1,2,3}, new int[]{6,10,12}, 5));
        System.out.println("Subset sum: " + subsetSum(new int[]{1,5,11,5}, 11));
        System.out.println("Can partition: " + canPartition(new int[]{1,5,11,5}));
        System.out.println("Coin change: " + coinChange(new int[]{1,2,5}, 11));
        System.out.println("Coin change 2: " + coinChange2(new int[]{1,2,5}, 5));
    }
}
