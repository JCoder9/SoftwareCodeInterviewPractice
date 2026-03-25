/**
 * Sort + Binary Search Pattern
 * 
 * Time Complexity: O(n log n) + O(log n) per query
 * Space Complexity: O(1)
 */

function findClosestElements(arr: number[], k: number, x: number): number[] {
    let left = 0, right = arr.length - k;
    
    while (left < right) {
        const mid = Math.floor((left + right) / 2);
        
        if (x - arr[mid] > arr[mid + k] - x) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    
    return arr.slice(left, left + k);
}

function search(nums: number[], target: number): number {
    let left = 0, right = nums.length - 1;
    
    while (left <= right) {
        const mid = Math.floor((left + right) / 2);
        
        if (nums[mid] === target) return mid;
        
        if (nums[left] <= nums[mid]) {
            if (nums[left] <= target && target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        } else {
            if (nums[mid] < target && target <= nums[right]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }
    
    return -1;
}

function findMin(nums: number[]): number {
    let left = 0, right = nums.length - 1;
    
    while (left < right) {
        const mid = Math.floor((left + right) / 2);
        
        if (nums[mid] > nums[right]) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    
    return nums[left];
}

function searchMatrix(matrix: number[][], target: number): boolean {
    if (matrix.length === 0 || matrix[0].length === 0) return false;
    
    const m = matrix.length, n = matrix[0].length;
    let left = 0, right = m * n - 1;
    
    while (left <= right) {
        const mid = Math.floor((left + right) / 2);
        const row = Math.floor(mid / n), col = mid % n;
        
        if (matrix[row][col] === target) {
            return true;
        } else if (matrix[row][col] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    
    return false;
}

// Test
if (require.main === module) {
    console.log("Find K Closest:", findClosestElements([1,2,3,4,5], 4, 3));
    console.log("Search Rotated:", search([4,5,6,7,0,1,2], 0));
    console.log("Find Min:", findMin([3,4,5,1,2]));
    console.log("Search Matrix:", searchMatrix([[1,3,5,7],[10,11,16,20],[23,30,34,60]], 3));
}

export { findClosestElements, search, findMin, searchMatrix };
