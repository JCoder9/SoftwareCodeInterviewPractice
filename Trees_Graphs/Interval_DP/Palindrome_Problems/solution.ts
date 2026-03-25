export function longestPalindrome(s: string): string {
    const n = s.length;
    if (n === 0) return "";
    const dp: boolean[][] = Array(n).fill(null).map(() => Array(n).fill(false));
    let start = 0;
    let maxLen = 1;
    for (let i = 0; i < n; i++) {
        dp[i][i] = true;
    }
    for (let i = 0; i < n - 1; i++) {
        if (s[i] === s[i + 1]) {
            dp[i][i + 1] = true;
            start = i;
            maxLen = 2;
        }
    }
    for (let length = 3; length <= n; length++) {
        for (let i = 0; i <= n - length; i++) {
            const j = i + length - 1;
            if (s[i] === s[j] && dp[i + 1][j - 1]) {
                dp[i][j] = true;
                start = i;
                maxLen = length;
            }
        }
    }
    return s.substring(start, start + maxLen);
}
