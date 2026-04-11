/**
 * Topological Sort - Basic Kahn's Algorithm (BFS-based)
 * 
 * Problem: Given directed graph, find valid ordering where all edges go left-to-right.
 *          Example: edges = [[0,1],[0,2],[1,3],[2,3]] → [0,1,2,3] or [0,2,1,3]
 * 
 * Pattern: Track in-degrees, process nodes with 0 in-degree, remove edges
 * 
 * Related LeetCode Problems:
 * - LC 207: Course Schedule (Medium) ⭐⭐⭐
 * - LC 210: Course Schedule II (Medium) ⭐⭐⭐
 * - LC 802: Find Eventual Safe States (Medium)
 * 
 * Time Complexity: O(V + E) - visit each node and edge once
 * Space Complexity: O(V + E) - graph storage + queue
 */

// ─────────────────────────────────────────────────────────────────────────────
// NAIVE APPROACH (Brute Force) — O(V²) time | O(V + E) space
// ─────────────────────────────────────────────────────────────────────────────
// INTERVIEW SCRIPT:
//   1. Describe:   "Brute force repeatedly scans all nodes to find one with no incoming
//                  edges, adds it to result, removes it from graph — O(V²)"
//   2. Problem:    "For 1000 nodes: 1000 × 1000 = 1 million scans to check in-degrees"
//   3. Transition: "Pre-compute in-degrees once, use queue for 0-degree nodes — O(V+E)"
//
// public List<Integer> kahnsAlgorithmNaive(int n, int[][] edges) {
//     List<List<Integer>> graph = new ArrayList<>();
//     for (int i = 0; i < n; i++) {
//         graph.add(new ArrayList<>());
//     }
//     for (int[] edge : edges) {
//         graph.get(edge[0]).add(edge[1]);
//     }
//     
//     List<Integer> topoOrder = new ArrayList<>();
//     boolean[] removed = new boolean[n];
//     
//     while (topoOrder.size() < n) {
//         // Scan all nodes to find one with in-degree 0
//         int nodeWithZeroInDegree = -1;
//         for (int i = 0; i < n; i++) {
//             if (removed[i]) continue;
//             int inDegree = 0;
//             for (int j = 0; j < n; j++) {
//                 if (!removed[j] && graph.get(j).contains(i)) {
//                     inDegree++;
//                 }
//             }
//             if (inDegree == 0) {
//                 nodeWithZeroInDegree = i;
//                 break;
//             }
//         }
//         if (nodeWithZeroInDegree == -1) break; // Cycle detected
//         topoOrder.add(nodeWithZeroInDegree);
//         removed[nodeWithZeroInDegree] = true;
//     }
//     return topoOrder.size() == n ? topoOrder : new ArrayList<>();
// }
// ─────────────────────────────────────────────────────────────────────────────

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
