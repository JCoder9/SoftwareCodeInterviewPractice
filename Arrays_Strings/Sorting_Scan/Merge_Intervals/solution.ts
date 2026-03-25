/**
 * Merge Intervals - Sort + Scan Pattern
 * 
 * Problem: Given a collection of intervals, merge all overlapping intervals.
 * 
 * Pattern: 1. Sort intervals by start time
 *          2. Scan through, merging overlapping intervals
 * 
 * Time Complexity: O(n log n) for sorting + O(n) for merging
 * Space Complexity: O(n) for result
 */

type Interval = [number, number];

/**
 * Merge overlapping intervals.
 */
function mergeIntervals(intervals: Interval[]): Interval[] {
    if (intervals.length === 0) return [];
    
    // Sort by start time
    intervals.sort((a, b) => a[0] - b[0]);
    
    const merged: Interval[] = [intervals[0]];
    
    for (let i = 1; i < intervals.length; i++) {
        const current = intervals[i];
        const last = merged[merged.length - 1];
        
        // Check if current overlaps with last merged interval
        if (current[0] <= last[1]) {
            // Merge: extend last interval's end
            last[1] = Math.max(last[1], current[1]);
        } else {
            // No overlap: add as new interval
            merged.push(current);
        }
    }
    
    return merged;
}

/**
 * Insert a new interval into sorted, non-overlapping intervals.
 */
function insertInterval(intervals: Interval[], newInterval: Interval): Interval[] {
    const result: Interval[] = [];
    let i = 0;
    const n = intervals.length;
    
    // Phase 1: Add intervals before new_interval
    while (i < n && intervals[i][1] < newInterval[0]) {
        result.push(intervals[i]);
        i++;
    }
    
    // Phase 2: Merge overlapping intervals
    while (i < n && intervals[i][0] <= newInterval[1]) {
        newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
        newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
        i++;
    }
    result.push(newInterval);
    
    // Phase 3: Add remaining intervals
    while (i < n) {
        result.push(intervals[i]);
        i++;
    }
    
    return result;
}

// Test cases
if (require.main === module) {
    console.log("Merge Intervals:");
    const test1: Interval[] = [[1, 3], [2, 6], [8, 10], [15, 18]];
    console.log("  " + JSON.stringify(mergeIntervals(test1)));
    
    console.log("\nInsert Interval:");
    const test2: Interval[] = [[1, 3], [6, 9]];
    const newInt: Interval = [2, 5];
    console.log("  " + JSON.stringify(insertInterval(test2, newInt)));
}

export { mergeIntervals, insertInterval };
