/**
 * Topological Sort - Alien Dictionary
 * 
 * Related LeetCode Problems:
 * - LC 269: Alien Dictionary (Hard) - Premium
 * - LC 953: Verifying an Alien Dictionary (Easy)
 * 
 * Time Complexity: O(C) where C is total characters
 * Space Complexity: O(1) for alphabet size
 */

import java.util.*;

public class AlienDictionary {
    
    // LC 269: Alien Dictionary
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();
        
        // Initialize
        for (String word : words) {
            for (char c : word.toCharArray()) {
                inDegree.put(c, 0);
                graph.put(c, new HashSet<>());
            }
        }
        
        // Build graph
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            int minLen = Math.min(word1.length(), word2.length());
            
            // Invalid case
            if (word1.length() > word2.length() && 
                word1.substring(0, minLen).equals(word2.substring(0, minLen))) {
                return "";
            }
            
            // Find first difference
            for (int j = 0; j < minLen; j++) {
                char c1 = word1.charAt(j);
                char c2 = word2.charAt(j);
                
                if (c1 != c2) {
                    if (!graph.get(c1).contains(c2)) {
                        graph.get(c1).add(c2);
                        inDegree.put(c2, inDegree.get(c2) + 1);
                    }
                    break;
                }
            }
        }
        
        // Kahn's algorithm
        Queue<Character> queue = new LinkedList<>();
        for (char c : inDegree.keySet()) {
            if (inDegree.get(c) == 0) {
                queue.offer(c);
            }
        }
        
        StringBuilder result = new StringBuilder();
        
        while (!queue.isEmpty()) {
            char c = queue.poll();
            result.append(c);
            
            for (char neighbor : graph.get(c)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        return result.length() == inDegree.size() ? result.toString() : "";
    }
    
    // LC 953: Verifying an Alien Dictionary
    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> orderMap = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            orderMap.put(order.charAt(i), i);
        }
        
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            
            for (int j = 0; j < word1.length(); j++) {
                if (j >= word2.length()) {
                    return false;
                }
                
                if (word1.charAt(j) != word2.charAt(j)) {
                    if (orderMap.get(word1.charAt(j)) > orderMap.get(word2.charAt(j))) {
                        return false;
                    }
                    break;
                }
            }
        }
        
        return true;
    }
    
    // Test
    public static void main(String[] args) {
        AlienDictionary solution = new AlienDictionary();
        
        String[] words1 = {"wrt", "wrf", "er", "ett", "rftt"};
        System.out.println("Alien order: " + solution.alienOrder(words1));
        
        String[] words2 = {"z", "x"};
        System.out.println("Alien order: " + solution.alienOrder(words2));
        
        String[] words3 = {"abc", "ab"};
        System.out.println("Alien order (invalid): " + solution.alienOrder(words3));
        
        String[] words4 = {"hello", "leetcode"};
        String order = "hlabcdefgijkmnopqrstuvwxyz";
        System.out.println("Is alien sorted: " + solution.isAlienSorted(words4, order));
    }
}
