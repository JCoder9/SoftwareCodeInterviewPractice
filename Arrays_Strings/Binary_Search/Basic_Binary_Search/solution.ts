/**
 * Binary Search - Classic Divide and Conquer Search
 *
 * Problem (LC 704): Given a sorted array of integers and a target, return the
 * index of the target if it exists, otherwise return -1.
 *
 * Problem (LC 35): Given a sorted array and a target, return the index to insert
 * the target so the array stays sorted (or return the index if it already exists).
 *
 * Problem (LC 34): Given a sorted array and a target, return the first and last
 * indices of the target. Return [-1, -1] if not found.
 *
 * Pattern: Maintain a [left, right] window. Each iteration eliminate half the
 *          search space by comparing the midpoint to the target.
 *
 * Time Complexity: O(log n) - search space halves each iteration
 * Space Complexity: O(1) - no extra memory needed
 */

// ─────────────────────────────────────────────────────────────────────────────
// NAIVE APPROACH (Linear Search) — O(n) time | O(1) space
// ─────────────────────────────────────────────────────────────────────────────
// INTERVIEW SCRIPT:
//   1. Describe:   "Scan every element left to right until we find the target."
//   2. Problem:    "Ignores the sorted property — O(n) instead of O(log n);
//                  n=10⁶ means a million comparisons vs ~20."
//   3. Transition: "Binary search halves the search space each step by
//                  exploiting sorted order, giving O(log n)."
//
// function binarySearchNaive(nums: number[], target: number): number {
//     for (let i = 0; i < nums.length; i++) {
//         if (nums[i] === target) return i;
//     }
//     return -1;
// }
// ─────────────────────────────────────────────────────────────────────────────

// ── Binary Search (LC 704) ────────────────────────────────────────────────────
// Pattern: Maintain [left, right] window; halve each iteration on comparison.
// Key detail: mid = left + Math.floor((right - left) / 2)  — avoids overflow.
// Time: O(log n)  Space: O(1)
function binarySearch(nums: number[], target: number): number {
    let left = 0, right = nums.length - 1;
    while (left <= right) {
        const mid = left + Math.floor((right - left) / 2);
        if (nums[mid] === target) return mid;
        else if (nums[mid] < target) left = mid + 1;
        else right = mid - 1;
    }
    return -1;
}

// ── Search Insert Position (LC 35) ────────────────────────────────────────────
// Pattern: Same loop; when target is absent, left is the insertion index.
// Time: O(log n)  Space: O(1)
function searchInsert(nums: number[], target: number): number {
    let left = 0, right = nums.length - 1;
    while (left <= right) {
        const mid = left + Math.floor((right - left) / 2);
        if (nums[mid] === target) return mid;
        else if (nums[mid] < target) left = mid + 1;
        else right = mid - 1;
    }
    return left;                                   // insertion point
}

// ── Find First and Last Position (LC 34) ──────────────────────────────────────
// Pattern: Two passes — one biased left (keep going left on hit),
//          one biased right (keep going right on hit).
// Time: O(log n)  Space: O(1)
function searchRange(nums: number[], target: number): number[] {
    function findBound(biasLeft: boolean): number {
        let lo = 0, hi = nums.length - 1, result = -1;
        while (lo <= hi) {
            const mid = lo + Math.floor((hi - lo) / 2);
            if (nums[mid] === target) {
                result = mid;
                if (biasLeft) hi = mid - 1;        // keep searching left
                else lo = mid + 1;                 // keep searching right
            } else if (nums[mid] < target) lo = mid + 1;
            else hi = mid - 1;
        }
        return result;
    }
    return [findBound(true), findBound(false)];
}

// ── Tests ──────────────────────────────────────────────────────────────────────
console.log(binarySearch([-1, 0, 3, 5, 9, 12], 9));      // 4
console.log(binarySearch([-1, 0, 3, 5, 9, 12], 2));      // -1
console.log(searchInsert([1, 3, 5, 6], 5));               // 2
console.log(searchInsert([1, 3, 5, 6], 2));               // 1
console.log(searchRange([5, 7, 7, 8, 8, 10], 8));         // [3, 4]
console.log(searchRange([5, 7, 7, 8, 8, 10], 6));         // [-1, -1]
