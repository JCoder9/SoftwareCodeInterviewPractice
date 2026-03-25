/**
 * BFS - BFS on Grid/Matrix
 * 
 * Related LeetCode Problems:
 * - LC 542: 01 Matrix (Medium)
 * - LC 994: Rotting Oranges (Medium)
 * - LC 1091: Shortest Path in Binary Matrix (Medium)
 * 
 * Time Complexity: O(rows × cols)
 * Space Complexity: O(rows × cols)
 */

// Shortest path in grid
export function bfsGridShortestPath(
    grid: number[][], 
    start: [number, number], 
    end: [number, number]
): number {
    if (grid[start[0]][start[1]] === 1 || grid[end[0]][end[1]] === 1) {
        return -1;
    }
    
    const rows = grid.length, cols = grid[0].length;
    const visited = new Set<string>([`${start[0]},${start[1]}`]);
    const queue: [number, number, number][] = [[start[0], start[1], 0]];
    
    const directions = [[0, 1], [1, 0], [0, -1], [-1, 0]];
    
    while (queue.length > 0) {
        const [r, c, dist] = queue.shift()!;
        
        if (r === end[0] && c === end[1]) {
            return dist;
        }
        
        for (const [dr, dc] of directions) {
            const nr = r + dr, nc = c + dc;
            const key = `${nr},${nc}`;
            
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols &&
                grid[nr][nc] === 0 && !visited.has(key)) {
                visited.add(key);
                queue.push([nr, nc, dist + 1]);
            }
        }
    }
    
    return -1;
}

// LC 994: Rotting Oranges
export function orangesRotting(grid: number[][]): number {
    const rows = grid.length, cols = grid[0].length;
    const queue: [number, number, number][] = [];
    let freshCount = 0;
    
    for (let r = 0; r < rows; r++) {
        for (let c = 0; c < cols; c++) {
            if (grid[r][c] === 2) {
                queue.push([r, c, 0]);
            } else if (grid[r][c] === 1) {
                freshCount++;
            }
        }
    }
    
    if (freshCount === 0) return 0;
    
    const directions = [[0, 1], [1, 0], [0, -1], [-1, 0]];
    let maxTime = 0;
    
    while (queue.length > 0) {
        const [r, c, time] = queue.shift()!;
        maxTime = Math.max(maxTime, time);
        
        for (const [dr, dc] of directions) {
            const nr = r + dr, nc = c + dc;
            
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] === 1) {
                grid[nr][nc] = 2;
                freshCount--;
                queue.push([nr, nc, time + 1]);
            }
        }
    }
    
    return freshCount === 0 ? maxTime : -1;
}

// LC 1091: Shortest Path in Binary Matrix
export function shortestPathBinaryMatrix(grid: number[][]): number {
    const n = grid.length;
    if (grid[0][0] === 1 || grid[n-1][n-1] === 1) {
        return -1;
    }
    
    if (n === 1) return 1;
    
    const visited = new Set<string>(["0,0"]);
    const queue: [number, number, number][] = [[0, 0, 1]];
    
    const directions = [[0, 1], [1, 0], [0, -1], [-1, 0],
                        [1, 1], [1, -1], [-1, 1], [-1, -1]];
    
    while (queue.length > 0) {
        const [r, c, dist] = queue.shift()!;
        
        for (const [dr, dc] of directions) {
            const nr = r + dr, nc = c + dc;
            
            if (nr === n - 1 && nc === n - 1) {
                return dist + 1;
            }
            
            const key = `${nr},${nc}`;
            if (nr >= 0 && nr < n && nc >= 0 && nc < n &&
                grid[nr][nc] === 0 && !visited.has(key)) {
                visited.add(key);
                queue.push([nr, nc, dist + 1]);
            }
        }
    }
    
    return -1;
}

// Test cases
if (require.main === module) {
    const grid = [[0, 0, 0], [1, 1, 0], [0, 0, 0]];
    console.log("Shortest path:", bfsGridShortestPath(grid, [0, 0], [2, 2]));
    
    const oranges = [[2, 1, 1], [1, 1, 0], [0, 1, 1]];
    console.log("Rotting oranges:", orangesRotting(oranges));
}
