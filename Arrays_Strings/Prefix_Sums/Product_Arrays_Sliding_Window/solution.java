/**
 * Product Except Self - Prefix/Suffix Product Pattern
 * 
 * Problem: Given array nums, return array where output[i] = product of all elements except nums[i].
 *          Must run in O(n) without using division.
 * 
 * Pattern: Use prefix products (left) and suffix products (right).
 *          result[i] = prefix[i-1] × suffix[i+1]
 * 
 * Related LeetCode Problems:
 * - LC 238: Product of Array Except Self (Medium) ⭐⭐⭐
 * - LC 152: Maximum Product Subarray (Medium)
 * - LC 713: Subarray Product Less Than K (Medium)
 * 
 * Time Complexity: O(n) - two passes
 * Space Complexity: O(1) - output array doesn't count
 */

// ─────────────────────────────────────────────────────────────────────────────
// NAIVE APPROACH (Brute Force) — O(n²) time | O(1) space
// ─────────────────────────────────────────────────────────────────────────────
// INTERVIEW SCRIPT:
//   1. Describe:   "Brute force calculates product for each position by multiplying
//                  all other elements — nested loops give O(n²)"
//   2. Problem:    "For n=10,000: 100 million operations; too slow for large arrays"
//   3. Transition: "With prefix/suffix products, precompute left and right products
//                  — reduces to O(n) with two passes"
//
// public static int[] productExceptSelfNaive(int[] nums) {
//     int n = nums.length;
//     int[] result = new int[n];
//     
//     for (int i = 0; i < n; i++) {
//         int product = 1;
//         for (int j = 0; j < n; j++) {
//             if (i != j) {
//                 product *= nums[j];
//             }
//         }
//         result[i] = product;
//     }
//     return result;
// }
// ─────────────────────────────────────────────────────────────────────────────

import java.util.*;

public class Solution {
    
    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0;
        
        int count = 0, product = 1, left = 0;
        
        for (int right = 0; right < nums.length; right++) {
            product *= nums[right];
            
            while (product >= k) {
                product /= nums[left];
                left++;
            }
            
            count += (right - left + 1);
        }
        
        return count;
    }
    
    public static int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;
        
        int maxSoFar = nums[0];
        int maxEndingHere = nums[0];
        int minEndingHere = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            
            if (num < 0) {
                int temp = maxEndingHere;
                maxEndingHere = minEndingHere;
                minEndingHere = temp;
            }
            
            maxEndingHere = Math.max(num, maxEndingHere * num);
            minEndingHere = Math.min(num, minEndingHere * num);
            
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        
        return maxSoFar;
    }
    
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        
        // Build prefix into result
        int prefix = 1;
        for (int i = 0; i < n; i++) {
            result[i] = prefix;
            prefix *= nums[i];
        }
        
        // Multiply by suffix
        int suffix = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= suffix;
            suffix *= nums[i];
        }
        
        return result;
    }
    
    public static int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        
        return Math.max(
            nums[n-1] * nums[n-2] * nums[n-3],
            nums[0] * nums[1] * nums[n-1]
        );
    }
    
    public static void main(String[] args) {
        System.out.println("Product < K: " + numSubarrayProductLessThanK(new int[]{10,5,2,6}, 100));
        System.out.println("Max Product: " + maxProduct(new int[]{2,3,-2,4}));
        System.out.println("Product Except Self: " + Arrays.toString(productExceptSelf(new int[]{1,2,3,4})));
        System.out.println("Max Product of Three: " + maximumProduct(new int[]{1,2,3}));
    }
}
