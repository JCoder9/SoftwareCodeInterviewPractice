/**
 * Maximum Sum of Subarray Size K - Fixed-Size Sliding Window
 * 
 * Problem: Find the maximum sum of any subarray of length k.
 * 
 * Pattern: Fixed window size - add new element, remove old element when window exceeds k.
 * 
 * Time Complexity: O(n) - single pass through array
 * Space Complexity: O(1) - only tracking window sum
 */

/**
 * Find maximum sum of any subarray of length k.
 * 
 * @param nums - Input array
 * @param k - Window size
 * @returns Maximum sum, or null if no valid window possible
 */
function maxSumK(nums: number[], k: number): number | null {
    if (nums.length < k || k <= 0) {
        return null;  // No valid window possible
    }
    
    let left = 0;
    let windowSum = 0;
    let best = -Infinity;  // Safe now

    for (let right = 0; right < nums.length; right++) {
        windowSum += nums[right];

        // Shrink window if it exceeds size k
        if (right - left + 1 > k) {
            windowSum -= nums[left];
            left++;
        }

        // Update best when window is exactly size k
        if (right - left + 1 === k) {
            best = Math.max(best, windowSum);
        }
    }
    return best;
}

// Test cases
if (require.main === module) {
    const testCases: [number[], number, number | null][] = [
        [[1, 4, 2, 10, 23, 3, 1, 0, 20], 4, 39],
        [[2, 3], 3, null],
        [[1, -1, 5, -2, 3], 2, 4],
        [[1, -1, 5, -2, 3], 3, 6],
        [[5], 1, 5],
    ];
    
    for (const [nums, k, expected] of testCases) {
        const result = maxSumK(nums, k);
        const status = result === expected ? "✓" : "✗";
        console.log(`${status} maxSumK([${nums}], k=${k}) = ${result} (expected ${expected})`);
    }
}

export { maxSumK };
