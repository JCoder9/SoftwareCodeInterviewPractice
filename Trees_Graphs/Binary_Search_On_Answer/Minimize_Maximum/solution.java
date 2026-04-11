/**
 * Binary Search on Answer - Minimize the Maximum (LC 410)
 * 
 * Problem: Split Array Largest Sum - Split into k subarrays minimizing max sum.
 *          Example: nums = [7,2,5,10,8], k = 2 → 18 ([7,2,5,10] and [8])
 * 
 * Pattern: If we can split with max_sum=X, we can with X+1. Binary search for min X.
 * 
 * Related LeetCode Problems:
 * - LC 410: Split Array Largest Sum (Hard) ⭐⭐⭐
 * - LC 1011: Capacity To Ship Packages Within D Days (Medium)
 * - LC 1231: Divide Chocolate (Hard)
 * 
 * Time Complexity: O(n × log(sum - max)) - n validations, log search space
 * Space Complexity: O(1)
 */

// ─────────────────────────────────────────────────────────────────────────────
// NAIVE APPROACH (Brute Force) — O(2^n) time | O(n) space
// ─────────────────────────────────────────────────────────────────────────────
// INTERVIEW SCRIPT:
//   1. Describe:   "Brute force tries all ways to place k-1 dividers — C(n-1,k-1)
//                  exponential combinations"
//   2. Problem:    "For n=100, k=50: ~10^29 combinations to check"
//   3. Transition: "Binary search on answer with greedy validation — O(n log sum)"
//
// // Conceptual - would use dynamic programming or backtracking to generate splits
// public int splitArrayNaive(int[] nums, int k) {
//     int minMaxSum = Integer.MAX_VALUE;
//     // Try all ways to split array into k parts (exponential)
//     // For each split, compute max sum among parts
//     // Track minimum of all max sums
//     return minMaxSum;
// }
// ─────────────────────────────────────────────────────────────────────────────

    /**
     * Binary Search on Answer - Minimize the Maximum
     * Time: O(n * log(sum - max)), Space: O(1)
     */
    public int splitArray(int[] nums, int k) {
        int left = 0;
        int right = 0;
        
        for (int num : nums) {
            left = Math.max(left, num);
            right += num;
        }
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (canSplit(nums, k, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return left;
    }
    
    private boolean canSplit(int[] nums, int k, int maxSum) {
        int groups = 1;
        int currentSum = 0;
        
        for (int num : nums) {
            if (currentSum + num > maxSum) {
                groups++;
                currentSum = num;
                if (groups > k) {
                    return false;
                }
            } else {
                currentSum += num;
            }
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        SplitArray sa = new SplitArray();
        System.out.println(sa.splitArray(new int[]{7, 2, 5, 10, 8}, 2));  // 18
    }
}
