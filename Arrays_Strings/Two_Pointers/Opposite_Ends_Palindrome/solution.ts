/**
 * Valid Palindrome - Opposite Ends Two Pointer Pattern
 * 
 * Problem: Check if a string is a palindrome, ignoring non-alphanumeric characters
 *          and case differences.
 * 
 * Pattern: Two pointers starting from opposite ends, moving towards center.
 * 
 * Time Complexity: O(n) - single pass through string
 * Space Complexity: O(1) - only using two pointers
 */

/**
 * Check if string is a palindrome (ignoring non-alphanumeric, case-insensitive).
 * 
 * @param s - Input string to check
 * @returns true if palindrome, false otherwise
 */
function isPalindrome(s: string): boolean {
    let l = 0, r = s.length - 1;

    while (l < r) {
        // Skip non-alphanumeric from left
        while (l < r && !isAlphanumeric(s[l])) {
            l++;
        }
        // Skip non-alphanumeric from right
        while (l < r && !isAlphanumeric(s[r])) {
            r--;
        }

        // Compare characters (case-insensitive)
        if (s[l].toLowerCase() !== s[r].toLowerCase()) {
            return false;
        }

        l++;
        r--;
    }
    return true;
}

function isAlphanumeric(char: string): boolean {
    return /[a-zA-Z0-9]/.test(char);
}

// Test cases
if (require.main === module) {
    const testCases: [string, boolean][] = [
        ["A man, a plan, a canal: Panama", true],
        ["race a car", false],
        [" ", true],
        ["a", true],
        ["ab", false],
        ["aba", true],
    ];
    
    for (const [s, expected] of testCases) {
        const result = isPalindrome(s);
        const status = result === expected ? "✓" : "✗";
        console.log(`${status} isPalindrome("${s}") = ${result} (expected ${expected})`);
    }
}

export { isPalindrome };
