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

/**
 * LC 1143: Longest Common Subsequence.
 */
export function longestCommonSubsequence(text1: string, text2: string): number {
    const m = text1.length, n = text2.length;
    const dp: number[][] = Array(m + 1).fill(0)
        .map(() => Array(n + 1).fill(0));
    
    for (let i = 1; i <= m; i++) {
        for (let j = 1; j <= n; j++) {
            if (text1[i-1] === text2[j-1]) {
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
export function lcsWithString(text1: string, text2: string): string {
    const m = text1.length, n = text2.length;
    const dp: number[][] = Array(m + 1).fill(0)
        .map(() => Array(n + 1).fill(0));
    
    for (let i = 1; i <= m; i++) {
        for (let j = 1; j <= n; j++) {
            if (text1[i-1] === text2[j-1]) {
                dp[i][j] = dp[i-1][j-1] + 1;
            } else {
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
    }
    
    // Backtrack
    let i = m, j = n;
    const lcs: string[] = [];
    
    while (i > 0 && j > 0) {
        if (text1[i-1] === text2[j-1]) {
            lcs.push(text1[i-1]);
            i--;
            j--;
        } else if (dp[i-1][j] > dp[i][j-1]) {
            i--;
        } else {
            j--;
        }
    }
    
    return lcs.reverse().join('');
}

// Test cases
if (require.main === module) {
    console.log(longestCommonSubsequence("abcde", "ace")); // 3
    console.log(longestCommonSubsequence("abc", "abc")); // 3
    console.log(longestCommonSubsequence("abc", "def")); // 0
    
    console.log("\nActual LCS:", lcsWithString("abcde", "ace")); // "ace"
}
