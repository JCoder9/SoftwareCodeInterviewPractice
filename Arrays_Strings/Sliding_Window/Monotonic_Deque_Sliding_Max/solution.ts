/**
 * Sliding Window Maximum - Monotonic Deque Pattern
 * 
 * Problem: Given an array and window size k, find the maximum element in each
 *          sliding window as it moves from left to right.
 * 
 * Pattern: Use a deque to maintain indices of elements in decreasing order of values.
 *          Front of deque always contains index of maximum element in current window.
 * 
 * Time Complexity: O(n) - each element added and removed from deque at most once
 * Space Complexity: O(k) - deque holds at most k elements
 */

/**
 * Find maximum in each sliding window of size k.
 * 
 * @param nums - Input array
 * @param k - Window size
 * @returns Array of maximum values for each window position
 */
function maxSlidingWindow(nums: number[], k: number): number[] {
    if (nums.length === 0 || k <= 0 || k > nums.length) {
        return [];  // No valid windows
    }
    
    const dq: number[] = [];  // Simulating deque with array (stores indices)
    const res: number[] = [];

    for (let i = 0; i < nums.length; i++) {
        // Remove indices of smaller elements from back
        while (dq.length > 0 && nums[dq[dq.length - 1]] <= nums[i]) {
            dq.pop();
        }
        dq.push(i);

        // Remove indices outside current window from front
        if (dq[0] <= i - k) {
            dq.shift();
        }

        // Once we have a full window, record maximum
        if (i >= k - 1) {
            res.push(nums[dq[0]]);
        }
    }
    return res;
}

// Test cases
if (require.main === module) {
    const testCases: [number[], number, number[]][] = [
        [[1, 3, -1, -3, 5, 3, 6, 7], 3, [3, 3, 5, 5, 6, 7]],
        [[1], 1, [1]],
        [[1, -1], 1, [1, -1]],
        [[9, 11], 2, [11]],
        [[4, -2], 2, [4]],
    ];
    
    for (const [nums, k, expected] of testCases) {
        const result = maxSlidingWindow(nums, k);
        const status = JSON.stringify(result) === JSON.stringify(expected) ? "✓" : "✗";
        console.log(`${status} maxSlidingWindow([${nums}], k=${k}) = [${result}] (expected [${expected}])`);
    }
}

export { maxSlidingWindow };
