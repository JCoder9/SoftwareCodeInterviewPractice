/**
 * Knapsack DP - Unbounded Knapsack
 * 
 * The Rule: Each item can be taken UNLIMITED times.
 * 
 * Related LeetCode Problems:
 * - LC 322: Coin Change (Medium)
 * - LC 518: Coin Change II (Medium)
 * - LC 377: Combination Sum IV (Medium)
 * - LC 983: Minimum Cost For Tickets (Medium)
 * 
 * Time Complexity: O(n * capacity)
 * Space Complexity: O(capacity)
 */

public class UnboundedKnapsack {
    
    /**
     * Unbounded knapsack - can use each item unlimited times.
     */
    public static int unboundedKnapsack(int[] weights, int[] values, int capacity) {
        int[] dp = new int[capacity + 1];
        
        for (int w = 1; w <= capacity; w++) {
            for (int i = 0; i < weights.length; i++) {
                if (weights[i] <= w) {
                    dp[w] = Math.max(dp[w], dp[w - weights[i]] + values[i]);
                }
            }
        }
        
        return dp[capacity];
    }
    
    /**
     * Alternative formulation - iterate items first.
     * Go FORWARD (unlike 0/1 knapsack which goes backward).
     */
    public static int unboundedKnapsackAlt(int[] weights, int[] values, int capacity) {
        int[] dp = new int[capacity + 1];
        
        for (int i = 0; i < weights.length; i++) {
            for (int w = weights[i]; w <= capacity; w++) {
                dp[w] = Math.max(dp[w], dp[w - weights[i]] + values[i]);
            }
        }
        
        return dp[capacity];
    }
    
    public static void main(String[] args) {
        int[] weights = {1, 3, 4};
        int[] values = {10, 40, 50};
        int capacity = 8;
        
        System.out.println("Unbounded: " + unboundedKnapsack(weights, values, capacity)); // 110
        System.out.println("Alternative: " + unboundedKnapsackAlt(weights, values, capacity)); // 110
        
        // Rod cutting problem
        int[] lengths = {1, 2, 3, 4, 5};
        int[] prices = {2, 5, 7, 8, 10};
        int rodLength = 5;
        System.out.println("\nRod cutting: " + unboundedKnapsack(lengths, prices, rodLength)); // 12
    }
}
