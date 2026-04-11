import java.util.*;

/**
 * Binary Search - Classic Divide and Conquer Search
 * Pattern: Maintain a [left, right] window. Each iteration eliminate half the
 *          search space by comparing the midpoint to the target.
 *
 * Time Complexity: O(log n) - search space halves each iteration
 * Space Complexity: O(1) - no extra memory needed
 */
public class solution {

    // ─────────────────────────────────────────────────────────────────────────
    // NAIVE APPROACH (Linear Search) — O(n) time | O(1) space
    // ─────────────────────────────────────────────────────────────────────────
    // INTERVIEW SCRIPT:
    //   1. Describe:   "Scan every element left to right until we find the target."
    //   2. Problem:    "Ignores the sorted property — O(n) instead of O(log n);
    //                  n=10⁶ means a million comparisons vs ~20."
    //   3. Transition: "Binary search halves the search space each step by
    //                  exploiting sorted order, giving O(log n)."
    //
    // static int binarySearchNaive(int[] nums, int target) {
    //     for (int i = 0; i < nums.length; i++) {
    //         if (nums[i] == target) return i;
    //     }
    //     return -1;
    // }
    // ─────────────────────────────────────────────────────────────────────────

    // ── Binary Search (LC 704) ────────────────────────────────────────────────
    // * Problem (LC 704): Given a sorted array of integers and a target, return the
    // * index of the target if it exists, otherwise return -1.
    // Pattern: Maintain [left, right] window; halve each iteration on comparison.
    // Key detail: mid = left + (right - left) / 2  — avoids integer overflow.
    // Time: O(log n)  Space: O(1)
    static int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    // ── Search Insert Position (LC 35) ────────────────────────────────────────
    // Problem (LC 35): Given a sorted array and a target, return the index to insert
    // the target so the array stays sorted (or return the index if it already exists).
    // Pattern: Same loop; when target is absent, left is the insertion index.
    // Time: O(log n)  Space: O(1)
    static int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return left;                               // insertion point
    }

    // ── Find First and Last Position (LC 34) ──────────────────────────────────
    // Problem (LC 34): Given a sorted array and a target, return the first and last
    // indices of the target. Return [-1, -1] if not found.
    // Pattern: Two passes — one biased left (keep going left on hit),
    //          one biased right (keep going right on hit).
    // Time: O(log n)  Space: O(1)
    static int[] searchRange(int[] nums, int target) {
        return new int[]{ findBound(nums, target, true), findBound(nums, target, false) };
    }

    private static int findBound(int[] nums, int target, boolean biasLeft) {
        int lo = 0, hi = nums.length - 1, result = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) {
                result = mid;
                if (biasLeft) hi = mid - 1;        // keep searching left
                else lo = mid + 1;                 // keep searching right
            } else if (nums[mid] < target) lo = mid + 1;
            else hi = mid - 1;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(binarySearch(new int[]{-1, 0, 3, 5, 9, 12}, 9));   // 4
        System.out.println(binarySearch(new int[]{-1, 0, 3, 5, 9, 12}, 2));   // -1
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 5));            // 2
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 2));            // 1
        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)));  // [3, 4]
        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6)));  // [-1, -1]
    }
}
