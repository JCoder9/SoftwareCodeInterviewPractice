import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;

class ArrayElement {
    int value;
    int arrayIndex;
    int elementIndex;
    
    ArrayElement(int value, int arrayIndex, int elementIndex) {
        this.value = value;
        this.arrayIndex = arrayIndex;
        this.elementIndex = elementIndex;
    }
}

public class Solution {
    /**
     * Heap/K-Way Merge - Merge K Sorted Arrays
     * Time: O(N log k), Space: O(k)
     */
    public List<Integer> mergeKArrays(int[][] arrays) {
        PriorityQueue<ArrayElement> minHeap = new PriorityQueue<>(
            (a, b) -> a.value - b.value
        );
        
        // Add first element from each array
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                minHeap.offer(new ArrayElement(arrays[i][0], i, 0));
            }
        }
        
        List<Integer> result = new ArrayList<>();
        
        while (!minHeap.isEmpty()) {
            ArrayElement elem = minHeap.poll();
            result.add(elem.value);
            
            // Add next element from same array
            if (elem.elementIndex + 1 < arrays[elem.arrayIndex].length) {
                int nextVal = arrays[elem.arrayIndex][elem.elementIndex + 1];
                minHeap.offer(new ArrayElement(nextVal, elem.arrayIndex, elem.elementIndex + 1));
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] arrays = {{1, 4, 5}, {1, 3, 4}, {2, 6}};
        System.out.println(sol.mergeKArrays(arrays));  // [1, 1, 2, 3, 4, 4, 5, 6]
    }
}
