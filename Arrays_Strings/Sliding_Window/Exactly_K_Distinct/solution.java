/**
 * Count Subarrays with Exactly K Distinct - Trick: exactlyK = atMostK - atMost(K-1)
 * 
 * Problem: Count the number of subarrays with exactly K distinct integers.
 * 
 * Pattern: Use the "at most K" sliding window twice and subtract.
 *          Key insight: exactlyK(arr, K) = atMostK(arr, K) - atMostK(arr, K-1)
 * 
 * Time Complexity: O(n) - two linear passes
 * Space Complexity: O(k) - frequency map with at most k distinct elements
 */

import java.util.*;

public class Solution {

    // ─────────────────────────────────────────────────────────────────────────
    // NAIVE APPROACH (Brute Force) - O(n²) time | O(k) space
    // ─────────────────────────────────────────────────────────────────────────
    // INTERVIEW SCRIPT:
    //   1. Describe:   "Brute force checks every subarray and counts distinct elements
    //                   — O(n²) time with O(k) space for the set"
    //   2. Problem:    "For n=10⁴, we'd do ~10⁸ operations; needs optimization"
    //   3. Transition: "Key insight: exactlyK = atMostK - atMost(K-1). We use two
    //                   sliding windows to count in O(n) time instead"
    //
    // public static int exactlyKDistinctNaive(int[] nums, int k) {
    //     int count = 0;
    //     for (int i = 0; i < nums.length; i++) {
    //         Set<Integer> distinct = new HashSet<>();
    //         for (int j = i; j < nums.length; j++) {
    //             distinct.add(nums[j]);
    //             if (distinct.size() == k) count++;
    //             else if (distinct.size() > k) break;
    //         }
    //     }
    //     return count;
    // }
    // ─────────────────────────────────────────────────────────────────────────
    
    /**
     * Count subarrays with exactly K distinct integers.
     * 
     * @param nums Input array
     * @param k Exact number of distinct integers required
     * @return Count of valid subarrays
     */
    public static int subarraysWithExactlyKDistinct(int[] nums, int k) {
        if (nums.length == 0 || k <= 0) return 0;
        
        return atMostKDistinct(nums, k) - atMostKDistinct(nums, k - 1);
    }

    /**
     * Count subarrays with at most K distinct integers.
     */
    private static int atMostKDistinct(int[] nums, int k) {
        if (k < 0) return 0;

        Map<Integer, Integer> count = new HashMap<>();
        int left = 0;
        int res = 0;

        for (int right = 0; right < nums.length; right++) {
            int x = nums[right];
            count.put(x, count.getOrDefault(x, 0) + 1);

            // Shrink window while we have too many distinct integers
            while (count.size() > k) {
                int y = nums[left];
                count.put(y, count.get(y) - 1);
                if (count.get(y) == 0) {
                    count.remove(y);
                }
                left++;
            }

            // All subarrays ending at right with start in [left..right] are valid
            res += right - left + 1;
        }
        return res;
    }

    // Test cases
    public static void main(String[] args) {
        Object[][] testCases = {
            {new int[]{1, 2, 1, 2, 3}, 2, 7},
            {new int[]{1, 2, 1, 3, 4}, 3, 3},
            {new int[]{1}, 1, 1},
            {new int[]{1, 1, 1}, 1, 6}
        };
        
        for (Object[] test : testCases) {
            int[] nums = (int[]) test[0];
            int k = (int) test[1];
            int expected = (int) test[2];
            
            int result = subarraysWithExactlyKDistinct(nums, k);
            String status = (result == expected) ? "✓" : "✗";
            System.out.println(status + " subarraysWithExactlyKDistinct(" + Arrays.toString(nums) + 
                             ", k=" + k + ") = " + result + " (expected " + expected + ")");
        }
    }
}
