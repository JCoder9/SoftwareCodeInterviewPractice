/**
 * DFS Recursion - Backtracking Pattern
 * 
 * Related LeetCode Problems:
 * - LC 78: Subsets (Medium)
 * - LC 46: Permutations (Medium)
 * - LC 77: Combinations (Medium)
 * - LC 39: Combination Sum (Medium)
 * - LC 17: Letter Combinations of a Phone Number (Medium)
 * 
 * Pattern: Choose → Explore → Un-choose
 * Time Complexity: O(2^n) for subsets, O(n!) for permutations
 * Space Complexity: O(n) for recursion depth
 */

// LC 78: Subsets
export function subsets(nums: number[]): number[][] {
    const result: number[][] = [];
    
    function backtrack(start: number, path: number[]): void {
        result.push([...path]);  // Add copy
        
        for (let i = start; i < nums.length; i++) {
            path.push(nums[i]);  // Choose
            backtrack(i + 1, path);  // Explore
            path.pop();  // Un-choose
        }
    }
    
    backtrack(0, []);
    return result;
}

// LC 46: Permutations (in-place with swapping)
export function permute(nums: number[]): number[][] {
    const result: number[][] = [];
    
    function backtrack(start: number): void {
        if (start === nums.length) {
            result.push([...nums]);
            return;
        }
        
        for (let i = start; i < nums.length; i++) {
            [nums[start], nums[i]] = [nums[i], nums[start]];  // Swap (Choose)
            backtrack(start + 1);  // Explore
            [nums[start], nums[i]] = [nums[i], nums[start]];  // Swap back (Un-choose)
        }
    }
    
    backtrack(0);
    return result;
}

// LC 77: Combinations
export function combine(n: number, k: number): number[][] {
    const result: number[][] = [];
    
    function backtrack(start: number, path: number[]): void {
        if (path.length === k) {
            result.push([...path]);
            return;
        }
        
        for (let i = start; i <= n; i++) {
            path.push(i);
            backtrack(i + 1, path);
            path.pop();
        }
    }
    
    backtrack(1, []);
    return result;
}

// LC 39: Combination Sum
export function combinationSum(candidates: number[], target: number): number[][] {
    const result: number[][] = [];
    
    function backtrack(start: number, path: number[], total: number): void {
        if (total === target) {
            result.push([...path]);
            return;
        }
        if (total > target) {
            return;
        }
        
        for (let i = start; i < candidates.length; i++) {
            path.push(candidates[i]);
            backtrack(i, path, total + candidates[i]);  // i, not i+1 (can reuse)
            path.pop();
        }
    }
    
    backtrack(0, [], 0);
    return result;
}

// LC 17: Letter Combinations of a Phone Number
export function letterCombinations(digits: string): string[] {
    if (!digits) {
        return [];
    }
    
    const phone: Record<string, string> = {
        '2': 'abc', '3': 'def', '4': 'ghi', '5': 'jkl',
        '6': 'mno', '7': 'pqrs', '8': 'tuv', '9': 'wxyz'
    };
    
    const result: string[] = [];
    
    function backtrack(index: number, path: string[]): void {
        if (index === digits.length) {
            result.push(path.join(''));
            return;
        }
        
        const letters = phone[digits[index]];
        for (const letter of letters) {
            path.push(letter);
            backtrack(index + 1, path);
            path.pop();
        }
    }
    
    backtrack(0, []);
    return result;
}

// Test cases
if (require.main === module) {
    // Test subsets
    console.log("Testing subsets:");
    console.log(subsets([1, 2, 3]));
    // [[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]
    
    // Test permute
    console.log("\nTesting permute:");
    console.log(permute([1, 2, 3]));
    // [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
    
    // Test combine
    console.log("\nTesting combine:");
    console.log(combine(4, 2));
    // [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
    
    // Test combinationSum
    console.log("\nTesting combinationSum:");
    console.log(combinationSum([2, 3, 6, 7], 7));
    // [[2,2,3],[7]]
    
    // Test letterCombinations
    console.log("\nTesting letterCombinations:");
    console.log(letterCombinations("23"));
    // ["ad","ae","af","bd","be","bf","cd","ce","cf"]
}
