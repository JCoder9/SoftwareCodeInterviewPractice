/**
 * Topological Sort - All Topological Orderings (Backtracking)
 * 
 * Related LeetCode Problems:
 * - Print all possible topological sorts
 * - Count all valid topological orderings
 * - Find lexicographically smallest topological order
 * 
 * Time Complexity: O(V! × E) in worst case
 * Space Complexity: O(V)
 */

import java.util.*;

public class AllTopologicalOrderings {
    
    // Find all possible topological orderings
    public List<List<Integer>> allTopologicalSorts(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[n];
        
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            inDegree[edge[1]]++;
        }
        
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentOrder = new ArrayList<>();
        boolean[] visited = new boolean[n];
        
        backtrack(graph, inDegree, visited, currentOrder, result, n);
        
        return result;
    }
    
    private void backtrack(List<List<Integer>> graph, int[] inDegree, boolean[] visited,
                          List<Integer> currentOrder, List<List<Integer>> result, int n) {
        if (currentOrder.size() == n) {
            result.add(new ArrayList<>(currentOrder));
            return;
        }
        
        for (int node = 0; node < n; node++) {
            if (!visited[node] && inDegree[node] == 0) {
                visited[node] = true;
                currentOrder.add(node);
                
                for (int neighbor : graph.get(node)) {
                    inDegree[neighbor]--;
                }
                
                backtrack(graph, inDegree, visited, currentOrder, result, n);
                
                visited[node] = false;
                currentOrder.remove(currentOrder.size() - 1);
                
                for (int neighbor : graph.get(node)) {
                    inDegree[neighbor]++;
                }
            }
        }
    }
    
    // Lexicographically smallest topological ordering
    public List<Integer> lexicographicallySmallest(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[n];
        
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            inDegree[edge[1]]++;
        }
        
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                minHeap.offer(i);
            }
        }
        
        List<Integer> result = new ArrayList<>();
        
        while (!minHeap.isEmpty()) {
            int node = minHeap.poll();
            result.add(node);
            
            for (int neighbor : graph.get(node)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    minHeap.offer(neighbor);
                }
            }
        }
        
        return result.size() == n ? result : new ArrayList<>();
    }
    
    // Validate if order is valid topological sort
    public boolean isValidTopologicalOrder(int n, int[][] edges, int[] order) {
        if (order.length != n) return false;
        
        Map<Integer, Integer> position = new HashMap<>();
        for (int i = 0; i < n; i++) {
            position.put(order[i], i);
        }
        
        for (int[] edge : edges) {
            if (position.get(edge[0]) >= position.get(edge[1])) {
                return false;
            }
        }
        
        return true;
    }
    
    // Test
    public static void main(String[] args) {
        AllTopologicalOrderings solution = new AllTopologicalOrderings();
        
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {2, 3}};
        
        List<List<Integer>> allSorts = solution.allTopologicalSorts(4, edges);
        System.out.println("All topological sorts (" + allSorts.size() + " total):");
        for (List<Integer> sort : allSorts) {
            System.out.println(sort);
        }
        
        List<Integer> lexSort = solution.lexicographicallySmallest(4, edges);
        System.out.println("\nLexicographically smallest: " + lexSort);
        
        System.out.println("Is [0, 1, 2, 3] valid? " + 
                          solution.isValidTopologicalOrder(4, edges, new int[]{0, 1, 2, 3}));
    }
}
