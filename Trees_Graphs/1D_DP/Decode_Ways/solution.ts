/**
 * 1D Dynamic Programming - Decode Ways
 * 
 * Related LeetCode Problems:
 * - LC 91: Decode Ways (Medium)
 * - LC 639: Decode Ways II (Hard) - with wildcards
 * - LC 842: Split Array into Fibonacci Sequence (Medium)
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n) or O(1) optimized
 */

// LC 91: Decode Ways
export function numDecodings(s: string): number {
    if (!s || s[0] === '0') {
        return 0;
    }
    
    const n = s.length;
    const dp = Array(n + 1).fill(0);
    dp[0] = 1;
    dp[1] = 1;
    
    for (let i = 2; i <= n; i++) {
        // 1-digit decode
        const oneDigit = parseInt(s.substring(i-1, i));
        if (oneDigit >= 1 && oneDigit <= 9) {
            dp[i] += dp[i-1];
        }
        
        // 2-digit decode
        const twoDigit = parseInt(s.substring(i-2, i));
        if (twoDigit >= 10 && twoDigit <= 26) {
            dp[i] += dp[i-2];
        }
    }
    
    return dp[n];
}

// Space-optimized version
export function numDecodingsOptimized(s: string): number {
    if (!s || s[0] === '0') {
        return 0;
    }
    
    let prev2 = 1, prev1 = 1;
    
    for (let i = 1; i < s.length; i++) {
        let current = 0;
        
        // 1-digit decode
        const oneDigit = parseInt(s[i]);
        if (oneDigit >= 1 && oneDigit <= 9) {
            current += prev1;
        }
        
        // 2-digit decode
        const twoDigit = parseInt(s.substring(i-1, i+1));
        if (twoDigit >= 10 && twoDigit <= 26) {
            current += prev2;
        }
        
        prev2 = prev1;
        prev1 = current;
    }
    
    return prev1;
}

// Test cases
if (require.main === module) {
    console.log("Decode '12':", numDecodings("12"));
    console.log("Decode '226':", numDecodings("226"));
    console.log("Decode '06':", numDecodings("06"));
    console.log("Decode '11106':", numDecodings("11106"));
    
    console.log("\nOptimized '12':", numDecodingsOptimized("12"));
    console.log("Optimized '226':", numDecodingsOptimized("226"));
}
