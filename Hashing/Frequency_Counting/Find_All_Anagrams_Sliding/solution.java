/**
 * Find All Anagrams - Sliding Window + Frequency
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

import java.util.*;

public class Solution {
    
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (p.length() > s.length()) return result;
        
        int[] pCount = new int[26];
        int[] sCount = new int[26];
        
        for (char c : p.toCharArray()) {
            pCount[c - 'a']++;
        }
        
        int windowSize = p.length();
        
        for (int i = 0; i < s.length(); i++) {
            sCount[s.charAt(i) - 'a']++;
            
            if (i >= windowSize) {
                sCount[s.charAt(i - windowSize) - 'a']--;
            }
            
            if (Arrays.equals(sCount, pCount)) {
                result.add(i - windowSize + 1);
            }
        }
        
        return result;
    }
    
    public static boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;
        
        int[] s1Count = new int[26];
        int[] s2Count = new int[26];
        
        for (char c : s1.toCharArray()) {
            s1Count[c - 'a']++;
        }
        
        for (int i = 0; i < s2.length(); i++) {
            s2Count[s2.charAt(i) - 'a']++;
            
            if (i >= s1.length()) {
                s2Count[s2.charAt(i - s1.length()) - 'a']--;
            }
            
            if (Arrays.equals(s1Count, s2Count)) {
                return true;
            }
        }
        
        return false;
    }
    
    public static String minWindow(String s, String t) {
        Map<Character, Integer> tCount = new HashMap<>();
        for (char c : t.toCharArray()) {
            tCount.put(c, tCount.getOrDefault(c, 0) + 1);
        }
        
        Map<Character, Integer> windowCount = new HashMap<>();
        int required = tCount.size();
        int formed = 0;
        
        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int minLeft = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            windowCount.put(c, windowCount.getOrDefault(c, 0) + 1);
            
            if (tCount.containsKey(c) && windowCount.get(c).equals(tCount.get(c))) {
                formed++;
            }
            
            while (left <= right && formed == required) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minLeft = left;
                }
                
                char leftChar = s.charAt(left);
                windowCount.put(leftChar, windowCount.get(leftChar) - 1);
                if (tCount.containsKey(leftChar) && windowCount.get(leftChar) < tCount.get(leftChar)) {
                    formed--;
                }
                
                left++;
            }
        }
        
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLen);
    }
    
    public static void main(String[] args) {
        System.out.println("Find Anagrams: " + findAnagrams("cbaebabacd", "abc"));
        System.out.println("Check Inclusion: " + checkInclusion("ab", "eidbaooo"));
        System.out.println("Min Window: " + minWindow("ADOBECODEBANC", "ABC"));
    }
}
