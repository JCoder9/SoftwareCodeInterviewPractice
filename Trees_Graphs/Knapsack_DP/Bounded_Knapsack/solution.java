/**
 * Knapsack DP - Bounded Knapsack
 * 
 * The Rule: Each item can be taken up to a LIMITED number of times.
 * 
 * Key Insight: Hybrid of 0/1 and unbounded.
 * - Can optimize by treating as multiple 0/1 items
 * - OR use binary representation for better complexity
 * 
 * Time Complexity: O(n * capacity * max_count) naive
 * Space Complexity: O(capacity)
 */

import java.util.*;

public class BoundedKnapsack {
    
    /**
     * Bounded knapsack - each item has limited count.
     */
    public static int boundedKnapsack(int[] weights, int[] values, 
                                      int[] counts, int capacity) {
        int[] dp = new int[capacity + 1];
        
        for (int i = 0; i < weights.length; i++) {
            for (int w = capacity; w >= weights[i]; w--) {
                for (int k = 1; k <= counts[i]; k++) {
                    if (weights[i] * k <= w) {
                        dp[w] = Math.max(dp[w], 
                            dp[w - weights[i] * k] + values[i] * k);
                    } else {
                        break;
                    }
                }
            }
        }
        
        return dp[capacity];
    }
    
    /**
     * Optimized: Flatten into multiple 0/1 items.
     */
    public static int boundedKnapsackOptimized(int[] weights, int[] values, 
                                               int[] counts, int capacity) {
        List<Integer> flatWeights = new ArrayList<>();
        List<Integer> flatValues = new ArrayList<>();
        
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < counts[i]; j++) {
                flatWeights.add(weights[i]);
                flatValues.add(values[i]);
            }
        }
        
        int[] dp = new int[capacity + 1];
        
        for (int i = 0; i < flatWeights.size(); i++) {
            for (int w = capacity; w >= flatWeights.get(i); w--) {
                dp[w] = Math.max(dp[w], 
                    dp[w - flatWeights.get(i)] + flatValues.get(i));
            }
        }
        
        return dp[capacity];
    }
    
    /**
     * Binary representation optimization.
     */
    public static int boundedKnapsackBinary(int[] weights, int[] values, 
                                           int[] counts, int capacity) {
        List<Integer> binaryWeights = new ArrayList<>();
        List<Integer> binaryValues = new ArrayList<>();
        
        for (int i = 0; i < weights.length; i++) {
            int count = counts[i];
            int k = 1;
            
            while (k <= count) {
                binaryWeights.add(weights[i] * k);
                binaryValues.add(values[i] * k);
                count -= k;
                k *= 2;
            }
            
            if (count > 0) {
                binaryWeights.add(weights[i] * count);
                binaryValues.add(values[i] * count);
            }
        }
        
        int[] dp = new int[capacity + 1];
        
        for (int i = 0; i < binaryWeights.size(); i++) {
            for (int w = capacity; w >= binaryWeights.get(i); w--) {
                dp[w] = Math.max(dp[w], 
                    dp[w - binaryWeights.get(i)] + binaryValues.get(i));
            }
        }
        
        return dp[capacity];
    }
    
    public static void main(String[] args) {
        int[] weights = {1, 3, 4};
        int[] values = {10, 40, 50};
        int[] counts = {2, 1, 3};
        int capacity = 8;
        
        System.out.println("Bounded: " + boundedKnapsack(weights, values, counts, capacity));
        System.out.println("Flattened: " + boundedKnapsackOptimized(weights, values, counts, capacity));
        System.out.println("Binary: " + boundedKnapsackBinary(weights, values, counts, capacity));
    }
}
