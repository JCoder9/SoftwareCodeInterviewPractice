/**
 * Two Sum - Classic Complement Search Pattern
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

function twoSum(nums: number[], target: number): number[] {
    const seen = new Map<number, number>();
    
    for (let i = 0; i < nums.length; i++) {
        const complement = target - nums[i];
        
        if (seen.has(complement)) {
            return [seen.get(complement)!, i];
        }
        
        seen.set(nums[i], i);
    }
    
    return [];
}

function twoSumSorted(numbers: number[], target: number): number[] {
    let left = 0, right = numbers.length - 1;
    
    while (left < right) {
        const sum = numbers[left] + numbers[right];
        
        if (sum === target) {
            return [left + 1, right + 1];
        } else if (sum < target) {
            left++;
        } else {
            right--;
        }
    }
    
    return [];
}

function threeSum(nums: number[]): number[][] {
    nums.sort((a, b) => a - b);
    const result: number[][] = [];
    
    for (let i = 0; i < nums.length - 2; i++) {
        if (i > 0 && nums[i] === nums[i-1]) continue;
        
        let left = i + 1, right = nums.length - 1;
        const targetSum = -nums[i];
        
        while (left < right) {
            const sum = nums[left] + nums[right];
            
            if (sum === targetSum) {
                result.push([nums[i], nums[left], nums[right]]);
                
                while (left < right && nums[left] === nums[left+1]) left++;
                while (left < right && nums[right] === nums[right-1]) right--;
                
                left++;
                right--;
            } else if (sum < targetSum) {
                left++;
            } else {
                right--;
            }
        }
    }
    
    return result;
}

function threeSumClosest(nums: number[], target: number): number {
    nums.sort((a, b) => a - b);
    let closest = Infinity;
    
    for (let i = 0; i < nums.length - 2; i++) {
        let left = i + 1, right = nums.length - 1;
        
        while (left < right) {
            const sum = nums[i] + nums[left] + nums[right];
            
            if (Math.abs(sum - target) < Math.abs(closest - target)) {
                closest = sum;
            }
            
            if (sum < target) {
                left++;
            } else if (sum > target) {
                right--;
            } else {
                return target;
            }
        }
    }
    
    return closest;
}

function fourSum(nums: number[], target: number): number[][] {
    nums.sort((a, b) => a - b);
    const result: number[][] = [];
    const n = nums.length;
    
    for (let i = 0; i < n - 3; i++) {
        if (i > 0 && nums[i] === nums[i-1]) continue;
        
        for (let j = i + 1; j < n - 2; j++) {
            if (j > i + 1 && nums[j] === nums[j-1]) continue;
            
            let left = j + 1, right = n - 1;
            
            while (left < right) {
                const sum = nums[i] + nums[j] + nums[left] + nums[right];
                
                if (sum === target) {
                    result.push([nums[i], nums[j], nums[left], nums[right]]);
                    
                    while (left < right && nums[left] === nums[left+1]) left++;
                    while (left < right && nums[right] === nums[right-1]) right--;
                    
                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
    }
    
    return result;
}

function twoSumLessThanK(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    let left = 0, right = nums.length - 1;
    let maxSum = -1;
    
    while (left < right) {
        const sum = nums[left] + nums[right];
        
        if (sum < k) {
            maxSum = Math.max(maxSum, sum);
            left++;
        } else {
            right--;
        }
    }
    
    return maxSum;
}

// Test
if (require.main === module) {
    console.log("Two Sum:", twoSum([2,7,11,15], 9));
    console.log("Two Sum Sorted:", twoSumSorted([2,7,11,15], 9));
    console.log("3Sum:", threeSum([-1,0,1,2,-1,-4]));
    console.log("3Sum Closest:", threeSumClosest([-1,2,1,-4], 1));
    console.log("4Sum:", fourSum([1,0,-1,0,-2,2], 0));
    console.log("Two Sum Less Than K:", twoSumLessThanK([34,23,1,24,75,33,54,8], 60));
}

export { twoSum, twoSumSorted, threeSum, threeSumClosest, fourSum, twoSumLessThanK };
