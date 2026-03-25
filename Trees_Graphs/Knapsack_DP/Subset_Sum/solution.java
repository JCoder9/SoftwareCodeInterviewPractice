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

public class SubsetSum {
    
    /**
     * Returns true if any subset sums to target.
     */
    public static boolean subsetSum(int[] nums, int target) {
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
     * Count number of subsets that sum to target.
     */
    public static int subsetSumCount(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        
        for (int num : nums) {
            for (int s = target; s >= num; s--) {
                dp[s] += dp[s - num];
            }
        }
        
        return dp[target];
    }
    
    /**
     * 2D version for clarity.
     */
    public static boolean subsetSum2D(int[] nums, int target) {
        int n = nums.length;
        boolean[][] dp = new boolean[n + 1][target + 1];
        
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }
        
        for (int i = 1; i <= n; i++) {
            for (int s = 0; s <= target; s++) {
                dp[i][s] = dp[i-1][s];
                
                if (nums[i-1] <= s) {
                    dp[i][s] = dp[i][s] || dp[i-1][s - nums[i-1]];
                }
            }
        }
        
        return dp[n][target];
    }
    
    public static void main(String[] args) {
        int[] nums = {3, 34, 4, 12, 5, 2};
        int target = 9;
        System.out.println("Can make " + target + ": " + subsetSum(nums, target)); // true
        
        int[] nums2 = {2, 3, 5, 8};
        int target2 = 11;
        System.out.println("Can make " + target2 + ": " + subsetSum(nums2, target2)); // true
        
        int[] nums3 = {1, 2, 3, 4};
        int target3 = 6;
        System.out.println("\nCount subsets: " + subsetSumCount(nums3, target3)); // 2
    }
}
