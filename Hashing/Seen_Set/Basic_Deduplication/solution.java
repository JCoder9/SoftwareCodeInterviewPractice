/**
 * Basic Deduplication - Remove Duplicates Preserving Order
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

import java.util.*;

public class Solution {
    
    public static List<Integer> removeDuplicates(int[] arr) {
        Set<Integer> seen = new HashSet<>();
        List<Integer> result = new ArrayList<>();
        
        for (int num : arr) {
            if (!seen.contains(num)) {
                seen.add(num);
                result.add(num);
            }
        }
        
        return result;
    }
    
    public static String removeDuplicateLetters(String s) {
        Map<Character, Integer> lastOccurrence = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            lastOccurrence.put(s.charAt(i), i);
        }
        
        Set<Character> seen = new HashSet<>();
        Deque<Character> stack = new ArrayDeque<>();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (seen.contains(c)) continue;
            
            while (!stack.isEmpty() && stack.peek() > c && lastOccurrence.get(stack.peek()) > i) {
                seen.remove(stack.pop());
            }
            
            stack.push(c);
            seen.add(c);
        }
        
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.removeLast());
        }
        
        return result.toString();
    }
    
    public static int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];
        
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        
        return slow;
    }
    
    public static int removeDuplicatesSorted(int[] nums) {
        if (nums.length == 0) return 0;
        
        int writeIdx = 1;
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[writeIdx] = nums[i];
                writeIdx++;
            }
        }
        
        return writeIdx;
    }
    
    public static void main(String[] args) {
        System.out.println("Remove Duplicates: " + removeDuplicates(new int[]{1,2,2,3,1,4}));
        System.out.println("Remove Duplicate Letters: " + removeDuplicateLetters("bcabc"));
        System.out.println("Find Duplicate: " + findDuplicate(new int[]{1,3,4,2,2}));
        
        int[] nums = {1,1,2};
        int k = removeDuplicatesSorted(nums);
        System.out.print("Remove Duplicates Sorted: Length=" + k + ", Array=");
        for (int i = 0; i < k; i++) System.out.print(nums[i] + " ");
        System.out.println();
    }
}
