/**
 * Find All Anagrams in String - Fixed Window + Frequency Matching
 * 
 * Problem: Find all starting indices of p's anagrams in string s.
 * 
 * Pattern: Fixed window size (len(p)) with frequency array comparison.
 *          Window slides through s, checking if frequencies match.
 * 
 * Time Complexity: O(n × 26) = O(n) for lowercase letters
 * Space Complexity: O(1) - two fixed-size arrays of 26
 */

/**
 * Find all starting indices of p's anagrams in s.
 * 
 * @param s - String to search in
 * @param p - Pattern to find anagrams of
 * @returns List of starting indices where anagrams are found
 */
function findAnagrams(s: string, p: string): number[] {
    if (s.length === 0 || p.length === 0 || p.length > s.length) {
        return [];  // No anagrams possible
    }

    // Frequency arrays for lowercase a-z
    const need: number[] = new Array(26).fill(0);
    const win: number[] = new Array(26).fill(0);
    
    // Build frequency array for pattern p
    for (let i = 0; i < p.length; i++) {
        need[p.charCodeAt(i) - 97]++;
    }

    const res: number[] = [];
    let left = 0;

    for (let right = 0; right < s.length; right++) {
        // Add new character to window
        win[s.charCodeAt(right) - 97]++;

        // Shrink window if it exceeds pattern length
        if (right - left + 1 > p.length) {
            win[s.charCodeAt(left) - 97]--;
            left++;
        }

        // Check if we have an anagram (frequency arrays match)
        if (right - left + 1 === p.length && arraysEqual(win, need)) {
            res.push(left);
        }
    }
    return res;
}

function arraysEqual(a: number[], b: number[]): boolean {
    if (a.length !== b.length) return false;
    for (let i = 0; i < a.length; i++) {
        if (a[i] !== b[i]) return false;
    }
    return true;
}

// Test cases
if (require.main === module) {
    const testCases: [string, string, number[]][] = [
        ["cbaebabacd", "abc", [0, 6]],
        ["abab", "ab", [0, 1, 2]],
        ["baa", "aa", [1]],
        ["a", "a", [0]],
        ["abc", "xyz", []],
    ];
    
    for (const [s, p, expected] of testCases) {
        const result = findAnagrams(s, p);
        const status = JSON.stringify(result) === JSON.stringify(expected) ? "✓" : "✗";
        console.log(`${status} findAnagrams("${s}", "${p}") = [${result}] (expected [${expected}])`);
    }
}

export { findAnagrams };
