/**
 * Find Missing Number - Various Detection Patterns
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1) to O(n) depending on approach
 */

import java.util.*;

public class Solution {
    
    public static int missingNumber(int[] nums) {
        int result = nums.length;
        
        for (int i = 0; i < nums.length; i++) {
            result ^= i ^ nums[i];
        }
        
        return result;
    }
    
    public static int missingNumberMath(int[] nums) {
        int n = nums.length;
        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;
        
        for (int num : nums) {
            actualSum += num;
        }
        
        return expectedSum - actualSum;
    }
    
    public static int firstMissingPositive(int[] nums) {
        int n = nums.length;
        
        boolean containsOne = false;
        for (int num : nums) {
            if (num == 1) {
                containsOne = true;
                break;
            }
        }
        
        if (!containsOne) return 1;
        
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0 || nums[i] > n) {
                nums[i] = 1;
            }
        }
        
        for (int num : nums) {
            int index = Math.abs(num) - 1;
            if (nums[index] > 0) {
                nums[index] = -nums[index];
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        
        return n + 1;
    }
    
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        for (int num : nums) {
            int index = Math.abs(num) - 1;
            if (nums[index] > 0) {
                nums[index] = -nums[index];
            }
        }
        
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result.add(i + 1);
            }
        }
        
        return result;
    }
    
    public static int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];
        
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        
        return slow;
    }
    
    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        
        for (int num : nums) {
            int index = Math.abs(num) - 1;
            
            if (nums[index] < 0) {
                result.add(Math.abs(num));
            } else {
                nums[index] = -nums[index];
            }
        }
        
        return result;
    }
    
    public static int[] findErrorNums(int[] nums) {
        int duplicate = -1;
        
        for (int num : nums) {
            int index = Math.abs(num) - 1;
            
            if (nums[index] < 0) {
                duplicate = Math.abs(num);
            } else {
                nums[index] = -nums[index];
            }
        }
        
        int missing = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                missing = i + 1;
                break;
            }
        }
        
        return new int[]{duplicate, missing};
    }
    
    public static void main(String[] args) {
        System.out.println("Missing Number: " + missingNumber(new int[]{3,0,1}));
        System.out.println("Missing Number (Math): " + missingNumberMath(new int[]{3,0,1}));
        System.out.println("First Missing Positive: " + firstMissingPositive(new int[]{1,2,0}));
        System.out.println("Find Disappeared: " + findDisappearedNumbers(new int[]{4,3,2,7,8,2,3,1}));
        System.out.println("Find Duplicate: " + findDuplicate(new int[]{1,3,4,2,2}));
        System.out.println("Find All Duplicates: " + findDuplicates(new int[]{4,3,2,7,8,2,3,1}));
        System.out.println("Set Mismatch: " + Arrays.toString(findErrorNums(new int[]{1,2,2,4})));
    }
}
