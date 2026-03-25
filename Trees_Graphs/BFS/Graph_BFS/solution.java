/**
 * BFS - BFS on Graph with Visited Tracking
 * 
 * Related LeetCode Problems:
 * - LC 133: Clone Graph (Medium)
 * - LC 797: All Paths From Source to Target (Medium)
 * - LC 841: Keys and Rooms (Medium)
 * 
 * Time Complexity: O(V + E)
 * Space Complexity: O(V)
 */

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
