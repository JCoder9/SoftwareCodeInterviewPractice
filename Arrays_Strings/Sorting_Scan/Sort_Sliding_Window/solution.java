/**
 * Sort + Sliding Window Pattern
 * 
 * Time Complexity: O(n log n) + O(n)
 * Space Complexity: O(1)
 */

import java.util.*;

public class Solution {
    
    public static int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);
        int MOD = 1000000007;
        int left = 0, right = nums.length - 1;
        int count = 0;
        
        int[] pow2 = new int[nums.length];
        pow2[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            pow2[i] = (pow2[i - 1] * 2) % MOD;
        }
        
        while (left <= right) {
            if (nums[left] + nums[right] <= target) {
                count = (count + pow2[right - left]) % MOD;
                left++;
            } else {
                right--;
            }
        }
        
        return count;
    }
    
    public static int longestOnes(int[] nums, int k) {
        int left = 0, zeros = 0, maxLen = 0;
        
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) zeros++;
            
            while (zeros > k) {
                if (nums[left] == 0) zeros--;
                left++;
            }
            
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
    
    public static int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        
        int left = 0;
        long total = 0;
        int maxFreq = 0;
        
        for (int right = 0; right < nums.length; right++) {
            total += nums[right];
            
            while ((long) nums[right] * (right - left + 1) - total > k) {
                total -= nums[left];
                left++;
            }
            
            maxFreq = Math.max(maxFreq, right - left + 1);
        }
        
        return maxFreq;
    }
    
    public static void main(String[] args) {
        System.out.println("Num Subsequences: " + numSubseq(new int[]{3,5,6,7}, 9));
        System.out.println("Longest Ones: " + longestOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0}, 2));
        System.out.println("Max Frequency: " + maxFrequency(new int[]{1,2,4}, 5));
    }
}
