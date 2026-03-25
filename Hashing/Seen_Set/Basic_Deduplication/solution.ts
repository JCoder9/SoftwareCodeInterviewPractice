/**
 * Basic Deduplication - Remove Duplicates Preserving Order
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

function removeDuplicates(arr: number[]): number[] {
    const seen = new Set<number>();
    const result: number[] = [];
    
    for (const num of arr) {
        if (!seen.has(num)) {
            seen.add(num);
            result.push(num);
        }
    }
    
    return result;
}

function removeDuplicateLetters(s: string): string {
    const lastOccurrence = new Map<string, number>();
    for (let i = 0; i < s.length; i++) {
        lastOccurrence.set(s[i], i);
    }
    
    const seen = new Set<string>();
    const stack: string[] = [];
    
    for (let i = 0; i < s.length; i++) {
        const c = s[i];
        
        if (seen.has(c)) continue;
        
        while (stack.length > 0 && stack[stack.length - 1] > c && 
               lastOccurrence.get(stack[stack.length - 1])! > i) {
            seen.delete(stack.pop()!);
        }
        
        stack.push(c);
        seen.add(c);
    }
    
    return stack.join('');
}

function findDuplicate(nums: number[]): number {
    let slow = nums[0];
    let fast = nums[0];
    
    do {
        slow = nums[slow];
        fast = nums[nums[fast]];
    } while (slow !== fast);
    
    slow = nums[0];
    while (slow !== fast) {
        slow = nums[slow];
        fast = nums[fast];
    }
    
    return slow;
}

function removeDuplicatesSorted(nums: number[]): number {
    if (nums.length === 0) return 0;
    
    let writeIdx = 1;
    
    for (let i = 1; i < nums.length; i++) {
        if (nums[i] !== nums[i - 1]) {
            nums[writeIdx] = nums[i];
            writeIdx++;
        }
    }
    
    return writeIdx;
}

// Test
if (require.main === module) {
    console.log("Remove Duplicates:", removeDuplicates([1,2,2,3,1,4]));
    console.log("Remove Duplicate Letters:", removeDuplicateLetters("bcabc"));
    console.log("Find Duplicate:", findDuplicate([1,3,4,2,2]));
    
    const nums = [1,1,2];
    const k = removeDuplicatesSorted(nums);
    console.log(`Remove Duplicates Sorted: Length=${k}, Array=${nums.slice(0, k)}`);
}

export { removeDuplicates, removeDuplicateLetters, findDuplicate, removeDuplicatesSorted };
