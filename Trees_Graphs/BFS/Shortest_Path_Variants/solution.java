/**
 * BFS - Shortest Path Variants
 * 
 * Related LeetCode Problems:
 * - LC 1091: Shortest Path in Binary Matrix (Medium)
 * - LC 542: 01 Matrix (Medium)
 * - LC 847: Shortest Path Visiting All Nodes (Hard)
 * - LC 1293: Shortest Path in a Grid with Obstacles Elimination (Hard)
 * 
 * Time Complexity: O(V + E) or O(rows × cols × k)
 * Space Complexity: O(V) or O(rows × cols × k)
 */

import java.util.*;

public class ShortestPathVariants {
    
    // LC 1091: Shortest Path in Binary Matrix (8-directional)
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] == 1 || grid[n-1][n-1] == 1) {
            return -1;
        }
        
        if (n == 1) return 1;
        
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0},
                              {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 1});
        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1], dist = curr[2];
            
            for (int[] dir : directions) {
                int nr = r + dir[0], nc = c + dir[1];
                
                if (nr == n - 1 && nc == n - 1) {
                    return dist + 1;
                }
                
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && 
                    grid[nr][nc] == 0 && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc, dist + 1});
                }
            }
        }
        
        return -1;
    }
    
    // LC 847: Shortest Path Visiting All Nodes
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        int target = (1 << n) - 1;
        
        Queue<int[]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        for (int i = 0; i < n; i++) {
            queue.offer(new int[]{i, 1 << i, 0});
            visited.add(i + "," + (1 << i));
        }
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int node = curr[0], mask = curr[1], dist = curr[2];
            
            if (mask == target) {
                return dist;
            }
            
            for (int neighbor : graph[node]) {
                int newMask = mask | (1 << neighbor);
                String state = neighbor + "," + newMask;
                
                if (!visited.contains(state)) {
                    visited.add(state);
                    queue.offer(new int[]{neighbor, newMask, dist + 1});
                }
            }
        }
        
        return -1;
    }
    
    // LC 1293: Shortest Path in a Grid with Obstacles Elimination
    public int shortestPath(int[][] grid, int k) {
        int rows = grid.length, cols = grid[0].length;
        
        if (rows == 1 && cols == 1) return 0;
        if (k >= rows + cols - 2) return rows + cols - 2;
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 0, 0});  // row, col, steps, obstacles
        
        boolean[][][] visited = new boolean[rows][cols][k + 1];
        visited[0][0][0] = true;
        
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1], steps = curr[2], obstacles = curr[3];
            
            for (int[] dir : directions) {
                int nr = r + dir[0], nc = c + dir[1];
                
                if (nr == rows - 1 && nc == cols - 1) {
                    return steps + 1;
                }
                
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                    int newObstacles = obstacles + grid[nr][nc];
                    
                    if (newObstacles <= k && !visited[nr][nc][newObstacles]) {
                        visited[nr][nc][newObstacles] = true;
                        queue.offer(new int[]{nr, nc, steps + 1, newObstacles});
                    }
                }
            }
        }
        
        return -1;
    }
    
    // Test
    public static void main(String[] args) {
        ShortestPathVariants solution = new ShortestPathVariants();
        
        int[][] grid1 = {{0, 0, 0}, {1, 1, 0}, {1, 1, 0}};
        System.out.println("Shortest path binary matrix: " + 
                           solution.shortestPathBinaryMatrix(grid1));
        
        int[][] graph2 = {{1, 2, 3}, {0}, {0}, {0}};
        System.out.println("Shortest path all nodes: " + 
                           solution.shortestPathLength(graph2));
    }
}
