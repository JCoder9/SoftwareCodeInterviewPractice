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

// LC 1368: Minimum Cost to Make at Least One Valid Path in a Grid
export function minCost(grid: number[][]): number {
    const rows = grid.length, cols = grid[0].length;
    const directions = [[0, 1], [0, -1], [1, 0], [-1, 0]];
    
    const dist: number[][] = Array.from({ length: rows }, () => 
        Array(cols).fill(Infinity)
    );
    dist[0][0] = 0;
    
    const dq: [number, number][] = [[0, 0]];
    
    while (dq.length > 0) {
        const [r, c] = dq.shift()!;
        
        for (let i = 0; i < 4; i++) {
            const nr = r + directions[i][0];
            const nc = c + directions[i][1];
            
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                const cost = (grid[r][c] === i + 1) ? 0 : 1;
                const newDist = dist[r][c] + cost;
                
                if (newDist < dist[nr][nc]) {
                    dist[nr][nc] = newDist;
                    if (cost === 0) {
                        dq.unshift([nr, nc]);  // Add to front
                    } else {
                        dq.push([nr, nc]);      // Add to back
                    }
                }
            }
        }
    }
    
    return dist[rows - 1][cols - 1];
}

// LC 2290: Minimum Obstacle Removal to Reach Corner
export function minimumObstacles(grid: number[][]): number {
    const rows = grid.length, cols = grid[0].length;
    const dist: number[][] = Array.from({ length: rows }, () => 
        Array(cols).fill(Infinity)
    );
    dist[0][0] = 0;
    
    const dq: [number, number][] = [[0, 0]];
    const directions = [[0, 1], [1, 0], [0, -1], [-1, 0]];
    
    while (dq.length > 0) {
        const [r, c] = dq.shift()!;
        
        if (r === rows - 1 && c === cols - 1) {
            return dist[r][c];
        }
        
        for (const [dr, dc] of directions) {
            const nr = r + dr, nc = c + dc;
            
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                const cost = grid[nr][nc];
                const newDist = dist[r][c] + cost;
                
                if (newDist < dist[nr][nc]) {
                    dist[nr][nc] = newDist;
                    if (cost === 0) {
                        dq.unshift([nr, nc]);
                    } else {
                        dq.push([nr, nc]);
                    }
                }
            }
        }
    }
    
    return dist[rows - 1][cols - 1];
}

// Test cases
if (require.main === module) {
    const grid1 = [[1, 1, 1, 1], [2, 2, 2, 2], [1, 1, 1, 1], [2, 2, 2, 2]];
    console.log("Min cost path:", minCost(grid1));
    
    const grid2 = [[0, 1, 1], [1, 1, 0], [1, 1, 0]];
    console.log("Min obstacles:", minimumObstacles(grid2));
}
