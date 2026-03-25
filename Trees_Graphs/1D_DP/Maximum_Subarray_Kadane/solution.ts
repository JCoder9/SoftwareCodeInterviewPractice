/**
 * 1D Dynamic Programming - Maximum Subarray (Kadane's Algorithm)
 * 
 * Related LeetCode Problems:
 * - LC 53: Maximum Subarray (Medium)
 * - LC 918: Maximum Sum Circular Subarray (Medium)
 * - LC 152: Maximum Product Subarray (Medium)
 * - LC 1191: K-Concatenation Maximum Sum (Medium)
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

// LC 53: Maximum Subarray
export function maxSubArray(nums: number[]): number {
    let maxEndingHere = nums[0];
    let maxSoFar = nums[0];
    
    for (let i = 1; i < nums.length; i++) {
        maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);
        maxSoFar = Math.max(maxSoFar, maxEndingHere);
    }
    
    return maxSoFar;
}

// LC 918: Maximum Sum Circular Subarray
export function maxSubarraySumCircular(nums: number[]): number {
    function kadaneMax(arr: number[]): number {
        let maxEndingHere = arr[0];
        let maxSoFar = arr[0];
        
        for (let i = 1; i < arr.length; i++) {
            maxEndingHere = Math.max(arr[i], maxEndingHere + arr[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        
        return maxSoFar;
    }
    
    function kadaneMin(arr: number[]): number {
        let minEndingHere = arr[0];
        let minSoFar = arr[0];
        
        for (let i = 1; i < arr.length; i++) {
            minEndingHere = Math.min(arr[i], minEndingHere + arr[i]);
            minSoFar = Math.min(minSoFar, minEndingHere);
        }
        
        return minSoFar;
    }
    
    const maxNormal = kadaneMax(nums);
    const totalSum = nums.reduce((a, b) => a + b, 0);
    const minSubarray = kadaneMin(nums);
    
    if (totalSum === minSubarray) {
        return maxNormal;
    }
    
    const maxCircular = totalSum - minSubarray;
    
    return Math.max(maxNormal, maxCircular);
}

// LC 152: Maximum Product Subarray
export function maxProduct(nums: number[]): number {
    if (nums.length === 0) return 0;
    
    let maxSoFar = nums[0];
    let minSoFar = nums[0];
    let result = nums[0];
    
    for (let i = 1; i < nums.length; i++) {
        const num = nums[i];
        
        if (num < 0) {
            [maxSoFar, minSoFar] = [minSoFar, maxSoFar];
        }
        
        maxSoFar = Math.max(num, maxSoFar * num);
        minSoFar = Math.min(num, minSoFar * num);
        
        result = Math.max(result, maxSoFar);
    }
    
    return result;
}

// Maximum sum subarray of size k
export function maxSumSubarrayOfSizeK(nums: number[], k: number): number {
    if (k > nums.length) return -1;
    
    let windowSum = nums.slice(0, k).reduce((a, b) => a + b, 0);
    let maxSum = windowSum;
    
    for (let i = k; i < nums.length; i++) {
        windowSum = windowSum - nums[i - k] + nums[i];
        maxSum = Math.max(maxSum, windowSum);
    }
    
    return maxSum;
}

// Test cases
if (require.main === module) {
    const nums1 = [-2, 1, -3, 4, -1, 2, 1, -5, 4];
    console.log("Max subarray sum:", maxSubArray(nums1));
    
    const nums2 = [1, -2, 3, -2];
    console.log("Max circular subarray:", maxSubarraySumCircular(nums2));
    
    const nums3 = [2, 3, -2, 4];
    console.log("Max product subarray:", maxProduct(nums3));
    
    const nums4 = [1, 4, 2, 10, 23, 3, 1, 0, 20];
    console.log("Max sum of size 4:", maxSumSubarrayOfSizeK(nums4, 4));
}
