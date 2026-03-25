/**
 * DFS Recursion - Memoization DFS / Top-Down DP Pattern
 * 
 * Related LeetCode Problems:
 * - LC 70: Climbing Stairs (Easy)
 * - LC 198: House Robber (Medium)
 * - LC 509: Fibonacci Number (Easy)
 * - LC 322: Coin Change (Medium)
 * - LC 139: Word Break (Medium)
 * 
 * Pattern: Recursive DFS with caching
 * Time Complexity: O(n) with memoization vs O(2^n) without
 * Space Complexity: O(n) for cache + O(n) for recursion stack
 */

import java.util.*;

public class MemoizationDFS {
    
    // LC 509: Fibonacci Number
    public int fib(int n) {
        Map<Integer, Integer> memo = new HashMap<>();
        return fibHelper(n, memo);
    }
    
    private int fibHelper(int n, Map<Integer, Integer> memo) {
        if (n <= 1) {
            return n;
        }
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        
        int result = fibHelper(n - 1, memo) + fibHelper(n - 2, memo);
        memo.put(n, result);
        return result;
    }
    
    // LC 70: Climbing Stairs
    public int climbStairs(int n) {
        Map<Integer, Integer> memo = new HashMap<>();
        return climbHelper(0, n, memo);
    }
    
    private int climbHelper(int i, int n, Map<Integer, Integer> memo) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        if (memo.containsKey(i)) {
            return memo.get(i);
        }
        
        int result = climbHelper(i + 1, n, memo) + climbHelper(i + 2, n, memo);
        memo.put(i, result);
        return result;
    }
    
    // LC 198: House Robber
    public int rob(int[] nums) {
        Map<Integer, Integer> memo = new HashMap<>();
        return robHelper(0, nums, memo);
    }
    
    private int robHelper(int i, int[] nums, Map<Integer, Integer> memo) {
        if (i >= nums.length) {
            return 0;
        }
        if (memo.containsKey(i)) {
            return memo.get(i);
        }
        
        // Rob current + skip next, or skip current
        int result = Math.max(nums[i] + robHelper(i + 2, nums, memo), 
                              robHelper(i + 1, nums, memo));
        memo.put(i, result);
        return result;
    }
    
    // LC 322: Coin Change
    public int coinChange(int[] coins, int amount) {
        Map<Integer, Integer> memo = new HashMap<>();
        int result = coinChangeHelper(coins, amount, memo);
        return result == Integer.MAX_VALUE ? -1 : result;
    }
    
    private int coinChangeHelper(int[] coins, int remaining, Map<Integer, Integer> memo) {
        if (remaining == 0) {
            return 0;
        }
        if (remaining < 0) {
            return Integer.MAX_VALUE;
        }
        if (memo.containsKey(remaining)) {
            return memo.get(remaining);
        }
        
        int minCoins = Integer.MAX_VALUE;
        for (int coin : coins) {
            int result = coinChangeHelper(coins, remaining - coin, memo);
            if (result != Integer.MAX_VALUE) {
                minCoins = Math.min(minCoins, result + 1);
            }
        }
        
        memo.put(remaining, minCoins);
        return minCoins;
    }
    
    // LC 139: Word Break
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        Map<Integer, Boolean> memo = new HashMap<>();
        return wordBreakHelper(s, wordSet, 0, memo);
    }
    
    private boolean wordBreakHelper(String s, Set<String> wordSet, int start, 
                                     Map<Integer, Boolean> memo) {
        if (start == s.length()) {
            return true;
        }
        if (memo.containsKey(start)) {
            return memo.get(start);
        }
        
        for (int end = start + 1; end <= s.length(); end++) {
            String word = s.substring(start, end);
            if (wordSet.contains(word) && wordBreakHelper(s, wordSet, end, memo)) {
                memo.put(start, true);
                return true;
            }
        }
        
        memo.put(start, false);
        return false;
    }
    
    // LC 329: Longest Increasing Path in a Matrix
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] memo = new int[rows][cols];
        int maxPath = 0;
        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                maxPath = Math.max(maxPath, dfsPath(matrix, r, c, memo));
            }
        }
        
        return maxPath;
    }
    
    private int dfsPath(int[][] matrix, int r, int c, int[][] memo) {
        if (memo[r][c] != 0) {
            return memo[r][c];
        }
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int maxPath = 1;
        
        for (int[] dir : directions) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && 
                matrix[nr][nc] > matrix[r][c]) {
                maxPath = Math.max(maxPath, 1 + dfsPath(matrix, nr, nc, memo));
            }
        }
        
        memo[r][c] = maxPath;
        return maxPath;
    }
    
    // Test
    public static void main(String[] args) {
        MemoizationDFS solution = new MemoizationDFS();
        
        // Test fib
        System.out.println("Testing fib:");
        System.out.println("fib(10) = " + solution.fib(10));  // 55
        
        // Test climbStairs
        System.out.println("\nTesting climbStairs:");
        System.out.println("climbStairs(5) = " + solution.climbStairs(5));  // 8
        
        // Test rob
        System.out.println("\nTesting rob:");
        System.out.println("rob([2,7,9,3,1]) = " + solution.rob(new int[]{2, 7, 9, 3, 1}));  // 12
        
        // Test coinChange
        System.out.println("\nTesting coinChange:");
        System.out.println("coinChange([1,2,5], 11) = " + 
                           solution.coinChange(new int[]{1, 2, 5}, 11));  // 3
        
        // Test wordBreak
        System.out.println("\nTesting wordBreak:");
        System.out.println("wordBreak('leetcode', ['leet','code']) = " + 
                           solution.wordBreak("leetcode", Arrays.asList("leet", "code")));  // true
    }
}
