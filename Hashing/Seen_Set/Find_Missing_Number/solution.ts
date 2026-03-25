/**
 * Find Missing Number - Various Detection Patterns
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1) to O(n) depending on approach
 */

function missingNumber(nums: number[]): number {
    let result = nums.length;
    
    for (let i = 0; i < nums.length; i++) {
        result ^= i ^ nums[i];
    }
    
    return result;
}

function missingNumberMath(nums: number[]): number {
    const n = nums.length;
    const expectedSum = n * (n + 1) / 2;
    const actualSum = nums.reduce((sum, num) => sum + num, 0);
    
    return expectedSum - actualSum;
}

function firstMissingPositive(nums: number[]): number {
    const n = nums.length;
    
    let containsOne = false;
    for (const num of nums) {
        if (num === 1) {
            containsOne = true;
            break;
        }
    }
    
    if (!containsOne) return 1;
    
    for (let i = 0; i < n; i++) {
        if (nums[i] <= 0 || nums[i] > n) {
            nums[i] = 1;
        }
    }
    
    for (const num of nums) {
        const index = Math.abs(num) - 1;
        if (nums[index] > 0) {
            nums[index] = -nums[index];
        }
    }
    
    for (let i = 0; i < n; i++) {
        if (nums[i] > 0) {
            return i + 1;
        }
    }
    
    return n + 1;
}

function findDisappearedNumbers(nums: number[]): number[] {
    for (const num of nums) {
        const index = Math.abs(num) - 1;
        if (nums[index] > 0) {
            nums[index] = -nums[index];
        }
    }
    
    const result: number[] = [];
    for (let i = 0; i < nums.length; i++) {
        if (nums[i] > 0) {
            result.push(i + 1);
        }
    }
    
    return result;
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

function findDuplicates(nums: number[]): number[] {
    const result: number[] = [];
    
    for (const num of nums) {
        const index = Math.abs(num) - 1;
        
        if (nums[index] < 0) {
            result.push(Math.abs(num));
        } else {
            nums[index] = -nums[index];
        }
    }
    
    return result;
}

function findErrorNums(nums: number[]): number[] {
    let duplicate = -1;
    
    for (const num of nums) {
        const index = Math.abs(num) - 1;
        
        if (nums[index] < 0) {
            duplicate = Math.abs(num);
        } else {
            nums[index] = -nums[index];
        }
    }
    
    let missing = -1;
    for (let i = 0; i < nums.length; i++) {
        if (nums[i] > 0) {
            missing = i + 1;
            break;
        }
    }
    
    return [duplicate, missing];
}

// Test
if (require.main === module) {
    console.log("Missing Number:", missingNumber([3,0,1]));
    console.log("Missing Number (Math):", missingNumberMath([3,0,1]));
    console.log("First Missing Positive:", firstMissingPositive([1,2,0]));
    console.log("Find Disappeared:", findDisappearedNumbers([4,3,2,7,8,2,3,1]));
    console.log("Find Duplicate:", findDuplicate([1,3,4,2,2]));
    console.log("Find All Duplicates:", findDuplicates([4,3,2,7,8,2,3,1]));
    console.log("Set Mismatch:", findErrorNums([1,2,2,4]));
}

export { missingNumber, missingNumberMath, firstMissingPositive, findDisappearedNumbers, findDuplicate, findDuplicates, findErrorNums };
