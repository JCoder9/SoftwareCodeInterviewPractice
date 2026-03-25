/**
 * Basic Sort + Single Scan Pattern
 * 
 * Time Complexity: O(n log n)
 * Space Complexity: O(1) to O(n)
 */

import java.util.*;

public class Solution {
    
    public static List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> result = new ArrayList<>();
        int minDiff = Integer.MAX_VALUE;
        
        for (int i = 0; i < arr.length - 1; i++) {
            int diff = arr[i + 1] - arr[i];
            if (diff < minDiff) {
                minDiff = diff;
                result.clear();
                result.add(Arrays.asList(arr[i], arr[i + 1]));
            } else if (diff == minDiff) {
                result.add(Arrays.asList(arr[i], arr[i + 1]));
            }
        }
        
        return result;
    }
    
    public static int largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        
        for (int i = nums.length - 1; i >= 2; i--) {
            if (nums[i - 1] + nums[i - 2] > nums[i]) {
                return nums[i] + nums[i - 1] + nums[i - 2];
            }
        }
        
        return 0;
    }
    
    public static int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i += 2) {
            sum += nums[i];
        }
        return sum;
    }
    
    public static int heightChecker(int[] heights) {
        int[] expected = heights.clone();
        Arrays.sort(expected);
        
        int count = 0;
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] != expected[i]) count++;
        }
        return count;
    }
    
    public static void main(String[] args) {
        System.out.println("Min Abs Diff: " + minimumAbsDifference(new int[]{4,2,1,3}));
        System.out.println("Largest Perimeter: " + largestPerimeter(new int[]{2,1,2}));
        System.out.println("Array Pair Sum: " + arrayPairSum(new int[]{1,4,3,2}));
        System.out.println("Height Checker: " + heightChecker(new int[]{1,1,4,2,1,3}));
    }
}
