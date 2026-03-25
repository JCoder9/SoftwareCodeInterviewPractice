/**
 * BFS - Bidirectional BFS
 * 
 * Related LeetCode Problems:
 * - LC 127: Word Ladder (Hard)
 * - LC 752: Open the Lock (Medium)
 * 
 * Time Complexity: O(b^(d/2))
 * Space Complexity: O(b^(d/2))
 */

export function bidirectionalBFS(
    graph: Map<number, number[]>, 
    start: number, 
    end: number
): number {
    if (start === end) return 0;
    
    const queueStart: [number, number][] = [[start, 0]];
    const queueEnd: [number, number][] = [[end, 0]];
    
    const visitedStart = new Map<number, number>([[start, 0]]);
    const visitedEnd = new Map<number, number>([[end, 0]]);
    
    while (queueStart.length > 0 || queueEnd.length > 0) {
        if (queueStart.length > 0) {
            const [node, dist] = queueStart.shift()!;
            
            for (const neighbor of graph.get(node) || []) {
                if (visitedEnd.has(neighbor)) {
                    return dist + 1 + visitedEnd.get(neighbor)!;
                }
                
                if (!visitedStart.has(neighbor)) {
                    visitedStart.set(neighbor, dist + 1);
                    queueStart.push([neighbor, dist + 1]);
                }
            }
        }
        
        if (queueEnd.length > 0) {
            const [node, dist] = queueEnd.shift()!;
            
            for (const neighbor of graph.get(node) || []) {
                if (visitedStart.has(neighbor)) {
                    return dist + 1 + visitedStart.get(neighbor)!;
                }
                
                if (!visitedEnd.has(neighbor)) {
                    visitedEnd.set(neighbor, dist + 1);
                    queueEnd.push([neighbor, dist + 1]);
                }
            }
        }
    }
    
    return -1;
}

// LC 752: Open the Lock
export function openLock(deadends: string[], target: string): number {
    if (target === "0000") return 0;
    
    const dead = new Set(deadends);
    if (dead.has("0000")) return -1;
    
    function getNeighbors(code: string): string[] {
        const result: string[] = [];
        for (let i = 0; i < 4; i++) {
            const digit = parseInt(code[i]);
            const up = (digit + 1) % 10;
            const down = (digit + 9) % 10;
            
            result.push(code.substring(0, i) + up + code.substring(i + 1));
            result.push(code.substring(0, i) + down + code.substring(i + 1));
        }
        return result;
    }
    
    let startSet = new Set<string>(["0000"]);
    let endSet = new Set<string>([target]);
    const visited = new Set<string>(["0000", target]);
    
    let steps = 0;
    
    while (startSet.size > 0 && endSet.size > 0) {
        if (startSet.size > endSet.size) {
            [startSet, endSet] = [endSet, startSet];
        }
        
        const nextSet = new Set<string>();
        
        for (const code of startSet) {
            for (const neighbor of getNeighbors(code)) {
                if (endSet.has(neighbor)) {
                    return steps + 1;
                }
                
                if (!visited.has(neighbor) && !dead.has(neighbor)) {
                    visited.add(neighbor);
                    nextSet.add(neighbor);
                }
            }
        }
        
        startSet = nextSet;
        steps++;
    }
    
    return -1;
}

// Test cases
if (require.main === module) {
    const deadends = ["0201", "0101", "0102", "1212", "2002"];
    console.log("Open lock:", openLock(deadends, "0202"));
}
