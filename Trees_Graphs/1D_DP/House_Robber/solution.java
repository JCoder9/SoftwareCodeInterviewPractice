/**
 * 1D Dynamic Programming - House Robber Pattern
 * 
 * Related LeetCode Problems:
 * - LC 198: House Robber (Medium)
 * - LC 213: House Robber II (Medium) - circular
 * - LC 740: Delete and Earn (Medium)
 * - LC 2320: Count Number of Ways to Place Houses (Medium)
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1) optimized
 */

import java.util.*;

public class HouseRobber {
    
    // LC 198: House Robber
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length <= 2) return Math.max(nums[0], nums.length == 1 ? 0 : nums[1]);
        
        int prev2 = nums[0];
        int prev1 = Math.max(nums[0], nums[1]);
        
        for (int i = 2; i < nums.length; i++) {
            int current = Math.max(nums[i] + prev2, prev1);
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
    
    // LC 213: House Robber II (circular)
    public int rob2(int[] nums) {
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        
        return Math.max(
            robLinear(nums, 0, nums.length - 2),  // exclude last
            robLinear(nums, 1, nums.length - 1)   // exclude first
        );
    }
    
    private int robLinear(int[] nums, int start, int end) {
        int prev2 = 0, prev1 = 0;
        
        for (int i = start; i <= end; i++) {
            int current = Math.max(nums[i] + prev2, prev1);
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
    
    // LC 740: Delete and Earn
    public int deleteAndEarn(int[] nums) {
        if (nums.length == 0) return 0;
        
        int maxNum = 0;
        for (int num : nums) {
            maxNum = Math.max(maxNum, num);
        }
        
        int[] points = new int[maxNum + 1];
        for (int num : nums) {
            points[num] += num;
        }
        
        if (points.length == 1) return points[0];
        if (points.length == 2) return Math.max(points[0], points[1]);
        
        int prev2 = points[0];
        int prev1 = Math.max(points[0], points[1]);
        
        for (int i = 2; i < points.length; i++) {
            int current = Math.max(points[i] + prev2, prev1);
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
    
    // LC 2320: Count Number of Ways to Place Houses
    public int countHousePlacements(int n) {
        int MOD = 1000000007;
        
        if (n == 1) return 4;
        
        long prev2 = 1, prev1 = 2;
        
        for (int i = 2; i <= n; i++) {
            long current = (prev1 + prev2) % MOD;
            prev2 = prev1;
            prev1 = current;
        }
        
        return (int)((prev1 * prev1) % MOD);
    }
    
    // Test
    public static void main(String[] args) {
        HouseRobber solution = new HouseRobber();
        
        System.out.println("Rob [1,2,3,1]: " + solution.rob(new int[]{1, 2, 3, 1}));
        System.out.println("Rob [2,7,9,3,1]: " + solution.rob(new int[]{2, 7, 9, 3, 1}));
        
        System.out.println("Rob II [2,3,2]: " + solution.rob2(new int[]{2, 3, 2}));
        System.out.println("Rob II [1,2,3,1]: " + solution.rob2(new int[]{1, 2, 3, 1}));
        
        System.out.println("Delete and earn [3,4,2]: " + 
                          solution.deleteAndEarn(new int[]{3, 4, 2}));
        
        System.out.println("Count placements (n=1): " + solution.countHousePlacements(1));
        System.out.println("Count placements (n=2): " + solution.countHousePlacements(2));
    }
}
