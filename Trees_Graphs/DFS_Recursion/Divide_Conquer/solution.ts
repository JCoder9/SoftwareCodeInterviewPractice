/**
 * DFS Recursion - Divide and Conquer Pattern
 * 
 * Related Problems:
 * - Merge Sort
 * - Quick Sort
 * - LC 108: Convert Sorted Array to Binary Search Tree (Easy)
 * - LC 215: Kth Largest Element (using Quick Select)
 * 
 * Pattern: Divide → Conquer → Combine
 * Time Complexity: O(n log n) for merge sort
 * Space Complexity: O(n) for merge sort
 */

// Merge Sort
export function mergeSort(arr: number[]): number[] {
    if (arr.length <= 1) {
        return arr;
    }
    
    // Divide
    const mid = Math.floor(arr.length / 2);
    const left = mergeSort(arr.slice(0, mid));
    const right = mergeSort(arr.slice(mid));
    
    // Conquer & Combine
    return merge(left, right);
}

function merge(left: number[], right: number[]): number[] {
    const result: number[] = [];
    let i = 0, j = 0;
    
    while (i < left.length && j < right.length) {
        if (left[i] <= right[j]) {
            result.push(left[i++]);
        } else {
            result.push(right[j++]);
        }
    }
    
    return result.concat(left.slice(i)).concat(right.slice(j));
}

// In-place Merge Sort
export function mergeSortInPlace(arr: number[], left: number, right: number): void {
    if (left >= right) {
        return;
    }
    
    const mid = Math.floor((left + right) / 2);
    mergeSortInPlace(arr, left, mid);
    mergeSortInPlace(arr, mid + 1, right);
    mergeInPlace(arr, left, mid, right);
}

function mergeInPlace(arr: number[], left: number, mid: number, right: number): void {
    const leftPart = arr.slice(left, mid + 1);
    const rightPart = arr.slice(mid + 1, right + 1);
    
    let i = 0, j = 0, k = left;
    
    while (i < leftPart.length && j < rightPart.length) {
        if (leftPart[i] <= rightPart[j]) {
            arr[k++] = leftPart[i++];
        } else {
            arr[k++] = rightPart[j++];
        }
    }
    
    while (i < leftPart.length) {
        arr[k++] = leftPart[i++];
    }
    
    while (j < rightPart.length) {
        arr[k++] = rightPart[j++];
    }
}

// Quick Sort
export function quickSort(arr: number[]): number[] {
    if (arr.length <= 1) {
        return arr;
    }
    
    const pivot = arr[Math.floor(arr.length / 2)];
    const left = arr.filter(x => x < pivot);
    const middle = arr.filter(x => x === pivot);
    const right = arr.filter(x => x > pivot);
    
    return [...quickSort(left), ...middle, ...quickSort(right)];
}

// TreeNode definition
class TreeNode {
    val: number;
    left: TreeNode | null;
    right: TreeNode | null;
    
    constructor(val: number = 0, left: TreeNode | null = null, right: TreeNode | null = null) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

// LC 108: Convert Sorted Array to Binary Search Tree
export function sortedArrayToBST(nums: number[]): TreeNode | null {
    if (!nums || nums.length === 0) {
        return null;
    }
    
    function buildBST(left: number, right: number): TreeNode | null {
        if (left > right) {
            return null;
        }
        
        const mid = Math.floor((left + right) / 2);
        const root = new TreeNode(nums[mid]);
        root.left = buildBST(left, mid - 1);
        root.right = buildBST(mid + 1, right);
        
        return root;
    }
    
    return buildBST(0, nums.length - 1);
}

// LC 215: Kth Largest Element (Quick Select)
export function findKthLargest(nums: number[], k: number): number {
    function quickSelect(left: number, right: number, kSmallest: number): number {
        if (left === right) {
            return nums[left];
        }
        
        const pivotIndex = partition(left, right);
        
        if (kSmallest === pivotIndex) {
            return nums[kSmallest];
        } else if (kSmallest < pivotIndex) {
            return quickSelect(left, pivotIndex - 1, kSmallest);
        } else {
            return quickSelect(pivotIndex + 1, right, kSmallest);
        }
    }
    
    function partition(left: number, right: number): number {
        const pivot = nums[right];
        let i = left;
        
        for (let j = left; j < right; j++) {
            if (nums[j] < pivot) {
                [nums[i], nums[j]] = [nums[j], nums[i]];
                i++;
            }
        }
        
        [nums[i], nums[right]] = [nums[right], nums[i]];
        return i;
    }
    
    // kth largest is (n - k)th smallest
    return quickSelect(0, nums.length - 1, nums.length - k);
}

// Test cases
if (require.main === module) {
    // Test merge sort
    console.log("Testing mergeSort:");
    const arr = [38, 27, 43, 3, 9, 82, 10];
    console.log(`Sorted: ${mergeSort(arr)}`);
    
    // Test in-place merge sort
    console.log("\nTesting mergeSortInPlace:");
    const arr2 = [38, 27, 43, 3, 9, 82, 10];
    mergeSortInPlace(arr2, 0, arr2.length - 1);
    console.log(`Sorted: ${arr2}`);
    
    // Test quick sort
    console.log("\nTesting quickSort:");
    const arr3 = [38, 27, 43, 3, 9, 82, 10];
    console.log(`Sorted: ${quickSort(arr3)}`);
    
    // Test sortedArrayToBST
    console.log("\nTesting sortedArrayToBST:");
    const nums = [-10, -3, 0, 5, 9];
    const root = sortedArrayToBST(nums);
    console.log(`BST root value: ${root?.val}`);  // 0
    
    // Test findKthLargest
    console.log("\nTesting findKthLargest:");
    console.log(`2nd largest in [3,2,1,5,6,4]: ${findKthLargest([3,2,1,5,6,4], 2)}`);  // 5
}
