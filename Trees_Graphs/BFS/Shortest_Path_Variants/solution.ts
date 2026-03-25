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

// LC 1091: Shortest Path in Binary Matrix (8-directional)
export function shortestPathBinaryMatrix(grid: number[][]): number {
    const n = grid.length;
    if (grid[0][0] === 1 || grid[n-1][n-1] === 1) {
        return -1;
    }
    
    if (n === 1) return 1;
    
    const directions = [[0, 1], [1, 0], [0, -1], [-1, 0],
                        [1, 1], [1, -1], [-1, 1], [-1, -1]];
    
    const queue: [number, number, number][] = [[0, 0, 1]];
    const visited = new Set<string>();
    visited.add("0,0");
    
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

// LC 847: Shortest Path Visiting All Nodes
export function shortestPathLength(graph: number[][]): number {
    const n = graph.length;
    const target = (1 << n) - 1;
    
    const queue: [number, number, number][] = [];
    const visited = new Set<string>();
    
    for (let i = 0; i < n; i++) {
        queue.push([i, 1 << i, 0]);
        visited.add(`${i},${1 << i}`);
    }
    
    while (queue.length > 0) {
        const [node, mask, dist] = queue.shift()!;
        
        if (mask === target) {
            return dist;
        }
        
        for (const neighbor of graph[node]) {
            const newMask = mask | (1 << neighbor);
            const state = `${neighbor},${newMask}`;
            
            if (!visited.has(state)) {
                visited.add(state);
                queue.push([neighbor, newMask, dist + 1]);
            }
        }
    }
    
    return -1;
}

// LC 1293: Shortest Path in a Grid with Obstacles Elimination
export function shortestPath(grid: number[][], k: number): number {
    const rows = grid.length, cols = grid[0].length;
    
    if (rows === 1 && cols === 1) return 0;
    if (k >= rows + cols - 2) return rows + cols - 2;
    
    const queue: [number, number, number, number][] = [[0, 0, 0, 0]];
    const visited = new Set<string>();
    visited.add("0,0,0");
    
    const directions = [[0, 1], [1, 0], [0, -1], [-1, 0]];
    
    while (queue.length > 0) {
        const [r, c, steps, obstacles] = queue.shift()!;
        
        for (const [dr, dc] of directions) {
            const nr = r + dr, nc = c + dc;
            
            if (nr === rows - 1 && nc === cols - 1) {
                return steps + 1;
            }
            
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                const newObstacles = obstacles + grid[nr][nc];
                const state = `${nr},${nc},${newObstacles}`;
                
                if (newObstacles <= k && !visited.has(state)) {
                    visited.add(state);
                    queue.push([nr, nc, steps + 1, newObstacles]);
                }
            }
        }
    }
    
    return -1;
}

// Test cases
if (require.main === module) {
    const grid1 = [[0, 0, 0], [1, 1, 0], [1, 1, 0]];
    console.log("Shortest path binary matrix:", shortestPathBinaryMatrix(grid1));
    
    const graph2 = [[1, 2, 3], [0], [0], [0]];
    console.log("Shortest path all nodes:", shortestPathLength(graph2));
    
    const grid3 = [[0, 0, 0], [1, 1, 0], [0, 0, 0], [0, 1, 1], [0, 0, 0]];
    console.log("Shortest path with elimination:", shortestPath(grid3, 1));
}
