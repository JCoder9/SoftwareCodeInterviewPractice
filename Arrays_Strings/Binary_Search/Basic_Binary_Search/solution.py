"""
Binary Search - Classic Divide and Conquer Search

Problem (LC 704): Given a sorted array of integers and a target, return the
index of the target if it exists, otherwise return -1.

Problem (LC 35): Given a sorted array and a target, return the index to insert
the target so the array stays sorted (or return the index if it already exists).

Problem (LC 34): Given a sorted array and a target, return the first and last
indices of the target. Return [-1, -1] if not found.

Pattern: Maintain a [left, right] window. Each iteration eliminate half the
         search space by comparing the midpoint to the target.

Time Complexity: O(log n) - search space halves each iteration
Space Complexity: O(1) - no extra memory needed
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Linear Search) — O(n) time | O(1) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Scan every element left to right until we find the target."
#   2. Problem:    "Ignores the sorted property — O(n) instead of O(log n);
#                  n=10⁶ means a million comparisons vs ~20."
#   3. Transition: "Binary search halves the search space each step by
#                  exploiting sorted order, giving O(log n)."
#
# def binary_search_naive(nums: list[int], target: int) -> int:
#     for i, v in enumerate(nums):
#         if v == target:
#             return i
#     return -1
# ─────────────────────────────────────────────────────────────────────────────

# ── Binary Search (LC 704) ────────────────────────────────────────────────────
# Pattern: Maintain [left, right] window; halve each iteration on comparison.
# Key detail: mid = left + (right - left) // 2  — avoids integer overflow.
# Time: O(log n)  Space: O(1)
def binary_search(nums: list[int], target: int) -> int:
    left, right = 0, len(nums) - 1
    while left <= right:
        mid = left + (right - left) // 2
        if nums[mid] == target:
            return mid
        elif nums[mid] < target:
            left = mid + 1
        else:
            right = mid - 1
    return -1


# ── Search Insert Position (LC 35) ────────────────────────────────────────────
# Pattern: Same loop; when target is absent, left is the insertion index.
# Time: O(log n)  Space: O(1)
def search_insert(nums: list[int], target: int) -> int:
    left, right = 0, len(nums) - 1
    while left <= right:
        mid = left + (right - left) // 2
        if nums[mid] == target:
            return mid
        elif nums[mid] < target:
            left = mid + 1
        else:
            right = mid - 1
    return left                                    # insertion point


# ── Find First and Last Position (LC 34) ──────────────────────────────────────
# Pattern: Two passes — one biased left (keep going left on hit),
#          one biased right (keep going right on hit).
# Time: O(log n)  Space: O(1)
def search_range(nums: list[int], target: int) -> list[int]:
    def find_bound(bias_left: bool) -> int:
        lo, hi, result = 0, len(nums) - 1, -1
        while lo <= hi:
            mid = lo + (hi - lo) // 2
            if nums[mid] == target:
                result = mid
                if bias_left:
                    hi = mid - 1               # keep searching left
                else:
                    lo = mid + 1               # keep searching right
            elif nums[mid] < target:
                lo = mid + 1
            else:
                hi = mid - 1
        return result

    return [find_bound(True), find_bound(False)]


if __name__ == "__main__":
    print(binary_search([-1, 0, 3, 5, 9, 12], 9))      # 4
    print(binary_search([-1, 0, 3, 5, 9, 12], 2))      # -1
    print(search_insert([1, 3, 5, 6], 5))               # 2
    print(search_insert([1, 3, 5, 6], 2))               # 1
    print(search_range([5, 7, 7, 8, 8, 10], 8))         # [3, 4]
    print(search_range([5, 7, 7, 8, 8, 10], 6))         # [-1, -1]
