import java.util.PriorityQueue;

class MatrixElement {
    int value;
    int row;
    int col;
    
    MatrixElement(int value, int row, int col) {
        this.value = value;
        this.row = row;
        this.col = col;
    }
}

public class Solution {
    /**
     * Heap/K-Way Merge - Kth Smallest in Sorted Matrix
     * Time: O(k log n), Space: O(n)
     */
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<MatrixElement> minHeap = new PriorityQueue<>(
            (a, b) -> a.value - b.value
        );
        
        // Add first element from each row
        for (int r = 0; r < Math.min(k, n); r++) {
            minHeap.offer(new MatrixElement(matrix[r][0], r, 0));
        }
        
        int result = 0;
        for (int i = 0; i < k; i++) {
            MatrixElement elem = minHeap.poll();
            result = elem.value;
            
            // Add next element from same row
            if (elem.col + 1 < n) {
                minHeap.offer(new MatrixElement(
                    matrix[elem.row][elem.col + 1], 
                    elem.row, 
                    elem.col + 1
                ));
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] matrix = {{1, 5, 9}, {10, 11, 13}, {12, 13, 15}};
        System.out.println(sol.kthSmallest(matrix, 8));  // 13
    }
}
