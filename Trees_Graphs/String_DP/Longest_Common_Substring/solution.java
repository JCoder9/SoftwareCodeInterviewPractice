public class LongestCommonSubstring {
    public int longestCommonSubstring(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        int maxLen = 0;
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    maxLen = Math.max(maxLen, dp[i][j]);
                }
            }
        }
        return maxLen;
    }
}
