/**
 * Sort + Two Pointers Pattern
 * 
 * Time Complexity: O(n²) typical
 * Space Complexity: O(1)
 */

function twoSum(numbers: number[], target: number): number[] {
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
        if (i > 0 && nums[i] === nums[i - 1]) continue;
        
        let left = i + 1, right = nums.length - 1;
        const target = -nums[i];
        
        while (left < right) {
            const sum = nums[left] + nums[right];
            
            if (sum === target) {
                result.push([nums[i], nums[left], nums[right]]);
                
                while (left < right && nums[left] === nums[left + 1]) left++;
                while (left < right && nums[right] === nums[right - 1]) right--;
                
                left++;
                right--;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
    }
    
    return result;
}

function maxArea(height: number[]): number {
    let left = 0, right = height.length - 1;
    let maxArea = 0;
    
    while (left < right) {
        const area = Math.min(height[left], height[right]) * (right - left);
        maxArea = Math.max(maxArea, area);
        
        if (height[left] < height[right]) {
            left++;
        } else {
            right--;
        }
    }
    
    return maxArea;
}

function trap(height: number[]): number {
    if (height.length === 0) return 0;
    
    let left = 0, right = height.length - 1;
    let leftMax = 0, rightMax = 0;
    let water = 0;
    
    while (left < right) {
        if (height[left] < height[right]) {
            if (height[left] >= leftMax) {
                leftMax = height[left];
            } else {
                water += leftMax - height[left];
            }
            left++;
        } else {
            if (height[right] >= rightMax) {
                rightMax = height[right];
            } else {
                water += rightMax - height[right];
            }
            right--;
        }
    }
    
    return water;
}

// Test
if (require.main === module) {
    console.log("Two Sum:", twoSum([2,7,11,15], 9));
    console.log("3Sum:", threeSum([-1,0,1,2,-1,-4]));
    console.log("Max Area:", maxArea([1,8,6,2,5,4,8,3,7]));
    console.log("Trap Water:", trap([0,1,0,2,1,0,1,3,2,1,2,1]));
}

export { twoSum, threeSum, maxArea, trap };
