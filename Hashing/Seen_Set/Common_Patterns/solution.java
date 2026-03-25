/**
 * Seen Set / Last Index / Deduplication - Hash Set Pattern
 * 
 * Problem: Use hash sets to track seen elements for duplicate detection,
 *          uniqueness checks, and state tracking.
 * 
 * Time Complexity: O(n) with O(1) lookups
 * Space Complexity: O(n)
 */

import java.util.*;

public class Solution {
    
    /**
     * Check if array contains duplicate values.
     */
    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num)) return true;
            seen.add(num);
        }
        return false;
    }
    
    /**
     * Find index of first non-repeating character.
     */
    public static int firstUniqueChar(String s) {
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
    
    /**
     * Find length of longest consecutive sequence.
     */
    public static int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) numSet.add(num);
        
        int longest = 0;
        
        for (int num : numSet) {
            // Only start counting if this is the beginning
            if (!numSet.contains(num - 1)) {
                int current = num;
                int length = 1;
                
                while (numSet.contains(current + 1)) {
                    current++;
                    length++;
                }
                
                longest = Math.max(longest, length);
            }
        }
        
        return longest;
    }
    
    /**
     * Find two indices where nums[i] + nums[j] = target.
     */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (seen.containsKey(complement)) {
                return new int[]{seen.get(complement), i};
            }
            seen.put(nums[i], i);
        }
        
        return null;
    }

    public static void main(String[] args) {
        System.out.println("Contains Duplicate: " + containsDuplicate(new int[]{1, 2, 3, 1}));
        System.out.println("First Unique Char: " + firstUniqueChar("leetcode"));
        System.out.println("Longest Consecutive: " + longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
        System.out.println("Two Sum: " + Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
    }
}
