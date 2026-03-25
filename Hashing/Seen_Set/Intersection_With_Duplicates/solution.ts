/**
 * Intersection With Duplicates - Count-Based Pattern
 * 
 * Time Complexity: O(n + m)
 * Space Complexity: O(min(n, m))
 */

function intersect(nums1: number[], nums2: number[]): number[] {
    if (nums1.length > nums2.length) {
        [nums1, nums2] = [nums2, nums1];
    }
    
    const count = new Map<number, number>();
    for (const num of nums1) {
        count.set(num, (count.get(num) || 0) + 1);
    }
    
    const result: number[] = [];
    for (const num of nums2) {
        if ((count.get(num) || 0) > 0) {
            result.push(num);
            count.set(num, count.get(num)! - 1);
        }
    }
    
    return result;
}

function commonChars(words: string[]): string[] {
    const commonCount = new Array(26).fill(Infinity);
    
    for (const word of words) {
        const wordCount = new Array(26).fill(0);
        
        for (const char of word) {
            wordCount[char.charCodeAt(0) - 'a'.charCodeAt(0)]++;
        }
        
        for (let i = 0; i < 26; i++) {
            commonCount[i] = Math.min(commonCount[i], wordCount[i]);
        }
    }
    
    const result: string[] = [];
    for (let i = 0; i < 26; i++) {
        for (let j = 0; j < commonCount[i]; j++) {
            result.push(String.fromCharCode(i + 'a'.charCodeAt(0)));
        }
    }
    
    return result;
}

function destCity(paths: string[][]): string {
    const sources = new Set<string>();
    
    for (const [source, dest] of paths) {
        sources.add(source);
    }
    
    for (const [source, dest] of paths) {
        if (!sources.has(dest)) {
            return dest;
        }
    }
    
    return '';
}

function checkIfExist(arr: number[]): boolean {
    const seen = new Set<number>();
    
    for (const num of arr) {
        if (seen.has(num * 2) || (num % 2 === 0 && seen.has(num / 2))) {
            return true;
        }
        seen.add(num);
    }
    
    return false;
}

function findLucky(arr: number[]): number {
    const count = new Map<number, number>();
    
    for (const num of arr) {
        count.set(num, (count.get(num) || 0) + 1);
    }
    
    let lucky = -1;
    for (const [num, freq] of count) {
        if (num === freq) {
            lucky = Math.max(lucky, num);
        }
    }
    
    return lucky;
}

function numJewelsInStones(jewels: string, stones: string): number {
    const jewelSet = new Set(jewels);
    
    let count = 0;
    for (const stone of stones) {
        if (jewelSet.has(stone)) {
            count++;
        }
    }
    
    return count;
}

function findPairs(nums: number[], k: number): number {
    if (k < 0) return 0;
    
    const count = new Map<number, number>();
    for (const num of nums) {
        count.set(num, (count.get(num) || 0) + 1);
    }
    
    let result = 0;
    for (const num of count.keys()) {
        if (k === 0) {
            if (count.get(num)! > 1) {
                result++;
            }
        } else {
            if (count.has(num + k)) {
                result++;
            }
        }
    }
    
    return result;
}

// Test
if (require.main === module) {
    console.log("Intersection II:", intersect([1,2,2,1], [2,2]));
    console.log("Common Chars:", commonChars(["bella","label","roller"]));
    console.log("Dest City:", destCity([["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]]));
    console.log("Check If Exist:", checkIfExist([10,2,5,3]));
    console.log("Find Lucky:", findLucky([2,2,3,4]));
    console.log("Jewels in Stones:", numJewelsInStones("aA", "aAAbbbb"));
    console.log("K-diff Pairs:", findPairs([3,1,4,1,5], 2));
}

export { intersect, commonChars, destCity, checkIfExist, findLucky, numJewelsInStones, findPairs };
