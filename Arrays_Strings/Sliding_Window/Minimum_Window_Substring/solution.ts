/**
 * Minimum Window Substring - Hard Classic Sliding Window
 * 
 * Problem: Find the minimum substring of s that contains all characters of t
 *          (including their frequencies).
 * 
 * Pattern: Expand window until it "covers" t, then shrink to minimize while maintaining coverage.
 *          Track "formed" count to efficiently check if window is valid.
 * 
 * Time Complexity: O(|s| + |t|) - each character in s visited at most twice
 * Space Complexity: O(|s| + |t|) - space for both frequency maps
 */

/**
 * Find minimum window substring of s containing all characters of t.
 * 
 * @param s - String to search in
 * @param t - Pattern string - must find all these characters
 * @returns Minimum window substring, or empty string if not found
 */
function minWindow(s: string, t: string): string {
    if (t.length === 0 || s.length === 0 || t.length > s.length) {
        return "";  // No window possible
    }

    // Build frequency map of what we need
    const need = new Map<string, number>();
    for (const c of t) {
        need.set(c, (need.get(c) || 0) + 1);
    }

    const required = need.size;
    const window = new Map<string, number>();
    let formed = 0;

    let left = 0;
    let bestLen = Infinity;
    let bestL = 0, bestR = 0;

    for (let right = 0; right < s.length; right++) {
        // Expand window: add character from right
        const c = s[right];
        window.set(c, (window.get(c) || 0) + 1);

        // Check if this character now has required frequency
        if (need.has(c) && window.get(c) === need.get(c)) {
            formed++;
        }

        // Try to shrink window while it's valid
        while (formed === required) {
            // Update best if current window is smaller
            if (right - left + 1 < bestLen) {
                bestLen = right - left + 1;
                bestL = left;
                bestR = right;
            }

            // Try to shrink from left
            const lc = s[left];
            window.set(lc, window.get(lc)! - 1);
            if (need.has(lc) && window.get(lc)! < need.get(lc)!) {
                formed--;
            }
            left++;
        }
    }

    return bestLen === Infinity ? "" : s.substring(bestL, bestR + 1);
}

// Test cases
if (require.main === module) {
    const testCases: [string, string, string][] = [
        ["ADOBECODEBANC", "ABC", "BANC"],
        ["a", "a", "a"],
        ["a", "aa", ""],
        ["ab", "b", "b"],
        ["abc", "cba", "abc"],
    ];
    
    for (const [s, t, expected] of testCases) {
        const result = minWindow(s, t);
        const status = result === expected ? "✓" : "✗";
        console.log(`${status} minWindow("${s}", "${t}") = "${result}" (expected "${expected}")`);
    }
}

export { minWindow };
