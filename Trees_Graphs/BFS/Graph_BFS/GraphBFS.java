/**
 * BFS - BFS on Graph with Visited Tracking
 * 
 * Problem: Traverse a graph level-by-level starting from a source node.
 *          Find shortest path or explore all reachable nodes.
 * 
 * Pattern: Use queue for BFS. Maintain visited set to avoid cycles.
 *          Essential for shortest path in unweighted graphs.
 * 
 * Related LeetCode Problems:
 * - LC 133: Clone Graph (Medium) ⭐⭐
 * - LC 127: Word Ladder (Hard) ⭐⭐⭐
 * - LC 797: All Paths From Source to Target (Medium)
 * - LC 841: Keys and Rooms (Medium)
 * 
 * Time Complexity: O(V + E) - V vertices, E edges
 * Space Complexity: O(V) - visited set and queue
 */

// ─────────────────────────────────────────────────────────────────────────────
// NAIVE APPROACH (Brute Force) — O(V!) time | O(V) space
// ─────────────────────────────────────────────────────────────────────────────
// INTERVIEW SCRIPT:
//   1. Describe:   "Brute force uses DFS recursively without visited set
//                  — explores all paths, exponential branching"
//   2. Problem:    "In graphs with cycles: infinite recursion! Even with cycle check,
//                  explores exponentially many redundant paths"
//   3. Transition: "BFS with visited set processes each node once, level by level
//                  — O(V + E) linear time"
//
// public List<Integer> bfsNaive(Map<Integer, List<Integer>> graph, int start) {
//     List<Integer> result = new ArrayList<>();
//     Set<Integer> currentPath = new HashSet<>();
//     dfsExplore(graph, start, currentPath, result);
//     return result;
// }
// 
// private void dfsExplore(Map<Integer, List<Integer>> graph, int node,
//                        Set<Integer> path, List<Integer> result) {
//     if (path.contains(node)) return;  // Only cycle detection
//     result.add(node);
//     path.add(node);
//     for (int neighbor : graph.get(node)) {
//         dfsExplore(graph, neighbor, new HashSet<>(path), result);
//     }
// }
// ─────────────────────────────────────────────────────────────────────────────

import java.util.*;

public class GraphBFS {
    
    // Basic BFS on graph
    public List<Integer> bfsGraph(Map<Integer, List<Integer>> graph, int start) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        
        visited.add(start);
        queue.offer(start);
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);
            
            for (int neighbor : graph.get(node)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        
        return result;
    }
    
    // Find shortest path length
    public int bfsShortestPath(Map<Integer, List<Integer>> graph, int start, int target) {
        if (start == target) return 0;
        
        Set<Integer> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        
        visited.add(start);
        queue.offer(new int[]{start, 0});
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int node = current[0];
            int distance = current[1];
            
            for (int neighbor : graph.get(node)) {
                if (neighbor == target) {
                    return distance + 1;
                }
                
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(new int[]{neighbor, distance + 1});
                }
            }
        }
        
        return -1;
    }
    
    // LC 797: All Paths From Source to Target
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        int target = graph.length - 1;
        
        Queue<List<Integer>> queue = new LinkedList<>();
        List<Integer> initialPath = new ArrayList<>();
        initialPath.add(0);
        queue.offer(initialPath);
        
        while (!queue.isEmpty()) {
            List<Integer> path = queue.poll();
            int node = path.get(path.size() - 1);
            
            if (node == target) {
                result.add(new ArrayList<>(path));
                continue;
            }
            
            for (int neighbor : graph[node]) {
                List<Integer> newPath = new ArrayList<>(path);
                newPath.add(neighbor);
                queue.offer(newPath);
            }
        }
        
        return result;
    }
    
    // LC 841: Keys and Rooms
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        
        visited.add(0);
        queue.offer(0);
        
        while (!queue.isEmpty()) {
            int room = queue.poll();
            
            for (int key : rooms.get(room)) {
                if (!visited.contains(key)) {
                    visited.add(key);
                    queue.offer(key);
                }
            }
        }
        
        return visited.size() == rooms.size();
    }
    
    // Test
    public static void main(String[] args) {
        GraphBFS solution = new GraphBFS();
        
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1, 2));
        graph.put(1, Arrays.asList(0, 3));
        graph.put(2, Arrays.asList(0));
        graph.put(3, Arrays.asList(1));
        
        System.out.println("Basic BFS: " + solution.bfsGraph(graph, 0));
        System.out.println("Shortest path: " + solution.bfsShortestPath(graph, 0, 3));
        
        int[][] graph2 = {{1, 2}, {3}, {3}, {}};
        System.out.println("All paths: " + solution.allPathsSourceTarget(graph2));
    }
}
