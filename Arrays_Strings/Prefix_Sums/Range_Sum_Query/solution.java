/**
 * Range Sum Query - Prefix Sum Pattern
 * 
 * Problem: Given an array, answer multiple queries for sum of elements in range [left, right].
 * 
 * Pattern: Build prefix sum array where prefix[i] = sum of nums[0...i-1].
 *          Then range_sum(left, right) = prefix[right+1] - prefix[left]
 * 
 * Time Complexity: O(n) preprocessing, O(1) per query
 * Space Complexity: O(n) for prefix array
 */

import java.util.*;

class NumArray {
    private int[] prefix;
    
    /**
     * Build prefix sum array.
     * prefix[i] = sum of nums[0] to nums[i-1]
     */
    public NumArray(int[] nums) {
        prefix = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
    }
    
    /**
     * Return sum of elements from index left to right (inclusive).
     */
    public int sumRange(int left, int right) {
        return prefix[right + 1] - prefix[left];
    }
}

public class Solution {
    
    /**
     * Count number of subarrays with sum equal to k.
     * 
     * Key insight: If prefix[j] - prefix[i] = k, then subarray [i, j) sums to k.
     * Use hash map to track prefix sums seen so far.
     */
    public static int subarraySumEqualsK(int[] nums, int k) {
        int count = 0;
        int prefixSum = 0;
        Map<Integer, Integer> prefixMap = new HashMap<>();
        prefixMap.put(0, 1);  // Empty prefix
        
        for (int num : nums) {
            prefixSum += num;
            
            // Check if (prefixSum - k) exists
            if (prefixMap.containsKey(prefixSum - k)) {
                count += prefixMap.get(prefixSum - k);
            }
            
            // Record current prefix sum
            prefixMap.put(prefixSum, prefixMap.getOrDefault(prefixSum, 0) + 1);
        }
        
        return count;
    }

    public static void main(String[] args) {
        // Test range sum query
        System.out.println("Range Sum Query:");
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray obj = new NumArray(nums);
        int[][] queries = {{0, 2}, {2, 5}, {0, 5}};
        for (int[] q : queries) {
            System.out.println("  sumRange(" + q[0] + ", " + q[1] + ") = " + obj.sumRange(q[0], q[1]));
        }
        
        // Test subarray sum equals k
        System.out.println("\nSubarray Sum Equals K:");
        Object[][] testCases = {
            {new int[]{1, 1, 1}, 2, 2},
            {new int[]{1, 2, 3}, 3, 2},
            {new int[]{1}, 0, 0}
        };
        for (Object[] test : testCases) {
            int[] arr = (int[]) test[0];
            int k = (int) test[1];
            int expected = (int) test[2];
            int result = subarraySumEqualsK(arr, k);
            String status = (result == expected) ? "✓" : "✗";
            System.out.println(status + " subarraySumEqualsK(" + Arrays.toString(arr) + ", k=" + k + ") = " + result);
        }
    }
}
