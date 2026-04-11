/**
 * 1D Dynamic Programming - Climbing Stairs Pattern
 * 
 * Problem: You're climbing stairs with n steps. You can climb 1 or 2 steps at a time.
 *          How many distinct ways can you reach the top?
 * 
 * Pattern: Build solution from smaller subproblems.
 *          dp[i] = dp[i-1] + dp[i-2] (Fibonacci-like)
 * 
 * Related LeetCode Problems:
 * - LC 70: Climbing Stairs (Easy)
 * - LC 746: Min Cost Climbing Stairs (Easy)
 * - LC 509: Fibonacci Number (Easy)
 * - LC 1137: N-th Tribonacci Number (Easy)
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1) optimized
 */

// ───────────────────────────────────────────────────────────────────────────
// NAIVE APPROACH (Brute Force) - O(2^n) time | O(n) space
// ───────────────────────────────────────────────────────────────────────────
// INTERVIEW SCRIPT:
//   1. Describe:   "Brute force uses naive recursion: climb(n) = climb(n-1) + climb(n-2)
//                   — exponential O(2^n) due to repeated subproblem calculations"
//   2. Problem:    "For n=40, makes billions of recursive calls; many duplicate
//                   computations"
//   3. Transition: "With DP (memoization or bottom-up), each subproblem solved once
//                   — reduces to O(n) time"
//
// public int climbStairsNaive(int n) {
//     if (n <= 2) return n;
//     return climbStairsNaive(n - 1) + climbStairsNaive(n - 2);
// }
// ───────────────────────────────────────────────────────────────────────────

public class ClimbingStairs {
    
    // LC 70: Climbing Stairs
    public int climbStairs(int n) {
        if (n <= 2) return n;
        
        int prev2 = 1, prev1 = 2;
        
        for (int i = 3; i <= n; i++) {
            int current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
    
    // LC 746: Min Cost Climbing Stairs
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        if (n <= 2) return Math.min(cost[0], cost[1]);
        
        int prev2 = cost[0], prev1 = cost[1];
        
        for (int i = 2; i < n; i++) {
            int current = cost[i] + Math.min(prev1, prev2);
            prev2 = prev1;
            prev1 = current;
        }
        
        return Math.min(prev1, prev2);
    }
    
    // LC 509: Fibonacci Number
    public int fib(int n) {
        if (n <= 1) return n;
        
        int prev2 = 0, prev1 = 1;
        
        for (int i = 2; i <= n; i++) {
            int current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
    
    // LC 1137: N-th Tribonacci Number
    public int tribonacci(int n) {
        if (n == 0) return 0;
        if (n <= 2) return 1;
        
        int prev3 = 0, prev2 = 1, prev1 = 1;
        
        for (int i = 3; i <= n; i++) {
            int current = prev1 + prev2 + prev3;
            prev3 = prev2;
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
    
    // Variable jumps
    public int climbStairsVariableJumps(int n, int[] jumps) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        
        for (int i = 0; i < n; i++) {
            if (dp[i] > 0) {
                for (int jump = 1; jump <= Math.min(jumps[i], n - i); jump++) {
                    dp[i + jump] += dp[i];
                }
            }
        }
        
        return dp[n];
    }
    
    // Test
    public static void main(String[] args) {
        ClimbingStairs solution = new ClimbingStairs();
        
        System.out.println("Climbing stairs (n=5): " + solution.climbStairs(5));
        
        int[] cost1 = {10, 15, 20};
        System.out.println("Min cost climbing: " + solution.minCostClimbingStairs(cost1));
        
        System.out.println("Fibonacci (n=10): " + solution.fib(10));
        System.out.println("Tribonacci (n=10): " + solution.tribonacci(10));
        
        int[] jumps = {3, 2, 1, 1, 4};
        System.out.println("Variable jumps: " + solution.climbStairsVariableJumps(5, jumps));
    }
}
