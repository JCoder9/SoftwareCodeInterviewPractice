/**
 * Remove Duplicates from Sorted Array - Same Direction Two Pointer Pattern
 * 
 * Problem: Remove duplicates from a sorted array in-place, keeping each unique value once.
 *          Return the new length.
 * 
 * Pattern: Slow pointer tracks write position, fast pointer scans array.
 * 
 * Time Complexity: O(n) - single pass through array
 * Space Complexity: O(1) - in-place modification
 */

/**
 * Remove duplicates from sorted array in-place.
 * 
 * @param nums - Sorted array (modified in-place)
 * @returns New length of array with unique elements
 */
function removeDuplicates(nums: number[]): number {
    if (nums.length === 0) {
        return 0;
    }

    let slow = 1;  // Next write position (first element always stays)
    
    for (let fast = 1; fast < nums.length; fast++) {
        // If current element is different from previous, keep it
        if (nums[fast] !== nums[fast - 1]) {
            nums[slow] = nums[fast];
            slow++;
        }
    }
    
    return slow;
}

// ─────────────────────────────────────────────────────────────────────────────
// VARIANT 1: Return a new array  (O(n) space)
// Asked as: "return the deduplicated array" / "don't modify the input"
// ─────────────────────────────────────────────────────────────────────────────
function removeDuplicatesReturnNew(nums: number[]): number[] {
    if (nums.length === 0) return [];

    const result: number[] = [nums[0]];
    for (let i = 1; i < nums.length; i++) {
        if (nums[i] !== nums[i - 1]) {
            result.push(nums[i]);
        }
    }

    return result;
}

// ─────────────────────────────────────────────────────────────────────────────
// VARIANT 2: In-place, return the array  (O(1) space)
// Asked as: "return the modified array" while still doing it in-place.
// Same two-pointer logic; splice to trim the tail, then return the array.
// ─────────────────────────────────────────────────────────────────────────────
function removeDuplicatesInplaceReturnArray(nums: number[]): number[] {
    if (nums.length === 0) return nums;

    let slow = 1;
    for (let fast = 1; fast < nums.length; fast++) {
        if (nums[fast] !== nums[fast - 1]) {
            nums[slow] = nums[fast];
            slow++;
        }
    }

    nums.splice(slow); // Trim excess elements in-place
    return nums;
}

// Test cases
if (require.main === module) {
    const testCases: [number[], number][] = [
        [[1, 1, 2], 2],
        [[0, 0, 1, 1, 1, 2, 2, 3, 3, 4], 5],
        [[1], 1],
        [[1, 2, 3], 3],
    ];
    
    for (const [nums, expectedLen] of testCases) {
        const original = [...nums];
        const length = removeDuplicates(nums);
        const uniqueVals = nums.slice(0, length);
        const status = length === expectedLen ? "✓" : "✗";
        console.log(`${status} [${original}] -> length=${length}, values=[${uniqueVals}]`);
    }

    console.log("\n--- Variant 1: return new array ---");
    const v1Cases: number[][] = [[1, 1, 2], [0, 0, 1, 1, 1, 2, 2, 3, 3, 4], [1], [1, 2, 3]];
    for (const nums of v1Cases) {
        const result = removeDuplicatesReturnNew(nums);
        console.log(`[${nums}] -> [${result}]`);
    }

    console.log("\n--- Variant 2: in-place, return array ---");
    for (const nums of v1Cases) {
        const result = removeDuplicatesInplaceReturnArray([...nums]);
        console.log(`[${nums}] -> [${result}]`);
    }
}

export { removeDuplicates, removeDuplicatesReturnNew, removeDuplicatesInplaceReturnArray };
