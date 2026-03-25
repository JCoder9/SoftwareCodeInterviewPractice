/**
 * Prefix Sum with HashMap - Subarray Sum Equals K
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

function subarraySumEqualsK(nums: number[], k: number): number {
    let count = 0;
    let currentSum = 0;
    const prefixCount = new Map<number, number>();
    prefixCount.set(0, 1);
    
    for (const num of nums) {
        currentSum += num;
        
        if (prefixCount.has(currentSum - k)) {
            count += prefixCount.get(currentSum - k)!;
        }
        
        prefixCount.set(currentSum, (prefixCount.get(currentSum) || 0) + 1);
    }
    
    return count;
}

function maxSubarrayLenEqualsK(nums: number[], k: number): number {
    let maxLen = 0;
    let currentSum = 0;
    const prefixMap = new Map<number, number>();
    prefixMap.set(0, -1);
    
    for (let i = 0; i < nums.length; i++) {
        currentSum += nums[i];
        
        if (prefixMap.has(currentSum - k)) {
            maxLen = Math.max(maxLen, i - prefixMap.get(currentSum - k)!);
        }
        
        if (!prefixMap.has(currentSum)) {
            prefixMap.set(currentSum, i);
        }
    }
    
    return maxLen;
}

function checkSubarraySumMultipleK(nums: number[], k: number): boolean {
    if (nums.length < 2) return false;
    
    const remainderMap = new Map<number, number>();
    remainderMap.set(0, -1);
    let currentSum = 0;
    
    for (let i = 0; i < nums.length; i++) {
        currentSum += nums[i];
        
        const remainder = (k !== 0) ? currentSum % k : currentSum;
        
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

// Test
if (require.main === module) {
    console.log("Subarray Sum = K:", subarraySumEqualsK([1,2,3,4,5], 9));
    console.log("Max Len = K:", maxSubarrayLenEqualsK([1,-1,5,-2,3], 3));
    console.log("Multiple of K:", checkSubarraySumMultipleK([23,2,4,6,7], 6));
}

export { subarraySumEqualsK, maxSubarrayLenEqualsK, checkSubarraySumMultipleK };
