/**
 * Ransom Note + First Unique Char + Top K Frequent
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(k)
 */

function canConstruct(ransomNote: string, magazine: string): boolean {
    const count = new Array(26).fill(0);
    
    for (const c of magazine) {
        count[c.charCodeAt(0) - 97]++;
    }
    
    for (const c of ransomNote) {
        if (count[c.charCodeAt(0) - 97] <= 0) return false;
        count[c.charCodeAt(0) - 97]--;
    }
    
    return true;
}

function firstUniqChar(s: string): number {
    const count = new Array(26).fill(0);
    
    for (const c of s) {
        count[c.charCodeAt(0) - 97]++;
    }
    
    for (let i = 0; i < s.length; i++) {
        if (count[s.charCodeAt(i) - 97] === 1) {
            return i;
        }
    }
    
    return -1;
}

function topKFrequent(nums: number[], k: number): number[] {
    const freqCount = new Map<number, number>();
    for (const num of nums) {
        freqCount.set(num, (freqCount.get(num) || 0) + 1);
    }
    
    // Bucket sort approach
    const buckets: number[][] = Array.from({ length: nums.length + 1 }, () => []);
    
    for (const [num, freq] of freqCount) {
        buckets[freq].push(num);
    }
    
    const result: number[] = [];
    for (let freq = buckets.length - 1; freq >= 0 && result.length < k; freq--) {
        for (const num of buckets[freq]) {
            result.push(num);
            if (result.length === k) break;
        }
    }
    
    return result;
}

function majorityElement(nums: number[]): number {
    let candidate = 0, count = 0;
    
    for (const num of nums) {
        if (count === 0) {
            candidate = num;
        }
        count += (num === candidate) ? 1 : -1;
    }
    
    return candidate;
}

function frequencySort(s: string): string {
    const freqCount = new Map<string, number>();
    for (const c of s) {
        freqCount.set(c, (freqCount.get(c) || 0) + 1);
    }
    
    const chars = Array.from(freqCount.keys());
    chars.sort((a, b) => freqCount.get(b)! - freqCount.get(a)!);
    
    let result = '';
    for (const c of chars) {
        result += c.repeat(freqCount.get(c)!);
    }
    
    return result;
}

// Test
if (require.main === module) {
    console.log("Ransom Note:", canConstruct("aa", "aab"));
    console.log("First Unique:", firstUniqChar("leetcode"));
    console.log("Top K Frequent:", topKFrequent([1,1,1,2,2,3], 2));
    console.log("Majority Element:", majorityElement([3,2,3]));
    console.log("Frequency Sort:", frequencySort("tree"));
}

export { canConstruct, firstUniqChar, topKFrequent, majorityElement, frequencySort };
