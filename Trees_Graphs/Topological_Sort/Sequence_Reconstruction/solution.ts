/**
 * Topological Sort - Sequence Reconstruction
 * 
 * Related LeetCode Problems:
 * - LC 444: Sequence Reconstruction (Medium) - Premium
 * - LC 310: Minimum Height Trees (Medium) - related
 * 
 * Time Complexity: O(V + E)
 * Space Complexity: O(V + E)
 */

// LC 444: Sequence Reconstruction
export function sequenceReconstruction(org: number[], seqs: number[][]): boolean {
    const graph = new Map<number, Set<number>>();
    const inDegree = new Map<number, number>();
    
    for (const num of org) {
        graph.set(num, new Set());
        inDegree.set(num, 0);
    }
    
    const allNums = new Set<number>();
    for (const seq of seqs) {
        for (const num of seq) {
            allNums.add(num);
        }
    }
    
    if (allNums.size !== org.length) return false;
    for (const num of org) {
        if (!allNums.has(num)) return false;
    }
    
    // Build graph
    for (const seq of seqs) {
        for (let i = 0; i < seq.length - 1; i++) {
            const u = seq[i];
            const v = seq[i + 1];
            
            if (!graph.has(u) || !graph.has(v)) {
                return false;
            }
            
            if (!graph.get(u)!.has(v)) {
                graph.get(u)!.add(v);
                inDegree.set(v, inDegree.get(v)! + 1);
            }
        }
    }
    
    // Kahn's algorithm - must be unique
    const queue: number[] = [];
    for (const num of org) {
        if (inDegree.get(num) === 0) {
            queue.push(num);
        }
    }
    
    const result: number[] = [];
    
    while (queue.length > 0) {
        if (queue.length !== 1) return false;
        
        const num = queue.shift()!;
        result.push(num);
        
        for (const neighbor of graph.get(num)!) {
            inDegree.set(neighbor, inDegree.get(neighbor)! - 1);
            if (inDegree.get(neighbor) === 0) {
                queue.push(neighbor);
            }
        }
    }
    
    if (result.length !== org.length) return false;
    for (let i = 0; i < org.length; i++) {
        if (org[i] !== result[i]) return false;
    }
    
    return true;
}

// Check if unique topological sort exists
export function uniqueTopologicalSort(n: number, edges: number[][]): number[] {
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
    
    const result: number[] = [];
    
    while (queue.length > 0) {
        if (queue.length > 1) return [];  // Not unique
        
        const node = queue.shift()!;
        result.push(node);
        
        for (const neighbor of graph[node]) {
            inDegree[neighbor]--;
            if (inDegree[neighbor] === 0) {
                queue.push(neighbor);
            }
        }
    }
    
    return result.length === n ? result : [];
}

// Check if topological sort is unique
export function isTopologicalSortUnique(n: number, edges: number[][]): boolean {
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
    
    while (queue.length > 0) {
        if (queue.length !== 1) return false;
        
        const node = queue.shift()!;
        
        for (const neighbor of graph[node]) {
            inDegree[neighbor]--;
            if (inDegree[neighbor] === 0) {
                queue.push(neighbor);
            }
        }
    }
    
    return true;
}

// Test cases
if (require.main === module) {
    const org1 = [1, 2, 3];
    const seqs1 = [[1, 2], [1, 3]];
    console.log("Sequence reconstruction:", sequenceReconstruction(org1, seqs1));
    
    const org2 = [1, 2, 3];
    const seqs2 = [[1, 2], [1, 3], [2, 3]];
    console.log("Sequence reconstruction:", sequenceReconstruction(org2, seqs2));
    
    const edges = [[0, 1], [1, 2], [2, 3]];
    console.log("Unique topo sort:", uniqueTopologicalSort(4, edges));
    console.log("Is unique:", isTopologicalSortUnique(4, edges));
}
