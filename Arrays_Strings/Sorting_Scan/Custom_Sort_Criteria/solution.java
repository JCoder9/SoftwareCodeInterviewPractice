/**
 * Sort by Custom Criteria Pattern
 * 
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 */

import java.util.*;

public class Solution {
    
    public static String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        
        Arrays.sort(strs, (a, b) -> (b + a).compareTo(a + b));
        
        if (strs[0].equals("0")) return "0";
        
        return String.join("", strs);
    }
    
    public static int[] sortArrayByParity(int[] nums) {
        Integer[] boxed = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        Arrays.sort(boxed, (a, b) -> (a % 2) - (b % 2));
        return Arrays.stream(boxed).mapToInt(Integer::intValue).toArray();
    }
    
    public static void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;
        
        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                swap(nums, mid, high);
                high--;
            }
        }
    }
    
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    public static String customSortString(String order, String s) {
        Map<Character, Integer> orderMap = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            orderMap.put(order.charAt(i), i);
        }
        
        Character[] chars = new Character[s.length()];
        for (int i = 0; i < s.length(); i++) {
            chars[i] = s.charAt(i);
        }
        
        Arrays.sort(chars, (a, b) -> orderMap.getOrDefault(a, 26) - orderMap.getOrDefault(b, 26));
        
        StringBuilder result = new StringBuilder();
        for (char c : chars) {
            result.append(c);
        }
        
        return result.toString();
    }
    
    public static void main(String[] args) {
        System.out.println("Largest Number: " + largestNumber(new int[]{10,2}));
        System.out.println("Sort by Parity: " + Arrays.toString(sortArrayByParity(new int[]{3,1,2,4})));
        
        int[] colors = {2,0,2,1,1,0};
        sortColors(colors);
        System.out.println("Sort Colors: " + Arrays.toString(colors));
        
        System.out.println("Custom Sort: " + customSortString("cba", "abcd"));
    }
}
