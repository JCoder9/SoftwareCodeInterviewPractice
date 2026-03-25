/**
 * Subarray Sum Equals K - Prefix Sum + Hash Map Pattern
 * 
 * Problem: Count/find subarrays whose sum equals target K.
 * 
 * Pattern: Use prefix sums with hash map to find subarrays in O(n) time.
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

import java.util.*;

public class Solution {
    
    /**
     * Count number of contiguous subarrays that sum to k.
     */
    public static int subarraySumEqualsK(int[] nums, int k) {
        int count = 0;
        int prefixSum = 0;
        Map<Integer, Integer> prefixMap = new HashMap<>();
        prefixMap.put(0, 1);  // Empty prefix
        
        for (int num : nums) {
            prefixSum += num;
            
            // Check if we can form subarray ending here with sum = k
            if (prefixMap.containsKey(prefixSum - k)) {
                count += prefixMap.get(prefixSum - k);
            }
            
            // Add current prefix sum to map
            prefixMap.put(prefixSum, prefixMap.getOrDefault(prefixSum, 0) + 1);
        }
        
        return count;
    }
    
    /**
     * Find maximum length of subarray that sums to k.
     */
    public static int maxSubarraySumEqualsK(int[] nums, int k) {
        int prefixSum = 0;
        Map<Integer, Integer> firstOccurrence = new HashMap<>();
        firstOccurrence.put(0, -1);
        int maxLength = 0;
        
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            
            // Check if subarray ending here sums to k
            if (firstOccurrence.containsKey(prefixSum - k)) {
                int length = i - firstOccurrence.get(prefixSum - k);
                maxLength = Math.max(maxLength, length);
            }
            
            // Only store first occurrence
            firstOccurrence.putIfAbsent(prefixSum, i);
        }
        
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println("Subarray Sum Equals K:");
        System.out.println("  " + subarraySumEqualsK(new int[]{1, 1, 1}, 2));
        System.out.println("  " + subarraySumEqualsK(new int[]{1, 2, 3}, 3));
        
        System.out.println("\nMax Length Subarray Sum Equals K:");
        System.out.println("  " + maxSubarraySumEqualsK(new int[]{1, -1, 5, -2, 3}, 3));
    }
}
