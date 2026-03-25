import java.util.*;

public class Solution {
    /**
     * Heap/K-Way Merge - Smallest Range Covering K Lists
     * Time: O(N log k), Space: O(k)
     */
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            (a, b) -> a[0] - b[0]  // Compare by value
        );
        
        int currentMax = Integer.MIN_VALUE;
        
        // Add first element from each list
        for (int i = 0; i < nums.size(); i++) {
            int val = nums.get(i).get(0);
            minHeap.offer(new int[]{val, i, 0});
            currentMax = Math.max(currentMax, val);
        }
        
        int[] resultRange = {Integer.MIN_VALUE, Integer.MAX_VALUE};
        
        while (minHeap.size() == nums.size()) {
            int[] curr = minHeap.poll();
            int currentMin = curr[0];
            int listIdx = curr[1];
            int elemIdx = curr[2];
            
            // Update result if smaller range found
            if (currentMax - currentMin < resultRange[1] - resultRange[0]) {
                resultRange[0] = currentMin;
                resultRange[1] = currentMax;
            }
            
            // Move to next element in same list
            if (elemIdx + 1 < nums.get(listIdx).size()) {
                int nextVal = nums.get(listIdx).get(elemIdx + 1);
                minHeap.offer(new int[]{nextVal, listIdx, elemIdx + 1});
                currentMax = Math.max(currentMax, nextVal);
            }
        }
        
        return resultRange;
    }
    
    public static void main(String[] args) {
        Solution sol = new Solution();
        List<List<Integer>> nums = Arrays.asList(
            Arrays.asList(4, 10, 15, 24, 26),
            Arrays.asList(0, 9, 12, 20),
            Arrays.asList(5, 18, 22, 30)
        );
        System.out.println(Arrays.toString(sol.smallestRange(nums)));  // [20, 24]
    }
}
