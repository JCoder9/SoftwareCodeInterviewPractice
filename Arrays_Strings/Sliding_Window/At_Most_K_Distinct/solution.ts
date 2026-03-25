/**
 * Longest Substring with At Most K Distinct Characters - Frequency Map Sliding Window
 * 
 * Problem: Find the length of the longest substring with at most K distinct characters.
 * 
 * Pattern: Variable window with frequency map - shrink when distinct count exceeds K.
 * 
 * Time Complexity: O(n) - each character visited at most twice
 * Space Complexity: O(min(k, alphabet)) - at most k+1 chars in map before shrinking
 */

/**
 * Find longest substring with at most K distinct characters.
 * 
 * @param s - Input string
 * @param k - Maximum number of distinct characters allowed
 * @returns Length of longest valid substring
 */
function longestAtMostKDistinct(s: string, k: number): number {
    if (s.length === 0 || k <= 0) return 0;

    const count = new Map<string, number>();
    let left = 0;
    let best = 0;

    for (let right = 0; right < s.length; right++) {
        const ch = s[right];
        count.set(ch, (count.get(ch) || 0) + 1);

        // Shrink window while we have too many distinct characters
        while (count.size > k) {
            const lc = s[left];
            count.set(lc, count.get(lc)! - 1);
            if (count.get(lc) === 0) {
                count.delete(lc);
            }
            left++;
        }

        // Window is valid, update best
        best = Math.max(best, right - left + 1);
    }
    return best;
}

// Test cases
if (require.main === module) {
    const testCases: [string, number, number][] = [
        ["eceba", 2, 3],
        ["aa", 1, 2],
        ["a", 2, 1],
        ["abcabc", 2, 2],
        ["abcabcabc", 3, 9],
    ];
    
    for (const [s, k, expected] of testCases) {
        const result = longestAtMostKDistinct(s, k);
        const status = result === expected ? "✓" : "✗";
        console.log(`${status} longestAtMostKDistinct("${s}", k=${k}) = ${result} (expected ${expected})`);
    }
}

export { longestAtMostKDistinct };
