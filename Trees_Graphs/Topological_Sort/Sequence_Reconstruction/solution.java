/**
 * Topological Sort - Sequence Reconstruction
 * 
 * Related LeetCode Problems:
 * - LC 444: Sequence Reconstruction (Medium) - Premium
 * - LC 310: Minimum Height Trees (Medium) - related
 * 
 * Time Complexity: O(V + E)
 * Space Complexity: O(V + E)
 */

import java.util.*;

public class SequenceReconstruction {
    
    // LC 444: Sequence Reconstruction
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        
        for (int num : org) {
            graph.put(num, new HashSet<>());
            inDegree.put(num, 0);
        }
        
        Set<Integer> allNums = new HashSet<>();
        for (List<Integer> seq : seqs) {
            for (int num : seq) {
                allNums.add(num);
            }
        }
        
        if (allNums.size() != org.length) return false;
        for (int num : org) {
            if (!allNums.contains(num)) return false;
        }
        
        // Build graph
        for (List<Integer> seq : seqs) {
            for (int i = 0; i < seq.size() - 1; i++) {
                int u = seq.get(i);
                int v = seq.get(i + 1);
                
                if (!graph.containsKey(u) || !graph.containsKey(v)) {
                    return false;
                }
                
                if (!graph.get(u).contains(v)) {
                    graph.get(u).add(v);
                    inDegree.put(v, inDegree.get(v) + 1);
                }
            }
        }
        
        // Kahn's algorithm - must be unique
        Queue<Integer> queue = new LinkedList<>();
        for (int num : org) {
            if (inDegree.get(num) == 0) {
                queue.offer(num);
            }
        }
        
        List<Integer> result = new ArrayList<>();
        
        while (!queue.isEmpty()) {
            if (queue.size() != 1) return false;
            
            int num = queue.poll();
            result.add(num);
            
            for (int neighbor : graph.get(num)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        if (result.size() != org.length) return false;
        for (int i = 0; i < org.length; i++) {
            if (org[i] != result.get(i)) return false;
        }
        
        return true;
    }
    
    // Check if unique topological sort exists
    public List<Integer> uniqueTopologicalSort(int n, int[][] edges) {
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
        
        List<Integer> result = new ArrayList<>();
        
        while (!queue.isEmpty()) {
            if (queue.size() > 1) return new ArrayList<>();  // Not unique
            
            int node = queue.poll();
            result.add(node);
            
            for (int neighbor : graph.get(node)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        return result.size() == n ? result : new ArrayList<>();
    }
    
    // Check if topological sort is unique
    public boolean isTopologicalSortUnique(int n, int[][] edges) {
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
        
        while (!queue.isEmpty()) {
            if (queue.size() != 1) return false;
            
            int node = queue.poll();
            
            for (int neighbor : graph.get(node)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        return true;
    }
    
    // Test
    public static void main(String[] args) {
        SequenceReconstruction solution = new SequenceReconstruction();
        
        int[] org1 = {1, 2, 3};
        List<List<Integer>> seqs1 = Arrays.asList(
            Arrays.asList(1, 2),
            Arrays.asList(1, 3)
        );
        System.out.println("Sequence reconstruction: " + 
                          solution.sequenceReconstruction(org1, seqs1));
        
        int[] org2 = {1, 2, 3};
        List<List<Integer>> seqs2 = Arrays.asList(
            Arrays.asList(1, 2),
            Arrays.asList(1, 3),
            Arrays.asList(2, 3)
        );
        System.out.println("Sequence reconstruction: " + 
                          solution.sequenceReconstruction(org2, seqs2));
        
        int[][] edges = {{0, 1}, {1, 2}, {2, 3}};
        System.out.println("Unique topo sort: " + solution.uniqueTopologicalSort(4, edges));
    }
}
