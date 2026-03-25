/**
 * 1D Dynamic Programming - House Robber Pattern
 * 
 * Related LeetCode Problems:
 * - LC 198: House Robber (Medium)
 * - LC 213: House Robber II (Medium) - circular
 * - LC 740: Delete and Earn (Medium)
 * - LC 2320: Count Number of Ways to Place Houses (Medium)
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1) optimized
 */

// LC 198: House Robber
export function rob(nums: number[]): number {
    if (nums.length === 0) return 0;
    if (nums.length <= 2) return Math.max(...nums.slice(0, 2));
    
    let prev2 = nums[0];
    let prev1 = Math.max(nums[0], nums[1]);
    
    for (let i = 2; i < nums.length; i++) {
        const current = Math.max(nums[i] + prev2, prev1);
        prev2 = prev1;
        prev1 = current;
    }
    
    return prev1;
}

// LC 213: House Robber II (circular)
export function rob2(nums: number[]): number {
    if (nums.length === 1) return nums[0];
    if (nums.length === 2) return Math.max(nums[0], nums[1]);
    
    function robLinear(start: number, end: number): number {
        let prev2 = 0, prev1 = 0;
        
        for (let i = start; i <= end; i++) {
            const current = Math.max(nums[i] + prev2, prev1);
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
    
    return Math.max(
        robLinear(0, nums.length - 2),  // exclude last
        robLinear(1, nums.length - 1)   // exclude first
    );
}

// LC 740: Delete and Earn
export function deleteAndEarn(nums: number[]): number {
    if (nums.length === 0) return 0;
    
    const maxNum = Math.max(...nums);
    const points = Array(maxNum + 1).fill(0);
    
    for (const num of nums) {
        points[num] += num;
    }
    
    if (points.length === 1) return points[0];
    if (points.length === 2) return Math.max(points[0], points[1]);
    
    let prev2 = points[0];
    let prev1 = Math.max(points[0], points[1]);
    
    for (let i = 2; i < points.length; i++) {
        const current = Math.max(points[i] + prev2, prev1);
        prev2 = prev1;
        prev1 = current;
    }
    
    return prev1;
}

// LC 2320: Count Number of Ways to Place Houses
export function countHousePlacements(n: number): number {
    const MOD = 1000000007;
    
    if (n === 1) return 4;
    
    let prev2 = 1, prev1 = 2;
    
    for (let i = 2; i <= n; i++) {
        const current = (prev1 + prev2) % MOD;
        prev2 = prev1;
        prev1 = current;
    }
    
    return (prev1 * prev1) % MOD;
}

// Test cases
if (require.main === module) {
    console.log("Rob [1,2,3,1]:", rob([1, 2, 3, 1]));
    console.log("Rob [2,7,9,3,1]:", rob([2, 7, 9, 3, 1]));
    
    console.log("Rob II [2,3,2]:", rob2([2, 3, 2]));
    console.log("Rob II [1,2,3,1]:", rob2([1, 2, 3, 1]));
    
    console.log("Delete and earn [3,4,2]:", deleteAndEarn([3, 4, 2]));
    
    console.log("Count placements (n=1):", countHousePlacements(1));
    console.log("Count placements (n=2):", countHousePlacements(2));
}
