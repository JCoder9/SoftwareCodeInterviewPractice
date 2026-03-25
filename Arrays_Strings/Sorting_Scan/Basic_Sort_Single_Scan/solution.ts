/**
 * Basic Sort + Single Scan Pattern
 * 
 * Time Complexity: O(n log n)
 * Space Complexity: O(1) to O(n)
 */

function minimumAbsDifference(arr: number[]): number[][] {
    arr.sort((a, b) => a - b);
    const result: number[][] = [];
    let minDiff = Infinity;
    
    for (let i = 0; i < arr.length - 1; i++) {
        const diff = arr[i + 1] - arr[i];
        if (diff < minDiff) {
            minDiff = diff;
            result.length = 0;
            result.push([arr[i], arr[i + 1]]);
        } else if (diff === minDiff) {
            result.push([arr[i], arr[i + 1]]);
        }
    }
    
    return result;
}

function largestPerimeter(nums: number[]): number {
    nums.sort((a, b) => b - a);
    
    for (let i = 0; i < nums.length - 2; i++) {
        if (nums[i + 1] + nums[i + 2] > nums[i]) {
            return nums[i] + nums[i + 1] + nums[i + 2];
        }
    }
    
    return 0;
}

function arrayPairSum(nums: number[]): number {
    nums.sort((a, b) => a - b);
    let sum = 0;
    for (let i = 0; i < nums.length; i += 2) {
        sum += nums[i];
    }
    return sum;
}

function heightChecker(heights: number[]): number {
    const expected = [...heights].sort((a, b) => a - b);
    return heights.filter((h, i) => h !== expected[i]).length;
}

// Test
if (require.main === module) {
    console.log("Min Abs Diff:", minimumAbsDifference([4,2,1,3]));
    console.log("Largest Perimeter:", largestPerimeter([2,1,2]));
    console.log("Array Pair Sum:", arrayPairSum([1,4,3,2]));
    console.log("Height Checker:", heightChecker([1,1,4,2,1,3]));
}

export { minimumAbsDifference, largestPerimeter, arrayPairSum, heightChecker };
