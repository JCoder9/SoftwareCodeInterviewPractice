export function minInsertions(s: string): number {
    const n = s.length;
    const dp: number[][] = Array(n).fill(null).map(() => Array(n).fill(0));
    for (let i = 0; i < n; i++) {
        dp[i][i] = 1;
    }
    for (let length = 2; length <= n; length++) {
        for (let i = 0; i <= n - length; i++) {
            const j = i + length - 1;
            if (s[i] === s[j]) {
                dp[i][j] = (i + 1 <= j - 1 ? dp[i + 1][j - 1] : 0) + 2;
            } else {
                dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }
    }
    const lpsLength = dp[0][n - 1];
    return n - lpsLength;
}
