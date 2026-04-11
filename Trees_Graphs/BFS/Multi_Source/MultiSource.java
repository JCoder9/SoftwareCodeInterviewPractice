/**
 * BFS - Multi-Source BFS Pattern
 * 
 * Problem: Start BFS from multiple sources simultaneously.
 *          Example: Rotting oranges spreading from all rotten oranges at once
 * 
 * Pattern: Add all sources to queue initially, process level-by-level
 * 
 * Related LeetCode Problems:
 * - LC 994: Rotting Oranges (Medium) ⭐⭐⭐
 * - LC 286: Walls and Gates (Medium)
 * - LC 1162: As Far from Land as Possible (Medium)
 * 
 * Time Complexity: O(rows × cols) - visit each cell once
 * Space Complexity: O(rows × cols) - queue in worst case
 */

// ─────────────────────────────────────────────────────────────────────────────
// NAIVE APPROACH (Brute Force) — O(n × grid_size) time | O(grid_size) space
// ─────────────────────────────────────────────────────────────────────────────
// INTERVIEW SCRIPT:
//   1. Describe:   "Brute force runs separate BFS from each source, takes max time —
//                  O(n × grid_size) where n = number of sources"
//   2. Problem:    "10 rotten oranges in 100×100 grid: 10 × 10,000 = 100K operations
//                  vs single simultaneous BFS = 10K operations"
//   3. Transition: "Start all sources in queue together — O(rows × cols)"
//
// public int orangesRottingNaive(int[][] grid) {
//     int rows = grid.length, cols = grid[0].length;
//     int maxTime = 0;
//     
//     // Run separate BFS from each rotten orange
//     for (int r = 0; r < rows; r++) {
//         for (int c = 0; c < cols; c++) {
//             if (grid[r][c] == 2) {
//                 // BFS from this rotten orange
//                 boolean[][] visited = new boolean[rows][cols];
//                 Queue<int[]> queue = new LinkedList<>();
//                 queue.offer(new int[]{r, c, 0});
//                 visited[r][c] = true;
//                 
//                 while (!queue.isEmpty()) {
//                     int[] curr = queue.poll();
//                     maxTime = Math.max(maxTime, curr[2]);
//                     // ... BFS logic
//                 }
//             }
//         }
//     }
//     return maxTime;
// }
// ─────────────────────────────────────────────────────────────────────────────

import java.util.*;

public class MultiSource {
    
    // LC 994: Rotting Oranges
    public int orangesRotting(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0;
        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) {
                    queue.offer(new int[]{r, c, 0});
                } else if (grid[r][c] == 1) {
                    freshCount++;
                }
            }
        }
        
        if (freshCount == 0) return 0;
        
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int maxTime = 0;
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0], c = current[1], time = current[2];
            maxTime = Math.max(maxTime, time);
            
            for (int[] dir : directions) {
                int nr = r + dir[0], nc = c + dir[1];
                
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == 1) {
                    grid[nr][nc] = 2;
                    freshCount--;
                    queue.offer(new int[]{nr, nc, time + 1});
                }
            }
        }
        
        return freshCount == 0 ? maxTime : -1;
    }
    
    // LC 286: Walls and Gates
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) return;
        
        int rows = rooms.length, cols = rooms[0].length;
        int INF = Integer.MAX_VALUE;
        Queue<int[]> queue = new LinkedList<>();
        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (rooms[r][c] == 0) {
                    queue.offer(new int[]{r, c});
                }
            }
        }
        
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0], c = current[1];
            
            for (int[] dir : directions) {
                int nr = r + dir[0], nc = c + dir[1];
                
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && rooms[nr][nc] == INF) {
                    rooms[nr][nc] = rooms[r][c] + 1;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
    }
    
    // LC 1162: As Far from Land as Possible
    public int maxDistance(int[][] grid) {
        int n = grid.length;
        Queue<int[]> queue = new LinkedList<>();
        
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 1) {
                    queue.offer(new int[]{r, c});
                }
            }
        }
        
        if (queue.isEmpty() || queue.size() == n * n) {
            return -1;
        }
        
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int maxDist = -1;
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0], c = current[1];
            
            for (int[] dir : directions) {
                int nr = r + dir[0], nc = c + dir[1];
                
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && grid[nr][nc] == 0) {
                    grid[nr][nc] = grid[r][c] + 1;
                    maxDist = Math.max(maxDist, grid[nr][nc] - 1);
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
        
        return maxDist;
    }
    
    // Test
    public static void main(String[] args) {
        MultiSource solution = new MultiSource();
        
        int[][] grid1 = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        System.out.println("Rotting oranges: " + solution.orangesRotting(grid1));
        
        int[][] grid2 = {{1, 0, 1}, {0, 0, 0}, {1, 0, 1}};
        System.out.println("Max distance: " + solution.maxDistance(grid2));
    }
}
