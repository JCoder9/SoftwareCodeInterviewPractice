/**
 * BFS - BFS on Graph with Visited Tracking
 * 
 * Related LeetCode Problems:
 * - LC 133: Clone Graph (Medium)
 * - LC 797: All Paths From Source to Target (Medium)
 * - LC 841: Keys and Rooms (Medium)
 * 
 * Time Complexity: O(V + E)
 * Space Complexity: O(V)
 */

// Basic BFS on graph
export function bfsGraph(graph: Map<number, number[]>, start: number): number[] {
    const visited = new Set<number>([start]);
    const queue: number[] = [start];
    const result: number[] = [];
    
    while (queue.length > 0) {
        const node = queue.shift()!;
        result.push(node);
        
        for (const neighbor of graph.get(node) || []) {
            if (!visited.has(neighbor)) {
                visited.add(neighbor);
                queue.push(neighbor);
            }
        }
    }
    
    return result;
}

// Find shortest path length
export function bfsShortestPath(
    graph: Map<number, number[]>, 
    start: number, 
    target: number
): number {
    if (start === target) return 0;
    
    const visited = new Set<number>([start]);
    const queue: [number, number][] = [[start, 0]];
    
    while (queue.length > 0) {
        const [node, distance] = queue.shift()!;
        
        for (const neighbor of graph.get(node) || []) {
            if (neighbor === target) {
                return distance + 1;
            }
            
            if (!visited.has(neighbor)) {
                visited.add(neighbor);
                queue.push([neighbor, distance + 1]);
            }
        }
    }
    
    return -1;
}

// LC 797: All Paths From Source to Target
export function allPathsSourceTarget(graph: number[][]): number[][] {
    const result: number[][] = [];
    const target = graph.length - 1;
    
    const queue: number[][] = [[0]];
    
    while (queue.length > 0) {
        const path = queue.shift()!;
        const node = path[path.length - 1];
        
        if (node === target) {
            result.push([...path]);
            continue;
        }
        
        for (const neighbor of graph[node]) {
            queue.push([...path, neighbor]);
        }
    }
    
    return result;
}

// LC 841: Keys and Rooms
export function canVisitAllRooms(rooms: number[][]): boolean {
    const visited = new Set<number>([0]);
    const queue: number[] = [0];
    
    while (queue.length > 0) {
        const room = queue.shift()!;
        
        for (const key of rooms[room]) {
            if (!visited.has(key)) {
                visited.add(key);
                queue.push(key);
            }
        }
    }
    
    return visited.size === rooms.length;
}

// Test cases
if (require.main === module) {
    const graph = new Map<number, number[]>([
        [0, [1, 2]],
        [1, [0, 3]],
        [2, [0]],
        [3, [1]]
    ]);
    
    console.log("Basic BFS:", bfsGraph(graph, 0));
    console.log("Shortest path:", bfsShortestPath(graph, 0, 3));
    
    const graph2 = [[1, 2], [3], [3], []];
    console.log("All paths:", allPathsSourceTarget(graph2));
}
