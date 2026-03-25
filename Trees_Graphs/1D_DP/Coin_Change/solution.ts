/**
 * 1D Dynamic Programming - Coin Change Pattern
 * 
 * Related LeetCode Problems:
 * - LC 322: Coin Change (Medium) - minimum coins
 * - LC 518: Coin Change II (Medium) - count combinations
 * - LC 377: Combination Sum IV (Medium) - count permutations
 * - LC 983: Minimum Cost For Tickets (Medium)
 * 
 * Time Complexity: O(n × amount)
 * Space Complexity: O(amount)
 */

// LC 322: Coin Change (minimum coins)
export function coinChange(coins: number[], amount: number): number {
    const dp = Array(amount + 1).fill(amount + 1);
    dp[0] = 0;
    
    for (let amt = 1; amt <= amount; amt++) {
        for (const coin of coins) {
            if (coin <= amt) {
                dp[amt] = Math.min(dp[amt], dp[amt - coin] + 1);
            }
        }
    }
    
    return dp[amount] > amount ? -1 : dp[amount];
}

// LC 518: Coin Change II (count combinations)
export function change(amount: number, coins: number[]): number {
    const dp = Array(amount + 1).fill(0);
    dp[0] = 1;
    
    for (const coin of coins) {
        for (let amt = coin; amt <= amount; amt++) {
            dp[amt] += dp[amt - coin];
        }
    }
    
    return dp[amount];
}

// LC 377: Combination Sum IV (count permutations)
export function combinationSum4(nums: number[], target: number): number {
    const dp = Array(target + 1).fill(0);
    dp[0] = 1;
    
    for (let amt = 1; amt <= target; amt++) {
        for (const num of nums) {
            if (num <= amt) {
                dp[amt] += dp[amt - num];
            }
        }
    }
    
    return dp[target];
}

// LC 983: Minimum Cost For Tickets
export function mincostTickets(days: number[], costs: number[]): number {
    const daySet = new Set(days);
    const maxDay = days[days.length - 1];
    const dp = Array(maxDay + 1).fill(0);
    
    for (let day = 1; day <= maxDay; day++) {
        if (!daySet.has(day)) {
            dp[day] = dp[day - 1];
        } else {
            const cost1 = dp[Math.max(0, day - 1)] + costs[0];
            const cost7 = dp[Math.max(0, day - 7)] + costs[1];
            const cost30 = dp[Math.max(0, day - 30)] + costs[2];
            dp[day] = Math.min(cost1, cost7, cost30);
        }
    }
    
    return dp[maxDay];
}

// Test cases
if (require.main === module) {
    const coins1 = [1, 2, 5];
    console.log("Min coins for 11:", coinChange(coins1, 11));
    
    console.log("Combinations for 5:", change(5, coins1));
    
    const nums = [1, 2, 3];
    console.log("Permutations for 4:", combinationSum4(nums, 4));
    
    const days = [1, 4, 6, 7, 8, 20];
    const costs = [2, 7, 15];
    console.log("Min ticket cost:", mincostTickets(days, costs));
}
