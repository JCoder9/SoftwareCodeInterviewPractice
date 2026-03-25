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

import java.util.*;

public class DivideConquer {
    
    // Merge Sort
    public int[] mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        
        // Divide
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        
        // Conquer
        left = mergeSort(left);
        right = mergeSort(right);
        
        // Combine
        return merge(left, right);
    }
    
    private int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;
        
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }
        
        while (i < left.length) {
            result[k++] = left[i++];
        }
        
        while (j < right.length) {
            result[k++] = right[j++];
        }
        
        return result;
    }
    
    // In-place Merge Sort
    public void mergeSortInPlace(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        
        int mid = (left + right) / 2;
        mergeSortInPlace(arr, left, mid);
        mergeSortInPlace(arr, mid + 1, right);
        mergeInPlace(arr, left, mid, right);
    }
    
    private void mergeInPlace(int[] arr, int left, int mid, int right) {
        int[] leftPart = Arrays.copyOfRange(arr, left, mid + 1);
        int[] rightPart = Arrays.copyOfRange(arr, mid + 1, right + 1);
        
        int i = 0, j = 0, k = left;
        
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
    
    // TreeNode definition
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    // LC 108: Convert Sorted Array to Binary Search Tree
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return buildBST(nums, 0, nums.length - 1);
    }
    
    private TreeNode buildBST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildBST(nums, left, mid - 1);
        root.right = buildBST(nums, mid + 1, right);
        
        return root;
    }
    
    // LC 215: Kth Largest Element (Quick Select)
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }
    
    private int quickSelect(int[] nums, int left, int right, int kSmallest) {
        if (left == right) {
            return nums[left];
        }
        
        int pivotIndex = partition(nums, left, right);
        
        if (kSmallest == pivotIndex) {
            return nums[kSmallest];
        } else if (kSmallest < pivotIndex) {
            return quickSelect(nums, left, pivotIndex - 1, kSmallest);
        } else {
            return quickSelect(nums, pivotIndex + 1, right, kSmallest);
        }
    }
    
    private int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int i = left;
        
        for (int j = left; j < right; j++) {
            if (nums[j] < pivot) {
                swap(nums, i, j);
                i++;
            }
        }
        
        swap(nums, i, right);
        return i;
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    // Test
    public static void main(String[] args) {
        DivideConquer solution = new DivideConquer();
        
        // Test merge sort
        System.out.println("Testing mergeSort:");
        int[] arr = {38, 27, 43, 3, 9, 82, 10};
        int[] sorted = solution.mergeSort(arr);
        System.out.println("Sorted: " + Arrays.toString(sorted));
        
        // Test in-place merge sort
        System.out.println("\nTesting mergeSortInPlace:");
        int[] arr2 = {38, 27, 43, 3, 9, 82, 10};
        solution.mergeSortInPlace(arr2, 0, arr2.length - 1);
        System.out.println("Sorted: " + Arrays.toString(arr2));
        
        // Test sortedArrayToBST
        System.out.println("\nTesting sortedArrayToBST:");
        int[] nums = {-10, -3, 0, 5, 9};
        TreeNode root = solution.sortedArrayToBST(nums);
        System.out.println("BST root value: " + root.val);  // 0
        
        // Test findKthLargest
        System.out.println("\nTesting findKthLargest:");
        System.out.println("2nd largest in [3,2,1,5,6,4]: " + 
                           solution.findKthLargest(new int[]{3,2,1,5,6,4}, 2));  // 5
    }
}
