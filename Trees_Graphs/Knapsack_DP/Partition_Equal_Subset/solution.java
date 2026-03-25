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

import java.util.Arrays;

public class PartitionEqualSubset {
    
    /**
     * LC 416: Partition Equal Subset Sum.
     */
    public static boolean canPartition(int[] nums) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        
        if (total % 2 != 0) return false;
        
        int target = total / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        
        for (int num : nums) {
            for (int s = target; s >= num; s--) {
                dp[s] = dp[s] || dp[s - num];
            }
        }
        
        return dp[target];
    }
    
    /**
     * Minimize difference between two partition sums.
     */
    public static int minSubsetSumDifference(int[] nums) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        
        int target = total / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        
        for (int num : nums) {
            for (int s = target; s >= num; s--) {
                dp[s] = dp[s] || dp[s - num];
            }
        }
        
        // Find largest sum <= target that's achievable
        for (int s = target; s >= 0; s--) {
            if (dp[s]) {
                return Math.abs((total - s) - s);
            }
        }
        
        return total;
    }
    
    public static void main(String[] args) {
        int[] nums1 = {1, 5, 11, 5};
        System.out.println("Can partition: " + canPartition(nums1)); // true
        
        int[] nums2 = {1, 2, 3, 5};
        System.out.println("Can partition: " + canPartition(nums2)); // false
        
        int[] nums3 = {1, 6, 11, 5};
        System.out.println("\nMin difference: " + minSubsetSumDifference(nums3)); // 1
    }
}
