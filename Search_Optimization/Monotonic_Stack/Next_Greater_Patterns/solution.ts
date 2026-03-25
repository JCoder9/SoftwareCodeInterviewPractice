/**
 * Monotonic Stack - Next Greater/Smaller Elements
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

function nextGreaterElements(nums: number[]): number[] {
    const n = nums.length;
    const result = new Array(n).fill(-1);
    const stack: number[] = [];
    
    for (let i = 0; i < n; i++) {
        while (stack.length > 0 && nums[stack[stack.length - 1]] < nums[i]) {
            result[stack.pop()!] = nums[i];
        }
        stack.push(i);
    }
    
    return result;
}

function dailyTemperatures(temperatures: number[]): number[] {
    const n = temperatures.length;
    const result = new Array(n).fill(0);
    const stack: number[] = [];
    
    for (let i = 0; i < n; i++) {
        while (stack.length > 0 && temperatures[stack[stack.length - 1]] < temperatures[i]) {
            const prevIdx = stack.pop()!;
            result[prevIdx] = i - prevIdx;
        }
        stack.push(i);
    }
    
    return result;
}

function largestRectangleHistogram(heights: number[]): number {
    const stack: number[] = [];
    let maxArea = 0;
    
    for (let i = 0; i <= heights.length; i++) {
        const h = (i === heights.length) ? 0 : heights[i];
        
        while (stack.length > 0 && heights[stack[stack.length - 1]] > h) {
            const height = heights[stack.pop()!];
            const width = stack.length === 0 ? i : i - stack[stack.length - 1] - 1;
            maxArea = Math.max(maxArea, height * width);
        }
        
        stack.push(i);
    }
    
    return maxArea;
}

function trapRainWater(height: number[]): number {
    const stack: number[] = [];
    let water = 0;
    
    for (let i = 0; i < height.length; i++) {
        while (stack.length > 0 && height[stack[stack.length - 1]] < height[i]) {
            const bottom = stack.pop()!;
            
            if (stack.length === 0) break;
            
            const distance = i - stack[stack.length - 1] - 1;
            const boundedHeight = Math.min(height[stack[stack.length - 1]], height[i]) - height[bottom];
            water += distance * boundedHeight;
        }
        
        stack.push(i);
    }
    
    return water;
}

// Test
if (require.main === module) {
    console.log("Next greater:", nextGreaterElements([2, 1, 2, 4, 3]));
    console.log("Daily temperatures:", dailyTemperatures([73, 74, 75, 71, 69, 72, 76, 73]));
    console.log("Largest rectangle:", largestRectangleHistogram([2, 1, 5, 6, 2, 3]));
    console.log("Trap rain water:", trapRainWater([0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]));
}

export { nextGreaterElements, dailyTemperatures, largestRectangleHistogram, trapRainWater };
