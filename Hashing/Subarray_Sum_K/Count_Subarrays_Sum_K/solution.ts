/**
 * Subarray Sum Equals K - Count Pattern
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

function subarraySum(nums: number[], k: number): number {
    const prefixCount = new Map<number, number>();
    prefixCount.set(0, 1);
    
    let count = 0;
    let prefixSum = 0;
    
    for (const num of nums) {
        prefixSum += num;
        
        count += prefixCount.get(prefixSum - k) || 0;
        
        prefixCount.set(prefixSum, (prefixCount.get(prefixSum) || 0) + 1);
    }
    
    return count;
}

function numSubarrayProductLessThanK(nums: number[], k: number): number {
    if (k <= 1) return 0;
    
    let count = 0;
    let product = 1;
    let left = 0;
    
    for (let right = 0; right < nums.length; right++) {
        product *= nums[right];
        
        while (product >= k) {
            product /= nums[left];
            left++;
        }
        
        count += right - left + 1;
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

function findMaxLength(nums: number[]): number {
    const countMap = new Map<number, number>();
    countMap.set(0, -1);
    
    let count = 0;
    let maxLength = 0;
    
    for (let i = 0; i < nums.length; i++) {
        count += nums[i] === 1 ? 1 : -1;
        
        if (countMap.has(count)) {
            maxLength = Math.max(maxLength, i - countMap.get(count)!);
        } else {
            countMap.set(count, i);
        }
    }
    
    return maxLength;
}

class TreeNode {
    val: number;
    left: TreeNode | null;
    right: TreeNode | null;
    constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
        this.val = val === undefined ? 0 : val;
        this.left = left === undefined ? null : left;
        this.right = right === undefined ? null : right;
    }
}

function pathSum(root: TreeNode | null, targetSum: number): number {
    const prefixCount = new Map<number, number>();
    prefixCount.set(0, 1);
    
    function dfs(node: TreeNode | null, currSum: number): number {
        if (!node) return 0;
        
        currSum += node.val;
        
        let count = prefixCount.get(currSum - targetSum) || 0;
        
        prefixCount.set(currSum, (prefixCount.get(currSum) || 0) + 1);
        
        count += dfs(node.left, currSum);
        count += dfs(node.right, currSum);
        
        prefixCount.set(currSum, prefixCount.get(currSum)! - 1);
        
        return count;
    }
    
    return dfs(root, 0);
}

// Test
if (require.main === module) {
    console.log("Subarray Sum K:", subarraySum([1,1,1], 2));
    console.log("Subarray Product:", numSubarrayProductLessThanK([10,5,2,6], 100));
    console.log("Continuous Subarray Sum:", checkSubarraySum([23,2,4,6,7], 6));
    console.log("Contiguous Array:", findMaxLength([0,1]));
}

export { subarraySum, numSubarrayProductLessThanK, checkSubarraySum, findMaxLength, pathSum, TreeNode };
