import java.util.PriorityQueue;

public class Solution {
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
