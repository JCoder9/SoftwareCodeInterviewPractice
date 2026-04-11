/**
 * BFS - BFS on Grid/Matrix (Shortest Path, Flood Fill)
 * 
 * Problem: Find shortest path in a grid from start to end, or simultaneously expand from multiple sources.
 *          Cells are connected via 4 directions (up/down/left/right).
 * 
 * Pattern: BFS naturally gives shortest path. Use queue with (row, col, distance).
 *          Track visited cells to avoid reprocessing.
 * 
 * Related LeetCode Problems:
 * - LC 542: 01 Matrix (Medium) ⭐⭐⭐
 * - LC 994: Rotting Oranges (Medium) ⭐⭐⭐
 * - LC 1091: Shortest Path in Binary Matrix (Medium) ⭐⭐
 * - LC 286: Walls and Gates (Medium)
 * 
 * Time Complexity: O(rows × cols) - visit each cell at most once
 * Space Complexity: O(rows × cols) - queue and visited set
 */

// ─────────────────────────────────────────────────────────────────────────────
// NAIVE APPROACH (Brute Force) — O((rows×cols)!) time | O(rows×cols) space
// ─────────────────────────────────────────────────────────────────────────────
// INTERVIEW SCRIPT:
//   1. Describe:   "Brute force explores all paths using DFS recursively
//                  — exponential number of possible routes"
//   2. Problem:    "For 10×10 grid: trillions of paths; doesn't guarantee shortest
//                  path without trying all routes"
//   3. Transition: "BFS explores level-by-level; first time reaching cell is
//                  shortest path — O(rows × cols)"
//
// private int minDist = Integer.MAX_VALUE;
// 
// public int shortestPathNaive(int[][] grid, int[] start, int[] end) {
//     Set<String> visited = new HashSet<>();
//     visited.add(start[0] + "," + start[1]);
//     dfsExplore(grid, start[0], start[1], end, 0, visited);
//     return minDist == Integer.MAX_VALUE ? -1 : minDist;
// }
// 
// private void dfsExplore(int[][] grid, int r, int c, int[] end,
//                         int dist, Set<String> visited) {
//     if (r == end[0] && c == end[1]) {
//         minDist = Math.min(minDist, dist);
//         return;
//     }
//     int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
//     for (int[] d : dirs) {
//         int nr = r + d[0], nc = c + d[1];
//         String key = nr + "," + nc;
//         if (inBounds(grid, nr, nc) && grid[nr][nc] == 0 && !visited.contains(key)) {
//             visited.add(key);
//             dfsExplore(grid, nr, nc, end, dist + 1, visited);
//             visited.remove(key);  // Backtrack
//         }
//     }
// }
// ─────────────────────────────────────────────────────────────────────────────

import java.util.*;

public class GridBFS {
    
    // Shortest path in grid
    public int bfsGridShortestPath(int[][] grid, int[] start, int[] end) {
        if (grid[start[0]][start[1]] == 1 || grid[end[0]][end[1]] == 1) {
            return -1;
        }
        
        int rows = grid.length, cols = grid[0].length;
        Set<String> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        
        visited.add(start[0] + "," + start[1]);
        queue.offer(new int[]{start[0], start[1], 0});
        
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0], c = current[1], dist = current[2];
            
            if (r == end[0] && c == end[1]) {
                return dist;
            }
            
            for (int[] dir : directions) {
                int nr = r + dir[0], nc = c + dir[1];
                String key = nr + "," + nc;
                
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols &&
                    grid[nr][nc] == 0 && !visited.contains(key)) {
                    visited.add(key);
                    queue.offer(new int[]{nr, nc, dist + 1});
                }
            }
        }
        
        return -1;
    }
    
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
    
    // LC 1091: Shortest Path in Binary Matrix
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] == 1 || grid[n-1][n-1] == 1) {
            return -1;
        }
        
        if (n == 1) return 1;
        
        Set<String> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        
        visited.add("0,0");
        queue.offer(new int[]{0, 0, 1});
        
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0},
                              {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0], c = current[1], dist = current[2];
            
            for (int[] dir : directions) {
                int nr = r + dir[0], nc = c + dir[1];
                
                if (nr == n - 1 && nc == n - 1) {
                    return dist + 1;
                }
                
                String key = nr + "," + nc;
                if (nr >= 0 && nr < n && nc >= 0 && nc < n &&
                    grid[nr][nc] == 0 && !visited.contains(key)) {
                    visited.add(key);
                    queue.offer(new int[]{nr, nc, dist + 1});
                }
            }
        }
        
        return -1;
    }
    
    // Test
    public static void main(String[] args) {
        GridBFS solution = new GridBFS();
        
        int[][] grid = {{0, 0, 0}, {1, 1, 0}, {0, 0, 0}};
        System.out.println("Shortest path: " + 
                           solution.bfsGridShortestPath(grid, new int[]{0, 0}, new int[]{2, 2}));
        
        int[][] oranges = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        System.out.println("Rotting oranges: " + solution.orangesRotting(oranges));
    }
}
