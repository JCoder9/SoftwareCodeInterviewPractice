/**
 * 1D Dynamic Programming - Decode Ways
 * 
 * Problem: Given string of digits, count ways to decode it where A=1, B=2, ..., Z=26.
 *          For example, "226" can be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 * 
 * Pattern: DP where dp[i] = ways to decode string up to index i.
 *          Can decode 1 digit (1-9) or 2 digits (10-26).
 * 
 * Related LeetCode Problems:
 * - LC 91: Decode Ways (Medium) ⭐⭐⭐
 * - LC 639: Decode Ways II (Hard) - with wildcards
 * - LC 842: Split Array into Fibonacci Sequence (Medium)
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n) or O(1) optimized
 */

// ─────────────────────────────────────────────────────────────────────────────
// NAIVE APPROACH (Brute Force) — O(2^n) time | O(n) space
// ─────────────────────────────────────────────────────────────────────────────
// INTERVIEW SCRIPT:
//   1. Describe:   "Brute force recursively tries decoding 1 or 2 digits at each
//                  position — exponential branching"
//   2. Problem:    "For n=30: over 1 billion recursive calls; overlapping subproblems
//                  computed many times"
//   3. Transition: "DP memoizes ways for each position; each solved once — O(n) time"
//
// public int numDecodingsNaive(String s) {
//     return decodeNaive(s, 0);
// }
// 
// private int decodeNaive(String s, int i) {
//     if (i == s.length()) return 1;
//     if (s.charAt(i) == '0') return 0;
//     
//     // Decode 1 digit
//     int count = decodeNaive(s, i + 1);
//     
//     // Decode 2 digits (if valid)
//     if (i + 1 < s.length()) {
//         int twoDigit = Integer.parseInt(s.substring(i, i + 2));
//         if (twoDigit >= 10 && twoDigit <= 26) {
//             count += decodeNaive(s, i + 2);
//         }
//     }
//     return count;
// }
// ─────────────────────────────────────────────────────────────────────────────

public class DecodeWays {
    
    // LC 91: Decode Ways
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            // 1-digit decode
            int oneDigit = Integer.parseInt(s.substring(i-1, i));
            if (oneDigit >= 1 && oneDigit <= 9) {
                dp[i] += dp[i-1];
            }
            
            // 2-digit decode
            int twoDigit = Integer.parseInt(s.substring(i-2, i));
            if (twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i-2];
            }
        }
        
        return dp[n];
    }
    
    // Space-optimized version
    public int numDecodingsOptimized(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        
        int prev2 = 1, prev1 = 1;
        
        for (int i = 1; i < s.length(); i++) {
            int current = 0;
            
            // 1-digit decode
            int oneDigit = s.charAt(i) - '0';
            if (oneDigit >= 1 && oneDigit <= 9) {
                current += prev1;
            }
            
            // 2-digit decode
            int twoDigit = Integer.parseInt(s.substring(i-1, i+1));
            if (twoDigit >= 10 && twoDigit <= 26) {
                current += prev2;
            }
            
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
    
    // Test
    public static void main(String[] args) {
        DecodeWays solution = new DecodeWays();
        
        System.out.println("Decode '12': " + solution.numDecodings("12"));
        System.out.println("Decode '226': " + solution.numDecodings("226"));
        System.out.println("Decode '06': " + solution.numDecodings("06"));
        System.out.println("Decode '11106': " + solution.numDecodings("11106"));
        
        System.out.println("\nOptimized '12': " + solution.numDecodingsOptimized("12"));
        System.out.println("Optimized '226': " + solution.numDecodingsOptimized("226"));
    }
}
