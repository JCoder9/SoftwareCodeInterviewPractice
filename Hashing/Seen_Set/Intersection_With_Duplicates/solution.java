/**
 * Intersection With Duplicates - Count-Based Pattern
 * 
 * Time Complexity: O(n + m)
 * Space Complexity: O(min(n, m))
 */

import java.util.*;

public class Solution {
    
    public static int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums1) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        
        List<Integer> result = new ArrayList<>();
        for (int num : nums2) {
            if (count.getOrDefault(num, 0) > 0) {
                result.add(num);
                count.put(num, count.get(num) - 1);
            }
        }
        
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
    
    public static List<String> commonChars(String[] words) {
        int[] commonCount = new int[26];
        Arrays.fill(commonCount, Integer.MAX_VALUE);
        
        for (String word : words) {
            int[] wordCount = new int[26];
            for (char c : word.toCharArray()) {
                wordCount[c - 'a']++;
            }
            
            for (int i = 0; i < 26; i++) {
                commonCount[i] = Math.min(commonCount[i], wordCount[i]);
            }
        }
        
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < commonCount[i]; j++) {
                result.add(String.valueOf((char)(i + 'a')));
            }
        }
        
        return result;
    }
    
    public static String destCity(List<List<String>> paths) {
        Set<String> sources = new HashSet<>();
        for (List<String> path : paths) {
            sources.add(path.get(0));
        }
        
        for (List<String> path : paths) {
            if (!sources.contains(path.get(1))) {
                return path.get(1);
            }
        }
        
        return "";
    }
    
    public static boolean checkIfExist(int[] arr) {
        Set<Integer> seen = new HashSet<>();
        
        for (int num : arr) {
            if (seen.contains(num * 2) || (num % 2 == 0 && seen.contains(num / 2))) {
                return true;
            }
            seen.add(num);
        }
        
        return false;
    }
    
    public static int findLucky(int[] arr) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : arr) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        
        int lucky = -1;
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if (entry.getKey().equals(entry.getValue())) {
                lucky = Math.max(lucky, entry.getKey());
            }
        }
        
        return lucky;
    }
    
    public static int numJewelsInStones(String jewels, String stones) {
        Set<Character> jewelSet = new HashSet<>();
        for (char c : jewels.toCharArray()) {
            jewelSet.add(c);
        }
        
        int count = 0;
        for (char c : stones.toCharArray()) {
            if (jewelSet.contains(c)) {
                count++;
            }
        }
        
        return count;
    }
    
    public static int findPairs(int[] nums, int k) {
        if (k < 0) return 0;
        
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        
        int result = 0;
        for (int num : count.keySet()) {
            if (k == 0) {
                if (count.get(num) > 1) {
                    result++;
                }
            } else {
                if (count.containsKey(num + k)) {
                    result++;
                }
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println("Intersection II: " + Arrays.toString(intersect(new int[]{1,2,2,1}, new int[]{2,2})));
        System.out.println("Common Chars: " + commonChars(new String[]{"bella","label","roller"}));
        
        List<List<String>> paths = Arrays.asList(
            Arrays.asList("London","New York"),
            Arrays.asList("New York","Lima"),
            Arrays.asList("Lima","Sao Paulo")
        );
        System.out.println("Dest City: " + destCity(paths));
        
        System.out.println("Check If Exist: " + checkIfExist(new int[]{10,2,5,3}));
        System.out.println("Find Lucky: " + findLucky(new int[]{2,2,3,4}));
        System.out.println("Jewels in Stones: " + numJewelsInStones("aA", "aAAbbbb"));
        System.out.println("K-diff Pairs: " + findPairs(new int[]{3,1,4,1,5}, 2));
    }
}
