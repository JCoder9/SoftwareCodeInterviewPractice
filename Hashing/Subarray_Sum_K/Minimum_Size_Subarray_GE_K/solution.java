/**
 * Minimum Size Subarray Sum >= K
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

import java.util.*;

public class Solution {
    
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
    
    public static int minOperations(int[] nums, int x) {
        int target = -x;
        for (int num : nums) {
            target += num;
        }
        
        if (target < 0) return -1;
        if (target == 0) return nums.length;
        
        int left = 0;
        int currentSum = 0;
        int maxLength = -1;
        
        for (int right = 0; right < nums.length; right++) {
            currentSum += nums[right];
            
            while (currentSum > target && left <= right) {
                currentSum -= nums[left];
                left++;
            }
            
            if (currentSum == target) {
                maxLength = Math.max(maxLength, right - left + 1);
            }
        }
        
        return maxLength != -1 ? nums.length - maxLength : -1;
    }
    
    public static int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] prefix = new long[n + 1];
        
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        
        Deque<Integer> dq = new ArrayDeque<>();
        int minLength = Integer.MAX_VALUE;
        
        for (int i = 0; i <= n; i++) {
            while (!dq.isEmpty() && prefix[i] - prefix[dq.peekFirst()] >= k) {
                minLength = Math.min(minLength, i - dq.pollFirst());
            }
            
            while (!dq.isEmpty() && prefix[i] <= prefix[dq.peekLast()]) {
                dq.pollLast();
            }
            
            dq.offerLast(i);
        }
        
        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }
    
    public static int maxConsecutiveAnswers(String answerKey, int k) {
        return Math.max(maxLengthWithChar(answerKey, 'T', k),
                       maxLengthWithChar(answerKey, 'F', k));
    }
    
    private static int maxLengthWithChar(String s, char c, int k) {
        int left = 0;
        int flips = 0;
        int maxLen = 0;
        
        for (int right = 0; right < s.length(); right++) {
            if (s.charAt(right) != c) {
                flips++;
            }
            
            while (flips > k) {
                if (s.charAt(left) != c) {
                    flips--;
                }
                left++;
            }
            
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
    
    public static int longestOnes(int[] nums, int k) {
        int left = 0;
        int zeros = 0;
        int maxLength = 0;
        
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                zeros++;
            }
            
            while (zeros > k) {
                if (nums[left] == 0) {
                    zeros--;
                }
                left++;
            }
            
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
    
    public static int characterReplacement(String s, int k) {
        int[] count = new int[26];
        int left = 0;
        int maxFreq = 0;
        int maxLength = 0;
        
        for (int right = 0; right < s.length(); right++) {
            count[s.charAt(right) - 'A']++;
            maxFreq = Math.max(maxFreq, count[s.charAt(right) - 'A']);
            
            while ((right - left + 1) - maxFreq > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }
            
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
    
    public static String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0) return "";
        
        Map<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        
        Map<Character, Integer> have = new HashMap<>();
        int required = need.size();
        int formed = 0;
        
        int left = 0;
        int minLength = Integer.MAX_VALUE;
        int[] result = {0, 0};
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            have.put(c, have.getOrDefault(c, 0) + 1);
            
            if (need.containsKey(c) && have.get(c).intValue() == need.get(c).intValue()) {
                formed++;
            }
            
            while (formed == required && left <= right) {
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    result[0] = left;
                    result[1] = right;
                }
                
                char leftChar = s.charAt(left);
                have.put(leftChar, have.get(leftChar) - 1);
                if (need.containsKey(leftChar) && have.get(leftChar) < need.get(leftChar)) {
                    formed--;
                }
                
                left++;
            }
        }
        
        return minLength == Integer.MAX_VALUE ? "" : s.substring(result[0], result[1] + 1);
    }
    
    public static void main(String[] args) {
        System.out.println("Min Subarray Len: " + minSubArrayLen(7, new int[]{2,3,1,2,4,3}));
        System.out.println("Min Operations: " + minOperations(new int[]{1,1,4,2,3}, 5));
        System.out.println("Shortest Subarray: " + shortestSubarray(new int[]{2,-1,2}, 3));
        System.out.println("Max Consecutive Answers: " + maxConsecutiveAnswers("TTFF", 2));
        System.out.println("Longest Ones: " + longestOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0}, 2));
        System.out.println("Character Replacement: " + characterReplacement("ABAB", 2));
        System.out.println("Min Window: " + minWindow("ADOBECODEBANC", "ABC"));
    }
}
