/**
 * Topological Sort - All Topological Orderings (Backtracking)
 * 
 * Related LeetCode Problems:
 * - Print all possible topological sorts
 * - Count all valid topological orderings
 * - Find lexicographically smallest topological order
 * 
 * Time Complexity: O(V! × E) in worst case
 * Space Complexity: O(V)
 */

// Find all possible topological orderings
export function allTopologicalSorts(n: number, edges: number[][]): number[][] {
    const graph: number[][] = Array.from({ length: n }, () => []);
    const inDegree = Array(n).fill(0);
    
    for (const [u, v] of edges) {
        graph[u].push(v);
        inDegree[v]++;
    }
    
    const result: number[][] = [];
    const currentOrder: number[] = [];
    const visited = Array(n).fill(false);
    
    function backtrack(): void {
        if (currentOrder.length === n) {
            result.push([...currentOrder]);
            return;
        }
        
        for (let node = 0; node < n; node++) {
            if (!visited[node] && inDegree[node] === 0) {
                visited[node] = true;
                currentOrder.push(node);
                
                for (const neighbor of graph[node]) {
                    inDegree[neighbor]--;
                }
                
                backtrack();
                
                visited[node] = false;
                currentOrder.pop();
                
                for (const neighbor of graph[node]) {
                    inDegree[neighbor]++;
                }
            }
        }
    }
    
    backtrack();
    return result;
}

// Count number of topological orderings
export function countTopologicalSorts(n: number, edges: number[][]): number {
    const graph: number[][] = Array.from({ length: n }, () => []);
    const inDegree = Array(n).fill(0);
    
    for (const [u, v] of edges) {
        graph[u].push(v);
        inDegree[v]++;
    }
    
    let count = 0;
    const visited = Array(n).fill(false);
    
    function backtrack(): void {
        let foundNode = false;
        
        for (let node = 0; node < n; node++) {
            if (!visited[node] && inDegree[node] === 0) {
                foundNode = true;
                visited[node] = true;
                
                for (const neighbor of graph[node]) {
                    inDegree[neighbor]--;
                }
                
                backtrack();
                
                visited[node] = false;
                for (const neighbor of graph[node]) {
                    inDegree[neighbor]++;
                }
            }
        }
        
        if (!foundNode) {
            count++;
        }
    }
    
    backtrack();
    return count;
}

// Lexicographically smallest topological ordering
export function lexicographicallySmallest(n: number, edges: number[][]): number[] {
    const graph: number[][] = Array.from({ length: n }, () => []);
    const inDegree = Array(n).fill(0);
    
    for (const [u, v] of edges) {
        graph[u].push(v);
        inDegree[v]++;
    }
    
    const minHeap: number[] = [];
    for (let i = 0; i < n; i++) {
        if (inDegree[i] === 0) {
            minHeap.push(i);
        }
    }
    minHeap.sort((a, b) => a - b);
    
    const result: number[] = [];
    
    while (minHeap.length > 0) {
        minHeap.sort((a, b) => a - b);
        const node = minHeap.shift()!;
        result.push(node);
        
        for (const neighbor of graph[node]) {
            inDegree[neighbor]--;
            if (inDegree[neighbor] === 0) {
                minHeap.push(neighbor);
            }
        }
    }
    
    return result.length === n ? result : [];
}

// Validate if order is valid topological sort
export function isValidTopologicalOrder(n: number, edges: number[][], order: number[]): boolean {
    if (order.length !== n) return false;
    
    const position = new Map<number, number>();
    for (let i = 0; i < n; i++) {
        position.set(order[i], i);
    }
    
    for (const [u, v] of edges) {
        if (position.get(u)! >= position.get(v)!) {
            return false;
        }
    }
    
    return true;
}

// Test cases
if (require.main === module) {
    const edges = [[0, 1], [0, 2], [1, 3], [2, 3]];
    
    const allSorts = allTopologicalSorts(4, edges);
    console.log(`All topological sorts (${allSorts.length} total):`);
    allSorts.forEach(sort => console.log(sort));
    
    const count = countTopologicalSorts(4, edges);
    console.log(`\nTotal orderings: ${count}`);
    
    const lexSort = lexicographicallySmallest(4, edges);
    console.log(`Lexicographically smallest: ${lexSort}`);
    
    console.log(`Is [0, 1, 2, 3] valid? ${isValidTopologicalOrder(4, edges, [0, 1, 2, 3])}`);
}
