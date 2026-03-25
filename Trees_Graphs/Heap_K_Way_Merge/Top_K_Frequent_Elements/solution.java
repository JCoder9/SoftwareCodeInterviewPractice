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
