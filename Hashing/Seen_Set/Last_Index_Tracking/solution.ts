/**
 * Last Index Tracking - First Unique Character Pattern
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(k) where k is unique characters
 */

function firstUniqChar(s: string): number {
    const count = new Map<string, number>();
    
    for (const char of s) {
        count.set(char, (count.get(char) || 0) + 1);
    }
    
    for (let i = 0; i < s.length; i++) {
        if (count.get(s[i]) === 1) {
            return i;
        }
    }
    
    return -1;
}

function uniqueMorseRepresentations(words: string[]): number {
    const morse = [".-","-...","-.-.","-..",".","..-.","--.","....","..",".---",
                   "-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-",
                   "..-","...-",".--","-..-","-.--","--.."];
    
    const transformations = new Set<string>();
    
    for (const word of words) {
        let code = '';
        for (const char of word) {
            code += morse[char.charCodeAt(0) - 'a'.charCodeAt(0)];
        }
        transformations.add(code);
    }
    
    return transformations.size;
}

function frequencySort(s: string): string {
    const count = new Map<string, number>();
    
    for (const char of s) {
        count.set(char, (count.get(char) || 0) + 1);
    }
    
    const buckets: string[][] = Array(s.length + 1).fill(null).map(() => []);
    
    for (const [char, freq] of count) {
        buckets[freq].push(char);
    }
    
    const result: string[] = [];
    for (let i = buckets.length - 1; i >= 0; i--) {
        for (const char of buckets[i]) {
            result.push(char.repeat(i));
        }
    }
    
    return result.join('');
}

function findShortestSubArray(nums: number[]): number {
    const first = new Map<number, number>();
    const last = new Map<number, number>();
    const count = new Map<number, number>();
    
    for (let i = 0; i < nums.length; i++) {
        const num = nums[i];
        if (!first.has(num)) {
            first.set(num, i);
        }
        last.set(num, i);
        count.set(num, (count.get(num) || 0) + 1);
    }
    
    const degree = Math.max(...count.values());
    let minLength = nums.length;
    
    for (const [num, freq] of count) {
        if (freq === degree) {
            const length = last.get(num)! - first.get(num)! + 1;
            minLength = Math.min(minLength, length);
        }
    }
    
    return minLength;
}

function longestConsecutive(nums: number[]): number {
    if (nums.length === 0) return 0;
    
    const numSet = new Set(nums);
    let maxLength = 0;
    
    for (const num of numSet) {
        if (!numSet.has(num - 1)) {
            let current = num;
            let length = 1;
            
            while (numSet.has(current + 1)) {
                current++;
                length++;
            }
            
            maxLength = Math.max(maxLength, length);
        }
    }
    
    return maxLength;
}

class Logger {
    private lastSeen: Map<string, number>;
    
    constructor() {
        this.lastSeen = new Map();
    }
    
    shouldPrintMessage(timestamp: number, message: string): boolean {
        if (!this.lastSeen.has(message)) {
            this.lastSeen.set(message, timestamp);
            return true;
        }
        
        if (timestamp - this.lastSeen.get(message)! >= 10) {
            this.lastSeen.set(message, timestamp);
            return true;
        }
        
        return false;
    }
}

// Test
if (require.main === module) {
    console.log("First Unique:", firstUniqChar("leetcode"));
    console.log("Unique Morse:", uniqueMorseRepresentations(["gin","zen","gig","msg"]));
    console.log("Frequency Sort:", frequencySort("tree"));
    console.log("Shortest Subarray:", findShortestSubArray([1,2,2,3,1]));
    console.log("Longest Consecutive:", longestConsecutive([100,4,200,1,3,2]));
    
    const logger = new Logger();
    console.log("Logger 1:", logger.shouldPrintMessage(1, "foo"));
    console.log("Logger 3:", logger.shouldPrintMessage(3, "foo"));
    console.log("Logger 11:", logger.shouldPrintMessage(11, "foo"));
}

export { firstUniqChar, uniqueMorseRepresentations, frequencySort, findShortestSubArray, longestConsecutive, Logger };
