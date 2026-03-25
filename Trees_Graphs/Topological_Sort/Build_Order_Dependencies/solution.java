/**
 * Topological Sort - Build Order / Project Dependencies
 * 
 * Related LeetCode Problems:
 * - Similar to Course Schedule but with direct dependencies
 * - Build order with multi-level dependencies
 * - Parallel build optimization
 * 
 * Time Complexity: O(V + E)
 * Space Complexity: O(V + E)
 */

import java.util.*;

public class BuildOrderDependencies {
    
    // Basic build order
    public List<String> buildOrder(List<String> projects, List<String[]> dependencies) {
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> inDegree = new HashMap<>();
        
        for (String project : projects) {
            graph.put(project, new ArrayList<>());
            inDegree.put(project, 0);
        }
        
        for (String[] dep : dependencies) {
            String before = dep[0];
            String after = dep[1];
            graph.get(before).add(after);
            inDegree.put(after, inDegree.get(after) + 1);
        }
        
        Queue<String> queue = new LinkedList<>();
        for (String project : projects) {
            if (inDegree.get(project) == 0) {
                queue.offer(project);
            }
        }
        
        List<String> buildSequence = new ArrayList<>();
        
        while (!queue.isEmpty()) {
            String project = queue.poll();
            buildSequence.add(project);
            
            for (String dependent : graph.get(project)) {
                inDegree.put(dependent, inDegree.get(dependent) - 1);
                if (inDegree.get(dependent) == 0) {
                    queue.offer(dependent);
                }
            }
        }
        
        return buildSequence.size() == projects.size() ? buildSequence : new ArrayList<>();
    }
    
    // Parallel build order (batches)
    public List<List<String>> parallelBuildOrder(List<String> projects, List<String[]> dependencies) {
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> inDegree = new HashMap<>();
        
        for (String project : projects) {
            graph.put(project, new ArrayList<>());
            inDegree.put(project, 0);
        }
        
        for (String[] dep : dependencies) {
            graph.get(dep[0]).add(dep[1]);
            inDegree.put(dep[1], inDegree.get(dep[1]) + 1);
        }
        
        List<List<String>> batches = new ArrayList<>();
        List<String> currentBatch = new ArrayList<>();
        
        for (String project : projects) {
            if (inDegree.get(project) == 0) {
                currentBatch.add(project);
            }
        }
        
        while (!currentBatch.isEmpty()) {
            batches.add(new ArrayList<>(currentBatch));
            List<String> nextBatch = new ArrayList<>();
            
            for (String project : currentBatch) {
                for (String dependent : graph.get(project)) {
                    inDegree.put(dependent, inDegree.get(dependent) - 1);
                    if (inDegree.get(dependent) == 0) {
                        nextBatch.add(dependent);
                    }
                }
            }
            
            currentBatch = nextBatch;
        }
        
        int total = batches.stream().mapToInt(List::size).sum();
        return total == projects.size() ? batches : new ArrayList<>();
    }
    
    // Minimum build time
    public int minimumBuildTime(List<String> projects, List<String[]> dependencies, 
                                 Map<String, Integer> buildTimes) {
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> inDegree = new HashMap<>();
        Map<String, Integer> earliestTime = new HashMap<>();
        
        for (String project : projects) {
            graph.put(project, new ArrayList<>());
            inDegree.put(project, 0);
            earliestTime.put(project, 0);
        }
        
        for (String[] dep : dependencies) {
            graph.get(dep[0]).add(dep[1]);
            inDegree.put(dep[1], inDegree.get(dep[1]) + 1);
        }
        
        Queue<String> queue = new LinkedList<>();
        for (String project : projects) {
            if (inDegree.get(project) == 0) {
                queue.offer(project);
            }
        }
        
        while (!queue.isEmpty()) {
            String project = queue.poll();
            int completionTime = earliestTime.get(project) + buildTimes.get(project);
            
            for (String dependent : graph.get(project)) {
                earliestTime.put(dependent, 
                                Math.max(earliestTime.get(dependent), completionTime));
                inDegree.put(dependent, inDegree.get(dependent) - 1);
                if (inDegree.get(dependent) == 0) {
                    queue.offer(dependent);
                }
            }
        }
        
        int maxTime = 0;
        for (String project : projects) {
            maxTime = Math.max(maxTime, earliestTime.get(project) + buildTimes.get(project));
        }
        
        return maxTime;
    }
    
    // Test
    public static void main(String[] args) {
        BuildOrderDependencies solution = new BuildOrderDependencies();
        
        List<String> projects = Arrays.asList("a", "b", "c", "d", "e", "f");
        List<String[]> deps = Arrays.asList(
            new String[]{"a", "d"},
            new String[]{"f", "b"},
            new String[]{"b", "d"},
            new String[]{"f", "a"},
            new String[]{"d", "c"}
        );
        
        System.out.println("Build order: " + solution.buildOrder(projects, deps));
        
        System.out.println("\nParallel build batches:");
        List<List<String>> batches = solution.parallelBuildOrder(projects, deps);
        for (int i = 0; i < batches.size(); i++) {
            System.out.println("  Batch " + (i + 1) + ": " + batches.get(i));
        }
        
        Map<String, Integer> buildTimes = new HashMap<>();
        buildTimes.put("a", 3); buildTimes.put("b", 2); buildTimes.put("c", 1);
        buildTimes.put("d", 4); buildTimes.put("e", 2); buildTimes.put("f", 1);
        
        System.out.println("\nMinimum build time: " + 
                          solution.minimumBuildTime(projects, deps, buildTimes));
    }
}
