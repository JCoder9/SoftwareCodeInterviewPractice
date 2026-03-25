/**
 * 1D Dynamic Programming - Longest Increasing Subsequence (LIS)
 * 
 * Related LeetCode Problems:
 * - LC 300: Longest Increasing Subsequence (Medium)
 * - LC 673: Number of Longest Increasing Subsequence (Medium)
 * - LC 354: Russian Doll Envelopes (Hard)
 * - LC 1671: Minimum Number of Removals to Make Mountain Array (Hard)
 * 
 * Time Complexity: O(n²) or O(n log n) with binary search
 * Space Complexity: O(n)
 */

import java.util.*;

public class LongestIncreasingSubsequence {
    
    // LC 300: LIS (DP solution O(n²))
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) return 0;
        
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        
        return Arrays.stream(dp).max().getAsInt();
    }
    
    // LC 300: LIS (Binary Search O(n log n))
    public int lengthOfLISOptimized(int[] nums) {
        List<Integer> tails = new ArrayList<>();
        
        for (int num : nums) {
            int pos = Collections.binarySearch(tails, num);
            
            if (pos < 0) {
                pos = -(pos + 1);
            }
            
            if (pos == tails.size()) {
                tails.add(num);
            } else {
                tails.set(pos, num);
            }
        }
        
        return tails.size();
    }
    
    // LC 673: Number of LIS
    public int findNumberOfLIS(int[] nums) {
        if (nums.length == 0) return 0;
        
        int n = nums.length;
        int[] lengths = new int[n];
        int[] counts = new int[n];
        Arrays.fill(lengths, 1);
        Arrays.fill(counts, 1);
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (lengths[j] + 1 > lengths[i]) {
                        lengths[i] = lengths[j] + 1;
                        counts[i] = counts[j];
                    } else if (lengths[j] + 1 == lengths[i]) {
                        counts[i] += counts[j];
                    }
                }
            }
        }
        
        int maxLength = Arrays.stream(lengths).max().getAsInt();
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (lengths[i] == maxLength) {
                result += counts[i];
            }
        }
        
        return result;
    }
    
    // Longest Decreasing Subsequence
    public int longestDecreasingSubsequence(int[] nums) {
        if (nums.length == 0) return 0;
        
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        
        return Arrays.stream(dp).max().getAsInt();
    }
    
    // Test
    public static void main(String[] args) {
        LongestIncreasingSubsequence solution = new LongestIncreasingSubsequence();
        
        int[] nums1 = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("LIS length: " + solution.lengthOfLIS(nums1));
        System.out.println("LIS length (optimized): " + solution.lengthOfLISOptimized(nums1));
        
        int[] nums2 = {1, 3, 5, 4, 7};
        System.out.println("Number of LIS: " + solution.findNumberOfLIS(nums2));
        
        int[] nums3 = {5, 4, 6, 3, 7, 2};
        System.out.println("LDS length: " + solution.longestDecreasingSubsequence(nums3));
    }
}
