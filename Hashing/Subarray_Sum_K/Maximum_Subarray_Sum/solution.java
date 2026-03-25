/**
 * Maximum Subarray Sum - Kadane's Algorithm Pattern
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

import java.util.*;

public class Solution {
    
    public static int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        
        return maxSum;
    }
    
    public static int maxSubarraySumCircular(int[] nums) {
        int maxKadane = kadaneMax(nums);
        int minKadane = kadaneMin(nums);
        
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        
        if (totalSum == minKadane) {
            return maxKadane;
        }
        
        return Math.max(maxKadane, totalSum - minKadane);
    }
    
    private static int kadaneMax(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        
        return maxSum;
    }
    
    private static int kadaneMin(int[] nums) {
        int minSum = nums[0];
        int currentSum = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.min(nums[i], currentSum + nums[i]);
            minSum = Math.min(minSum, currentSum);
        }
        
        return minSum;
    }
    
    public static int maximumSum(int[] nums) {
        int n = nums.length;
        
        int maxEndingHere = nums[0];
        int maxEndingWithDel = 0;
        int result = nums[0];
        
        for (int i = 1; i < n; i++) {
            maxEndingWithDel = Math.max(maxEndingHere, maxEndingWithDel + nums[i]);
            maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);
            result = Math.max(result, Math.max(maxEndingHere, maxEndingWithDel));
        }
        
        return result;
    }
    
    public static int maxAbsoluteSum(int[] nums) {
        int maxSum = 0, maxCurrent = 0;
        int minSum = 0, minCurrent = 0;
        
        for (int num : nums) {
            maxCurrent = Math.max(num, maxCurrent + num);
            maxSum = Math.max(maxSum, maxCurrent);
            
            minCurrent = Math.min(num, minCurrent + num);
            minSum = Math.min(minSum, minCurrent);
        }
        
        return Math.max(Math.abs(maxSum), Math.abs(minSum));
    }
    
    public static int maxProduct(int[] nums) {
        int maxProduct = nums[0];
        int currentMax = nums[0];
        int currentMin = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            
            int tempMax = Math.max(num, Math.max(currentMax * num, currentMin * num));
            currentMin = Math.min(num, Math.min(currentMax * num, currentMin * num));
            currentMax = tempMax;
            
            maxProduct = Math.max(maxProduct, currentMax);
        }
        
        return maxProduct;
    }
    
    public static int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int n = nums.length;
        int[] prefix = new int[n + 1];
        
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        
        int maxSum = 0;
        
        int maxFirst = 0;
        for (int i = firstLen; i <= n - secondLen; i++) {
            maxFirst = Math.max(maxFirst, prefix[i] - prefix[i - firstLen]);
            maxSum = Math.max(maxSum, maxFirst + prefix[i + secondLen] - prefix[i]);
        }
        
        int maxSecond = 0;
        for (int i = secondLen; i <= n - firstLen; i++) {
            maxSecond = Math.max(maxSecond, prefix[i] - prefix[i - secondLen]);
            maxSum = Math.max(maxSum, maxSecond + prefix[i + firstLen] - prefix[i]);
        }
        
        return maxSum;
    }
    
    public static void main(String[] args) {
        System.out.println("Max Subarray: " + maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println("Max Circular: " + maxSubarraySumCircular(new int[]{5,-3,5}));
        System.out.println("Max with Deletion: " + maximumSum(new int[]{1,-2,0,3}));
        System.out.println("Max Absolute Sum: " + maxAbsoluteSum(new int[]{1,-3,2,3,-4}));
        System.out.println("Max Product: " + maxProduct(new int[]{2,3,-2,4}));
        System.out.println("Max Sum Two No Overlap: " + maxSumTwoNoOverlap(new int[]{0,6,5,2,2,5,1,9,4}, 1, 2));
    }
}
