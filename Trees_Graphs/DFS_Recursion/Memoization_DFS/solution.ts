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

// LC 509: Fibonacci Number
export function fib(n: number): number {
    const memo = new Map<number, number>();
    
    function fibHelper(n: number): number {
        if (n <= 1) {
            return n;
        }
        if (memo.has(n)) {
            return memo.get(n)!;
        }
        
        const result = fibHelper(n - 1) + fibHelper(n - 2);
        memo.set(n, result);
        return result;
    }
    
    return fibHelper(n);
}

// LC 70: Climbing Stairs
export function climbStairs(n: number): number {
    const memo = new Map<number, number>();
    
    function dfs(i: number): number {
        if (i > n) {
            return 0;
        }
        if (i === n) {
            return 1;
        }
        if (memo.has(i)) {
            return memo.get(i)!;
        }
        
        const result = dfs(i + 1) + dfs(i + 2);
        memo.set(i, result);
        return result;
    }
    
    return dfs(0);
}

// LC 198: House Robber
export function rob(nums: number[]): number {
    const memo = new Map<number, number>();
    
    function dfs(i: number): number {
        if (i >= nums.length) {
            return 0;
        }
        if (memo.has(i)) {
            return memo.get(i)!;
        }
        
        // Rob current + skip next, or skip current
        const result = Math.max(nums[i] + dfs(i + 2), dfs(i + 1));
        memo.set(i, result);
        return result;
    }
    
    return dfs(0);
}

// LC 322: Coin Change
export function coinChange(coins: number[], amount: number): number {
    const memo = new Map<number, number>();
    
    function dfs(remaining: number): number {
        if (remaining === 0) {
            return 0;
        }
        if (remaining < 0) {
            return Infinity;
        }
        if (memo.has(remaining)) {
            return memo.get(remaining)!;
        }
        
        let minCoins = Infinity;
        for (const coin of coins) {
            const result = dfs(remaining - coin);
            if (result !== Infinity) {
                minCoins = Math.min(minCoins, result + 1);
            }
        }
        
        memo.set(remaining, minCoins);
        return minCoins;
    }
    
    const result = dfs(amount);
    return result === Infinity ? -1 : result;
}

// LC 139: Word Break
export function wordBreak(s: string, wordDict: string[]): boolean {
    const wordSet = new Set(wordDict);
    const memo = new Map<number, boolean>();
    
    function dfs(start: number): boolean {
        if (start === s.length) {
            return true;
        }
        if (memo.has(start)) {
            return memo.get(start)!;
        }
        
        for (let end = start + 1; end <= s.length; end++) {
            const word = s.substring(start, end);
            if (wordSet.has(word) && dfs(end)) {
                memo.set(start, true);
                return true;
            }
        }
        
        memo.set(start, false);
        return false;
    }
    
    return dfs(0);
}

// LC 329: Longest Increasing Path in a Matrix
export function longestIncreasingPath(matrix: number[][]): number {
    if (!matrix || matrix.length === 0) {
        return 0;
    }
    
    const rows = matrix.length;
    const cols = matrix[0].length;
    const memo = new Map<string, number>();
    
    function dfs(r: number, c: number): number {
        const key = `${r},${c}`;
        if (memo.has(key)) {
            return memo.get(key)!;
        }
        
        const directions = [[0, 1], [0, -1], [1, 0], [-1, 0]];
        let maxPath = 1;
        
        for (const [dr, dc] of directions) {
            const nr = r + dr;
            const nc = c + dc;
            
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && 
                matrix[nr][nc] > matrix[r][c]) {
                maxPath = Math.max(maxPath, 1 + dfs(nr, nc));
            }
        }
        
        memo.set(key, maxPath);
        return maxPath;
    }
    
    let result = 0;
    for (let r = 0; r < rows; r++) {
        for (let c = 0; c < cols; c++) {
            result = Math.max(result, dfs(r, c));
        }
    }
    
    return result;
}

// Test cases
if (require.main === module) {
    // Test fib
    console.log("Testing fib:");
    console.log(`fib(10) = ${fib(10)}`);  // 55
    
    // Test climbStairs
    console.log("\nTesting climbStairs:");
    console.log(`climbStairs(5) = ${climbStairs(5)}`);  // 8
    
    // Test rob
    console.log("\nTesting rob:");
    console.log(`rob([2,7,9,3,1]) = ${rob([2, 7, 9, 3, 1])}`);  // 12
    
    // Test coinChange
    console.log("\nTesting coinChange:");
    console.log(`coinChange([1,2,5], 11) = ${coinChange([1, 2, 5], 11)}`);  // 3
    
    // Test wordBreak
    console.log("\nTesting wordBreak:");
    console.log(`wordBreak('leetcode', ['leet','code']) = ${wordBreak('leetcode', ['leet', 'code'])}`);  // true
    
    // Test longestIncreasingPath
    console.log("\nTesting longestIncreasingPath:");
    const matrix = [[9, 9, 4], [6, 6, 8], [2, 1, 1]];
    console.log(`longestIncreasingPath(matrix) = ${longestIncreasingPath(matrix)}`);  // 4
}
