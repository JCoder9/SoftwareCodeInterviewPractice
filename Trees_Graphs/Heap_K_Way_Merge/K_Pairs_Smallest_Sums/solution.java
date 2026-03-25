import java.util.*;

public class Solution {
    /**
     * Heap/K-Way Merge - K Pairs with Smallest Sums
     * Time: O(k log k), Space: O(k)
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0) return result;
        
        // Min heap: [sum, i, j]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            (a, b) -> a[0] - b[0]
        );
        
        Set<String> visited = new HashSet<>();
        
        minHeap.offer(new int[]{nums1[0] + nums2[0], 0, 0});
        visited.add("0,0");
        
        while (!minHeap.isEmpty() && result.size() < k) {
            int[] curr = minHeap.poll();
            int i = curr[1];
            int j = curr[2];
            
            result.add(Arrays.asList(nums1[i], nums2[j]));
            
            // Add next pairs
            if (i + 1 < nums1.length && !visited.contains((i + 1) + "," + j)) {
                minHeap.offer(new int[]{nums1[i + 1] + nums2[j], i + 1, j});
                visited.add((i + 1) + "," + j);
            }
            
            if (j + 1 < nums2.length && !visited.contains(i + "," + (j + 1))) {
                minHeap.offer(new int[]{nums1[i] + nums2[j + 1], i, j + 1});
                visited.add(i + "," + (j + 1));
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.kSmallestPairs(new int[]{1, 7, 11}, new int[]{2, 4, 6}, 3));
        System.out.println(sol.kSmallestPairs(new int[]{1, 1, 2}, new int[]{1, 2, 3}, 2));
    }
}
