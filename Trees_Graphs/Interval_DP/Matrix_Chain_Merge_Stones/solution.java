public class MergeStones {
    public int mergeStones(int[] stones) {
        int n = stones.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stones[i];
        }
        int[][] dp = new int[n][n];
        for (int length = 2; length <= n; length++) {
            for (int i = 0; i <= n - length; i++) {
                int j = i + length - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + prefix[j + 1] - prefix[i];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }
        return dp[0][n - 1];
    }
}
