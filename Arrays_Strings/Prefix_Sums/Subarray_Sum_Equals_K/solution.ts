/**
 * Subarray Sum Equals K - Prefix Sum + HashMap Pattern
 * 
 * LeetCode 560 - Extremely common at Google, Facebook, Amazon, Microsoft
 * 
 * Problem: Given an array and integer k, count how many contiguous subarrays sum to k.
 * 
 * Key Insight: If prefix[j] - prefix[i] = k, then subarray from i+1 to j sums to k.
 *              Rearranging: prefix[i] = prefix[j] - k
 *              So while at position j, check if (currentSum - k) was seen before!
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n) for HashMap
 */

// ─────────────────────────────────────────────────────────────────────────
// NAIVE APPROACH (Brute Force) - O(n²) time | O(1) space
// ─────────────────────────────────────────────────────────────────────────
// INTERVIEW SCRIPT:
//   1. Describe:   "Check every possible subarray - for each starting position,
//                   extend right and track sum until we hit k"
//   2. Problem:    "This is O(n²) with nested loops. For n=10⁴, that's 10⁸ operations"
//   3. Transition: "With prefix sums + HashMap, we can do it in O(n) with single pass.
//                   The key is: if we've seen (currentSum - k) before, those positions
//                   start subarrays that end here and sum to k"
//
// function subarraySumBruteForce(nums: number[], k: number): number {
//     let count = 0;
//     for (let start = 0; start < nums.length; start++) {
//         let sum = 0;
//         for (let end = start; end < nums.length; end++) {
//             sum += nums[end];
//             if (sum === k) count++;
//         }
//     }
//     return count;
// }
// ─────────────────────────────────────────────────────────────────────────

/**
 * Count subarrays with sum equals k.
 * 
 * HashMap stores: prefixSum -> frequency (how many times we've seen this prefix sum)
 * Why frequency? Same prefix sum can appear multiple times!
 * 
 * Example: nums = [1, -1, 1, -1, 1], k = 0
 *          prefixSum cycles: 1, 0, 1, 0, 1
 *          Multiple ways to form subarrays with sum = 0
 */
function subarraySumEqualsK(nums: number[], k: number): number {
    let count = 0;
    let prefixSum = 0;
    const prefixMap = new Map<number, number>();
    prefixMap.set(0, 1);  // Handle subarrays starting from index 0
    
    for (const num of nums) {
        prefixSum += num;
        
        // If (prefixSum - k) exists, those positions can start subarrays ending here
        if (prefixMap.has(prefixSum - k)) {
            count += prefixMap.get(prefixSum - k)!;
        }
        
        // Record current prefix sum
        prefixMap.set(prefixSum, (prefixMap.get(prefixSum) || 0) + 1);
    }
    
    return count;
}

/**
 * VARIANT: Find maximum length of subarray with sum equals k.
 * 
 * Strategy: Store first occurrence of each prefix sum (want longest subarray)
 */
function maxSubarrayLenEqualsK(nums: number[], k: number): number {
    let maxLen = 0;
    let prefixSum = 0;
    const prefixMap = new Map<number, number>();
    prefixMap.set(0, -1);  // For subarrays starting from index 0
    
    for (let i = 0; i < nums.length; i++) {
        prefixSum += nums[i];
        
        if (prefixMap.has(prefixSum - k)) {
            maxLen = Math.max(maxLen, i - prefixMap.get(prefixSum - k)!);
        }
        
        // Only store FIRST occurrence (want max length)
        if (!prefixMap.has(prefixSum)) {
            prefixMap.set(prefixSum, i);
        }
    }
    
    return maxLen;
}

/**
 * VARIANT (LC 523): Check if subarray sum is multiple of k (length >= 2).
 * 
 * Key: Two sums with same remainder (mod k) means the subarray between them is divisible by k.
 */
function checkSubarraySumMultipleK(nums: number[], k: number): boolean {
    if (nums.length < 2) return false;
    
    const remainderMap = new Map<number, number>();
    remainderMap.set(0, -1);
    let prefixSum = 0;
    
    for (let i = 0; i < nums.length; i++) {
        prefixSum += nums[i];
        const remainder = k !== 0 ? prefixSum % k : prefixSum;
        
        if (remainderMap.has(remainder)) {
            if (i - remainderMap.get(remainder)! >= 2) {
                return true;
            }
        } else {
            remainderMap.set(remainder, i);
        }
    }
    
    return false;
}

/**
 * VARIANT (LC 974): Count subarrays with sum divisible by k.
 * 
 * Handle negative remainders: ((sum % k) + k) % k ensures positive remainder
 */
function subarraysDivisibleByK(nums: number[], k: number): number {
    let count = 0;
    let prefixSum = 0;
    const remainderCount = new Map<number, number>();
    remainderCount.set(0, 1);
    
    for (const num of nums) {
        prefixSum += num;
        let remainder = ((prefixSum % k) + k) % k;  // Handle negative remainders
        
        count += remainderCount.get(remainder) || 0;
        remainderCount.set(remainder, (remainderCount.get(remainder) || 0) + 1);
    }
    
    return count;
}

// Example usage
console.log("=== Subarray Sum Equals K (LC 560) ===");
const testCases: [number[], number, number][] = [
    [[1, 1, 1], 2, 2],           // [1,1] at [0,1] and [1,2]
    [[1, 2, 3], 3, 2],           // [3] and [1,2]
    [[1, -1, 0], 0, 3]           // [-1,1], [0], [-1,1,0]
];
for (const [nums, k, expected] of testCases) {
    const result = subarraySumEqualsK(nums, k);
    const status = result === expected ? "✓" : "✗";
    console.log(`${status} subarraySumEqualsK([${nums}], k=${k}) = ${result}`);
}

console.log("\n=== Maximum Length Subarray Sum K ===");
console.log(`maxLen([1,-1,5,-2,3], k=3) = ${maxSubarrayLenEqualsK([1,-1,5,-2,3], 3)}`);  // 4: [-1,5,-2,3]

console.log("\n=== Subarray Sum Multiple of K (LC 523) ===");
console.log(`multiple([23,2,4,6,7], k=6) = ${checkSubarraySumMultipleK([23,2,4,6,7], 6)}`);  // true: [2,4]

console.log("\n=== Count Divisible by K (LC 974) ===");
console.log(`divisible([4,5,0,-2,-3,1], k=5) = ${subarraysDivisibleByK([4,5,0,-2,-3,1], 5)}`);  // 7

export { subarraySumEqualsK, maxSubarrayLenEqualsK, checkSubarraySumMultipleK, subarraysDivisibleByK };
