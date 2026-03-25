/**
 * Sum Divisible by K - Modulo Arithmetic Pattern
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(k)
 */

import java.util.*;

public class Solution {
    
    public static int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> remainderCount = new HashMap<>();
        remainderCount.put(0, 1);
        
        int count = 0;
        int prefixSum = 0;
        
        for (int num : nums) {
            prefixSum += num;
            int remainder = ((prefixSum % k) + k) % k;
            
            count += remainderCount.getOrDefault(remainder, 0);
            remainderCount.put(remainder, remainderCount.getOrDefault(remainder, 0) + 1);
        }
        
        return count;
    }
    
    public static int minSubarray(int[] nums, int p) {
        long totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        
        int targetRemainder = (int)(totalSum % p);
        
        if (targetRemainder == 0) return 0;
        
        Map<Integer, Integer> remainderMap = new HashMap<>();
        remainderMap.put(0, -1);
        
        int prefixSum = 0;
        int minLength = nums.length;
        
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            int currentRemainder = prefixSum % p;
            
            int needed = ((currentRemainder - targetRemainder) % p + p) % p;
            
            if (remainderMap.containsKey(needed)) {
                minLength = Math.min(minLength, i - remainderMap.get(needed));
            }
            
            remainderMap.put(currentRemainder, i);
        }
        
        return minLength < nums.length ? minLength : -1;
    }
    
    public static int countNicePairs(int[] nums) {
        final int MOD = 1_000_000_007;
        
        Map<Integer, Integer> diffCount = new HashMap<>();
        int count = 0;
        
        for (int num : nums) {
            int diff = num - reverse(num);
            count = (count + diffCount.getOrDefault(diff, 0)) % MOD;
            diffCount.put(diff, diffCount.getOrDefault(diff, 0) + 1);
        }
        
        return count;
    }
    
    private static int reverse(int n) {
        int result = 0;
        while (n > 0) {
            result = result * 10 + n % 10;
            n /= 10;
        }
        return result;
    }
    
    public static boolean checkSubarraySum(int[] nums, int k) {
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
    
    public static int waysToSplitArray(int[] nums) {
        long totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        
        long leftSum = 0;
        int count = 0;
        
        for (int i = 0; i < nums.length - 1; i++) {
            leftSum += nums[i];
            long rightSum = totalSum - leftSum;
            
            if (leftSum >= rightSum) {
                count++;
            }
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        System.out.println("Subarrays Div by K: " + subarraysDivByK(new int[]{4,5,0,-2,-3,1}, 5));
        System.out.println("Min Subarray: " + minSubarray(new int[]{3,1,4,2}, 6));
        System.out.println("Count Nice Pairs: " + countNicePairs(new int[]{42,11,1,97}));
        System.out.println("Check Subarray Sum: " + checkSubarraySum(new int[]{23,2,4,6,7}, 6));
        System.out.println("Ways to Split: " + waysToSplitArray(new int[]{10,4,-8,7}));
    }
}
