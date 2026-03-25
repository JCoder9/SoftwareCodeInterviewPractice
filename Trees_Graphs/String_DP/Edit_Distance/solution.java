/**
 * String DP - Edit Distance (Levenshtein Distance)
 * 
 * Problem: Minimum operations (insert, delete, replace) to convert word1 to word2.
 * 
 * Related LeetCode Problems:
 * - LC 72: Edit Distance (Hard)
 * - LC 161: One Edit Distance (Medium)
 * - LC 583: Delete Operation for Two Strings (Medium)
 * 
 * Time Complexity: O(m × n)
 * Space Complexity: O(m × n)
 */

public class EditDistance {
    
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 0; i <= m; i++) dp[i][0] = i;
        for (int j = 0; j <= n; j++) dp[0][j] = j;
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = 1 + Math.min(
                        dp[i-1][j-1],  // Replace
                        Math.min(dp[i-1][j], dp[i][j-1])  // Delete, Insert
                    );
                }
            }
        }
        
        return dp[m][n];
    }
    
    public static void main(String[] args) {
        EditDistance ed = new EditDistance();
        System.out.println(ed.minDistance("horse", "ros")); // 3
        System.out.println(ed.minDistance("intention", "execution")); // 5
    }
}
