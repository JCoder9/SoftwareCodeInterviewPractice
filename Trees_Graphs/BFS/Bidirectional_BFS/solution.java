/**
 * BFS - Bidirectional BFS
 * 
 * Related LeetCode Problems:
 * - LC 127: Word Ladder (Hard)
 * - LC 752: Open the Lock (Medium)
 * 
 * Time Complexity: O(b^(d/2))
 * Space Complexity: O(b^(d/2))
 */

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
