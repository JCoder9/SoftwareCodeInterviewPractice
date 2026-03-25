/**
 * K Distinct Characters - Sliding Window Pattern
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(k)
 */

import java.util.*;

public class Solution {
    
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k == 0) return 0;
        
        Map<Character, Integer> charCount = new HashMap<>();
        int left = 0, maxLen = 0;
        
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
            
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
    
    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> charIndex = new HashMap<>();
        int left = 0, maxLen = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            
            if (charIndex.containsKey(c) && charIndex.get(c) >= left) {
                left = charIndex.get(c) + 1;
            }
            
            charIndex.put(c, right);
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
    
    public static int characterReplacement(String s, int k) {
        Map<Character, Integer> count = new HashMap<>();
        int left = 0, maxFreq = 0, maxLen = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            count.put(c, count.getOrDefault(c, 0) + 1);
            maxFreq = Math.max(maxFreq, count.get(c));
            
            while ((right - left + 1) - maxFreq > k) {
                char leftChar = s.charAt(left);
                count.put(leftChar, count.get(leftChar) - 1);
                left++;
            }
            
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
    
    public static int totalFruit(int[] fruits) {
        Map<Integer, Integer> basket = new HashMap<>();
        int left = 0, maxFruits = 0;
        
        for (int right = 0; right < fruits.length; right++) {
            basket.put(fruits[right], basket.getOrDefault(fruits[right], 0) + 1);
            
            while (basket.size() > 2) {
                basket.put(fruits[left], basket.get(fruits[left]) - 1);
                if (basket.get(fruits[left]) == 0) {
                    basket.remove(fruits[left]);
                }
                left++;
            }
            
            maxFruits = Math.max(maxFruits, right - left + 1);
        }
        
        return maxFruits;
    }
    
    public static void main(String[] args) {
        System.out.println("K Distinct: " + lengthOfLongestSubstringKDistinct("eceba", 2));
        System.out.println("No Repeating: " + lengthOfLongestSubstring("abcabcbb"));
        System.out.println("Character Replacement: " + characterReplacement("ABAB", 2));
        System.out.println("Total Fruit: " + totalFruit(new int[]{1,2,1}));
    }
}
