/**
 * DFS Recursion - Graph DFS with Visited Tracking Pattern
 * 
 * Related LeetCode Problems:
 * - LC 200: Number of Islands (Medium)
 * - LC 695: Max Area of Island (Medium)
 * - LC 733: Flood Fill (Easy)
 * - LC 130: Surrounded Regions (Medium)
 * 
 * Time Complexity: O(m × n) for grids
 * Space Complexity: O(m × n) for recursion stack
 */

// LC 200: Number of Islands
export function numIslands(grid: string[][]): number {
    if (!grid || grid.length === 0) {
        return 0;
    }
    
    const rows = grid.length;
    const cols = grid[0].length;
    let islands = 0;
    
    function dfs(r: number, c: number): void {
        if (r < 0 || r >= rows || c < 0 || c >= cols || grid[r][c] === '0') {
            return;
        }
        
        grid[r][c] = '0';  // Mark as visited
        
        dfs(r + 1, c);
        dfs(r - 1, c);
        dfs(r, c + 1);
        dfs(r, c - 1);
    }
    
    for (let r = 0; r < rows; r++) {
        for (let c = 0; c < cols; c++) {
            if (grid[r][c] === '1') {
                islands++;
                dfs(r, c);
            }
        }
    }
    
    return islands;
}

// LC 695: Max Area of Island
export function maxAreaOfIsland(grid: number[][]): number {
    if (!grid || grid.length === 0) {
        return 0;
    }
    
    const rows = grid.length;
    const cols = grid[0].length;
    let maxArea = 0;
    
    function dfs(r: number, c: number): number {
        if (r < 0 || r >= rows || c < 0 || c >= cols || grid[r][c] === 0) {
            return 0;
        }
        
        grid[r][c] = 0;  // Mark as visited
        
        return 1 + dfs(r + 1, c) + dfs(r - 1, c) + 
               dfs(r, c + 1) + dfs(r, c - 1);
    }
    
    for (let r = 0; r < rows; r++) {
        for (let c = 0; c < cols; c++) {
            if (grid[r][c] === 1) {
                maxArea = Math.max(maxArea, dfs(r, c));
            }
        }
    }
    
    return maxArea;
}

// LC 733: Flood Fill
export function floodFill(image: number[][], sr: number, sc: number, newColor: number): number[][] {
    if (!image || image[sr][sc] === newColor) {
        return image;
    }
    
    const rows = image.length;
    const cols = image[0].length;
    const originalColor = image[sr][sc];
    
    function dfs(r: number, c: number): void {
        if (r < 0 || r >= rows || c < 0 || c >= cols || image[r][c] !== originalColor) {
            return;
        }
        
        image[r][c] = newColor;
        
        dfs(r + 1, c);
        dfs(r - 1, c);
        dfs(r, c + 1);
        dfs(r, c - 1);
    }
    
    dfs(sr, sc);
    return image;
}

// Test cases
if (require.main === module) {
    // Test numIslands
    console.log("Testing numIslands:");
    const grid = [
        ["1","1","0","0","0"],
        ["1","1","0","0","0"],
        ["0","0","1","0","0"],
        ["0","0","0","1","1"]
    ];
    console.log(`Number of islands: ${numIslands(grid)}`);  // 3
    
    // Test maxAreaOfIsland
    console.log("\nTesting maxAreaOfIsland:");
    const grid2 = [
        [0,0,1,0,0,0,0,1,0,0,0,0,0],
        [0,0,0,0,0,0,0,1,1,1,0,0,0],
        [0,1,1,0,1,0,0,0,0,0,0,0,0],
        [0,1,0,0,1,1,0,0,1,0,1,0,0]
    ];
    console.log(`Max area of island: ${maxAreaOfIsland(grid2)}`);  // 6
}
