/**
 * Range Sum Query - Prefix Sum Pattern
 * 
 * Problem: Given an array, answer multiple queries for sum of elements in range [left, right].
 * 
 * Pattern: Build prefix sum array where prefix[i] = sum of nums[0...i-1].
 *          Then range_sum(left, right) = prefix[right+1] - prefix[left]
 * 
 * Time Complexity: O(n) preprocessing, O(1) per query
 * Space Complexity: O(n) for prefix array
 */

class NumArray {
    private prefix: number[];
    
    /**
     * Build prefix sum array.
     * prefix[i] = sum of nums[0] to nums[i-1]
     */
    constructor(nums: number[]) {
        this.prefix = [0];
        for (const num of nums) {
            this.prefix.push(this.prefix[this.prefix.length - 1] + num);
        }
    }
    
    /**
     * Return sum of elements from index left to right (inclusive).
     */
    sumRange(left: number, right: number): number {
        return this.prefix[right + 1] - this.prefix[left];
    }
}

/**
 * Count number of subarrays with sum equal to k.
 * 
 * Key insight: If prefix[j] - prefix[i] = k, then subarray [i, j) sums to k.
 * Use hash map to track prefix sums seen so far.
 */
function subarraySumEqualsK(nums: number[], k: number): number {
    let count = 0;
    let prefixSum = 0;
    const prefixMap = new Map<number, number>();
    prefixMap.set(0, 1);  // Empty prefix
    
    for (const num of nums) {
        prefixSum += num;
        
        // Check if (prefixSum - k) exists
        if (prefixMap.has(prefixSum - k)) {
            count += prefixMap.get(prefixSum - k)!;
        }
        
        // Record current prefix sum
        prefixMap.set(prefixSum, (prefixMap.get(prefixSum) || 0) + 1);
    }
    
    return count;
}

// Test cases
if (require.main === module) {
    // Test range sum query
    console.log("Range Sum Query:");
    const nums = [-2, 0, 3, -5, 2, -1];
    const obj = new NumArray(nums);
    const queries: [number, number][] = [[0, 2], [2, 5], [0, 5]];
    for (const [left, right] of queries) {
        console.log(`  sumRange(${left}, ${right}) = ${obj.sumRange(left, right)}`);
    }
    
    // Test subarray sum equals k
    console.log("\nSubarray Sum Equals K:");
    const testCases: [number[], number, number][] = [
        [[1, 1, 1], 2, 2],
        [[1, 2, 3], 3, 2],
        [[1], 0, 0],
    ];
    for (const [arr, k, expected] of testCases) {
        const result = subarraySumEqualsK(arr, k);
        const status = result === expected ? "✓" : "✗";
        console.log(`${status} subarraySumEqualsK([${arr}], k=${k}) = ${result}`);
    }
}

export { NumArray, subarraySumEqualsK };
