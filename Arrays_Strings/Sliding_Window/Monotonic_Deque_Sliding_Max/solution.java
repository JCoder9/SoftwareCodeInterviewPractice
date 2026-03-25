/**
 * Sliding Window Maximum - Monotonic Deque Pattern
 * 
 * Problem: Given an array and window size k, find the maximum element in each
 *          sliding window as it moves from left to right.
 * 
 * Pattern: Use a deque to maintain indices of elements in decreasing order of values.
 *          Front of deque always contains index of maximum element in current window.
 * 
 * Time Complexity: O(n) - each element added and removed from deque at most once
 * Space Complexity: O(k) - deque holds at most k elements
 */

import java.util.*;

public class Solution {
    
    /**
     * Find maximum in each sliding window of size k.
     * 
     * @param nums Input array
     * @param k Window size
     * @return Array of maximum values for each window position
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k <= 0 || k > nums.length) {
            return new int[0];  // No valid windows
        }
        
        Deque<Integer> dq = new ArrayDeque<>();  // Stores indices
        int n = nums.length;
        int[] res = new int[n - k + 1];
        int ri = 0;

        for (int i = 0; i < n; i++) {
            // Remove indices of smaller elements from back
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
                dq.pollLast();
            }
            dq.addLast(i);

            // Remove indices outside current window from front
            if (dq.peekFirst() <= i - k) {
                dq.pollFirst();
            }

            // Once we have a full window, record maximum
            if (i >= k - 1) {
                res[ri++] = nums[dq.peekFirst()];
            }
        }
        return res;
    }

    // Test cases
    public static void main(String[] args) {
        Object[][] testCases = {
            {new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3, new int[]{3, 3, 5, 5, 6, 7}},
            {new int[]{1}, 1, new int[]{1}},
            {new int[]{1, -1}, 1, new int[]{1, -1}},
            {new int[]{9, 11}, 2, new int[]{11}},
            {new int[]{4, -2}, 2, new int[]{4}}
        };
        
        for (Object[] test : testCases) {
            int[] nums = (int[]) test[0];
            int k = (int) test[1];
            int[] expected = (int[]) test[2];
            
            int[] result = maxSlidingWindow(nums, k);
            String status = Arrays.equals(result, expected) ? "✓" : "✗";
            System.out.println(status + " maxSlidingWindow(" + Arrays.toString(nums) + 
                             ", k=" + k + ") = " + Arrays.toString(result) + 
                             " (expected " + Arrays.toString(expected) + ")");
        }
    }
}
