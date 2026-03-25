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

public class TargetSum {
    
    /**
     * LC 494: Target Sum.
     */
    public static int findTargetSumWays(int[] nums, int target) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        
        if (Math.abs(target) > total || (target + total) % 2 != 0) {
            return 0;
        }
        
        int subsetSum = (target + total) / 2;
        int[] dp = new int[subsetSum + 1];
        dp[0] = 1;
        
        for (int num : nums) {
            for (int s = subsetSum; s >= num; s--) {
                dp[s] += dp[s - num];
            }
        }
        
        return dp[subsetSum];
    }
    
    /**
     * 2D version for clarity.
     */
    public static int findTargetSumWays2D(int[] nums, int target) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        
        if (Math.abs(target) > total || (target + total) % 2 != 0) {
            return 0;
        }
        
        int subsetSum = (target + total) / 2;
        int n = nums.length;
        int[][] dp = new int[n + 1][subsetSum + 1];
        dp[0][0] = 1;
        
        for (int i = 1; i <= n; i++) {
            for (int s = 0; s <= subsetSum; s++) {
                dp[i][s] = dp[i-1][s];
                
                if (nums[i-1] <= s) {
                    dp[i][s] += dp[i-1][s - nums[i-1]];
                }
            }
        }
        
        return dp[n][subsetSum];
    }
    
    public static void main(String[] args) {
        int[] nums1 = {1, 1, 1, 1, 1};
        int target1 = 3;
        System.out.println("Ways to reach " + target1 + ": " + 
            findTargetSumWays(nums1, target1)); // 5
        
        int[] nums2 = {1};
        int target2 = 1;
        System.out.println("Ways to reach " + target2 + ": " + 
            findTargetSumWays(nums2, target2)); // 1
        
        int[] nums3 = {2, 3, 1};
        int target3 = 2;
        System.out.println("Ways to reach " + target3 + ": " + 
            findTargetSumWays(nums3, target3)); // 2
    }
}
