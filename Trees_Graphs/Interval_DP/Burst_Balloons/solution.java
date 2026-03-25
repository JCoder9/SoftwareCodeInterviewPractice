public class BurstBalloons {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] balloons = new int[n + 2];
        balloons[0] = 1;
        balloons[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            balloons[i + 1] = nums[i];
        }
        int[][] dp = new int[n + 2][n + 2];
        for (int length = 2; length < n + 2; length++) {
            for (int left = 0; left < n + 2 - length; left++) {
                int right = left + length;
                for (int k = left + 1; k < right; k++) {
                    int coins = balloons[left] * balloons[k] * balloons[right];
                    int total = coins + dp[left][k] + dp[k][right];
                    dp[left][right] = Math.max(dp[left][right], total);
                }
            }
        }
        return dp[0][n + 1];
    }
}
