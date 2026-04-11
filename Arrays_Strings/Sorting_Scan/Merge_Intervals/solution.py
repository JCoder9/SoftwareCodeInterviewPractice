"""
Merge Intervals - Sort + Scan Pattern

Problem: Given collection of intervals, merge all overlapping intervals.
         Example: [[1,3],[2,6],[8,10],[15,18]] → [[1,6],[8,10],[15,18]]

Pattern: 1. Sort intervals by start time
         2. Scan through, merging overlapping intervals

Related LeetCode Problems:
- LC 56: Merge Intervals (Medium) ⭐⭐⭐
- LC 57: Insert Interval (Medium)
- LC 252: Meeting Rooms (Easy)
- LC 253: Meeting Rooms II (Medium)

Time Complexity: O(n log n) - dominated by sorting
Space Complexity: O(n) - result array
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(n³) time | O(n) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force compares every pair of intervals, merges if overlap,
#                  repeats until no more merges — O(n³) worst case"
#   2. Problem:    "For 100 intervals: up to 1 million comparisons; repeated scans
#                  after each merge"
#   3. Transition: "Sort first, then single scan left-to-right merging adjacent
#                  overlaps — O(n log n)"
#
# def merge_intervals_naive(intervals):
#     if not intervals:
#         return []
#     
#     result = [list(interval) for interval in intervals]
#     merged = True
#     
#     # Keep merging until no more overlaps found
#     while merged:
#         merged = False
#         i = 0
#         while i < len(result):
#             j = i + 1
#             while j < len(result):
#                 # Check if intervals[i] and intervals[j] overlap
#                 if not (result[i][1] < result[j][0] or result[j][1] < result[i][0]):
#                     # Merge them
#                     result[i] = [min(result[i][0], result[j][0]),
#                                  max(result[i][1], result[j][1])]
#                     result.pop(j)
#                     merged = True
#                     break
#                 j += 1
#             i += 1
#     return result
# ─────────────────────────────────────────────────────────────────────────────

from typing import List

def merge_intervals(intervals: List[List[int]]) -> List[List[int]]:
    """
    Merge overlapping intervals.
    
    Args:
        intervals: List of [start, end] intervals
        
    Returns:
        List of merged intervals
    """
    if not intervals:
        return []
    
    # Sort by start time
    intervals.sort(key=lambda x: x[0])
    
    merged = [intervals[0]]
    
    for current in intervals[1:]:
        last = merged[-1]
        
        # Check if current overlaps with last merged interval
        if current[0] <= last[1]:
            # Merge: extend last interval's end
            last[1] = max(last[1], current[1])
        else:
            # No overlap: add as new interval
            merged.append(current)
    
    return merged


def insert_interval(intervals: List[List[int]], new_interval: List[int]) -> List[List[int]]:
    """
    Insert a new interval into sorted, non-overlapping intervals and merge if needed.
    
    Strategy: Three phases
    1. Add all intervals that end before new interval starts
    2. Merge all overlapping intervals
    3. Add all intervals that start after new interval ends
    
    Time: O(n), Space: O(n)
    """
    result = []
    i = 0
    n = len(intervals)
    
    # Phase 1: Add intervals before new_interval
    while i < n and intervals[i][1] < new_interval[0]:
        result.append(intervals[i])
        i += 1
    
    # Phase 2: Merge overlapping intervals
    while i < n and intervals[i][0] <= new_interval[1]:
        new_interval[0] = min(new_interval[0], intervals[i][0])
        new_interval[1] = max(new_interval[1], intervals[i][1])
        i += 1
    result.append(new_interval)
    
    # Phase 3: Add remaining intervals
    while i < n:
        result.append(intervals[i])
        i += 1
    
    return result


# Test cases
if __name__ == "__main__":
    print("Merge Intervals:")
    test_cases = [
        ([[1, 3], [2, 6], [8, 10], [15, 18]], [[1, 6], [8, 10], [15, 18]]),
        ([[1, 4], [4, 5]], [[1, 5]]),
        ([[1, 4], [0, 4]], [[0, 4]]),
    ]
    for intervals, expected in test_cases:
        result = merge_intervals(intervals)
        status = "✓" if result == expected else "✗"
        print(f"{status} merge_intervals({intervals}) = {result}")
    
    print("\nInsert Interval:")
    test_cases2 = [
        ([[1, 3], [6, 9]], [2, 5], [[1, 5], [6, 9]]),
        ([[1, 2], [3, 5], [6, 7], [8, 10], [12, 16]], [4, 8], [[1, 2], [3, 10], [12, 16]]),
    ]
    for intervals, new_int, expected in test_cases2:
        result = insert_interval(intervals, new_int)
        status = "✓" if result == expected else "✗"
        print(f"{status} insert_interval({intervals}, {new_int}) = {result}")
