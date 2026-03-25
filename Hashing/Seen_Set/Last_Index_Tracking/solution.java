/**
 * Last Index Tracking - First Unique Character Pattern
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(k) where k is unique characters
 */

import java.util.*;

public class Solution {
    
    public static int firstUniqChar(String s) {
        Map<Character, Integer> count = new HashMap<>();
        
        for (char c : s.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        
        for (int i = 0; i < s.length(); i++) {
            if (count.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        
        return -1;
    }
    
    public static int uniqueMorseRepresentations(String[] words) {
        String[] morse = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---",
                         "-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-",
                         "..-","...-",".--","-..-","-.--","--.."};
        
        Set<String> transformations = new HashSet<>();
        
        for (String word : words) {
            StringBuilder code = new StringBuilder();
            for (char c : word.toCharArray()) {
                code.append(morse[c - 'a']);
            }
            transformations.add(code.toString());
        }
        
        return transformations.size();
    }
    
    public static String frequencySort(String s) {
        Map<Character, Integer> count = new HashMap<>();
        for (char c : s.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        
        List<Character>[] buckets = new List[s.length() + 1];
        for (char c : count.keySet()) {
            int freq = count.get(c);
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(c);
        }
        
        StringBuilder result = new StringBuilder();
        for (int i = buckets.length - 1; i >= 0; i--) {
            if (buckets[i] != null) {
                for (char c : buckets[i]) {
                    for (int j = 0; j < i; j++) {
                        result.append(c);
                    }
                }
            }
        }
        
        return result.toString();
    }
    
    public static int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> first = new HashMap<>();
        Map<Integer, Integer> last = new HashMap<>();
        Map<Integer, Integer> count = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (!first.containsKey(num)) {
                first.put(num, i);
            }
            last.put(num, i);
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        
        int degree = Collections.max(count.values());
        int minLength = nums.length;
        
        for (int num : count.keySet()) {
            if (count.get(num) == degree) {
                int length = last.get(num) - first.get(num) + 1;
                minLength = Math.min(minLength, length);
            }
        }
        
        return minLength;
    }
    
    public static int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        
        int maxLength = 0;
        
        for (int num : numSet) {
            if (!numSet.contains(num - 1)) {
                int current = num;
                int length = 1;
                
                while (numSet.contains(current + 1)) {
                    current++;
                    length++;
                }
                
                maxLength = Math.max(maxLength, length);
            }
        }
        
        return maxLength;
    }
    
    static class Logger {
        private Map<String, Integer> lastSeen;
        
        public Logger() {
            lastSeen = new HashMap<>();
        }
        
        public boolean shouldPrintMessage(int timestamp, String message) {
            if (!lastSeen.containsKey(message)) {
                lastSeen.put(message, timestamp);
                return true;
            }
            
            if (timestamp - lastSeen.get(message) >= 10) {
                lastSeen.put(message, timestamp);
                return true;
            }
            
            return false;
        }
    }
    
    public static void main(String[] args) {
        System.out.println("First Unique: " + firstUniqChar("leetcode"));
        System.out.println("Unique Morse: " + uniqueMorseRepresentations(new String[]{"gin","zen","gig","msg"}));
        System.out.println("Frequency Sort: " + frequencySort("tree"));
        System.out.println("Shortest Subarray: " + findShortestSubArray(new int[]{1,2,2,3,1}));
        System.out.println("Longest Consecutive: " + longestConsecutive(new int[]{100,4,200,1,3,2}));
        
        Logger logger = new Logger();
        System.out.println("Logger 1: " + logger.shouldPrintMessage(1, "foo"));
        System.out.println("Logger 3: " + logger.shouldPrintMessage(3, "foo"));
        System.out.println("Logger 11: " + logger.shouldPrintMessage(11, "foo"));
    }
}
