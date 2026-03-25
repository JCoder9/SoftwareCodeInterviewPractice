/**
 * Topological Sort - Basic Kahn's Algorithm (BFS-based)
 * 
 * Related LeetCode Problems:
 * - LC 207: Course Schedule (Medium)
 * - LC 210: Course Schedule II (Medium)
 * - LC 802: Find Eventual Safe States (Medium)
 * 
 * Time Complexity: O(V + E)
 * Space Complexity: O(V + E)
 */

// Basic Kahn's algorithm
export function kahnsAlgorithm(n: number, edges: number[][]): number[] {
    const graph: number[][] = Array.from({ length: n }, () => []);
    const inDegree = Array(n).fill(0);
    
    for (const [u, v] of edges) {
        graph[u].push(v);
        inDegree[v]++;
    }
    
    const queue: number[] = [];
    for (let i = 0; i < n; i++) {
        if (inDegree[i] === 0) {
            queue.push(i);
        }
    }
    
    const topoOrder: number[] = [];
    
    while (queue.length > 0) {
        const node = queue.shift()!;
        topoOrder.push(node);
        
        for (const neighbor of graph[node]) {
            inDegree[neighbor]--;
            if (inDegree[neighbor] === 0) {
                queue.push(neighbor);
            }
        }
    }
    
    return topoOrder.length === n ? topoOrder : [];
}

// LC 207: Course Schedule
export function canFinish(numCourses: number, prerequisites: number[][]): boolean {
    const graph: number[][] = Array.from({ length: numCourses }, () => []);
    const inDegree = Array(numCourses).fill(0);
    
    for (const [course, prereq] of prerequisites) {
        graph[prereq].push(course);
        inDegree[course]++;
    }
    
    const queue: number[] = [];
    for (let i = 0; i < numCourses; i++) {
        if (inDegree[i] === 0) {
            queue.push(i);
        }
    }
    
    let count = 0;
    while (queue.length > 0) {
        const node = queue.shift()!;
        count++;
        
        for (const neighbor of graph[node]) {
            inDegree[neighbor]--;
            if (inDegree[neighbor] === 0) {
                queue.push(neighbor);
            }
        }
    }
    
    return count === numCourses;
}

// LC 210: Course Schedule II
export function findOrder(numCourses: number, prerequisites: number[][]): number[] {
    const graph: number[][] = Array.from({ length: numCourses }, () => []);
    const inDegree = Array(numCourses).fill(0);
    
    for (const [course, prereq] of prerequisites) {
        graph[prereq].push(course);
        inDegree[course]++;
    }
    
    const queue: number[] = [];
    for (let i = 0; i < numCourses; i++) {
        if (inDegree[i] === 0) {
            queue.push(i);
        }
    }
    
    const order: number[] = [];
    
    while (queue.length > 0) {
        const node = queue.shift()!;
        order.push(node);
        
        for (const neighbor of graph[node]) {
            inDegree[neighbor]--;
            if (inDegree[neighbor] === 0) {
                queue.push(neighbor);
            }
        }
    }
    
    return order.length === numCourses ? order : [];
}

// Test cases
if (require.main === module) {
    const edges = [[0, 1], [0, 2], [1, 3], [2, 3]];
    console.log("Topological order:", kahnsAlgorithm(4, edges));
    
    console.log("Can finish:", canFinish(2, [[1, 0]]));
    console.log("Can finish with cycle:", canFinish(2, [[1, 0], [0, 1]]));
    
    console.log("Course order:", findOrder(4, [[1, 0], [2, 0], [3, 1], [3, 2]]));
}
