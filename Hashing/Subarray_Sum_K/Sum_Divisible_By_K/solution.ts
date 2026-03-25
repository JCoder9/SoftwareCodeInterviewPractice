/**
 * Sum Divisible by K - Modulo Arithmetic Pattern
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(k)
 */

function subarraysDivByK(nums: number[], k: number): number {
    const remainderCount = new Map<number, number>();
    remainderCount.set(0, 1);
    
    let count = 0;
    let prefixSum = 0;
    
    for (const num of nums) {
        prefixSum += num;
        let remainder = ((prefixSum % k) + k) % k;
        
        count += remainderCount.get(remainder) || 0;
        remainderCount.set(remainder, (remainderCount.get(remainder) || 0) + 1);
    }
    
    return count;
}

function minSubarray(nums: number[], p: number): number {
    const totalSum = nums.reduce((sum, num) => sum + num, 0);
    const targetRemainder = totalSum % p;
    
    if (targetRemainder === 0) return 0;
    
    const remainderMap = new Map<number, number>();
    remainderMap.set(0, -1);
    
    let prefixSum = 0;
    let minLength = nums.length;
    
    for (let i = 0; i < nums.length; i++) {
        prefixSum += nums[i];
        const currentRemainder = prefixSum % p;
        
        const needed = ((currentRemainder - targetRemainder) % p + p) % p;
        
        if (remainderMap.has(needed)) {
            minLength = Math.min(minLength, i - remainderMap.get(needed)!);
        }
        
        remainderMap.set(currentRemainder, i);
    }
    
    return minLength < nums.length ? minLength : -1;
}

function countNicePairs(nums: number[]): number {
    const MOD = 1_000_000_007;
    
    function reverse(n: number): number {
        let result = 0;
        while (n > 0) {
            result = result * 10 + (n % 10);
            n = Math.floor(n / 10);
        }
        return result;
    }
    
    const diffCount = new Map<number, number>();
    let count = 0;
    
    for (const num of nums) {
        const diff = num - reverse(num);
        count = (count + (diffCount.get(diff) || 0)) % MOD;
        diffCount.set(diff, (diffCount.get(diff) || 0) + 1);
    }
    
    return count;
}

function checkSubarraySum(nums: number[], k: number): boolean {
    const remainderMap = new Map<number, number>();
    remainderMap.set(0, -1);
    
    let prefixSum = 0;
    
    for (let i = 0; i < nums.length; i++) {
        prefixSum += nums[i];
        
        const remainder = k !== 0 ? prefixSum % k : prefixSum;
        
        if (remainderMap.has(remainder)) {
            if (i - remainderMap.get(remainder)! >= 2) {
                return true;
            }
        } else {
            remainderMap.set(remainder, i);
        }
    }
    
    return false;
}

function waysToSplitArray(nums: number[]): number {
    const totalSum = nums.reduce((sum, num) => sum + num, 0);
    
    let leftSum = 0;
    let count = 0;
    
    for (let i = 0; i < nums.length - 1; i++) {
        leftSum += nums[i];
        const rightSum = totalSum - leftSum;
        
        if (leftSum >= rightSum) {
            count++;
        }
    }
    
    return count;
}

// Test
if (require.main === module) {
    console.log("Subarrays Div by K:", subarraysDivByK([4,5,0,-2,-3,1], 5));
    console.log("Min Subarray:", minSubarray([3,1,4,2], 6));
    console.log("Count Nice Pairs:", countNicePairs([42,11,1,97]));
    console.log("Check Subarray Sum:", checkSubarraySum([23,2,4,6,7], 6));
    console.log("Ways to Split:", waysToSplitArray([10,4,-8,7]));
}

export { subarraysDivByK, minSubarray, countNicePairs, checkSubarraySum, waysToSplitArray };
