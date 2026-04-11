/**
 * XOR Prefix - Find Subarrays with XOR = K
 * 
 * Problem: Count/find subarrays where XOR equals K.
 *          Example: nums = [4,2,2,6,4], k = 6 → 4 subarrays
 * 
 * Key Insight: XOR property: a ^ b = k means a ^ k = b!
 *              Track prefix XOR values in hashmap.
 * 
 * Related LeetCode Problems:
 * - LC 1310: XOR Queries of a Subarray (Medium) ⭐⭐
 * - LC 1442: Count Triplets That Can Form Two Arrays of Equal XOR (Medium)
 * - LC 136: Single Number (Easy) ⭐⭐⭐
 * 
 * Property: prefix_xor[j] ^ prefix_xor[i] = XOR of subarray from i+1 to j
 * 
 * Time Complexity: O(n) - single pass with hashmap lookups
 * Space Complexity: O(n) - hashmap storage
 */

// ─────────────────────────────────────────────────────────────────────────────
// NAIVE APPROACH (Brute Force) — O(n²) time | O(1) space
// ─────────────────────────────────────────────────────────────────────────────
// INTERVIEW SCRIPT:
//   1. Describe:   "Brute force checks all subarrays with nested loops, computes XOR
//                  for each — O(n²)"
//   2. Problem:    "For n=1000: 1000² = 1M subarray checks; redundant XOR
//                  computations"
//   3. Transition: "Use prefix XOR with hashmap to find matching subarrays in
//                  one pass — O(n)"
//
// public int countSubarraysXorKNaive(int[] nums, int k) {
//     int count = 0;
//     int n = nums.length;
//     for (int i = 0; i < n; i++) {
//         int xorVal = 0;
//         for (int j = i; j < n; j++) {
//             xorVal ^= nums[j];
//             if (xorVal == k) {
//                 count++;
//             }
//         }
//     }
//     return count;
// }
// ─────────────────────────────────────────────────────────────────────────────

import java.util.*;

public class Solution {
    
    public static int countSubarraysXorK(int[] nums, int k) {
        int count = 0;
        int xorPrefix = 0;
        Map<Integer, Integer> prefixMap = new HashMap<>();
        prefixMap.put(0, 1);
        
        for (int num : nums) {
            xorPrefix ^= num;
            
            int target = xorPrefix ^ k;
            if (prefixMap.containsKey(target)) {
                count += prefixMap.get(target);
            }
            
            prefixMap.put(xorPrefix, prefixMap.getOrDefault(xorPrefix, 0) + 1);
        }
        
        return count;
    }
    
    public static int[] xorQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] prefixXor = new int[n + 1];
        
        for (int i = 0; i < n; i++) {
            prefixXor[i + 1] = prefixXor[i] ^ nums[i];
        }
        
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int left = queries[i][0], right = queries[i][1];
            result[i] = prefixXor[right + 1] ^ prefixXor[left];
        }
        
        return result;
    }
    
    public static int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }
    
    public static int[] singleNumberIII(int[] nums) {
        int xorAll = 0;
        for (int num : nums) {
            xorAll ^= num;
        }
        
        int rightmostBit = xorAll & (-xorAll);
        
        int a = 0, b = 0;
        for (int num : nums) {
            if ((num & rightmostBit) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        
        return new int[]{a, b};
    }
    
    public static int missingNumber(int[] nums) {
        int xorAll = 0;
        int n = nums.length;
        
        for (int i = 0; i <= n; i++) {
            xorAll ^= i;
        }
        
        for (int num : nums) {
            xorAll ^= num;
        }
        
        return xorAll;
    }
    
    public static void main(String[] args) {
        System.out.println("Count XOR = K: " + countSubarraysXorK(new int[]{4,2,2,6,4}, 6));
        System.out.println("XOR Queries: " + Arrays.toString(xorQueries(new int[]{1,3,4,8}, new int[][]{{0,1},{1,2},{0,3},{3,3}})));
        System.out.println("Single Number: " + singleNumber(new int[]{4,1,2,1,2}));
        System.out.println("Single Number III: " + Arrays.toString(singleNumberIII(new int[]{1,2,1,3,2,5})));
        System.out.println("Missing Number: " + missingNumber(new int[]{3,0,1}));
    }
}
