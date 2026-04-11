/**
 * 1D Dynamic Programming - Word Break
 * 
 * Problem: Given string s and dictionary of words, return true if s can be segmented
 *          into space-separated sequence of dictionary words.
 *          Example: s = "leetcode", dict = ["leet", "code"] → true
 * 
 * Pattern: dp[i] = true if s[0:i] can be segmented.
 *          For each position i, check all splits: if s[0:j] works and s[j:i] is a word.
 * 
 * Related LeetCode Problems:
 * - LC 139: Word Break (Medium) ⭐⭐⭐
 * - LC 140: Word Break II (Hard) - return all possible sentences
 * - LC 472: Concatenated Words (Hard)
 * 
 * Time Complexity: O(n²) or O(n² + m) with trie
 * Space Complexity: O(n)
 */

// ─────────────────────────────────────────────────────────────────────────────
// NAIVE APPROACH (Brute Force) — O(2^n) time | O(n) space
// ─────────────────────────────────────────────────────────────────────────────
// INTERVIEW SCRIPT:
//   1. Describe:   "Brute force tries all split combinations recursively
//                  — exponential branching at each position"
//   2. Problem:    "For length-20 string: over 1 million split combinations;
//                  overlapping subproblems computed repeatedly"
//   3. Transition: "DP memoizes whether each prefix is breakable — O(n²) time"
//
// public boolean wordBreakNaive(String s, List<String> wordDict) {
//     Set<String> wordSet = new HashSet<>(wordDict);
//     return canBreak(s, 0, wordSet);
// }
// 
// private boolean canBreak(String s, int start, Set<String> wordSet) {
//     if (start == s.length()) return true;
//     
//     // Try all possible next words
//     for (int end = start + 1; end <= s.length(); end++) {
//         String word = s.substring(start, end);
//         if (wordSet.contains(word) && canBreak(s, end, wordSet)) {
//             return true;
//         }
//     }
//     return false;
// }
// ─────────────────────────────────────────────────────────────────────────────

import java.util.*;

public class WordBreak {
    
    // LC 139: Word Break
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        int n = s.length();
        
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[n];
    }
    
    // Optimized with max word length
    public boolean wordBreakOptimized(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        int maxLen = 0;
        for (String word : wordDict) {
            maxLen = Math.max(maxLen, word.length());
        }
        
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        
        for (int i = 1; i <= n; i++) {
            for (int j = Math.max(0, i - maxLen); j < i; j++) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[n];
    }
    
    // LC 140: Word Break II
    public List<String> wordBreakII(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        Map<Integer, List<String>> memo = new HashMap<>();
        return backtrack(s, wordSet, 0, memo);
    }
    
    private List<String> backtrack(String s, Set<String> wordSet, 
                                   int start, Map<Integer, List<String>> memo) {
        if (memo.containsKey(start)) {
            return memo.get(start);
        }
        
        List<String> result = new ArrayList<>();
        
        if (start == s.length()) {
            result.add("");
            return result;
        }
        
        for (int end = start + 1; end <= s.length(); end++) {
            String word = s.substring(start, end);
            if (wordSet.contains(word)) {
                List<String> subSentences = backtrack(s, wordSet, end, memo);
                for (String sentence : subSentences) {
                    result.add(word + (sentence.isEmpty() ? "" : " " + sentence));
                }
            }
        }
        
        memo.put(start, result);
        return result;
    }
    
    // Count number of ways to break
    public int wordBreakCount(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        int n = s.length();
        
        int[] dp = new int[n + 1];
        dp[0] = 1;
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] > 0 && wordSet.contains(s.substring(j, i))) {
                    dp[i] += dp[j];
                }
            }
        }
        
        return dp[n];
    }
    
    // Test
    public static void main(String[] args) {
        WordBreak solution = new WordBreak();
        
        System.out.println("Can break 'leetcode': " + 
            solution.wordBreak("leetcode", Arrays.asList("leet", "code")));
        
        System.out.println("Can break 'applepenapple': " + 
            solution.wordBreak("applepenapple", Arrays.asList("apple", "pen")));
        
        System.out.println("Can break 'catsandog': " + 
            solution.wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
        
        System.out.println("\nOptimized 'leetcode': " + 
            solution.wordBreakOptimized("leetcode", Arrays.asList("leet", "code")));
        
        System.out.println("\nAll sentences for 'catsanddog':");
        System.out.println(solution.wordBreakII("catsanddog", 
            Arrays.asList("cat", "cats", "and", "sand", "dog")));
        
        System.out.println("\nNumber of ways to break 'leetcode': " + 
            solution.wordBreakCount("leetcode", Arrays.asList("leet", "code", "le", "et", "co", "de")));
    }
}
