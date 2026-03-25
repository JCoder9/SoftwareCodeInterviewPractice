/**
 * Product Less Than K - Sliding Window Variant
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

function numSubarrayProductLessThanK(nums: number[], k: number): number {
    if (k <= 1) return 0;
    
    let count = 0, product = 1, left = 0;
    
    for (let right = 0; right < nums.length; right++) {
        product *= nums[right];
        
        while (product >= k) {
            product /= nums[left];
            left++;
        }
        
        count += (right - left + 1);
    }
    
    return count;
}

function maxProduct(nums: number[]): number {
    if (nums.length === 0) return 0;
    
    let maxSoFar = nums[0];
    let maxEndingHere = nums[0];
    let minEndingHere = nums[0];
    
    for (let i = 1; i < nums.length; i++) {
        const num = nums[i];
        
        if (num < 0) {
            [maxEndingHere, minEndingHere] = [minEndingHere, maxEndingHere];
        }
        
        maxEndingHere = Math.max(num, maxEndingHere * num);
        minEndingHere = Math.min(num, minEndingHere * num);
        
        maxSoFar = Math.max(maxSoFar, maxEndingHere);
    }
    
    return maxSoFar;
}

function productExceptSelf(nums: number[]): number[] {
    const n = nums.length;
    const result: number[] = new Array(n);
    
    let prefix = 1;
    for (let i = 0; i < n; i++) {
        result[i] = prefix;
        prefix *= nums[i];
    }
    
    let suffix = 1;
    for (let i = n - 1; i >= 0; i--) {
        result[i] *= suffix;
        suffix *= nums[i];
    }
    
    return result;
}

function maximumProduct(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    
    return Math.max(
        nums[n-1] * nums[n-2] * nums[n-3],
        nums[0] * nums[1] * nums[n-1]
    );
}

// Test
if (require.main === module) {
    console.log("Product < K:", numSubarrayProductLessThanK([10,5,2,6], 100));
    console.log("Max Product:", maxProduct([2,3,-2,4]));
    console.log("Product Except Self:", productExceptSelf([1,2,3,4]));
    console.log("Max Product of Three:", maximumProduct([1,2,3]));
}

export { numSubarrayProductLessThanK, maxProduct, productExceptSelf, maximumProduct };
