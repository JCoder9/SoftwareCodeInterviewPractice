/**
 * Topological Sort - Dependency Resolution
 * 
 * Time Complexity: O(V + E)
 * Space Complexity: O(V + E)
 */

import java.util.*;

public class Solution {
    
    public static List<Integer> topologicalSortKahn(int numNodes, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] inDegree = new int[numNodes];
        
        for (int i = 0; i < numNodes; i++) {
            graph.put(i, new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            inDegree[edge[1]]++;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numNodes; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);
            
            for (int neighbor : graph.get(node)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        return result.size() == numNodes ? result : new ArrayList<>();
    }
    
    public static boolean canFinishCourses(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] inDegree = new int[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new ArrayList<>());
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
            int course = queue.poll();
            count++;
            
            for (int next : graph.get(course)) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        
        return count == numCourses;
    }

    public static void main(String[] args) {
        System.out.println("Can finish [[1,0]]: " + canFinishCourses(2, new int[][]{{1, 0}}));
        System.out.println("Can finish [[1,0],[0,1]]: " + canFinishCourses(2, new int[][]{{1, 0}, {0, 1}}));
        
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {2, 3}};
        System.out.println("Topological sort: " + topologicalSortKahn(4, edges));
    }
}
