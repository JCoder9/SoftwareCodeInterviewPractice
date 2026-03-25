/**
 * 2D DP - String/Sequence Matching
 * 
 * Time Complexity: O(m * n)
 * Space Complexity: O(n)
 */

function longestCommonSubsequence(text1: string, text2: string): number {
    const m = text1.length, n = text2.length;
    let prev = new Array(n + 1).fill(0);
    
    for (let i = 1; i <= m; i++) {
        const curr = new Array(n + 1).fill(0);
        for (let j = 1; j <= n; j++) {
            if (text1[i - 1] === text2[j - 1]) {
                curr[j] = prev[j - 1] + 1;
            } else {
                curr[j] = Math.max(prev[j], curr[j - 1]);
            }
        }
        prev = curr;
    }
    
    return prev[n];
}

function editDistance(word1: string, word2: string): number {
    const m = word1.length, n = word2.length;
    let prev = Array.from({ length: n + 1 }, (_, i) => i);
    
    for (let i = 1; i <= m; i++) {
        const curr = [i];
        
        for (let j = 1; j <= n; j++) {
            if (word1[i - 1] === word2[j - 1]) {
                curr.push(prev[j - 1]);
            } else {
                curr.push(1 + Math.min(prev[j], curr[j - 1], prev[j - 1]));
            }
        }
        
        prev = curr;
    }
    
    return prev[n];
}

function numDistinct(s: string, t: string): number {
    const m = s.length, n = t.length;
    let prev = new Array(n + 1).fill(0);
    prev[0] = 1;
    
    for (let i = 1; i <= m; i++) {
        const curr = new Array(n + 1).fill(0);
        curr[0] = 1;
        
        for (let j = 1; j <= n; j++) {
            if (s[i - 1] === t[j - 1]) {
                curr[j] = prev[j - 1] + prev[j];
            } else {
                curr[j] = prev[j];
            }
        }
        
        prev = curr;
    }
    
    return prev[n];
}

// Test
if (require.main === module) {
    console.log("LCS('abcde', 'ace'):", longestCommonSubsequence("abcde", "ace"));
    console.log("Edit distance('horse', 'ros'):", editDistance("horse", "ros"));
    console.log("Num distinct('rabbbit', 'rabbit'):", numDistinct("rabbbit", "rabbit"));
}

export { longestCommonSubsequence, editDistance, numDistinct };
