/**
 * Monotonic Stack - Trapping Rain Water
 * Time: O(n), Space: O(n)
 */
function trap(height: number[]): number {
    if (!height || height.length === 0) return 0;
    
    const n = height.length;
    const leftMax: number[] = new Array(n);
    const rightMax: number[] = new Array(n);
    
    // Build left max array
    leftMax[0] = height[0];
    for (let i = 1; i < n; i++) {
        leftMax[i] = Math.max(leftMax[i-1], height[i]);
    }
    
    // Build right max array
    rightMax[n-1] = height[n-1];
    for (let i = n-2; i >= 0; i--) {
        rightMax[i] = Math.max(rightMax[i+1], height[i]);
    }
    
    // Calculate water
    let water = 0;
    for (let i = 0; i < n; i++) {
        water += Math.min(leftMax[i], rightMax[i]) - height[i];
    }
    
    return water;
}

// Stack-based solution
function trapStack(height: number[]): number {
    const stack: number[] = [];
    let water = 0;
    
    for (let i = 0; i < height.length; i++) {
        while (stack.length > 0 && height[i] > height[stack[stack.length - 1]]) {
            const bottom = stack.pop()!;
            
            if (stack.length === 0) break;
            
            const distance = i - stack[stack.length - 1] - 1;
            const boundedHeight = Math.min(height[i], 
                                          height[stack[stack.length - 1]]) 
                                 - height[bottom];
            water += distance * boundedHeight;
        }
        stack.push(i);
    }
    
    return water;
}

const heights = [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1];
console.log(trap(heights));       // 6
console.log(trapStack(heights));  // 6
