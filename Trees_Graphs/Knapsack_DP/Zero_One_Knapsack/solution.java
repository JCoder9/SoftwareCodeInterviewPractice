/**
 * Knapsack DP - 0/1 Knapsack (Classic)
 * 
 * The Rule: Each item can be taken ONCE or NOT AT ALL (0 or 1 times).
 * 
 * Related LeetCode Problems:
 * - LC 416: Partition Equal Subset Sum (Medium)
 * - LC 494: Target Sum (Medium)
 * - LC 1049: Last Stone Weight II (Medium)
 * 
 * Time Complexity: O(n * capacity)
 * Space Complexity: O(n * capacity) or O(capacity) optimized
 */

public class ZeroOneKnapsack {
    
    /**
     * Classic 0/1 Knapsack - 2D DP approach.
     */
    public static int knapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];
        
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                // Don't take item
                dp[i][w] = dp[i-1][w];
                
                // Take item if it fits
                if (weights[i-1] <= w) {
                    dp[i][w] = Math.max(dp[i][w], 
                                       dp[i-1][w - weights[i-1]] + values[i-1]);
                }
            }
        }
        
        return dp[n][capacity];
    }
    
    /**
     * Space-optimized version using 1D array.
     * MUST iterate backwards!
     */
    public static int knapsackOptimized(int[] weights, int[] values, int capacity) {
        int[] dp = new int[capacity + 1];
        
        for (int i = 0; i < weights.length; i++) {
            // MUST go backwards to avoid using updated values
            for (int w = capacity; w >= weights[i]; w--) {
                dp[w] = Math.max(dp[w], dp[w - weights[i]] + values[i]);
            }
        }
        
        return dp[capacity];
    }
    
    public static void main(String[] args) {
        int[] weights = {1, 3, 4, 5};
        int[] values = {1, 4, 5, 7};
        int capacity = 7;
        
        System.out.println("2D DP: " + knapsack(weights, values, capacity)); // 9
        System.out.println("1D DP: " + knapsackOptimized(weights, values, capacity)); // 9
        
        // More test cases
        int[] weights2 = {2, 1, 3, 2};
        int[] values2 = {12, 10, 20, 15};
        int capacity2 = 5;
        System.out.println("\nTest 2: " + knapsack(weights2, values2, capacity2)); // 37
    }
}
