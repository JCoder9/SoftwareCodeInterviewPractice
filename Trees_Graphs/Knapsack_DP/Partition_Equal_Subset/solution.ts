/**
 * Knapsack DP - Partition Equal Subset Sum
 * 
 * The Rule: Can you split the array into two subsets with equal sum?
 * 
 * Related LeetCode Problems:
 * - LC 416: Partition Equal Subset Sum (Medium)
 * - LC 805: Split Array With Same Average (Hard)
 * - LC 1981: Minimize the Difference Between Target and Chosen Elements (Medium)
 * 
 * Time Complexity: O(n * sum)
 * Space Complexity: O(sum)
 */

/**
 * LC 416: Partition Equal Subset Sum.
 */
export function canPartition(nums: number[]): boolean {
    const total = nums.reduce((sum, num) => sum + num, 0);
    
    if (total % 2 !== 0) return false;
    
    const target = total / 2;
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
 * Minimize difference between two partition sums.
 */
export function minSubsetSumDifference(nums: number[]): number {
    const total = nums.reduce((sum, num) => sum + num, 0);
    const target = Math.floor(total / 2);
    
    const dp: boolean[] = Array(target + 1).fill(false);
    dp[0] = true;
    
    for (const num of nums) {
        for (let s = target; s >= num; s--) {
            dp[s] = dp[s] || dp[s - num];
        }
    }
    
    // Find largest sum <= target that's achievable
    for (let s = target; s >= 0; s--) {
        if (dp[s]) {
            return Math.abs((total - s) - s);
        }
    }
    
    return total;
}

// Test cases
if (require.main === module) {
    const nums1 = [1, 5, 11, 5];
    console.log("Can partition:", canPartition(nums1)); // true
    
    const nums2 = [1, 2, 3, 5];
    console.log("Can partition:", canPartition(nums2)); // false
    
    const nums3 = [1, 6, 11, 5];
    console.log("\nMin difference:", minSubsetSumDifference(nums3)); // 1
}
