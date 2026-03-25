export function mergeStones(stones: number[]): number {
    const n = stones.length;
    const prefix: number[] = new Array(n + 1).fill(0);
    for (let i = 0; i < n; i++) {
        prefix[i + 1] = prefix[i] + stones[i];
    }
    const dp: number[][] = Array(n).fill(null).map(() => Array(n).fill(0));
    for (let length = 2; length <= n; length++) {
        for (let i = 0; i <= n - length; i++) {
            const j = i + length - 1;
            dp[i][j] = Infinity;
            for (let k = i; k < j; k++) {
                const cost = dp[i][k] + dp[k + 1][j] + prefix[j + 1] - prefix[i];
                dp[i][j] = Math.min(dp[i][j], cost);
            }
        }
    }
    return dp[0][n - 1];
}
