/**
 * Count Subarrays with Exactly K Distinct - Trick: exactlyK = atMostK - atMost(K-1)
 * 
 * Problem: Count the number of subarrays with exactly K distinct integers.
 * 
 * Pattern: Use the "at most K" sliding window twice and subtract.
 *          Key insight: exactlyK(arr, K) = atMostK(arr, K) - atMostK(arr, K-1)
 * 
 * Time Complexity: O(n) - two linear passes
 * Space Complexity: O(k) - frequency map with at most k distinct elements
 */

/**
 * Count subarrays with exactly K distinct integers.
 * 
 * @param nums - Input array
 * @param k - Exact number of distinct integers required
 * @returns Count of valid subarrays
 */
function subarraysWithExactlyKDistinct(nums: number[], k: number): number {
    if (nums.length === 0 || k <= 0) return 0;
    
    return atMostKDistinct(nums, k) - atMostKDistinct(nums, k - 1);
}

/**
 * Count subarrays with at most K distinct integers.
 */
function atMostKDistinct(nums: number[], k: number): number {
    if (k < 0) return 0;

    const count = new Map<number, number>();
    let left = 0;
    let res = 0;

    for (let right = 0; right < nums.length; right++) {
        const x = nums[right];
        count.set(x, (count.get(x) || 0) + 1);

        // Shrink window while we have too many distinct integers
        while (count.size > k) {
            const y = nums[left];
            count.set(y, count.get(y)! - 1);
            if (count.get(y) === 0) {
                count.delete(y);
            }
            left++;
        }

        // All subarrays ending at right with start in [left..right] are valid
        res += right - left + 1;
    }
    return res;
}

// Test cases
if (require.main === module) {
    const testCases: [number[], number, number][] = [
        [[1, 2, 1, 2, 3], 2, 7],
        [[1, 2, 1, 3, 4], 3, 3],
        [[1], 1, 1],
        [[1, 1, 1], 1, 6],
    ];
    
    for (const [nums, k, expected] of testCases) {
        const result = subarraysWithExactlyKDistinct(nums, k);
        const status = result === expected ? "✓" : "✗";
        console.log(`${status} subarraysWithExactlyKDistinct([${nums}], k=${k}) = ${result} (expected ${expected})`);
    }
}

export { subarraysWithExactlyKDistinct };
