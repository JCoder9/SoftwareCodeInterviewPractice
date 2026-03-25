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

/**
 * Bounded knapsack - each item has limited count.
 */
export function boundedKnapsack(
    weights: number[], 
    values: number[], 
    counts: number[], 
    capacity: number
): number {
    const dp: number[] = Array(capacity + 1).fill(0);
    
    for (let i = 0; i < weights.length; i++) {
        for (let w = capacity; w >= weights[i]; w--) {
            for (let k = 1; k <= counts[i]; k++) {
                if (weights[i] * k <= w) {
                    dp[w] = Math.max(
                        dp[w],
                        dp[w - weights[i] * k] + values[i] * k
                    );
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
export function boundedKnapsackOptimized(
    weights: number[], 
    values: number[], 
    counts: number[], 
    capacity: number
): number {
    const flatWeights: number[] = [];
    const flatValues: number[] = [];
    
    for (let i = 0; i < weights.length; i++) {
        for (let j = 0; j < counts[i]; j++) {
            flatWeights.push(weights[i]);
            flatValues.push(values[i]);
        }
    }
    
    const dp: number[] = Array(capacity + 1).fill(0);
    
    for (let i = 0; i < flatWeights.length; i++) {
        for (let w = capacity; w >= flatWeights[i]; w--) {
            dp[w] = Math.max(dp[w], 
                dp[w - flatWeights[i]] + flatValues[i]);
        }
    }
    
    return dp[capacity];
}

/**
 * Binary representation optimization.
 */
export function boundedKnapsackBinary(
    weights: number[], 
    values: number[], 
    counts: number[], 
    capacity: number
): number {
    const binaryWeights: number[] = [];
    const binaryValues: number[] = [];
    
    for (let i = 0; i < weights.length; i++) {
        let count = counts[i];
        let k = 1;
        
        while (k <= count) {
            binaryWeights.push(weights[i] * k);
            binaryValues.push(values[i] * k);
            count -= k;
            k *= 2;
        }
        
        if (count > 0) {
            binaryWeights.push(weights[i] * count);
            binaryValues.push(values[i] * count);
        }
    }
    
    const dp: number[] = Array(capacity + 1).fill(0);
    
    for (let i = 0; i < binaryWeights.length; i++) {
        for (let w = capacity; w >= binaryWeights[i]; w--) {
            dp[w] = Math.max(dp[w], 
                dp[w - binaryWeights[i]] + binaryValues[i]);
        }
    }
    
    return dp[capacity];
}

// Test cases
if (require.main === module) {
    const weights = [1, 3, 4];
    const values = [10, 40, 50];
    const counts = [2, 1, 3];
    const capacity = 8;
    
    console.log("Bounded:", boundedKnapsack(weights, values, counts, capacity));
    console.log("Flattened:", boundedKnapsackOptimized(weights, values, counts, capacity));
    console.log("Binary:", boundedKnapsackBinary(weights, values, counts, capacity));
}
