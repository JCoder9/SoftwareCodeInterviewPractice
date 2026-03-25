/**
 * Move Zeroes to End - Same Direction Two Pointer Pattern
 * 
 * Problem: Move all zeros to the end of array while maintaining relative order
 *          of non-zero elements.
 * 
 * Pattern: Slow pointer tracks write position for non-zeros, fast pointer scans array.
 * 
 * Time Complexity: O(n) - single pass through array
 * Space Complexity: O(1) - in-place modification
 */

/**
 * Move all zeros to end of array, maintaining order of non-zeros.
 * 
 * @param nums - Array to modify (modified in-place)
 */
function moveZeroes(nums: number[]): void {
    let slow = 0;  // Next position to write non-zero element
    
    // First pass: move all non-zeros to front
    for (let fast = 0; fast < nums.length; fast++) {
        if (nums[fast] !== 0) {
            nums[slow] = nums[fast];
            slow++;
        }
    }
    
    // Second pass: fill rest with zeros
    for (let i = slow; i < nums.length; i++) {
        nums[i] = 0;
    }
}

// Test cases
if (require.main === module) {
    const testCases: number[][] = [
        [0, 1, 0, 3, 12],
        [0],
        [1, 2, 3],
        [0, 0, 1],
        [1, 0, 0, 2, 0, 3],
    ];
    
    for (const nums of testCases) {
        const original = [...nums];
        moveZeroes(nums);
        console.log(`[${original}] -> [${nums}]`);
    }
}

export { moveZeroes };
