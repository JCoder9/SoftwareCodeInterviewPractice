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

/**
 * Classic 0/1 Knapsack - 2D DP approach.
 */
export function knapsack01(weights: number[], values: number[], capacity: number): number {
    const n = weights.length;
    const dp: number[][] = Array(n + 1)
        .fill(0)
        .map(() => Array(capacity + 1).fill(0));
    
    for (let i = 1; i <= n; i++) {
        for (let w = 0; w <= capacity; w++) {
            // Don't take item
            dp[i][w] = dp[i-1][w];
            
            // Take item if it fits
            if (weights[i-1] <= w) {
                dp[i][w] = Math.max(
                    dp[i][w],
                    dp[i-1][w - weights[i-1]] + values[i-1]
                );
            }
        }
    }
    
    return dp[n][capacity];
}

/**
 * Space-optimized version using 1D array.
 * MUST iterate backwards!
 */
export function knapsack01Optimized(weights: number[], values: number[], capacity: number): number {
    const dp: number[] = Array(capacity + 1).fill(0);
    
    for (let i = 0; i < weights.length; i++) {
        // MUST go backwards to avoid using updated values
        for (let w = capacity; w >= weights[i]; w--) {
            dp[w] = Math.max(dp[w], dp[w - weights[i]] + values[i]);
        }
    }
    
    return dp[capacity];
}

// Test cases
if (require.main === module) {
    const weights = [1, 3, 4, 5];
    const values = [1, 4, 5, 7];
    const capacity = 7;
    
    console.log("2D DP:", knapsack01(weights, values, capacity)); // 9
    console.log("1D DP:", knapsack01Optimized(weights, values, capacity)); // 9
    
    // More test cases
    const weights2 = [2, 1, 3, 2];
    const values2 = [12, 10, 20, 15];
    const capacity2 = 5;
    console.log("\nTest 2:", knapsack01(weights2, values2, capacity2)); // 37
}
