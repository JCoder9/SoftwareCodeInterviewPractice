/**
 * BFS - Bidirectional BFS (Meet in the Middle)
 * 
 * Problem: Find shortest path in large search space.
 *          Example: Word Ladder, Open the Lock
 * 
 * Pattern: BFS from both start AND end simultaneously, meet in middle
 * 
 * Related LeetCode Problems:
 * - LC 127: Word Ladder (Hard) ⭐⭐⭐
 * - LC 752: Open the Lock (Medium)
 * - LC 433: Minimum Genetic Mutation (Medium)
 * 
 * Key Insight: Search space grows exponentially - O(b^d) becomes O(b^(d/2))
 * 
 * Time Complexity: O(b^(d/2)) - much better than O(b^d)
 * Space Complexity: O(b^(d/2))
 */

// ─────────────────────────────────────────────────────────────────────────────
// NAIVE APPROACH (Brute Force) — O(b^d) time | O(b^d) space
// ─────────────────────────────────────────────────────────────────────────────
// INTERVIEW SCRIPT:
//   1. Describe:   "Standard BFS from start explores all nodes level-by-level —
//                  O(b^d) where b=branching, d=depth"
//   2. Problem:    "For b=10, d=6: 10^6 = 1M states; for d=8: 100M states"
//   3. Transition: "BFS from both ends meets in middle — O(b^(d/2)); for d=6:
//                  2×10^3 vs 1M"
//
// public int bfsNaive(Map<Integer, List<Integer>> graph, int start, int end) {
//     Queue<int[]> queue = new LinkedList<>();
//     Set<Integer> visited = new HashSet<>();
//     queue.offer(new int[]{start, 0});
//     visited.add(start);
//     
//     while (!queue.isEmpty()) {
//         int[] curr = queue.poll();
//         if (curr[0] == end) return curr[1];
//         for (int neighbor : graph.get(curr[0])) {
//             if (!visited.contains(neighbor)) {
//                 visited.add(neighbor);
//                 queue.offer(new int[]{neighbor, curr[1] + 1});
//             }
//         }
//     }
//     return -1;
// }
// ─────────────────────────────────────────────────────────────────────────────

import java.util.*;

public class BidirectionalBFS {
    
    public int bidirectionalBFS(Map<Integer, List<Integer>> graph, int start, int end) {
        if (start == end) return 0;
        
        Queue<int[]> queueStart = new LinkedList<>();
        Queue<int[]> queueEnd = new LinkedList<>();
        
        Map<Integer, Integer> visitedStart = new HashMap<>();
        Map<Integer, Integer> visitedEnd = new HashMap<>();
        
        queueStart.offer(new int[]{start, 0});
        queueEnd.offer(new int[]{end, 0});
        visitedStart.put(start, 0);
        visitedEnd.put(end, 0);
        
        while (!queueStart.isEmpty() || !queueEnd.isEmpty()) {
            if (!queueStart.isEmpty()) {
                int[] current = queueStart.poll();
                int node = current[0], dist = current[1];
                
                for (int neighbor : graph.get(node)) {
                    if (visitedEnd.containsKey(neighbor)) {
                        return dist + 1 + visitedEnd.get(neighbor);
                    }
                    
                    if (!visitedStart.containsKey(neighbor)) {
                        visitedStart.put(neighbor, dist + 1);
                        queueStart.offer(new int[]{neighbor, dist + 1});
                    }
                }
            }
            
            if (!queueEnd.isEmpty()) {
                int[] current = queueEnd.poll();
                int node = current[0], dist = current[1];
                
                for (int neighbor : graph.get(node)) {
                    if (visitedStart.containsKey(neighbor)) {
                        return dist + 1 + visitedStart.get(neighbor);
                    }
                    
                    if (!visitedEnd.containsKey(neighbor)) {
                        visitedEnd.put(neighbor, dist + 1);
                        queueEnd.offer(new int[]{neighbor, dist + 1});
                    }
                }
            }
        }
        
        return -1;
    }
    
    // LC 752: Open the Lock
    public int openLock(String[] deadends, String target) {
        if (target.equals("0000")) return 0;
        
        Set<String> dead = new HashSet<>(Arrays.asList(deadends));
        if (dead.contains("0000")) return -1;
        
        Set<String> startSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        Set<String> visited = new HashSet<>();
        
        startSet.add("0000");
        endSet.add(target);
        visited.add("0000");
        visited.add(target);
        
        int steps = 0;
        
        while (!startSet.isEmpty() && !endSet.isEmpty()) {
            if (startSet.size() > endSet.size()) {
                Set<String> temp = startSet;
                startSet = endSet;
                endSet = temp;
            }
            
            Set<String> nextSet = new HashSet<>();
            
            for (String code : startSet) {
                for (String neighbor : getNeighbors(code)) {
                    if (endSet.contains(neighbor)) {
                        return steps + 1;
                    }
                    
                    if (!visited.contains(neighbor) && !dead.contains(neighbor)) {
                        visited.add(neighbor);
                        nextSet.add(neighbor);
                    }
                }
            }
            
            startSet = nextSet;
            steps++;
        }
        
        return -1;
    }
    
    private List<String> getNeighbors(String code) {
        List<String> result = new ArrayList<>();
        char[] chars = code.toCharArray();
        
        for (int i = 0; i < 4; i++) {
            char original = chars[i];
            
            chars[i] = original == '9' ? '0' : (char)(original + 1);
            result.add(new String(chars));
            
            chars[i] = original == '0' ? '9' : (char)(original - 1);
            result.add(new String(chars));
            
            chars[i] = original;
        }
        
        return result;
    }
    
    // Test
    public static void main(String[] args) {
        BidirectionalBFS solution = new BidirectionalBFS();
        
        String[] deadends = {"0201", "0101", "0102", "1212", "2002"};
        System.out.println("Open lock: " + solution.openLock(deadends, "0202"));
    }
}
