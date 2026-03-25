/**
 * Knapsack DP - Target Sum
 * 
 * The Rule: Add + or - before each number to reach a target sum.
 * 
 * Related LeetCode Problems:
 * - LC 494: Target Sum (Medium)
 * - LC 1049: Last Stone Weight II (Medium)
 * 
 * Key Insight: Transforms into subset sum!
 * P - N = target, P + N = total => P = (target + total) / 2
 * 
 * Time Complexity: O(n * sum)
 * Space Complexity: O(sum)
 */

/**
 * LC 494: Target Sum.
 */
export function findTargetSumWays(nums: number[], target: number): number {
    const total = nums.reduce((sum, num) => sum + num, 0);
    
    if (Math.abs(target) > total || (target + total) % 2 !== 0) {
        return 0;
    }
    
    const subsetSum = (target + total) / 2;
    const dp: number[] = Array(subsetSum + 1).fill(0);
    dp[0] = 1;
    
    for (const num of nums) {
        for (let s = subsetSum; s >= num; s--) {
            dp[s] += dp[s - num];
        }
    }
    
    return dp[subsetSum];
}

/**
 * 2D version for clarity.
 */
export function findTargetSumWays2D(nums: number[], target: number): number {
    const total = nums.reduce((sum, num) => sum + num, 0);
    
    if (Math.abs(target) > total || (target + total) % 2 !== 0) {
        return 0;
    }
    
    const subsetSum = (target + total) / 2;
    const n = nums.length;
    const dp: number[][] = Array(n + 1).fill(null)
        .map(() => Array(subsetSum + 1).fill(0));
    dp[0][0] = 1;
    
    for (let i = 1; i <= n; i++) {
        for (let s = 0; s <= subsetSum; s++) {
            dp[i][s] = dp[i-1][s];
            
            if (nums[i-1] <= s) {
                dp[i][s] += dp[i-1][s - nums[i-1]];
            }
        }
    }
    
    return dp[n][subsetSum];
}

// Test cases
if (require.main === module) {
    const nums1 = [1, 1, 1, 1, 1];
    const target1 = 3;
    console.log("Ways to reach", target1 + ":", findTargetSumWays(nums1, target1)); // 5
    
    const nums2 = [1];
    const target2 = 1;
    console.log("Ways to reach", target2 + ":", findTargetSumWays(nums2, target2)); // 1
    
    const nums3 = [2, 3, 1];
    const target3 = 2;
    console.log("Ways to reach", target3 + ":", findTargetSumWays(nums3, target3)); // 2
}
