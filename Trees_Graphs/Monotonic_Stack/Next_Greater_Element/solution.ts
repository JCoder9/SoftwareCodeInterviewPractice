/**
 * Monotonic Stack - Next Greater Element
 * Time: O(n), Space: O(n)
 */
function nextGreaterElement(nums: number[]): number[] {
    const n = nums.length;
    const result: number[] = new Array(n).fill(-1);
    const stack: number[] = []; // Stack of indices
    
    for (let i = 0; i < n; i++) {
        // Pop smaller elements - nums[i] is their next greater
        while (stack.length > 0 && nums[i] > nums[stack[stack.length - 1]]) {
            const idx = stack.pop()!;
            result[idx] = nums[i];
        }
        stack.push(i);
    }
    
    return result;
}

const nums = [2, 1, 2, 4, 3];
console.log(nextGreaterElement(nums)); // [4, 2, 4, -1, -1]
