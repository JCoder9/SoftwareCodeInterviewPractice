/**
 * BFS - 0-1 BFS (Deque-based BFS for graphs with 0 and 1 weights)
 * 
 * Related LeetCode Problems:
 * - LC 1368: Minimum Cost to Make at Least One Valid Path in a Grid (Hard)
 * - LC 2290: Minimum Obstacle Removal to Reach Corner (Hard)
 * 
 * Key Insight: For graphs with only 0 and 1 edge weights, we can use a deque.
 * - Add 0-weight edges to the front (process immediately)
 * - Add 1-weight edges to the back (process later)
 * 
 * Time Complexity: O(V + E)
 * Space Complexity: O(V)
 */

import java.util.*;

public class ZeroOneBFS {
    
    // LC 1368: Minimum Cost to Make at Least One Valid Path in a Grid
    public int minCost(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        
        int[][] dist = new int[rows][cols];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        dist[0][0] = 0;
        
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[]{0, 0});
        
        while (!dq.isEmpty()) {
            int[] curr = dq.poll();
            int r = curr[0], c = curr[1];
            
            for (int i = 0; i < 4; i++) {
                int nr = r + directions[i][0];
                int nc = c + directions[i][1];
                
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                    int cost = (grid[r][c] == i + 1) ? 0 : 1;
                    int newDist = dist[r][c] + cost;
                    
                    if (newDist < dist[nr][nc]) {
                        dist[nr][nc] = newDist;
                        if (cost == 0) {
                            dq.offerFirst(new int[]{nr, nc});
                        } else {
                            dq.offerLast(new int[]{nr, nc});
                        }
                    }
                }
            }
        }
        
        return dist[rows - 1][cols - 1];
    }
    
    // LC 2290: Minimum Obstacle Removal to Reach Corner
    public int minimumObstacles(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int[][] dist = new int[rows][cols];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        dist[0][0] = 0;
        
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[]{0, 0});
        
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        
        while (!dq.isEmpty()) {
            int[] curr = dq.poll();
            int r = curr[0], c = curr[1];
            
            if (r == rows - 1 && c == cols - 1) {
                return dist[r][c];
            }
            
            for (int[] dir : directions) {
                int nr = r + dir[0], nc = c + dir[1];
                
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                    int cost = grid[nr][nc];
                    int newDist = dist[r][c] + cost;
                    
                    if (newDist < dist[nr][nc]) {
                        dist[nr][nc] = newDist;
                        if (cost == 0) {
                            dq.offerFirst(new int[]{nr, nc});
                        } else {
                            dq.offerLast(new int[]{nr, nc});
                        }
                    }
                }
            }
        }
        
        return dist[rows - 1][cols - 1];
    }
    
    // Test
    public static void main(String[] args) {
        ZeroOneBFS solution = new ZeroOneBFS();
        
        int[][] grid1 = {{1, 1, 1, 1}, {2, 2, 2, 2}, {1, 1, 1, 1}, {2, 2, 2, 2}};
        System.out.println("Min cost path: " + solution.minCost(grid1));
        
        int[][] grid2 = {{0, 1, 1}, {1, 1, 0}, {1, 1, 0}};
        System.out.println("Min obstacles: " + solution.minimumObstacles(grid2));
    }
}
