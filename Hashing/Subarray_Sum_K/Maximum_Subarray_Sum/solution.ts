/**
 * Maximum Subarray Sum - Kadane's Algorithm Pattern
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

function maxSubArray(nums: number[]): number {
    let maxSum = nums[0];
    let currentSum = nums[0];
    
    for (let i = 1; i < nums.length; i++) {
        currentSum = Math.max(nums[i], currentSum + nums[i]);
        maxSum = Math.max(maxSum, currentSum);
    }
    
    return maxSum;
}

function maxSubarraySumCircular(nums: number[]): number {
    function kadaneMax(arr: number[]): number {
        let maxSum = arr[0];
        let currentSum = arr[0];
        
        for (let i = 1; i < arr.length; i++) {
            currentSum = Math.max(arr[i], currentSum + arr[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        
        return maxSum;
    }
    
    function kadaneMin(arr: number[]): number {
        let minSum = arr[0];
        let currentSum = arr[0];
        
        for (let i = 1; i < arr.length; i++) {
            currentSum = Math.min(arr[i], currentSum + arr[i]);
            minSum = Math.min(minSum, currentSum);
        }
        
        return minSum;
    }
    
    const maxKadane = kadaneMax(nums);
    const minKadane = kadaneMin(nums);
    const totalSum = nums.reduce((sum, num) => sum + num, 0);
    
    if (totalSum === minKadane) {
        return maxKadane;
    }
    
    return Math.max(maxKadane, totalSum - minKadane);
}

function maximumSum(nums: number[]): number {
    const n = nums.length;
    
    let maxEndingHere = nums[0];
    let maxEndingWithDel = 0;
    let result = nums[0];
    
    for (let i = 1; i < n; i++) {
        maxEndingWithDel = Math.max(maxEndingHere, maxEndingWithDel + nums[i]);
        maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);
        result = Math.max(result, maxEndingHere, maxEndingWithDel);
    }
    
    return result;
}

function maxAbsoluteSum(nums: number[]): number {
    let maxSum = 0, maxCurrent = 0;
    let minSum = 0, minCurrent = 0;
    
    for (const num of nums) {
        maxCurrent = Math.max(num, maxCurrent + num);
        maxSum = Math.max(maxSum, maxCurrent);
        
        minCurrent = Math.min(num, minCurrent + num);
        minSum = Math.min(minSum, minCurrent);
    }
    
    return Math.max(Math.abs(maxSum), Math.abs(minSum));
}

function maxProduct(nums: number[]): number {
    let maxProduct = nums[0];
    let currentMax = nums[0];
    let currentMin = nums[0];
    
    for (let i = 1; i < nums.length; i++) {
        const num = nums[i];
        
        const tempMax = Math.max(num, currentMax * num, currentMin * num);
        currentMin = Math.min(num, currentMax * num, currentMin * num);
        currentMax = tempMax;
        
        maxProduct = Math.max(maxProduct, currentMax);
    }
    
    return maxProduct;
}

function maxSumTwoNoOverlap(nums: number[], firstLen: number, secondLen: number): number {
    const n = nums.length;
    const prefix = [0];
    
    for (const num of nums) {
        prefix.push(prefix[prefix.length - 1] + num);
    }
    
    let maxSum = 0;
    
    let maxFirst = 0;
    for (let i = firstLen; i <= n - secondLen; i++) {
        maxFirst = Math.max(maxFirst, prefix[i] - prefix[i - firstLen]);
        maxSum = Math.max(maxSum, maxFirst + prefix[i + secondLen] - prefix[i]);
    }
    
    let maxSecond = 0;
    for (let i = secondLen; i <= n - firstLen; i++) {
        maxSecond = Math.max(maxSecond, prefix[i] - prefix[i - secondLen]);
        maxSum = Math.max(maxSum, maxSecond + prefix[i + firstLen] - prefix[i]);
    }
    
    return maxSum;
}

// Test
if (require.main === module) {
    console.log("Max Subarray:", maxSubArray([-2,1,-3,4,-1,2,1,-5,4]));
    console.log("Max Circular:", maxSubarraySumCircular([5,-3,5]));
    console.log("Max with Deletion:", maximumSum([1,-2,0,3]));
    console.log("Max Absolute Sum:", maxAbsoluteSum([1,-3,2,3,-4]));
    console.log("Max Product:", maxProduct([2,3,-2,4]));
    console.log("Max Sum Two No Overlap:", maxSumTwoNoOverlap([0,6,5,2,2,5,1,9,4], 1, 2));
}

export { maxSubArray, maxSubarraySumCircular, maximumSum, maxAbsoluteSum, maxProduct, maxSumTwoNoOverlap };
