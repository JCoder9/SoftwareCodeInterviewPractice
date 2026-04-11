/**
 * Heap/K-Way Merge - Kth Largest Element
 * 
 * Problem: Find the kth largest element in an unsorted array.
 *          Example: [3, 2, 1, 5, 6, 4] with k=2 → 5 (second largest)
 * 
 * Pattern: Use min-heap of size k. Keep only k largest elements.
 *          The smallest element in this heap is the kth largest overall.
 * 
 * Related LeetCode Problems:
 * - LC 215: Kth Largest Element in an Array (Medium) ⭐⭐⭐
 * - LC 703: Kth Largest Element in a Stream (Easy)
 * - LC 973: K Closest Points to Origin (Medium)
 * 
 * Time Complexity: O(n log k) - n insertions into heap of size k
 * Space Complexity: O(k) - heap stores k elements
 */

// ─────────────────────────────────────────────────────────────────────────────
// NAIVE APPROACH (Brute Force) — O(n log n) time | O(1) space
// ─────────────────────────────────────────────────────────────────────────────
// INTERVIEW SCRIPT:
//   1. Describe:   "Brute force sorts entire array descending, returns element at
//                  index k-1 — O(n log n) time"
//   2. Problem:    "Sorts all n elements when we only need kth; wasteful for large n
//                  and small k"
//   3. Transition: "Min-heap of size k tracks k largest; O(n log k), better when
//                  k ≪ n"
//
// public int findKthLargestNaive(int[] nums, int k) {
//     Arrays.sort(nums);
//     return nums[nums.length - k];
// }
// ─────────────────────────────────────────────────────────────────────────────

import java.util.PriorityQueue;
    /**
     * Heap/K-Way Merge - Kth Largest Element
     * Time: O(n log k), Space: O(k)
     */
    public int findKthLargest(int[] nums, int k) {
        // Min heap in Java (default PriorityQueue)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for (int num : nums) {
            minHeap.offer(num);
            // Keep only k largest elements
            if (minHeap.size() > k) {
                minHeap.poll();  // Remove smallest
            }
        }
        
        return minHeap.peek();  // Smallest of k largest
    }
    
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));  // 5
    }
}
