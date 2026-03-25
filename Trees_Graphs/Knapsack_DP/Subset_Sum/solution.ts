/**
 * Knapsack DP - Subset Sum Problem
 * 
 * The Rule: Can you select items that sum to EXACTLY the target?
 * 
 * Related LeetCode Problems:
 * - LC 416: Partition Equal Subset Sum (Medium)
 * - LC 698: Partition to K Equal Sum Subsets (Medium)
 * 
 * Time Complexity: O(n * target)
 * Space Complexity: O(target)
 */

/**
 * Returns true if any subset sums to target.
 */
export function subsetSum(nums: number[], target: number): boolean {
    const dp: boolean[] = Array(target + 1).fill(false);
    dp[0] = true;
    
    for (const num of nums) {
        for (let s = target; s >= num; s--) {
            dp[s] = dp[s] || dp[s - num];
        }
    }
    
    return dp[target];
}

/**
 * Count number of subsets that sum to target.
 */
export function subsetSumCount(nums: number[], target: number): number {
    const dp: number[] = Array(target + 1).fill(0);
    dp[0] = 1;
    
    for (const num of nums) {
        for (let s = target; s >= num; s--) {
            dp[s] += dp[s - num];
        }
    }
    
    return dp[target];
}

/**
 * 2D version for clarity.
 */
export function subsetSum2D(nums: number[], target: number): boolean {
    const n = nums.length;
    const dp: boolean[][] = Array(n + 1).fill(null)
        .map(() => Array(target + 1).fill(false));
    
    for (let i = 0; i <= n; i++) {
        dp[i][0] = true;
    }
    
    for (let i = 1; i <= n; i++) {
        for (let s = 0; s <= target; s++) {
            dp[i][s] = dp[i-1][s];
            
            if (nums[i-1] <= s) {
                dp[i][s] = dp[i][s] || dp[i-1][s - nums[i-1]];
            }
        }
    }
    
    return dp[n][target];
}

// Test cases
if (require.main === module) {
    const nums = [3, 34, 4, 12, 5, 2];
    const target = 9;
    console.log("Can make", target + ":", subsetSum(nums, target)); // true
    
    const nums2 = [2, 3, 5, 8];
    const target2 = 11;
    console.log("Can make", target2 + ":", subsetSum(nums2, target2)); // true
    
    const nums3 = [1, 2, 3, 4];
    const target3 = 6;
    console.log("\nCount subsets:", subsetSumCount(nums3, target3)); // 2
}
