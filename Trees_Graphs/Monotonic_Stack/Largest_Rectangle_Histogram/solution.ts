/**
 * Monotonic Stack - Largest Rectangle in Histogram
 * Time: O(n), Space: O(n)
 */
function largestRectangleArea(heights: number[]): number {
    const stack: number[] = [];
    let maxArea = 0;
    const n = heights.length;
    
    for (let i = 0; i < n; i++) {
        // Pop taller bars and calculate area
        while (stack.length > 0 && heights[i] < heights[stack[stack.length - 1]]) {
            const heightIdx = stack.pop()!;
            const height = heights[heightIdx];
            const width = stack.length === 0 ? i : i - stack[stack.length - 1] - 1;
            maxArea = Math.max(maxArea, height * width);
        }
        stack.push(i);
    }
    
    // Process remaining bars
    while (stack.length > 0) {
        const heightIdx = stack.pop()!;
        const height = heights[heightIdx];
        const width = stack.length === 0 ? n : n - stack[stack.length - 1] - 1;
        maxArea = Math.max(maxArea, height * width);
    }
    
    return maxArea;
}

const heights = [2, 1, 5, 6, 2, 3];
console.log(largestRectangleArea(heights)); // 10
