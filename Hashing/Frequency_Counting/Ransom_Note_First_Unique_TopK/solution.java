/**
 * Ransom Note + First Unique Char + Top K Frequent
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(k)
 */

import java.util.*;

public class Solution {
    
    public static boolean canConstruct(String ransomNote, String magazine) {
        int[] count = new int[26];
        
        for (char c : magazine.toCharArray()) {
            count[c - 'a']++;
        }
        
        for (char c : ransomNote.toCharArray()) {
            if (count[c - 'a'] <= 0) return false;
            count[c - 'a']--;
        }
        
        return true;
    }
    
    public static int firstUniqChar(String s) {
        int[] count = new int[26];
        
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        
        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        
        return -1;
    }
    
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqCount = new HashMap<>();
        for (int num : nums) {
            freqCount.put(num, freqCount.getOrDefault(num, 0) + 1);
        }
        
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        
        for (Map.Entry<Integer, Integer> entry : freqCount.entrySet()) {
            heap.offer(new int[]{entry.getValue(), entry.getKey()});
            if (heap.size() > k) {
                heap.poll();
            }
        }
        
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = heap.poll()[1];
        }
        
        return result;
    }
    
    public static int majorityElement(int[] nums) {
        int candidate = 0, count = 0;
        
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }
        
        return candidate;
    }
    
    public static String frequencySort(String s) {
        Map<Character, Integer> freqCount = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqCount.put(c, freqCount.getOrDefault(c, 0) + 1);
        }
        
        List<Character> chars = new ArrayList<>(freqCount.keySet());
        chars.sort((a, b) -> freqCount.get(b) - freqCount.get(a));
        
        StringBuilder result = new StringBuilder();
        for (char c : chars) {
            int count = freqCount.get(c);
            for (int i = 0; i < count; i++) {
                result.append(c);
            }
        }
        
        return result.toString();
    }
    
    public static void main(String[] args) {
        System.out.println("Ransom Note: " + canConstruct("aa", "aab"));
        System.out.println("First Unique: " + firstUniqChar("leetcode"));
        System.out.println("Top K Frequent: " + Arrays.toString(topKFrequent(new int[]{1,1,1,2,2,3}, 2)));
        System.out.println("Majority Element: " + majorityElement(new int[]{3,2,3}));
        System.out.println("Frequency Sort: " + frequencySort("tree"));
    }
}
