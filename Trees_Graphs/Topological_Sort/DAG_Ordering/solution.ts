/**
 * Topological Sort - Dependency Resolution
 * 
 * Time Complexity: O(V + E)
 * Space Complexity: O(V + E)
 */

function topologicalSortKahn(numNodes: number, edges: number[][]): number[] {
    const graph = new Map<number, number[]>();
    const inDegree = new Array(numNodes).fill(0);
    
    for (let i = 0; i < numNodes; i++) {
        graph.set(i, []);
    }
    
    for (const [u, v] of edges) {
        graph.get(u)!.push(v);
        inDegree[v]++;
    }
    
    const queue: number[] = [];
    for (let i = 0; i < numNodes; i++) {
        if (inDegree[i] === 0) {
            queue.push(i);
        }
    }
    
    const result: number[] = [];
    while (queue.length > 0) {
        const node = queue.shift()!;
        result.push(node);
        
        for (const neighbor of graph.get(node)!) {
            inDegree[neighbor]--;
            if (inDegree[neighbor] === 0) {
                queue.push(neighbor);
            }
        }
    }
    
    return result.length === numNodes ? result : [];
}

function canFinishCourses(numCourses: number, prerequisites: number[][]): boolean {
    const graph = new Map<number, number[]>();
    const inDegree = new Array(numCourses).fill(0);
    
    for (let i = 0; i < numCourses; i++) {
        graph.set(i, []);
    }
    
    for (const [course, prereq] of prerequisites) {
        graph.get(prereq)!.push(course);
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
        const course = queue.shift()!;
        count++;
        
        for (const next of graph.get(course)!) {
            inDegree[next]--;
            if (inDegree[next] === 0) {
                queue.push(next);
            }
        }
    }
    
    return count === numCourses;
}

// Test
if (require.main === module) {
    console.log("Can finish [[1,0]]:", canFinishCourses(2, [[1, 0]]));
    console.log("Can finish [[1,0],[0,1]]:", canFinishCourses(2, [[1, 0], [0, 1]]));
    
    const edges = [[0, 1], [0, 2], [1, 3], [2, 3]];
    console.log("Topological sort:", topologicalSortKahn(4, edges));
}

export { topologicalSortKahn, canFinishCourses };
