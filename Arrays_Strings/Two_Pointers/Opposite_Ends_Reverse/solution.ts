/**
 * Reverse Array/String In-Place - Opposite Ends Two Pointer Pattern
 * 
 * Problem: Reverse an array or string in-place by swapping elements from both ends.
 * 
 * Pattern: Two pointers starting from opposite ends, swapping and moving inward.
 * 
 * Time Complexity: O(n) - single pass through half of array
 * Space Complexity: O(1) - in-place modification
 */

/**
 * Reverse an array in-place using two pointers.
 * 
 * @param chars - Array to reverse (modified in-place)
 */
function reverseArray<T>(chars: T[]): void {
    let l = 0, r = chars.length - 1;
    
    while (l < r) {
        // Swap elements at left and right pointers
        [chars[l], chars[r]] = [chars[r], chars[l]];
        l++;
        r--;
    }
}

/**
 * Reverse a string (creates new string since strings are immutable).
 * 
 * @param s - String to reverse
 * @returns Reversed string
 */
function reverseString(s: string): string {
    const chars = s.split('');
    reverseArray(chars);
    return chars.join('');
}

// Test cases
if (require.main === module) {
    // Test array reversal
    const testArrays = [
        ['h', 'e', 'l', 'l', 'o'],
        ['H', 'a', 'n', 'n', 'a', 'h'],
        ['a'],
        ['a', 'b']
    ];
    
    console.log("Array reversal tests:");
    for (const arr of testArrays) {
        const original = [...arr];
        reverseArray(arr);
        console.log(`  [${original.join(', ')}] -> [${arr.join(', ')}]`);
    }
    
    // Test string reversal
    const testStrings = ["hello", "Hannah", "a", "ab", ""];
    
    console.log("\nString reversal tests:");
    for (const s of testStrings) {
        const result = reverseString(s);
        console.log(`  '${s}' -> '${result}'`);
    }
}

export { reverseArray, reverseString };
