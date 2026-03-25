/**
 * 1D Dynamic Programming - Core Patterns
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1) optimized
 */

function climbStairs(n: number): number {
    if (n <= 2) return n;
    
    let prev2 = 1, prev1 = 2;
    for (let i = 3; i <= n; i++) {
        const curr = prev1 + prev2;
        prev2 = prev1;
        prev1 = curr;
    }
    
    return prev1;
}

function robHouses(nums: number[]): number {
    if (nums.length === 0) return 0;
    if (nums.length === 1) return nums[0];
    
    let prev2 = 0, prev1 = 0;
    for (const num of nums) {
        const curr = Math.max(prev1, num + prev2);
        prev2 = prev1;
        prev1 = curr;
    }
    
    return prev1;
}

function canJump(nums: number[]): boolean {
    let maxReach = 0;
    for (let i = 0; i < nums.length; i++) {
        if (i > maxReach) return false;
        maxReach = Math.max(maxReach, i + nums[i]);
    }
    return true;
}

function minJumps(nums: number[]): number {
    const n = nums.length;
    if (n === 1) return 0;
    
    let jumps = 0;
    let currentEnd = 0;
    let farthest = 0;
    
    for (let i = 0; i < n - 1; i++) {
        farthest = Math.max(farthest, i + nums[i]);
        
        if (i === currentEnd) {
            jumps++;
            currentEnd = farthest;
            
            if (currentEnd >= n - 1) break;
        }
    }
    
    return jumps;
}

function decodeWays(s: string): number {
    if (!s || s[0] === '0') return 0;
    
    const n = s.length;
    let prev2 = 1, prev1 = 1;
    
    for (let i = 1; i < n; i++) {
        let curr = 0;
        
        if (s[i] !== '0') {
            curr += prev1;
        }
        
        const twoDigit = parseInt(s.substring(i - 1, i + 1));
        if (twoDigit >= 10 && twoDigit <= 26) {
            curr += prev2;
        }
        
        prev2 = prev1;
        prev1 = curr;
    }
    
    return prev1;
}

// Test
if (require.main === module) {
    console.log("Climb stairs (5):", climbStairs(5));
    console.log("Rob houses [1,2,3,1]:", robHouses([1, 2, 3, 1]));
    console.log("Can jump [2,3,1,1,4]:", canJump([2, 3, 1, 1, 4]));
    console.log("Min jumps [2,3,1,1,4]:", minJumps([2, 3, 1, 1, 4]));
    console.log("Decode ways '226':", decodeWays("226"));
}

export { climbStairs, robHouses, canJump, minJumps, decodeWays };
