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

public class Solution {

    // ─────────────────────────────────────────────────────────────────────────
    // NAIVE APPROACH (Brute Force) - O(n) time | O(n) space
    // ─────────────────────────────────────────────────────────────────────────
    // INTERVIEW SCRIPT:
    //   1. Describe:   "Brute force filters all alphanumeric chars to a new string,
    //                   then compares with its reverse — O(n) time, O(n) space"
    //   2. Problem:    "Uses extra space for filtered string and its reverse"
    //   3. Transition: "With two pointers from opposite ends we skip non-alphanumeric
    //                   in-place and compare — same O(n) time but O(1) space"
    //
    // public static boolean isPalindromeNaive(String s) {
    //     StringBuilder filtered = new StringBuilder();
    //     for (char c : s.toCharArray()) {
    //         if (Character.isLetterOrDigit(c)) {
    //             filtered.append(Character.toLowerCase(c));
    //         }
    //     }
    //     String str = filtered.toString();
    //     String rev = new StringBuilder(str).reverse().toString();
    //     return str.equals(rev);
    // }
    // ─────────────────────────────────────────────────────────────────────────
    
    /**
     * Check if string is a palindrome (ignoring non-alphanumeric, case-insensitive).
     * 
     * @param s Input string to check
     * @return true if palindrome, false otherwise
     */
    public static boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;

        while (l < r) {
            // Skip non-alphanumeric from left
            while (l < r && !Character.isLetterOrDigit(s.charAt(l))) {
                l++;
            }
            // Skip non-alphanumeric from right
            while (l < r && !Character.isLetterOrDigit(s.charAt(r))) {
                r--;
            }

            // Compare characters (case-insensitive)
            char a = Character.toLowerCase(s.charAt(l));
            char b = Character.toLowerCase(s.charAt(r));
            if (a != b) {
                return false;
            }

            l++;
            r--;
        }
        return true;
    }

    // Test cases
    public static void main(String[] args) {
        String[][] testCases = {
            {"A man, a plan, a canal: Panama", "true"},
            {"race a car", "false"},
            {" ", "true"},
            {"a", "true"},
            {"ab", "false"},
            {"aba", "true"}
        };
        
        for (String[] test : testCases) {
            String s = test[0];
            boolean expected = Boolean.parseBoolean(test[1]);
            boolean result = isPalindrome(s);
            String status = (result == expected) ? "✓" : "✗";
            System.out.println(status + " isPalindrome(\"" + s + "\") = " + result + " (expected " + expected + ")");
        }
    }
}
