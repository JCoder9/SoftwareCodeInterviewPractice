/**
 * Seen Set / Last Index / Deduplication - Hash Set Pattern
 * 
 * Problem: Use hash sets to track seen elements.
 * 
 * Time Complexity: O(n) with O(1) lookups
 * Space Complexity: O(n)
 */

function containsDuplicate(nums: number[]): boolean {
    const seen = new Set<number>();
    for (const num of nums) {
        if (seen.has(num)) return true;
        seen.add(num);
    }
    return false;
}

function firstUniqueChar(s: string): number {
    const count = new Map<string, number>();
    
    for (const c of s) {
        count.set(c, (count.get(c) || 0) + 1);
    }
    
    for (let i = 0; i < s.length; i++) {
        if (count.get(s[i]) === 1) return i;
    }
    return -1;
}

function longestConsecutive(nums: number[]): number {
    const numSet = new Set(nums);
    let longest = 0;
    
    for (const num of numSet) {
        if (!numSet.has(num - 1)) {
            let current = num;
            let length = 1;
            
            while (numSet.has(current + 1)) {
                current++;
                length++;
            }
            
            longest = Math.max(longest, length);
        }
    }
    
    return longest;
}

function twoSum(nums: number[], target: number): number[] | null {
    const seen = new Map<number, number>();
    
    for (let i = 0; i < nums.length; i++) {
        const complement = target - nums[i];
        if (seen.has(complement)) {
            return [seen.get(complement)!, i];
        }
        seen.set(nums[i], i);
    }
    
    return null;
}

// Test cases
if (require.main === module) {
    console.log("Contains Duplicate: " + containsDuplicate([1, 2, 3, 1]));
    console.log("First Unique Char: " + firstUniqueChar("leetcode"));
    console.log("Longest Consecutive: " + longestConsecutive([100, 4, 200, 1, 3, 2]));
    console.log("Two Sum: " + JSON.stringify(twoSum([2, 7, 11, 15], 9)));
}

export { containsDuplicate, firstUniqueChar, longestConsecutive, twoSum };
