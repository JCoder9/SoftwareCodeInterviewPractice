/**
 * 1D Dynamic Programming - Climbing Stairs Pattern
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

// LC 70: Climbing Stairs
export function climbStairs(n: number): number {
    if (n <= 2) return n;
    
    let prev2 = 1, prev1 = 2;
    
    for (let i = 3; i <= n; i++) {
        const current = prev1 + prev2;
        prev2 = prev1;
        prev1 = current;
    }
    
    return prev1;
}

// LC 746: Min Cost Climbing Stairs
export function minCostClimbingStairs(cost: number[]): number {
    const n = cost.length;
    if (n <= 2) return Math.min(cost[0], cost[1]);
    
    let prev2 = cost[0], prev1 = cost[1];
    
    for (let i = 2; i < n; i++) {
        const current = cost[i] + Math.min(prev1, prev2);
        prev2 = prev1;
        prev1 = current;
    }
    
    return Math.min(prev1, prev2);
}

// LC 509: Fibonacci Number
export function fib(n: number): number {
    if (n <= 1) return n;
    
    let prev2 = 0, prev1 = 1;
    
    for (let i = 2; i <= n; i++) {
        const current = prev1 + prev2;
        prev2 = prev1;
        prev1 = current;
    }
    
    return prev1;
}

// LC 1137: N-th Tribonacci Number
export function tribonacci(n: number): number {
    if (n === 0) return 0;
    if (n <= 2) return 1;
    
    let prev3 = 0, prev2 = 1, prev1 = 1;
    
    for (let i = 3; i <= n; i++) {
        const current = prev1 + prev2 + prev3;
        prev3 = prev2;
        prev2 = prev1;
        prev1 = current;
    }
    
    return prev1;
}

// Variable jumps
export function climbStairsVariableJumps(n: number, jumps: number[]): number {
    const dp = Array(n + 1).fill(0);
    dp[0] = 1;
    
    for (let i = 0; i < n; i++) {
        if (dp[i] > 0) {
            for (let jump = 1; jump <= Math.min(jumps[i], n - i); jump++) {
                dp[i + jump] += dp[i];
            }
        }
    }
    
    return dp[n];
}

// Test cases
if (require.main === module) {
    console.log("Climbing stairs (n=5):", climbStairs(5));
    
    const cost1 = [10, 15, 20];
    console.log("Min cost climbing:", minCostClimbingStairs(cost1));
    
    console.log("Fibonacci (n=10):", fib(10));
    console.log("Tribonacci (n=10):", tribonacci(10));
    
    const jumps = [3, 2, 1, 1, 4];
    console.log("Variable jumps:", climbStairsVariableJumps(5, jumps));
}
