/**
 * Merge Intervals - Sort + Scan Pattern
 * 
 * Problem: Given collection of intervals, merge all overlapping intervals.
 *          Example: [[1,3],[2,6],[8,10],[15,18]] → [[1,6],[8,10],[15,18]]
 * 
 * Pattern: 1. Sort intervals by start time
 *          2. Scan through, merging overlapping intervals
 * 
 * Related LeetCode Problems:
 * - LC 56: Merge Intervals (Medium) ⭐⭐⭐
 * - LC 57: Insert Interval (Medium)
 * - LC 252: Meeting Rooms (Easy)
 * - LC 253: Meeting Rooms II (Medium)
 * 
 * Time Complexity: O(n log n) - dominated by sorting
 * Space Complexity: O(n) - result array
 */

// ─────────────────────────────────────────────────────────────────────────────
// NAIVE APPROACH (Brute Force) — O(n³) time | O(n) space
// ─────────────────────────────────────────────────────────────────────────────
// INTERVIEW SCRIPT:
//   1. Describe:   "Brute force compares every pair, merges if overlap, repeats
//                  until no more merges — O(n³) worst case"
//   2. Problem:    "For 100 intervals: up to 1 million comparisons; repeated scans
//                  after each merge"
//   3. Transition: "Sort first, then single left-to-right scan — O(n log n)"
//
// public int[][] mergeIntervalsNaive(int[][] intervals) {
//     List<int[]> result = new ArrayList<>();
//     for (int[] interval : intervals) {
//         result.add(new int[]{interval[0], interval[1]});
//     }
//     
//     boolean merged = true;
//     while (merged) {
//         merged = false;
//         for (int i = 0; i < result.size(); i++) {
//             for (int j = i + 1; j < result.size(); j++) {
//                 int[] a = result.get(i), b = result.get(j);
//                 // Check if they overlap
//                 if (!(a[1] < b[0] || b[1] < a[0])) {
//                     // Merge
//                     result.set(i, new int[]{Math.min(a[0], b[0]),
//                                             Math.max(a[1], b[1])});
//                     result.remove(j);
//                     merged = true;
//                     break;
//                 }
//             }
//             if (merged) break;
//         }
//     }
//     return result.toArray(new int[result.size()][]);
// }
// ─────────────────────────────────────────────────────────────────────────────

import java.util.*;

public class Solution {
    
    /**
     * Merge overlapping intervals.
     */
    public static int[][] mergeIntervals(int[][] intervals) {
        if (intervals.length == 0) return new int[0][];
        
        // Sort by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        List<int[]> merged = new ArrayList<>();
        merged.add(intervals[0]);
        
        for (int i = 1; i < intervals.length; i++) {
            int[] current = intervals[i];
            int[] last = merged.get(merged.size() - 1);
            
            // Check if current overlaps with last merged interval
            if (current[0] <= last[1]) {
                // Merge: extend last interval's end
                last[1] = Math.max(last[1], current[1]);
            } else {
                // No overlap: add as new interval
                merged.add(current);
            }
        }
        
        return merged.toArray(new int[merged.size()][]);
    }
    
    /**
     * Insert a new interval into sorted, non-overlapping intervals.
     */
    public static int[][] insertInterval(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;
        
        // Phase 1: Add intervals before new_interval
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }
        
        // Phase 2: Merge overlapping intervals
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);
        
        // Phase 3: Add remaining intervals
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }
        
        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        System.out.println("Merge Intervals:");
        int[][] test1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] result1 = mergeIntervals(test1);
        System.out.println("  " + Arrays.deepToString(result1));
        
        System.out.println("\nInsert Interval:");
        int[][] test2 = {{1, 3}, {6, 9}};
        int[] newInt = {2, 5};
        int[][] result2 = insertInterval(test2, newInt);
        System.out.println("  " + Arrays.deepToString(result2));
    }
}
