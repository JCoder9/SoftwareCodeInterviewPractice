/**
 * Longest Subarray Sum K - Positive Numbers Only
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

import java.util.*;

public class Solution {
    
    public static int maxSubArrayLenPositive(int[] nums, int k) {
        int left = 0;
        int currentSum = 0;
        int maxLength = 0;
        
        for (int right = 0; right < nums.length; right++) {
            currentSum += nums[right];
            
            while (currentSum > k && left <= right) {
                currentSum -= nums[left];
                left++;
            }
            
            if (currentSum == k) {
                maxLength = Math.max(maxLength, right - left + 1);
            }
        }
        
        return maxLength;
    }
    
    public static int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int currentSum = 0;
        int minLength = Integer.MAX_VALUE;
        
        for (int right = 0; right < nums.length; right++) {
            currentSum += nums[right];
            
            while (currentSum >= target) {
                minLength = Math.min(minLength, right - left + 1);
                currentSum -= nums[left];
                left++;
            }
        }
        
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
    
    public static int numSubarraysWithSum(int[] nums, int goal) {
        return atMost(nums, goal) - atMost(nums, goal - 1);
    }
    
    private static int atMost(int[] nums, int target) {
        if (target < 0) return 0;
        
        int left = 0;
        int currentSum = 0;
        int count = 0;
        
        for (int right = 0; right < nums.length; right++) {
            currentSum += nums[right];
            
            while (currentSum > target) {
                currentSum -= nums[left];
                left++;
            }
            
            count += right - left + 1;
        }
        
        return count;
    }
    
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k == 0) return 0;
        
        int left = 0;
        Map<Character, Integer> charCount = new HashMap<>();
        int maxLength = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
            
            while (charCount.size() > k) {
                char leftChar = s.charAt(left);
                charCount.put(leftChar, charCount.get(leftChar) - 1);
                if (charCount.get(leftChar) == 0) {
                    charCount.remove(leftChar);
                }
                left++;
            }
            
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
    
    public static int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int totalSum = 0;
        for (int card : cardPoints) {
            totalSum += card;
        }
        
        if (k == n) return totalSum;
        
        int windowSize = n - k;
        int currentSum = 0;
        for (int i = 0; i < windowSize; i++) {
            currentSum += cardPoints[i];
        }
        
        int minSum = currentSum;
        
        for (int i = windowSize; i < n; i++) {
            currentSum += cardPoints[i] - cardPoints[i - windowSize];
            minSum = Math.min(minSum, currentSum);
        }
        
        return totalSum - minSum;
    }
    
    public static int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int n = nums.length;
        int[] prefix = new int[n + 1];
        
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        
        int maxSum = 0;
        
        // firstLen before secondLen
        int maxFirst = 0;
        for (int i = firstLen; i <= n - secondLen; i++) {
            maxFirst = Math.max(maxFirst, prefix[i] - prefix[i - firstLen]);
            maxSum = Math.max(maxSum, maxFirst + prefix[i + secondLen] - prefix[i]);
        }
        
        // secondLen before firstLen
        int maxSecond = 0;
        for (int i = secondLen; i <= n - firstLen; i++) {
            maxSecond = Math.max(maxSecond, prefix[i] - prefix[i - secondLen]);
            maxSum = Math.max(maxSum, maxSecond + prefix[i + firstLen] - prefix[i]);
        }
        
        return maxSum;
    }
    
    public static void main(String[] args) {
        System.out.println("Longest Subarray Sum K: " + maxSubArrayLenPositive(new int[]{1,2,3,4,5}, 9));
        System.out.println("Min Subarray Len: " + minSubArrayLen(7, new int[]{2,3,1,2,4,3}));
        System.out.println("Binary Subarrays: " + numSubarraysWithSum(new int[]{1,0,1,0,1}, 2));
        System.out.println("K Distinct: " + lengthOfLongestSubstringKDistinct("eceba", 2));
        System.out.println("Max Score Cards: " + maxScore(new int[]{1,2,3,4,5,6,1}, 3));
        System.out.println("Max Sum Two No Overlap: " + maxSumTwoNoOverlap(new int[]{0,6,5,2,2,5,1,9,4}, 1, 2));
    }
}
