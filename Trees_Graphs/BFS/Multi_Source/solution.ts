/**
 * BFS - Multi-Source BFS
 * 
 * Related LeetCode Problems:
 * - LC 994: Rotting Oranges (Medium)
 * - LC 286: Walls and Gates (Medium)
 * - LC 1162: As Far from Land as Possible (Medium)
 * 
 * Time Complexity: O(rows × cols)
 * Space Complexity: O(rows × cols)
 */

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

// LC 286: Walls and Gates
export function wallsAndGates(rooms: number[][]): void {
    if (!rooms || rooms.length === 0) return;
    
    const rows = rooms.length, cols = rooms[0].length;
    const INF = 2147483647;
    const queue: [number, number][] = [];
    
    for (let r = 0; r < rows; r++) {
        for (let c = 0; c < cols; c++) {
            if (rooms[r][c] === 0) {
                queue.push([r, c]);
            }
        }
    }
    
    const directions = [[0, 1], [1, 0], [0, -1], [-1, 0]];
    
    while (queue.length > 0) {
        const [r, c] = queue.shift()!;
        
        for (const [dr, dc] of directions) {
            const nr = r + dr, nc = c + dc;
            
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && rooms[nr][nc] === INF) {
                rooms[nr][nc] = rooms[r][c] + 1;
                queue.push([nr, nc]);
            }
        }
    }
}

// LC 1162: As Far from Land as Possible
export function maxDistance(grid: number[][]): number {
    const n = grid.length;
    const queue: [number, number][] = [];
    
    for (let r = 0; r < n; r++) {
        for (let c = 0; c < n; c++) {
            if (grid[r][c] === 1) {
                queue.push([r, c]);
            }
        }
    }
    
    if (queue.length === 0 || queue.length === n * n) {
        return -1;
    }
    
    const directions = [[0, 1], [1, 0], [0, -1], [-1, 0]];
    let maxDist = -1;
    
    while (queue.length > 0) {
        const [r, c] = queue.shift()!;
        
        for (const [dr, dc] of directions) {
            const nr = r + dr, nc = c + dc;
            
            if (nr >= 0 && nr < n && nc >= 0 && nc < n && grid[nr][nc] === 0) {
                grid[nr][nc] = grid[r][c] + 1;
                maxDist = Math.max(maxDist, grid[nr][nc] - 1);
                queue.push([nr, nc]);
            }
        }
    }
    
    return maxDist;
}

// Test cases
if (require.main === module) {
    const grid1 = [[2, 1, 1], [1, 1, 0], [0, 1, 1]];
    console.log("Rotting oranges:", orangesRotting(grid1));
    
    const grid2 = [[1, 0, 1], [0, 0, 0], [1, 0, 1]];
    console.log("Max distance:", maxDistance(grid2));
}
