/**
 * Knapsack Pattern - 0/1 and Unbounded
 * 
 * Time Complexity: O(n * capacity)
 * Space Complexity: O(capacity)
 */

function knapsack01(weights: number[], values: number[], capacity: number): number {
    const n = weights.length;
    const dp = new Array(capacity + 1).fill(0);
    
    for (let i = 0; i < n; i++) {
        for (let w = capacity; w >= weights[i]; w--) {
            dp[w] = Math.max(dp[w], values[i] + dp[w - weights[i]]);
        }
    }
    
    return dp[capacity];
}

function subsetSum(nums: number[], target: number): boolean {
    const dp = new Array(target + 1).fill(false);
    dp[0] = true;
    
    for (const num of nums) {
        for (let t = target; t >= num; t--) {
            dp[t] = dp[t] || dp[t - num];
        }
    }
    
    return dp[target];
}

function canPartition(nums: number[]): boolean {
    const totalSum = nums.reduce((a, b) => a + b, 0);
    
    if (totalSum % 2 !== 0) return false;
    
    const target = totalSum / 2;
    return subsetSum(nums, target);
}

function coinChange(coins: number[], amount: number): number {
    const dp = new Array(amount + 1).fill(Infinity);
    dp[0] = 0;
    
    for (const coin of coins) {
        for (let a = coin; a <= amount; a++) {
            dp[a] = Math.min(dp[a], dp[a - coin] + 1);
        }
    }
    
    return dp[amount] === Infinity ? -1 : dp[amount];
}

function coinChange2(coins: number[], amount: number): number {
    const dp = new Array(amount + 1).fill(0);
    dp[0] = 1;
    
    for (const coin of coins) {
        for (let a = coin; a <= amount; a++) {
            dp[a] += dp[a - coin];
        }
    }
    
    return dp[amount];
}

// Test
if (require.main === module) {
    console.log("0/1 Knapsack:", knapsack01([1, 2, 3], [6, 10, 12], 5));
    console.log("Subset sum:", subsetSum([1, 5, 11, 5], 11));
    console.log("Can partition:", canPartition([1, 5, 11, 5]));
    console.log("Coin change:", coinChange([1, 2, 5], 11));
    console.log("Coin change 2:", coinChange2([1, 2, 5], 5));
}

export { knapsack01, subsetSum, canPartition, coinChange, coinChange2 };
