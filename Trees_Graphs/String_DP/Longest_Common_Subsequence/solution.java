/**
 * String DP - Longest Common Subsequence (LCS)
 * 
 * Problem: Find the length of the longest subsequence common to both strings.
 * 
 * Related LeetCode Problems:
 * - LC 1143: Longest Common Subsequence (Medium)
 * - LC 583: Delete Operation for Two Strings (Medium)
 * - LC 712: Minimum ASCII Delete Sum for Two Strings (Medium)
 * 
 * Time Complexity: O(m × n)
 * Space Complexity: O(m × n) or O(min(m,n)) optimized
 */

public class LongestCommonSubsequence {
    
    /**
     * LC 1143: Longest Common Subsequence.
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        
        return dp[m][n];
    }
    
    /**
     * Return the actual LCS string.
     */
    public String lcsWithString(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        
        // Backtrack
        StringBuilder lcs = new StringBuilder();
        int i = m, j = n;
        
        while (i > 0 && j > 0) {
            if (text1.charAt(i-1) == text2.charAt(j-1)) {
                lcs.append(text1.charAt(i-1));
                i--;
                j--;
            } else if (dp[i-1][j] > dp[i][j-1]) {
                i--;
            } else {
                j--;
            }
        }
        
        return lcs.reverse().toString();
    }
    
    public static void main(String[] args) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        System.out.println(lcs.longestCommonSubsequence("abcde", "ace")); // 3
        System.out.println(lcs.longestCommonSubsequence("abc", "abc")); // 3
        System.out.println(lcs.longestCommonSubsequence("abc", "def")); // 0
        
        System.out.println("\nActual LCS: " + lcs.lcsWithString("abcde", "ace")); // "ace"
    }
}
