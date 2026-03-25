/**
 * Minimum Length Subarray with Sum >= S - Variable-Size Sliding Window
 * 
 * Problem: Find the minimum length of a contiguous subarray whose sum is >= S.
 * 
 * Pattern: Variable window - expand to include elements, shrink when valid to minimize.
 * 
 * Time Complexity: O(n) - each element visited at most twice
 * Space Complexity: O(1) - only tracking window sum and pointers
 */

/**
 * Find minimum length of subarray with sum >= S.
 * 
 * @param nums - Input array (positive integers)
 * @param S - Target sum threshold
 * @returns Minimum length, or 0 if no such subarray exists
 */
function minLenSumAtLeastS(nums: number[], S: number): number {
    if (nums.length === 0) return 0;
    
    let left = 0;
    let windowSum = 0;
    let best = Infinity;

    for (let right = 0; right < nums.length; right++) {
        windowSum += nums[right];

        // Shrink window while it's valid (sum >= S)
        while (windowSum >= S) {
            best = Math.min(best, right - left + 1);
            windowSum -= nums[left];
            left++;
        }
    }
    return best === Infinity ? 0 : best;
}

// Test cases
if (require.main === module) {
    const testCases: [number[], number, number][] = [
        [[2, 3, 1, 2, 4, 3], 7, 2],
        [[1, 4, 4], 4, 1],
        [[1, 1, 1, 1, 1], 11, 0],
        [[1, 2, 3, 4, 5], 11, 3],
        [[5, 1, 3, 5, 10, 7], 15, 2],
    ];
    
    for (const [nums, S, expected] of testCases) {
        const result = minLenSumAtLeastS(nums, S);
        const status = result === expected ? "✓" : "✗";
        console.log(`${status} minLenSumAtLeastS([${nums}], S=${S}) = ${result} (expected ${expected})`);
    }
}

export { minLenSumAtLeastS };
