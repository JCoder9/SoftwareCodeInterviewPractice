export function minDeleteOperations(word1: string, word2: string): number {
    const m = word1.length, n = word2.length;
    const dp: number[][] = Array(m + 1).fill(0).map(() => Array(n + 1).fill(0));
    for (let i = 1; i <= m; i++) {
        for (let j = 1; j <= n; j++) {
            if (word1[i-1] === word2[j-1]) {
                dp[i][j] = dp[i-1][j-1] + 1;
            } else {
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
    }
    const lcs = dp[m][n];
    return (m - lcs) + (n - lcs);
}
