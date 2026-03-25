export function longestCommonSubstring(text1: string, text2: string): number {
    const m = text1.length, n = text2.length;
    const dp: number[][] = Array(m + 1).fill(0).map(() => Array(n + 1).fill(0));
    let maxLen = 0;
    
    for (let i = 1; i <= m; i++) {
        for (let j = 1; j <= n; j++) {
            if (text1[i-1] === text2[j-1]) {
                dp[i][j] = dp[i-1][j-1] + 1;
                maxLen = Math.max(maxLen, dp[i][j]);
            }
        }
    }
    return maxLen;
}
