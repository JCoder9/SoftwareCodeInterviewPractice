/**
 * Topological Sort - Alien Dictionary
 * 
 * Related LeetCode Problems:
 * - LC 269: Alien Dictionary (Hard) - Premium
 * - LC 953: Verifying an Alien Dictionary (Easy)
 * 
 * Time Complexity: O(C) where C is total characters
 * Space Complexity: O(1) for alphabet size
 */

// LC 269: Alien Dictionary
export function alienOrder(words: string[]): string {
    const graph = new Map<string, Set<string>>();
    const inDegree = new Map<string, number>();
    
    // Initialize
    for (const word of words) {
        for (const char of word) {
            if (!inDegree.has(char)) {
                inDegree.set(char, 0);
                graph.set(char, new Set());
            }
        }
    }
    
    // Build graph
    for (let i = 0; i < words.length - 1; i++) {
        const word1 = words[i];
        const word2 = words[i + 1];
        const minLen = Math.min(word1.length, word2.length);
        
        // Invalid case
        if (word1.length > word2.length && 
            word1.substring(0, minLen) === word2.substring(0, minLen)) {
            return "";
        }
        
        // Find first difference
        for (let j = 0; j < minLen; j++) {
            const c1 = word1[j];
            const c2 = word2[j];
            
            if (c1 !== c2) {
                if (!graph.get(c1)!.has(c2)) {
                    graph.get(c1)!.add(c2);
                    inDegree.set(c2, inDegree.get(c2)! + 1);
                }
                break;
            }
        }
    }
    
    // Kahn's algorithm
    const queue: string[] = [];
    for (const [char, degree] of inDegree.entries()) {
        if (degree === 0) {
            queue.push(char);
        }
    }
    
    let result = "";
    
    while (queue.length > 0) {
        const char = queue.shift()!;
        result += char;
        
        for (const neighbor of graph.get(char)!) {
            inDegree.set(neighbor, inDegree.get(neighbor)! - 1);
            if (inDegree.get(neighbor) === 0) {
                queue.push(neighbor);
            }
        }
    }
    
    return result.length === inDegree.size ? result : "";
}

// LC 953: Verifying an Alien Dictionary
export function isAlienSorted(words: string[], order: string): boolean {
    const orderMap = new Map<string, number>();
    for (let i = 0; i < order.length; i++) {
        orderMap.set(order[i], i);
    }
    
    for (let i = 0; i < words.length - 1; i++) {
        const word1 = words[i];
        const word2 = words[i + 1];
        
        for (let j = 0; j < word1.length; j++) {
            if (j >= word2.length) {
                return false;
            }
            
            if (word1[j] !== word2[j]) {
                if (orderMap.get(word1[j])! > orderMap.get(word2[j])!) {
                    return false;
                }
                break;
            }
        }
    }
    
    return true;
}

// Test cases
if (require.main === module) {
    const words1 = ["wrt", "wrf", "er", "ett", "rftt"];
    console.log("Alien order:", alienOrder(words1));
    
    const words2 = ["z", "x"];
    console.log("Alien order:", alienOrder(words2));
    
    const words3 = ["abc", "ab"];
    console.log("Alien order (invalid):", alienOrder(words3));
    
    const words4 = ["hello", "leetcode"];
    const order = "hlabcdefgijkmnopqrstuvwxyz";
    console.log("Is alien sorted:", isAlienSorted(words4, order));
}
