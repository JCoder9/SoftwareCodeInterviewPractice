/**
 * Subarray Sum Equals K - Count Pattern
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

import java.util.*;

public class Solution {
    
    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1);
        
        int count = 0;
        int prefixSum = 0;
        
        for (int num : nums) {
            prefixSum += num;
            
            count += prefixCount.getOrDefault(prefixSum - k, 0);
            
            prefixCount.put(prefixSum, prefixCount.getOrDefault(prefixSum, 0) + 1);
        }
        
        return count;
    }
    
    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0;
        
        int count = 0;
        int product = 1;
        int left = 0;
        
        for (int right = 0; right < nums.length; right++) {
            product *= nums[right];
            
            while (product >= k) {
                product /= nums[left];
                left++;
            }
            
            count += right - left + 1;
        }
        
        return count;
    }
    
    public static boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> remainderMap = new HashMap<>();
        remainderMap.put(0, -1);
        
        int prefixSum = 0;
        
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            
            int remainder = (k != 0) ? prefixSum % k : prefixSum;
            
            if (remainderMap.containsKey(remainder)) {
                if (i - remainderMap.get(remainder) >= 2) {
                    return true;
                }
            } else {
                remainderMap.put(remainder, i);
            }
        }
        
        return false;
    }
    
    public static int findMaxLength(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        countMap.put(0, -1);
        
        int count = 0;
        int maxLength = 0;
        
        for (int i = 0; i < nums.length; i++) {
            count += (nums[i] == 1) ? 1 : -1;
            
            if (countMap.containsKey(count)) {
                maxLength = Math.max(maxLength, i - countMap.get(count));
            } else {
                countMap.put(count, i);
            }
        }
        
        return maxLength;
    }
    
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }
    
    private static Map<Long, Integer> prefixCount;
    
    public static int pathSum(TreeNode root, int targetSum) {
        prefixCount = new HashMap<>();
        prefixCount.put(0L, 1);
        return dfs(root, 0L, targetSum);
    }
    
    private static int dfs(TreeNode node, long currSum, int targetSum) {
        if (node == null) return 0;
        
        currSum += node.val;
        
        int count = prefixCount.getOrDefault(currSum - targetSum, 0);
        
        prefixCount.put(currSum, prefixCount.getOrDefault(currSum, 0) + 1);
        
        count += dfs(node.left, currSum, targetSum);
        count += dfs(node.right, currSum, targetSum);
        
        prefixCount.put(currSum, prefixCount.get(currSum) - 1);
        
        return count;
    }
    
    public static void main(String[] args) {
        System.out.println("Subarray Sum K: " + subarraySum(new int[]{1,1,1}, 2));
        System.out.println("Subarray Product: " + numSubarrayProductLessThanK(new int[]{10,5,2,6}, 100));
        System.out.println("Continuous Subarray Sum: " + checkSubarraySum(new int[]{23,2,4,6,7}, 6));
        System.out.println("Contiguous Array: " + findMaxLength(new int[]{0,1}));
    }
}
