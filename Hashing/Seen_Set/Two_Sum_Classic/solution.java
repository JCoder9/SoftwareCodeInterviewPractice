/**
 * Two Sum - Classic Complement Search Pattern
 * 
 * Problem: Given an array of integers and a target, find two numbers that add up to the target.
 *          Return the indices of the two numbers.
 * 
 * Pattern: Use hash map to store seen values and check for complement (target - current).
 * 
 * Time Complexity: O(n) - single pass through array
 * Space Complexity: O(n) - hash map stores up to n elements
 */

import java.util.*;

public class Solution {

    // ─────────────────────────────────────────────────────────────────────────
    // NAIVE APPROACH (Brute Force) - O(n²) time | O(1) space
    // ─────────────────────────────────────────────────────────────────────────
    // INTERVIEW SCRIPT:
    //   1. Describe:   "Brute force checks every pair of numbers to see if they
    //                   sum to target — O(n²) time with nested loops"
    //   2. Problem:    "For n=10⁴, we'd do ~10⁸ comparisons; too slow"
    //   3. Transition: "With a hash map we track seen values and check for the
    //                   complement in O(1) — drops to O(n) time"
    //
    // public static int[] twoSumNaive(int[] nums, int target) {
    //     for (int i = 0; i < nums.length; i++) {
    //         for (int j = i + 1; j < nums.length; j++) {
    //             if (nums[i] + nums[j] == target) {
    //                 return new int[]{i, j};
    //             }
    //         }
    //     }
    //     return new int[0];  // No solution found
    // }
    // ─────────────────────────────────────────────────────────────────────────
    
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            
            if (seen.containsKey(complement)) {
                return new int[]{seen.get(complement), i};
            }
            
            seen.put(nums[i], i);
        }
        
        return new int[0];
    }
    
    /**
 * LC 167: Two Sum II - Input Array Is Sorted
 * NOTE: This problem requires 1-indexed positions (not 0-indexed like LC 1)
 */
    public static int[] twoSumSorted(int[] numbers, int target) {
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
        
        return new int[0];
    }
    
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        
        for (int i = 0; i < nums.length - 2; i++) { //-2 as two pointers will be used for the remaining two numbers
            if (i > 0 && nums[i] == nums[i-1]) continue; //ignore duplicates, wont help get to 0
            
            int left = i + 1, right = nums.length - 1;
            int target = -nums[i]; //since we're looking for 0 sum, the target for the two pointers is -nums[i]
            
            while (left < right) {
                int sum = nums[left] + nums[right];
                
                if (sum == target) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    
                    while (left < right && nums[left] == nums[left+1]) left++;
                    while (left < right && nums[right] == nums[right-1]) right--;
                    
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
    
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = Integer.MAX_VALUE;
        
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;
            
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                
                if (Math.abs(sum - target) < Math.abs(closest - target)) {
                    closest = sum;
                }
                
                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    return target;
                }
            }
        }
        
        return closest;
    }
    
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue;
            
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j-1]) continue;
                
                int left = j + 1, right = n - 1;
                
                while (left < right) {
                    long sum = (long)nums[i] + nums[j] + nums[left] + nums[right];
                    
                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        
                        while (left < right && nums[left] == nums[left+1]) left++;
                        while (left < right && nums[right] == nums[right-1]) right--;
                        
                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        
        return result;
    }
    
    public static int twoSumLessThanK(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        int maxSum = -1;
        
        while (left < right) {
            int sum = nums[left] + nums[right];
            
            if (sum < k) {
                maxSum = Math.max(maxSum, sum);
                left++;
            } else {
                right--;
            }
        }
        
        return maxSum;
    }
    
    public static void main(String[] args) {
        System.out.println("Two Sum: " + Arrays.toString(twoSum(new int[]{2,7,11,15}, 9)));
        System.out.println("Two Sum Sorted: " + Arrays.toString(twoSumSorted(new int[]{2,7,11,15}, 9)));
        System.out.println("3Sum: " + threeSum(new int[]{-1,0,1,2,-1,-4}));
        System.out.println("3Sum Closest: " + threeSumClosest(new int[]{-1,2,1,-4}, 1));
        System.out.println("4Sum: " + fourSum(new int[]{1,0,-1,0,-2,2}, 0));
        System.out.println("Two Sum Less Than K: " + twoSumLessThanK(new int[]{34,23,1,24,75,33,54,8}, 60));
    }
}


