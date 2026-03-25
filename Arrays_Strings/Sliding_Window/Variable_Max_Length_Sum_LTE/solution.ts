/**
 * Maximum Length Subarray with Sum <= S - Variable-Size Sliding Window
 * 
 * Problem: Find the maximum length of a contiguous subarray whose sum is <= S.
 * 
 * Pattern: Variable window - expand to include elements, shrink when invalid.
 * 
 * Time Complexity: O(n) - each element visited at most twice
 * Space Complexity: O(1) - only tracking window sum and pointers
 */

/**
 * Find maximum length of subarray with sum <= S.
 * 
 * @param nums - Input array (positive integers)
 * @param S - Maximum sum threshold
 * @returns Maximum length of valid subarray
 */
function maxLenSumAtMostS(nums: number[], S: number): number {
    if (nums.length === 0) return 0;
    
    let left = 0;
    let windowSum = 0;
    let best = 0;

    for (let right = 0; right < nums.length; right++) {
        windowSum += nums[right];

        // Shrink window while it's invalid (sum > S)
        while (windowSum > S) {
            windowSum -= nums[left];
            left++;
        }

        // Window is valid, update best
        best = Math.max(best, right - left + 1);
    }
    return best;
}

// Test cases
if (require.main === module) {
    const testCases: [number[], number, number][] = [
        [[1, 2, 3, 4, 5], 8, 3],
        [[5, 1, 1, 1, 1], 6, 5],
        [[3, 1, 2, 1], 4, 3],
        [[1, 1, 1], 2, 2],
        [[10], 5, 0],
    ];
    
    for (const [nums, S, expected] of testCases) {
        const result = maxLenSumAtMostS(nums, S);
        const status = result === expected ? "✓" : "✗";
        console.log(`${status} maxLenSumAtMostS([${nums}], S=${S}) = ${result} (expected ${expected})`);
    }
}

export { maxLenSumAtMostS };
