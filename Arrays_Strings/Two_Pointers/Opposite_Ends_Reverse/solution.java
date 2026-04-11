/**
 * Reverse Array/String In-Place - Opposite Ends Two Pointer Pattern
 * 
 * Problem: Reverse an array or string in-place by swapping elements from both ends.
 * 
 * Pattern: Two pointers starting from opposite ends, swapping and moving inward.
 * 
 * Time Complexity: O(n) - single pass through half of array
 * Space Complexity: O(1) - in-place modification
 */

public class Solution {

    // ─────────────────────────────────────────────────────────────────────────
    // NAIVE APPROACH (Brute Force) - O(n) time | O(n) space
    // ─────────────────────────────────────────────────────────────────────────
    // INTERVIEW SCRIPT:
    //   1. Describe:   "Brute force creates a new array and copies elements from
    //                   end to start — O(n) time, O(n) space"
    //   2. Problem:    "Uses extra space for the new array; not in-place"
    //   3. Transition: "With two pointers from opposite ends we swap elements
    //                   in-place — same O(n) time but O(1) space"
    //
    // public static void reverseCharArrayNaive(char[] a) {
    //     char[] temp = new char[a.length];
    //     for (int i = 0; i < a.length; i++) {
    //         temp[i] = a[a.length - 1 - i];
    //     }
    //     for (int i = 0; i < a.length; i++) {
    //         a[i] = temp[i];
    //     }
    // }
    // ─────────────────────────────────────────────────────────────────────────
    
    /**
     * Reverse a character array in-place using two pointers.
     * 
     * @param a Character array to reverse (modified in-place)
     */
    public static void reverseCharArray(char[] a) {
        int l = 0, r = a.length - 1;
        
        while (l < r) {
            // Swap elements at left and right pointers
            char tmp = a[l];
            a[l] = a[r];
            a[r] = tmp;
            l++;
            r--;
        }
    }

    /**
     * Reverse a string (creates new string since strings are immutable).
     * 
     * @param s String to reverse
     * @return Reversed string
     */
    public static String reverseString(String s) {
        char[] chars = s.toCharArray();
        reverseCharArray(chars);
        return new String(chars);
    }

    // Test cases
    public static void main(String[] args) {
        // Test array reversal
        char[][] testArrays = {
            {'h', 'e', 'l', 'l', 'o'},
            {'H', 'a', 'n', 'n', 'a', 'h'},
            {'a'},
            {'a', 'b'}
        };
        
        System.out.println("Array reversal tests:");
        for (char[] arr : testArrays) {
            String original = new String(arr);
            reverseCharArray(arr);
            String reversed = new String(arr);
            System.out.println("  " + original + " -> " + reversed);
        }
        
        // Test string reversal
        String[] testStrings = {"hello", "Hannah", "a", "ab", ""};
        
        System.out.println("\nString reversal tests:");
        for (String s : testStrings) {
            String result = reverseString(s);
            System.out.println("  '" + s + "' -> '" + result + "'");
        }
    }
}
