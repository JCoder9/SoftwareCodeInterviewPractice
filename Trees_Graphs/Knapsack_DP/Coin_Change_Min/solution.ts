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

/**
 * LC 322: Coin Change - minimum coins.
 */
export function coinChange(coins: number[], amount: number): number {
    const dp: number[] = Array(amount + 1).fill(amount + 1);
    dp[0] = 0;
    
    for (const coin of coins) {
        for (let a = coin; a <= amount; a++) {
            dp[a] = Math.min(dp[a], dp[a - coin] + 1);
        }
    }
    
    return dp[amount] > amount ? -1 : dp[amount];
}

/**
 * 2D version (less space efficient but clearer).
 */
export function coinChange2D(coins: number[], amount: number): number {
    const n = coins.length;
    const dp: number[][] = Array(n + 1).fill(null)
        .map(() => Array(amount + 1).fill(amount + 1));
    
    for (let i = 0; i <= n; i++) {
        dp[i][0] = 0;
    }
    
    for (let i = 1; i <= n; i++) {
        for (let a = 1; a <= amount; a++) {
            dp[i][a] = dp[i-1][a];
            
            if (coins[i-1] <= a) {
                dp[i][a] = Math.min(dp[i][a], dp[i][a - coins[i-1]] + 1);
            }
        }
    }
    
    return dp[n][amount] > amount ? -1 : dp[n][amount];
}

// Test cases
if (require.main === module) {
    const coins1 = [1, 2, 5];
    const amount1 = 11;
    console.log("Min coins for", amount1 + ":", coinChange(coins1, amount1)); // 3
    
    const coins2 = [2];
    const amount2 = 3;
    console.log("Min coins for", amount2 + ":", coinChange(coins2, amount2)); // -1
    
    const coins3 = [1];
    const amount3 = 0;
    console.log("Min coins for", amount3 + ":", coinChange(coins3, amount3)); // 0
    
    console.log("\n2D version:", coinChange2D(coins1, amount1)); // 3
}
