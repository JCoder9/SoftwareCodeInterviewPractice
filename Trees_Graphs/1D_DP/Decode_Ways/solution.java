/**
 * 1D Dynamic Programming - Decode Ways
 * 
 * Related LeetCode Problems:
 * - LC 91: Decode Ways (Medium)
 * - LC 639: Decode Ways II (Hard) - with wildcards
 * - LC 842: Split Array into Fibonacci Sequence (Medium)
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n) or O(1) optimized
 */

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
