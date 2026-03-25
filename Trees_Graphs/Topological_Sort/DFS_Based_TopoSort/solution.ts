/**
 * Topological Sort - DFS-based Topological Sort
 * 
 * Related LeetCode Problems:
 * - LC 207: Course Schedule (Medium)
 * - LC 210: Course Schedule II (Medium)
 * - LC 851: Loud and Rich (Medium)
 * 
 * Time Complexity: O(V + E)
 * Space Complexity: O(V + E)
 */

// LC 207: Course Schedule (DFS version)
export function canFinish(numCourses: number, prerequisites: number[][]): boolean {
    const graph: number[][] = Array.from({ length: numCourses }, () => []);
    
    for (const [course, prereq] of prerequisites) {
        graph[prereq].push(course);
    }
    
    const state = Array(numCourses).fill(0);
    
    function hasCycle(node: number): boolean {
        if (state[node] === 1) return true;
        if (state[node] === 2) return false;
        
        state[node] = 1;
        
        for (const neighbor of graph[node]) {
            if (hasCycle(neighbor)) {
                return true;
            }
        }
        
        state[node] = 2;
        return false;
    }
    
    for (let i = 0; i < numCourses; i++) {
        if (state[i] === 0) {
            if (hasCycle(i)) {
                return false;
            }
        }
    }
    
    return true;
}

// LC 210: Course Schedule II (DFS version)
export function findOrder(numCourses: number, prerequisites: number[][]): number[] {
    const graph: number[][] = Array.from({ length: numCourses }, () => []);
    
    for (const [course, prereq] of prerequisites) {
        graph[prereq].push(course);
    }
    
    const state = Array(numCourses).fill(0);
    const result: number[] = [];
    let cycleDetected = false;
    
    function dfs(node: number): void {
        if (state[node] === 1) {
            cycleDetected = true;
            return;
        }
        if (state[node] === 2) return;
        
        state[node] = 1;
        
        for (const neighbor of graph[node]) {
            dfs(neighbor);
            if (cycleDetected) return;
        }
        
        state[node] = 2;
        result.push(node);
    }
    
    for (let i = 0; i < numCourses; i++) {
        if (state[i] === 0) {
            dfs(i);
            if (cycleDetected) {
                return [];
            }
        }
    }
    
    return result.reverse();
}

// LC 851: Loud and Rich
export function loudAndRich(richer: number[][], quiet: number[]): number[] {
    const n = quiet.length;
    const graph: number[][] = Array.from({ length: n }, () => []);
    
    for (const [a, b] of richer) {
        graph[b].push(a);
    }
    
    const answer = Array(n).fill(-1);
    
    function dfs(node: number): number {
        if (answer[node] >= 0) return answer[node];
        
        answer[node] = node;
        
        for (const richerPerson of graph[node]) {
            const candidate = dfs(richerPerson);
            if (quiet[candidate] < quiet[answer[node]]) {
                answer[node] = candidate;
            }
        }
        
        return answer[node];
    }
    
    for (let i = 0; i < n; i++) {
        dfs(i);
    }
    
    return answer;
}

// Test cases
if (require.main === module) {
    console.log("Can finish:", canFinish(2, [[1, 0]]));
    console.log("Can finish with cycle:", canFinish(2, [[1, 0], [0, 1]]));
    
    console.log("Course order:", findOrder(4, [[1, 0], [2, 0], [3, 1], [3, 2]]));
    
    const richer = [[1, 0], [2, 1], [3, 1], [3, 7], [4, 3], [5, 3], [6, 3]];
    const quiet = [3, 2, 5, 4, 6, 1, 7, 0];
    console.log("Loud and rich:", loudAndRich(richer, quiet));
}
