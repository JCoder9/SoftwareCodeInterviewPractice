/**
 * Knapsack DP - Coin Change II (Count Ways)
 * 
 * The Rule: Count HOW MANY ways to make the target amount.
 * 
 * Related LeetCode Problems:
 * - LC 518: Coin Change II (Medium)
 * - LC 377: Combination Sum IV (Medium)
 * 
 * Key Insight: Order matters!
 * - Combinations: Loop coins FIRST
 * - Permutations: Loop amount FIRST
 * 
 * Time Complexity: O(n * amount)
 * Space Complexity: O(amount)
 */

/**
 * LC 518: Coin Change II - count ways (COMBINATIONS).
 * IMPORTANT: Loop coins first!
 */
export function change(coins: number[], amount: number): number {
    const dp: number[] = Array(amount + 1).fill(0);
    dp[0] = 1;
    
    // IMPORTANT: Loop coins first for combinations
    for (const coin of coins) {
        for (let a = coin; a <= amount; a++) {
            dp[a] += dp[a - coin];
        }
    }
    
    return dp[amount];
}

/**
 * LC 377: Combination Sum IV - count ways (PERMUTATIONS).
 * IMPORTANT: Loop amount first!
 */
export function combinationSumPermutations(coins: number[], amount: number): number {
    const dp: number[] = Array(amount + 1).fill(0);
    dp[0] = 1;
    
    // IMPORTANT: Loop amount first for permutations
    for (let a = 1; a <= amount; a++) {
        for (const coin of coins) {
            if (coin <= a) {
                dp[a] += dp[a - coin];
            }
        }
    }
    
    return dp[amount];
}

/**
 * 2D version for clarity.
 */
export function change2D(coins: number[], amount: number): number {
    const n = coins.length;
    const dp: number[][] = Array(n + 1).fill(null)
        .map(() => Array(amount + 1).fill(0));
    
    for (let i = 0; i <= n; i++) {
        dp[i][0] = 1;
    }
    
    for (let i = 1; i <= n; i++) {
        for (let a = 0; a <= amount; a++) {
            dp[i][a] = dp[i-1][a];
            
            if (coins[i-1] <= a) {
                dp[i][a] += dp[i][a - coins[i-1]];
            }
        }
    }
    
    return dp[n][amount];
}

// Test cases
if (require.main === module) {
    const coins1 = [1, 2, 5];
    const amount1 = 5;
    console.log("Ways (combinations):", change(coins1, amount1)); // 4
    
    const coins2 = [2];
    const amount2 = 3;
    console.log("Ways:", change(coins2, amount2)); // 0
    
    const coins3 = [10];
    const amount3 = 10;
    console.log("Ways:", change(coins3, amount3)); // 1
    
    console.log("\nPermutations:", combinationSumPermutations(coins1, amount1));
    
    console.log("\n2D version:", change2D(coins1, amount1));
}
