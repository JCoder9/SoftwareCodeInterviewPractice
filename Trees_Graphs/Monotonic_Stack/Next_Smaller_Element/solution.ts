/**
 * Monotonic Stack - Next Smaller Element
 * Time: O(n), Space: O(n)
 */
function nextSmallerElement(nums: number[]): number[] {
    const n = nums.length;
    const result: number[] = new Array(n).fill(-1);
    const stack: number[] = [];
    
    for (let i = 0; i < n; i++) {
        // Pop larger elements
        while (stack.length > 0 && nums[i] < nums[stack[stack.length - 1]]) {
            const idx = stack.pop()!;
            result[idx] = nums[i];
        }
        stack.push(i);
    }
    
    return result;
}

const nums = [4, 2, 1, 5, 3];
console.log(nextSmallerElement(nums)); // [2, 1, -1, 3, -1]
