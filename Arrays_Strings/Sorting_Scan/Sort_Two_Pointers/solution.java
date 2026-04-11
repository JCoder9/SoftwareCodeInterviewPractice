/**
 * Sort + Two Pointers Pattern
 * 
 * Problem: Find pairs/triplets with specific sum after sorting.
 *          Example: Two Sum in sorted array, 3Sum, 4Sum
 * 
 * Pattern: Sort array first, then use two pointers from opposite ends
 * 
 * Related LeetCode Problems:
 * - LC 15: 3Sum (Medium) ⭐⭐⭐
 * - LC 167: Two Sum II - Input Array Is Sorted (Medium)
 * - LC 16: 3Sum Closest (Medium)
 * 
 * Time Complexity: O(n log n) for sort + O(n²) for 3Sum
 * Space Complexity: O(1) or O(n) for result storage
 */

// ─────────────────────────────────────────────────────────────────────────────
// NAIVE APPROACH (Brute Force) — O(n³) time | O(1) space
// ─────────────────────────────────────────────────────────────────────────────
// INTERVIEW SCRIPT:
//   1. Describe:   "Brute force checks all triplet combinations with 3 nested loops —
//                  O(n³) for 3Sum"
//   2. Problem:    "For n=100: 100³ = 1M comparisons; for n=1000: 1 billion"
//   3. Transition: "Sort first, fix one element, use two pointers for pair — O(n²)"
//
// public List<List<Integer>> threeSumNaive(int[] nums) {
//     List<List<Integer>> result = new ArrayList<>();
//     int n = nums.length;
//     for (int i = 0; i < n; i++) {
//         for (int j = i + 1; j < n; j++) {
//             for (int k = j + 1; k < n; k++) {
//                 if (nums[i] + nums[j] + nums[k] == 0) {
//                     List<Integer> triplet = Arrays.asList(nums[i], nums[j], nums[k]);
//                     Collections.sort(triplet);
//                     if (!result.contains(triplet)) {
//                         result.add(triplet);
//                     }
//                 }
//             }
//         }
//     }
//     return result;
// }
// ─────────────────────────────────────────────────────────────────────────────

import java.util.*;

public class Solution {
    
    public static int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        
        return new int[]{};
    }
    
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            
            int left = i + 1, right = nums.length - 1;
            int target = -nums[i];
            
            while (left < right) {
                int sum = nums[left] + nums[right];
                
                if (sum == target) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    
                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        
        return result;
    }
    
    public static int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;
        
        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(maxArea, area);
            
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        
        return maxArea;
    }
    
    public static int trap(int[] height) {
        if (height.length == 0) return 0;
        
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int water = 0;
        
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    water += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    water += rightMax - height[right];
                }
                right--;
            }
        }
        
        return water;
    }
    
    public static void main(String[] args) {
        System.out.println("Two Sum: " + Arrays.toString(twoSum(new int[]{2,7,11,15}, 9)));
        System.out.println("3Sum: " + threeSum(new int[]{-1,0,1,2,-1,-4}));
        System.out.println("Max Area: " + maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
        System.out.println("Trap Water: " + trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}
