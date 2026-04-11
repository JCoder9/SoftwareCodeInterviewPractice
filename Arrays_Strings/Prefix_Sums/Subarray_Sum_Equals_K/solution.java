/**
 * Subarray Sum Equals K - Prefix Sum + HashMap Pattern
 * 
 * LeetCode 560 - Extremely common at Google, Facebook, Amazon, Microsoft
 * 
 * Problem: Given an array and integer k, count how many contiguous subarrays sum to k.
 * 
 * Key Insight: If prefix[j] - prefix[i] = k, then subarray from i+1 to j sums to k.
 *              Rearranging: prefix[i] = prefix[j] - k
 *              So while at position j, check if (currentSum - k) was seen before!
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n) for HashMap
 */

import java.util.*;

// ─────────────────────────────────────────────────────────────────────────
// NAIVE APPROACH (Brute Force) - O(n²) time | O(1) space
// ─────────────────────────────────────────────────────────────────────────
// INTERVIEW SCRIPT:
//   1. Describe:   "Check every possible subarray - for each starting position,
//                   extend right and track sum until we hit k"
//   2. Problem:    "This is O(n²) with nested loops. For n=10⁴, that's 10⁸ operations"
//   3. Transition: "With prefix sums + HashMap, we can do it in O(n) with single pass.
//                   The key is: if we've seen (currentSum - k) before, those positions
//                   start subarrays that end here and sum to k"
//
// public static int subarraySumBruteForce(int[] nums, int k) {
//     int count = 0;
//     for (int start = 0; start < nums.length; start++) {
//         int sum = 0;
//         for (int end = start; end < nums.length; end++) {
//             sum += nums[end];
//             if (sum == k) count++;
//         }
//     }
//     return count;
// }
// ─────────────────────────────────────────────────────────────────────────

public class Solution {
    
    /**
     * Count subarrays with sum equals k.
     * 
     * HashMap stores: prefixSum -> frequency (how many times we've seen this prefix sum)
     * Why frequency? Same prefix sum can appear multiple times!
     * 
     * Example: nums = [1, -1, 1, -1, 1], k = 0
     *          prefixSum cycles: 1, 0, 1, 0, 1
     *          Multiple ways to form subarrays with sum = 0
     */
    public static int subarraySumEqualsK(int[] nums, int k) {
        int count = 0;
        int prefixSum = 0;
        Map<Integer, Integer> prefixMap = new HashMap<>();
        prefixMap.put(0, 1);  // Handle subarrays starting from index 0
        
        for (int num : nums) {
            prefixSum += num;
            
            // If (prefixSum - k) exists, those positions can start subarrays ending here
            if (prefixMap.containsKey(prefixSum - k)) {
                count += prefixMap.get(prefixSum - k);
            }
            
            // Record current prefix sum
            prefixMap.put(prefixSum, prefixMap.getOrDefault(prefixSum, 0) + 1);
        }
        
        return count;
    }
    
    /**
     * VARIANT: Find maximum length of subarray with sum equals k.
     * 
     * Strategy: Store first occurrence of each prefix sum (want longest subarray)
     */
    public static int maxSubarrayLenEqualsK(int[] nums, int k) {
        int maxLen = 0;
        int prefixSum = 0;
        Map<Integer, Integer> prefixMap = new HashMap<>();
        prefixMap.put(0, -1);  // For subarrays starting from index 0
        
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            
            if (prefixMap.containsKey(prefixSum - k)) {
                maxLen = Math.max(maxLen, i - prefixMap.get(prefixSum - k));
            }
            
            // Only store FIRST occurrence (want max length)
            if (!prefixMap.containsKey(prefixSum)) {
                prefixMap.put(prefixSum, i);
            }
        }
        
        return maxLen;
    }
    
    /**
     * VARIANT (LC 523): Check if subarray sum is multiple of k (length >= 2).
     * 
     * Key: Two sums with same remainder (mod k) means the subarray between them is divisible by k.
     */
    public static boolean checkSubarraySumMultipleK(int[] nums, int k) {
        if (nums.length < 2) return false;
        
        Map<Integer, Integer> remainderMap = new HashMap<>();
        remainderMap.put(0, -1);
        int prefixSum = 0;
        
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            int remainder = (k != 0) ? prefixSum % k : prefixSum;
            
            if (remainderMap.containsKey(remainder)) {
                if (i - remainderMap.get(remainder) >= 2) {
                    return true;
                }
            } else {
                remainderMap.put(remainder, i);
            }
        }
        
        return false;
    }
    
    /**
     * VARIANT (LC 974): Count subarrays with sum divisible by k.
     * 
     * Handle negative remainders: ((sum % k) + k) % k ensures positive remainder
     */
    public static int subarraySumsDivisibleByK(int[] nums, int k) {
        int count = 0;
        int prefixSum = 0;
        Map<Integer, Integer> remainderCount = new HashMap<>();
        remainderCount.put(0, 1);
        
        for (int num : nums) {
            prefixSum += num;
            int remainder = ((prefixSum % k) + k) % k;  // Handle negative remainders
            
            count += remainderCount.getOrDefault(remainder, 0);
            remainderCount.put(remainder, remainderCount.getOrDefault(remainder, 0) + 1);
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Subarray Sum Equals K (LC 560) ===");
        Object[][] testCases1 = {
            {new int[]{1, 1, 1}, 2, 2},           // [1,1] at [0,1] and [1,2]
            {new int[]{1, 2, 3}, 3, 2},           // [3] and [1,2]
            {new int[]{1, -1, 0}, 0, 3}           // [-1,1], [0], [-1,1,0]
        };
        for (Object[] test : testCases1) {
            int[] arr = (int[]) test[0];
            int k = (int) test[1];
            int expected = (int) test[2];
            int result = subarraySumEqualsK(arr, k);
            String status = (result == expected) ? "✓" : "✗";
            System.out.println(status + " subarraySumEqualsK(" + Arrays.toString(arr) + ", k=" + k + ") = " + result);
        }
        
        System.out.println("\n=== Maximum Length Subarray Sum K ===");
        System.out.println("maxLen([1,-1,5,-2,3], k=3) = " + maxSubarrayLenEqualsK(new int[]{1,-1,5,-2,3}, 3));  // 4: [-1,5,-2,3]
        
        System.out.println("\n=== Subarray Sum Multiple of K (LC 523) ===");
        System.out.println("multiple([23,2,4,6,7], k=6) = " + checkSubarraySumMultipleK(new int[]{23,2,4,6,7}, 6));  // true: [2,4]
        
        System.out.println("\n=== Count Divisible by K (LC 974) ===");
        System.out.println("divisible([4,5,0,-2,-3,1], k=5) = " + subarraySumsDivisibleByK(new int[]{4,5,0,-2,-3,1}, 5));  // 7
    }
}
