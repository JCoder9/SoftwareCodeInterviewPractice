/**
 * Monotonic Stack - Previous Greater Element
 * Time: O(n), Space: O(n)
 */
function previousGreaterElement(nums: number[]): number[] {
    const n = nums.length;
    const result: number[] = new Array(n).fill(-1);
    const stack: number[] = [];
    
    for (let i = 0; i < n; i++) {
        // Remove smaller or equal elements
        while (stack.length > 0 && nums[stack[stack.length - 1]] <= nums[i]) {
            stack.pop();
        }
        
        // Stack top is previous greater
        if (stack.length > 0) {
            result[i] = nums[stack[stack.length - 1]];
        }
        
        stack.push(i);
    }
    
    return result;
}

const nums = [4, 2, 3, 1, 5];
console.log(previousGreaterElement(nums)); // [-1, 4, 4, 3, -1]
