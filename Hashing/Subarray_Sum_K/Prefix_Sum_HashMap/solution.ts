/**
 * Subarray Sum Equals K - Prefix Sum + Hash Map Pattern
 * 
 * Problem: Count/find subarrays whose sum equals target K.
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

function subarraySumEqualsK(nums: number[], k: number): number {
    let count = 0;
    let prefixSum = 0;
    const prefixMap = new Map<number, number>();
    prefixMap.set(0, 1);
    
    for (const num of nums) {
        prefixSum += num;
        
        if (prefixMap.has(prefixSum - k)) {
            count += prefixMap.get(prefixSum - k)!;
        }
        
        prefixMap.set(prefixSum, (prefixMap.get(prefixSum) || 0) + 1);
    }
    
    return count;
}

function maxSubarraySumEqualsK(nums: number[], k: number): number {
    let prefixSum = 0;
    const firstOccurrence = new Map<number, number>();
    firstOccurrence.set(0, -1);
    let maxLength = 0;
    
    for (let i = 0; i < nums.length; i++) {
        prefixSum += nums[i];
        
        if (firstOccurrence.has(prefixSum - k)) {
            const length = i - firstOccurrence.get(prefixSum - k)!;
            maxLength = Math.max(maxLength, length);
        }
        
        if (!firstOccurrence.has(prefixSum)) {
            firstOccurrence.set(prefixSum, i);
        }
    }
    
    return maxLength;
}

// Test cases
if (require.main === module) {
    console.log("Subarray Sum Equals K:");
    console.log("  " + subarraySumEqualsK([1, 1, 1], 2));
    console.log("  " + subarraySumEqualsK([1, 2, 3], 3));
    
    console.log("\nMax Length Subarray Sum Equals K:");
    console.log("  " + maxSubarraySumEqualsK([1, -1, 5, -2, 3], 3));
}

export { subarraySumEqualsK, maxSubarraySumEqualsK };
