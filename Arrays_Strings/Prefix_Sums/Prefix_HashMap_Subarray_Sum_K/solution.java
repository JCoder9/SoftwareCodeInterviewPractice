/**
 * Prefix Sum with HashMap - Subarray Sum Equals K
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

import java.util.*;

public class Solution {
    
    public static int subarraySumEqualsK(int[] nums, int k) {
        int count = 0;
        int currentSum = 0;
        Map<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1);
        
        for (int num : nums) {
            currentSum += num;
            
            if (prefixCount.containsKey(currentSum - k)) {
                count += prefixCount.get(currentSum - k);
            }
            
            prefixCount.put(currentSum, prefixCount.getOrDefault(currentSum, 0) + 1);
        }
        
        return count;
    }
    
    public static int maxSubarrayLenEqualsK(int[] nums, int k) {
        int maxLen = 0;
        int currentSum = 0;
        Map<Integer, Integer> prefixMap = new HashMap<>();
        prefixMap.put(0, -1);
        
        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];
            
            if (prefixMap.containsKey(currentSum - k)) {
                maxLen = Math.max(maxLen, i - prefixMap.get(currentSum - k));
            }
            
            if (!prefixMap.containsKey(currentSum)) {
                prefixMap.put(currentSum, i);
            }
        }
        
        return maxLen;
    }
    
    public static boolean checkSubarraySumMultipleK(int[] nums, int k) {
        if (nums.length < 2) return false;
        
        Map<Integer, Integer> remainderMap = new HashMap<>();
        remainderMap.put(0, -1);
        int currentSum = 0;
        
        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];
            
            int remainder = (k != 0) ? currentSum % k : currentSum;
            
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
    
    public static int subarraySumsDivisibleByK(int[] nums, int k) {
        int count = 0;
        int currentSum = 0;
        Map<Integer, Integer> remainderCount = new HashMap<>();
        remainderCount.put(0, 1);
        
        for (int num : nums) {
            currentSum += num;
            int remainder = ((currentSum % k) + k) % k;
            
            count += remainderCount.getOrDefault(remainder, 0);
            remainderCount.put(remainder, remainderCount.getOrDefault(remainder, 0) + 1);
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        System.out.println("Subarray Sum = K: " + subarraySumEqualsK(new int[]{1,2,3,4,5}, 9));
        System.out.println("Max Len = K: " + maxSubarrayLenEqualsK(new int[]{1,-1,5,-2,3}, 3));
        System.out.println("Multiple of K: " + checkSubarraySumMultipleK(new int[]{23,2,4,6,7}, 6));
        System.out.println("Divisible by K: " + subarraySumsDivisibleByK(new int[]{4,5,0,-2,-3,1}, 5));
    }
}
