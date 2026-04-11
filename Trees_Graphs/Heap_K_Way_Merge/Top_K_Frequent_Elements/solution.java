/**
 * Top K Frequent Elements - Min Heap Pattern
 * 
 * Problem: Find k most frequent elements in array.
 *          Example: nums = [1,1,1,2,2,3], k = 2 → [1,2]
 * 
 * Pattern: Use min heap of size k to track top k by frequency
 * 
 * Related LeetCode Problems:
 * - LC 347: Top K Frequent Elements (Medium) ⭐⭐⭐
 * - LC 692: Top K Frequent Words (Medium)
 * - LC 973: K Closest Points to Origin (Medium)
 * 
 * Time Complexity: O(n log k) - n elements, each heap operation O(log k)
 * Space Complexity: O(n) - frequency map storage
 */

// ─────────────────────────────────────────────────────────────────────────────
// NAIVE APPROACH (Brute Force) — O(n log n) time | O(n) space
// ─────────────────────────────────────────────────────────────────────────────
// INTERVIEW SCRIPT:
//   1. Describe:   "Brute force counts frequencies, sorts all pairs by frequency,
//                  returns top k — O(n log n)"
//   2. Problem:    "For 100,000 elements with k=10: sorting 100K vs min heap of 10
//                  (100K log 100K vs 100K log 10)"
//   3. Transition: "Use min heap of size k to track only top k — O(n log k)"
//
// public int[] topKFrequentNaive(int[] nums, int k) {
//     Map<Integer, Integer> count = new HashMap<>();
//     for (int num : nums) {
//         count.put(num, count.getOrDefault(num, 0) + 1);
//     }
//     
//     List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(count.entrySet());
//     entries.sort((a, b) -> b.getValue() - a.getValue());
//     
//     int[] result = new int[k];
//     for (int i = 0; i < k; i++) {
//         result[i] = entries.get(i).getKey();
//     }
//     return result;
// }
// ─────────────────────────────────────────────────────────────────────────────

import java.util.*;

public class Solution {
    /**
     * Heap/K-Way Merge - Top K Frequent Elements
     * Time: O(n log k), Space: O(n)
     */
    public int[] topKFrequent(int[] nums, int k) {
        // Count frequencies
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        
        // Min heap based on frequency
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            (a, b) -> a[0] - b[0]  // Compare by frequency
        );
        
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            minHeap.offer(new int[]{entry.getValue(), entry.getKey()});
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        
        // Extract numbers
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll()[1];
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2)));
        System.out.println(Arrays.toString(sol.topKFrequent(new int[]{4, 4, 4, 2, 2, 3}, 2)));
    }
}
