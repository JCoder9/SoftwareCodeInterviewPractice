/**
 * Longest Subarray Sum K - With Negatives
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

import java.util.*;

public class Solution {
    
    public static int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> prefixMap = new HashMap<>();
        prefixMap.put(0, -1);
        
        int prefixSum = 0;
        int maxLength = 0;
        
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            
            if (prefixMap.containsKey(prefixSum - k)) {
                maxLength = Math.max(maxLength, i - prefixMap.get(prefixSum - k));
            }
            
            if (!prefixMap.containsKey(prefixSum)) {
                prefixMap.put(prefixSum, i);
            }
        }
        
        return maxLength;
    }
    
    public static int longestWPI(int[] hours) {
        Map<Integer, Integer> prefixMap = new HashMap<>();
        prefixMap.put(0, -1);
        
        int prefixSum = 0;
        int maxLength = 0;
        
        for (int i = 0; i < hours.length; i++) {
            prefixSum += hours[i] > 8 ? 1 : -1;
            
            if (prefixSum > 0) {
                maxLength = i + 1;
            } else {
                if (prefixMap.containsKey(prefixSum - 1)) {
                    maxLength = Math.max(maxLength, i - prefixMap.get(prefixSum - 1));
                }
            }
            
            if (!prefixMap.containsKey(prefixSum)) {
                prefixMap.put(prefixSum, i);
            }
        }
        
        return maxLength;
    }
    
    public static int numSubmatrixSumTarget(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int count = 0;
        
        for (int top = 0; top < rows; top++) {
            int[] colSums = new int[cols];
            
            for (int bottom = top; bottom < rows; bottom++) {
                for (int c = 0; c < cols; c++) {
                    colSums[c] += matrix[bottom][c];
                }
                
                Map<Integer, Integer> prefixCount = new HashMap<>();
                prefixCount.put(0, 1);
                int prefixSum = 0;
                
                for (int colSum : colSums) {
                    prefixSum += colSum;
                    count += prefixCount.getOrDefault(prefixSum - target, 0);
                    prefixCount.put(prefixSum, prefixCount.getOrDefault(prefixSum, 0) + 1);
                }
            }
        }
        
        return count;
    }
    
    public static double findMaxAverage(int[] nums, int k) {
        int currentSum = 0;
        for (int i = 0; i < k; i++) {
            currentSum += nums[i];
        }
        
        int maxSum = currentSum;
        
        for (int i = k; i < nums.length; i++) {
            currentSum += nums[i] - nums[i - k];
            maxSum = Math.max(maxSum, currentSum);
        }
        
        return (double) maxSum / k;
    }
    
    public static double findMaxAverageII(int[] nums, int k) {
        double left = Integer.MAX_VALUE;
        double right = Integer.MIN_VALUE;
        
        for (int num : nums) {
            left = Math.min(left, num);
            right = Math.max(right, num);
        }
        
        double epsilon = 1e-5;
        
        while (right - left > epsilon) {
            double mid = (left + right) / 2;
            
            if (canAchieveAvg(nums, k, mid)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    private static boolean canAchieveAvg(int[] nums, int k, double targetAvg) {
        int n = nums.length;
        double[] prefix = new double[n + 1];
        
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + (nums[i] - targetAvg);
        }
        
        double minPrefix = 0;
        for (int i = k; i <= n; i++) {
            if (i >= k) {
                minPrefix = Math.min(minPrefix, prefix[i - k]);
            }
            
            if (prefix[i] - minPrefix >= 0) {
                return true;
            }
        }
        
        return false;
    }
    
    public static void main(String[] args) {
        System.out.println("Max Subarray Len: " + maxSubArrayLen(new int[]{1,-1,5,-2,3}, 3));
        System.out.println("Longest WPI: " + longestWPI(new int[]{9,9,6,0,6,6,9}));
        System.out.println("Submatrix Sum: " + numSubmatrixSumTarget(new int[][]{{0,1,0},{1,1,1},{0,1,0}}, 0));
        System.out.println("Max Average I: " + findMaxAverage(new int[]{1,12,-5,-6,50,3}, 4));
        System.out.println("Max Average II: " + findMaxAverageII(new int[]{1,12,-5,-6,50,3}, 4));
    }
}
