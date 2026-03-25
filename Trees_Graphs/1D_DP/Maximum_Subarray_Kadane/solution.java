/**
 * 1D Dynamic Programming - Maximum Subarray (Kadane's Algorithm)
 * 
 * Related LeetCode Problems:
 * - LC 53: Maximum Subarray (Medium)
 * - LC 918: Maximum Sum Circular Subarray (Medium)
 * - LC 152: Maximum Product Subarray (Medium)
 * - LC 1191: K-Concatenation Maximum Sum (Medium)
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

public class MaximumSubarrayKadane {
    
    // LC 53: Maximum Subarray
    public int maxSubArray(int[] nums) {
        int maxEndingHere = nums[0];
        int maxSoFar = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        
        return maxSoFar;
    }
    
    // LC 918: Maximum Sum Circular Subarray
    public int maxSubarraySumCircular(int[] nums) {
        int maxNormal = kadaneMax(nums);
        int totalSum = 0;
        
        for (int num : nums) {
            totalSum += num;
        }
        
        int minSubarray = kadaneMin(nums);
        
        if (totalSum == minSubarray) {
            return maxNormal;
        }
        
        int maxCircular = totalSum - minSubarray;
        
        return Math.max(maxNormal, maxCircular);
    }
    
    private int kadaneMax(int[] nums) {
        int maxEndingHere max SoFar = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        
        return maxSoFar;
    }
    
    private int kadaneMin(int[] nums) {
        int minEndingHere = nums[0];
        int minSoFar = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            minEndingHere = Math.min(nums[i], minEndingHere + nums[i]);
            minSoFar = Math.min(minSoFar, minEndingHere);
        }
        
        return minSoFar;
    }
    
    // LC 152: Maximum Product Subarray
    public int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;
        
        int maxSoFar = nums[0];
        int minSoFar = nums[0];
        int result = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            
            if (num < 0) {
                int temp = maxSoFar;
                maxSoFar = minSoFar;
                minSoFar = temp;
            }
            
            maxSoFar = Math.max(num, maxSoFar * num);
            minSoFar = Math.min(num, minSoFar * num);
            
            result = Math.max(result, maxSoFar);
        }
        
        return result;
    }
    
    // Maximum sum subarray of size k
    public int maxSumSubarrayOfSizeK(int[] nums, int k) {
        if (k > nums.length) return -1;
        
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }
        
        int maxSum = windowSum;
        
        for (int i = k; i < nums.length; i++) {
            windowSum = windowSum - nums[i - k] + nums[i];
            maxSum = Math.max(maxSum, windowSum);
        }
        
        return maxSum;
    }
    
    // Test
    public static void main(String[] args) {
        MaximumSubarrayKadane solution = new MaximumSubarrayKadane();
        
        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Max subarray sum: " + solution.maxSubArray(nums1));
        
        int[] nums2 = {1, -2, 3, -2};
        System.out.println("Max circular subarray: " + solution.maxSubarraySumCircular(nums2));
        
        int[] nums3 = {2, 3, -2, 4};
        System.out.println("Max product subarray: " + solution.maxProduct(nums3));
        
        int[] nums4 = {1, 4, 2, 10, 23, 3, 1, 0, 20};
        System.out.println("Max sum of size 4: " + solution.maxSumSubarrayOfSizeK(nums4, 4));
    }
}
