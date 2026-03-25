/**
 * Two Arrays Intersection - Unique Elements Pattern
 * 
 * Time Complexity: O(n + m)
 * Space Complexity: O(min(n, m))
 */

import java.util.*;

public class Solution {
    
    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }
        
        Set<Integer> result = new HashSet<>();
        for (int num : nums2) {
            if (set1.contains(num)) {
                result.add(num);
            }
        }
        
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
    
    public static int[] intersectionTwoPointers(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        List<Integer> result = new ArrayList<>();
        int i = 0, j = 0;
        
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                if (result.isEmpty() || result.get(result.size() - 1) != nums1[i]) {
                    result.add(nums1[i]);
                }
                i++;
                j++;
            }
        }
        
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
    
    public static String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            indexMap.put(list1[i], i);
        }
        
        int minSum = Integer.MAX_VALUE;
        List<String> result = new ArrayList<>();
        
        for (int j = 0; j < list2.length; j++) {
            if (indexMap.containsKey(list2[j])) {
                int indexSum = indexMap.get(list2[j]) + j;
                
                if (indexSum < minSum) {
                    minSum = indexSum;
                    result.clear();
                    result.add(list2[j]);
                } else if (indexSum == minSum) {
                    result.add(list2[j]);
                }
            }
        }
        
        return result.toArray(new String[0]);
    }
    
    public static String[] findWords(String[] words) {
        Set<Character> row1 = new HashSet<>(Arrays.asList('q','w','e','r','t','y','u','i','o','p'));
        Set<Character> row2 = new HashSet<>(Arrays.asList('a','s','d','f','g','h','j','k','l'));
        Set<Character> row3 = new HashSet<>(Arrays.asList('z','x','c','v','b','n','m'));
        
        List<String> result = new ArrayList<>();
        
        for (String word : words) {
            String lower = word.toLowerCase();
            
            if (inRow(lower, row1) || inRow(lower, row2) || inRow(lower, row3)) {
                result.add(word);
            }
        }
        
        return result.toArray(new String[0]);
    }
    
    private static boolean inRow(String word, Set<Character> row) {
        for (char c : word.toCharArray()) {
            if (!row.contains(c)) {
                return false;
            }
        }
        return true;
    }
    
    public static String[] uncommonFromSentences(String s1, String s2) {
        Map<String, Integer> count = new HashMap<>();
        
        for (String word : s1.split(" ")) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        
        for (String word : s2.split(" ")) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : count.entrySet()) {
            if (entry.getValue() == 1) {
                result.add(entry.getKey());
            }
        }
        
        return result.toArray(new String[0]);
    }
    
    public static String removeVowels(String s) {
        Set<Character> vowels = new HashSet<>(Arrays.asList('a','e','i','o','u'));
        StringBuilder result = new StringBuilder();
        
        for (char c : s.toCharArray()) {
            if (!vowels.contains(c)) {
                result.append(c);
            }
        }
        
        return result.toString();
    }
    
    public static void main(String[] args) {
        System.out.println("Intersection: " + Arrays.toString(intersection(new int[]{1,2,2,1}, new int[]{2,2})));
        System.out.println("Find Restaurant: " + Arrays.toString(
            findRestaurant(
                new String[]{"Shogun","Tapioca Express","Burger King","KFC"},
                new String[]{"Piatti","The Grill at Torrey Pines","Hungry Hunter Steakhouse","Shogun"}
            )
        ));
        System.out.println("Keyboard Row: " + Arrays.toString(findWords(new String[]{"Hello","Alaska","Dad","Peace"})));
        System.out.println("Uncommon Words: " + Arrays.toString(uncommonFromSentences("this apple is sweet", "this apple is sour")));
        System.out.println("Remove Vowels: " + removeVowels("leetcodeisacommunityforcoders"));
    }
}
