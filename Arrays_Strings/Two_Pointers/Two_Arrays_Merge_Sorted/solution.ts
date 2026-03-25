/**
 * Merge Two Sorted Arrays - Two Pointer Pattern Across Arrays
 * 
 * Problem: Merge two sorted arrays into a single sorted array.
 * 
 * Pattern: One pointer for each array, compare and merge.
 * 
 * Time Complexity: O(m + n) - single pass through both arrays
 * Space Complexity: O(m + n) - for output array
 */

/**
 * Merge two sorted arrays into a single sorted array.
 * 
 * @param a - First sorted array
 * @param b - Second sorted array
 * @returns Merged sorted array containing all elements from both arrays
 */
function mergeSorted(a: number[], b: number[]): number[] {
    let i = 0, j = 0;
    const out: number[] = [];

    // Merge elements while both arrays have remaining elements
    while (i < a.length && j < b.length) {
        if (a[i] <= b[j]) {
            out.push(a[i]);
            i++;
        } else {
            out.push(b[j]);
            j++;
        }
    }
    
    // Append remaining elements from a (if any)
    while (i < a.length) {
        out.push(a[i]);
        i++;
    }
    
    // Append remaining elements from b (if any)
    while (j < b.length) {
        out.push(b[j]);
        j++;
    }

    return out;
}

// Test cases
if (require.main === module) {
    const testCases: [number[], number[]][] = [
        [[1, 3, 5], [2, 4, 6]],
        [[1, 2, 3], [4, 5, 6]],
        [[], [1, 2, 3]],
        [[1, 2, 3], []],
        [[1], [2]],
    ];
    
    for (const [a, b] of testCases) {
        const result = mergeSorted(a, b);
        console.log(`mergeSorted([${a}], [${b}]) = [${result}]`);
    }
}

export { mergeSorted };
