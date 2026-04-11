/**
 * DFS Recursion - Graph DFS with Visited Tracking Pattern
 * 
 * Problem: Given m×n 2D grid of '1's (land) and '0's (water), count number of islands.
 *          An island is surrounded by water and formed by connecting adjacent lands horizontally/vertically.
 * 
 * Pattern: DFS to explore each connected component. Mark visited cells to avoid revisiting.
 *          Each DFS call sinks an entire island.
 * 
 * Related LeetCode Problems:
 * - LC 200: Number of Islands (Medium) ⭐⭐⭐
 * - LC 695: Max Area of Island (Medium) ⭐⭐
 * - LC 733: Flood Fill (Easy)
 * - LC 130: Surrounded Regions (Medium)
 * 
 * Time Complexity: O(m × n) - visit each cell at most once
 * Space Complexity: O(m × n) - worst case recursion depth
 */

// ─────────────────────────────────────────────────────────────────────────────
// NAIVE APPROACH (Brute Force) — O(m × n × cells) time | O(m × n) space
// ─────────────────────────────────────────────────────────────────────────────
// INTERVIEW SCRIPT:
//   1. Describe:   "Brute force: for each unvisited cell, do full BFS/DFS with
//                  separate visited set — lots of redundant work"
//   2. Problem:    "For dense grid with one island: potentially O((m×n)²) operations
//                  checking cells multiple times"
//   3. Transition: "Mutate grid in-place (mark '1' → '0') as we explore
//                  — each cell visited exactly once: O(m × n)"
//
// public int numIslandsNaive(char[][] grid) {
//     if (grid == null || grid.length == 0) return 0;
//     int rows = grid.length, cols = grid[0].length;
//     Set<String> globalVisited = new HashSet<>();
//     int islands = 0;
//     
//     for (int r = 0; r < rows; r++) {
//         for (int c = 0; c < cols; c++) {
//             if (grid[r][c] == '1' && !globalVisited.contains(r + "," + c)) {
//                 // Explore island with separate tracking - inefficient!
//                 Set<String> visited = new HashSet<>();
//                 Queue<int[]> q = new LinkedList<>();
//                 q.offer(new int[]{r, c});
//                 while (!q.isEmpty()) {
//                     int[] cell = q.poll();
//                     String key = cell[0] + "," + cell[1];
//                     if (visited.contains(key)) continue;
//                     visited.add(key);
//                     globalVisited.add(key);
//                     // ... explore neighbors ...
//                 }
//                 islands++;
//             }
//         }
//     }
//     return islands;
// }
// ─────────────────────────────────────────────────────────────────────────────

public class GraphDFSVisited {
    
    // LC 200: Number of Islands
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        int islands = 0;
        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    islands++;
                    dfsIsland(grid, r, c);
                }
            }
        }
        
        return islands;
    }
    
    private void dfsIsland(char[][] grid, int r, int c) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        if (r < 0 || r >= rows || c < 0 || c >= cols || grid[r][c] == '0') {
            return;
        }
        
        grid[r][c] = '0';  // Mark as visited
        
        dfsIsland(grid, r + 1, c);
        dfsIsland(grid, r - 1, c);
        dfsIsland(grid, r, c + 1);
        dfsIsland(grid, r, c - 1);
    }
    
    // LC 695: Max Area of Island
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        int maxArea = 0;
        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    maxArea = Math.max(maxArea, dfsArea(grid, r, c));
                }
            }
        }
        
        return maxArea;
    }
    
    private int dfsArea(int[][] grid, int r, int c) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        if (r < 0 || r >= rows || c < 0 || c >= cols || grid[r][c] == 0) {
            return 0;
        }
        
        grid[r][c] = 0;  // Mark as visited
        
        return 1 + dfsArea(grid, r + 1, c) + dfsArea(grid, r - 1, c) +
               dfsArea(grid, r, c + 1) + dfsArea(grid, r, c - 1);
    }
    
    // LC 733: Flood Fill
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image[sr][sc] == newColor) {
            return image;
        }
        
        int originalColor = image[sr][sc];
        dfsFloodFill(image, sr, sc, originalColor, newColor);
        return image;
    }
    
    private void dfsFloodFill(int[][] image, int r, int c, int origColor, int newColor) {
        int rows = image.length;
        int cols = image[0].length;
        
        if (r < 0 || r >= rows || c < 0 || c >= cols || image[r][c] != origColor) {
            return;
        }
        
        image[r][c] = newColor;
        
        dfsFloodFill(image, r + 1, c, origColor, newColor);
        dfsFloodFill(image, r - 1, c, origColor, newColor);
        dfsFloodFill(image, r, c + 1, origColor, newColor);
        dfsFloodFill(image, r, c - 1, origColor, newColor);
    }
    
    // Test
    public static void main(String[] args) {
        GraphDFSVisited solution = new GraphDFSVisited();
        
        // Test numIslands
        char[][] grid = {
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
        };
        System.out.println("Number of islands: " + solution.numIslands(grid));  // 3
        
        // Test maxAreaOfIsland
        int[][] grid2 = {
            {0,0,1,0,0,0,0,1,0,0,0,0,0},
            {0,0,0,0,0,0,0,1,1,1,0,0,0},
            {0,1,1,0,1,0,0,0,0,0,0,0,0},
            {0,1,0,0,1,1,0,0,1,0,1,0,0}
        };
        System.out.println("Max area of island: " + solution.maxAreaOfIsland(grid2));  // 6
    }
}
