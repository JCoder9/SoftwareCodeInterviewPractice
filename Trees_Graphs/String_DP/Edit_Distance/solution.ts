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

export function minDistance(word1: string, word2: string): number {
    const m = word1.length, n = word2.length;
    const dp: number[][] = Array(m + 1).fill(0)
        .map(() => Array(n + 1).fill(0));
    
    for (let i = 0; i <= m; i++) dp[i][0] = i;
    for (let j = 0; j <= n; j++) dp[0][j] = j;
    
    for (let i = 1; i <= m; i++) {
        for (let j = 1; j <= n; j++) {
            if (word1[i-1] === word2[j-1]) {
                dp[i][j] = dp[i-1][j-1];
            } else {
                dp[i][j] = 1 + Math.min(
                    dp[i-1][j-1],  // Replace
                    dp[i-1][j],     // Delete
                    dp[i][j-1]      // Insert
                );
            }
        }
    }
    
    return dp[m][n];
}

if (require.main === module) {
    console.log(minDistance("horse", "ros")); // 3
    console.log(minDistance("intention", "execution")); // 5
}
