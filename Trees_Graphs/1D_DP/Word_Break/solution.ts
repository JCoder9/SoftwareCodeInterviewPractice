/**
 * 1D Dynamic Programming - Word Break
 * 
 * Related LeetCode Problems:
 * - LC 139: Word Break (Medium)
 * - LC 140: Word Break II (Hard) - return all possible sentences
 * - LC 472: Concatenated Words (Hard)
 * 
 * Time Complexity: O(n²) or O(n² + m) with trie
 * Space Complexity: O(n)
 */

// LC 139: Word Break
export function wordBreak(s: string, wordDict: string[]): boolean {
    const wordSet = new Set(wordDict);
    const n = s.length;
    
    const dp = Array(n + 1).fill(false);
    dp[0] = true;
    
    for (let i = 1; i <= n; i++) {
        for (let j = 0; j < i; j++) {
            if (dp[j] && wordSet.has(s.substring(j, i))) {
                dp[i] = true;
                break;
            }
        }
    }
    
    return dp[n];
}

// Optimized with max word length
export function wordBreakOptimized(s: string, wordDict: string[]): boolean {
    const wordSet = new Set(wordDict);
    const maxLen = Math.max(...wordDict.map(w => w.length));
    const n = s.length;
    
    const dp = Array(n + 1).fill(false);
    dp[0] = true;
    
    for (let i = 1; i <= n; i++) {
        for (let j = Math.max(0, i - maxLen); j < i; j++) {
            if (dp[j] && wordSet.has(s.substring(j, i))) {
                dp[i] = true;
                break;
            }
        }
    }
    
    return dp[n];
}

// LC 140: Word Break II
export function wordBreakII(s: string, wordDict: string[]): string[] {
    const wordSet = new Set(wordDict);
    const memo = new Map<number, string[]>();
    
    function backtrack(start: number): string[] {
        if (start === s.length) {
            return [""];
        }
        
        if (memo.has(start)) {
            return memo.get(start)!;
        }
        
        const result: string[] = [];
        
        for (let end = start + 1; end <= s.length; end++) {
            const word = s.substring(start, end);
            if (wordSet.has(word)) {
                const subSentences = backtrack(end);
                for (const sentence of subSentences) {
                    result.push(word + (sentence ? " " + sentence : ""));
                }
            }
        }
        
        memo.set(start, result);
        return result;
    }
    
    return backtrack(0);
}

// Count number of ways to break
export function wordBreakCount(s: string, wordDict: string[]): number {
    const wordSet = new Set(wordDict);
    const n = s.length;
    
    const dp = Array(n + 1).fill(0);
    dp[0] = 1;
    
    for (let i = 1; i <= n; i++) {
        for (let j = 0; j < i; j++) {
            if (dp[j] > 0 && wordSet.has(s.substring(j, i))) {
                dp[i] += dp[j];
            }
        }
    }
    
    return dp[n];
}

// Test cases
if (require.main === module) {
    console.log("Can break 'leetcode':", 
        wordBreak("leetcode", ["leet", "code"]));
    
    console.log("Can break 'applepenapple':", 
        wordBreak("applepenapple", ["apple", "pen"]));
    
    console.log("Can break 'catsandog':", 
        wordBreak("catsandog", ["cats", "dog", "sand", "and", "cat"]));
    
    console.log("\nOptimized 'leetcode':", 
        wordBreakOptimized("leetcode", ["leet", "code"]));
    
    console.log("\nAll sentences for 'catsanddog':");
    console.log(wordBreakII("catsanddog", ["cat", "cats", "and", "sand", "dog"]));
    
    console.log("\nNumber of ways to break 'leetcode':", 
        wordBreakCount("leetcode", ["leet", "code", "le", "et", "co", "de"]));
}
