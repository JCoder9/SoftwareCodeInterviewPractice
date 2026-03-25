/**
 * Topological Sort - Basic Kahn's Algorithm (BFS-based)
 * 
 * Related LeetCode Problems:
 * - LC 207: Course Schedule (Medium)
 * - LC 210: Course Schedule II (Medium)
 * - LC 802: Find Eventual Safe States (Medium)
 * 
 * Time Complexity: O(V + E)
 * Space Complexity: O(V + E)
 */

import java.util.*;

public class BasicKahnsAlgorithm {
    
    // Basic Kahn's algorithm
    public List<Integer> kahnsAlgorithm(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[n];
        
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            inDegree[edge[1]]++;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        List<Integer> topoOrder = new ArrayList<>();
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            topoOrder.add(node);
            
            for (int neighbor : graph.get(node)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        return topoOrder.size() == n ? topoOrder : new ArrayList<>();
    }
    
    // LC 207: Course Schedule
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] prereq : prerequisites) {
            graph.get(prereq[1]).add(prereq[0]);
            inDegree[prereq[0]]++;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int count = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            count++;
            
            for (int neighbor : graph.get(node)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        return count == numCourses;
    }
    
    // LC 210: Course Schedule II
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] prereq : prerequisites) {
            graph.get(prereq[1]).add(prereq[0]);
            inDegree[prereq[0]]++;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int[] order = new int[numCourses];
        int index = 0;
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            order[index++] = node;
            
            for (int neighbor : graph.get(node)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        return index == numCourses ? order : new int[0];
    }
    
    // Test
    public static void main(String[] args) {
        BasicKahnsAlgorithm solution = new BasicKahnsAlgorithm();
        
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {2, 3}};
        System.out.println("Topological order: " + solution.kahnsAlgorithm(4, edges));
        
        System.out.println("Can finish: " + solution.canFinish(2, new int[][]{{1, 0}}));
        
        int[] order = solution.findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}});
        System.out.println("Course order: " + Arrays.toString(order));
    }
}
