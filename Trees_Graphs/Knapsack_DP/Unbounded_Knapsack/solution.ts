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

/**
 * Unbounded knapsack - can use each item unlimited times.
 */
export function unboundedKnapsack(weights: number[], values: number[], capacity: number): number {
    const dp: number[] = Array(capacity + 1).fill(0);
    
    for (let w = 1; w <= capacity; w++) {
        for (let i = 0; i < weights.length; i++) {
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
export function unboundedKnapsackAlt(weights: number[], values: number[], capacity: number): number {
    const dp: number[] = Array(capacity + 1).fill(0);
    
    for (let i = 0; i < weights.length; i++) {
        for (let w = weights[i]; w <= capacity; w++) {
            dp[w] = Math.max(dp[w], dp[w - weights[i]] + values[i]);
        }
    }
    
    return dp[capacity];
}

// Test cases
if (require.main === module) {
    const weights = [1, 3, 4];
    const values = [10, 40, 50];
    const capacity = 8;
    
    console.log("Unbounded:", unboundedKnapsack(weights, values, capacity)); // 110
    console.log("Alternative:", unboundedKnapsackAlt(weights, values, capacity)); // 110
    
    // Rod cutting problem
    const lengths = [1, 2, 3, 4, 5];
    const prices = [2, 5, 7, 8, 10];
    const rodLength = 5;
    console.log("\nRod cutting:", unboundedKnapsack(lengths, prices, rodLength)); // 12
}
