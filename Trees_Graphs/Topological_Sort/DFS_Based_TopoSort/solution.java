/**
 * Topological Sort - DFS-based Topological Sort
 * 
 * Related LeetCode Problems:
 * - LC 207: Course Schedule (Medium)
 * - LC 210: Course Schedule II (Medium)
 * - LC 851: Loud and Rich (Medium)
 * 
 * Time Complexity: O(V + E)
 * Space Complexity: O(V + E)
 */

import java.util.*;

public class DFSBasedTopoSort {
    
    // LC 207: Course Schedule (DFS version)
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] prereq : prerequisites) {
            graph.get(prereq[1]).add(prereq[0]);
        }
        
        int[] state = new int[numCourses];  // 0: unvisited, 1: visiting, 2: visited
        
        for (int i = 0; i < numCourses; i++) {
            if (state[i] == 0) {
                if (hasCycle(graph, state, i)) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private boolean hasCycle(List<List<Integer>> graph, int[] state, int node) {
        if (state[node] == 1) return true;   // Cycle detected
        if (state[node] == 2) return false;  // Already visited
        
        state[node] = 1;
        
        for (int neighbor : graph.get(node)) {
            if (hasCycle(graph, state, neighbor)) {
                return true;
            }
        }
        
        state[node] = 2;
        return false;
    }
    
    // LC 210: Course Schedule II (DFS version)
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] prereq : prerequisites) {
            graph.get(prereq[1]).add(prereq[0]);
        }
        
        int[] state = new int[numCourses];
        List<Integer> result = new ArrayList<>();
        boolean[] cycleDetected = new boolean[1];
        
        for (int i = 0; i < numCourses; i++) {
            if (state[i] == 0) {
                dfs(graph, state, i, result, cycleDetected);
                if (cycleDetected[0]) {
                    return new int[0];
                }
            }
        }
        
        // Reverse the result
        int[] order = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            order[i] = result.get(numCourses - 1 - i);
        }
        
        return order;
    }
    
    private void dfs(List<List<Integer>> graph, int[] state, int node, 
                     List<Integer> result, boolean[] cycleDetected) {
        if (state[node] == 1) {
            cycleDetected[0] = true;
            return;
        }
        if (state[node] == 2) return;
        
        state[node] = 1;
        
        for (int neighbor : graph.get(node)) {
            dfs(graph, state, neighbor, result, cycleDetected);
            if (cycleDetected[0]) return;
        }
        
        state[node] = 2;
        result.add(node);
    }
    
    // LC 851: Loud and Rich
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        List<List<Integer>> graph = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] r : richer) {
            graph.get(r[1]).add(r[0]);
        }
        
        int[] answer = new int[n];
        Arrays.fill(answer, -1);
        
        for (int i = 0; i < n; i++) {
            dfsQuiet(graph, quiet, answer, i);
        }
        
        return answer;
    }
    
    private int dfsQuiet(List<List<Integer>> graph, int[] quiet, int[] answer, int node) {
        if (answer[node] >= 0) return answer[node];
        
        answer[node] = node;
        
        for (int richerPerson : graph.get(node)) {
            int candidate = dfsQuiet(graph, quiet, answer, richerPerson);
            if (quiet[candidate] < quiet[answer[node]]) {
                answer[node] = candidate;
            }
        }
        
        return answer[node];
    }
    
    // Test
    public static void main(String[] args) {
        DFSBasedTopoSort solution = new DFSBasedTopoSort();
        
        System.out.println("Can finish: " + solution.canFinish(2, new int[][]{{1, 0}}));
        
        int[] order = solution.findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}});
        System.out.println("Course order: " + Arrays.toString(order));
        
        int[][] richer = {{1, 0}, {2, 1}, {3, 1}, {3, 7}, {4, 3}, {5, 3}, {6, 3}};
        int[] quiet = {3, 2, 5, 4, 6, 1, 7, 0};
        System.out.println("Loud and rich: " + Arrays.toString(solution.loudAndRich(richer, quiet)));
    }
}
