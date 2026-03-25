public class KthSmallestMatrix {
    /**
     * Binary Search on Answer - Kth Smallest in Sorted Matrix
     * Time: O(n * log(max - min)), Space: O(1)
     */
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (countLessEqual(matrix, mid) < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    private int countLessEqual(int[][] matrix, int target) {
        int count = 0;
        int row = matrix.length - 1;
        int col = 0;
        
        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] <= target) {
                count += row + 1;
                col++;
            } else {
                row--;
            }
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        KthSmallestMatrix ksm = new KthSmallestMatrix();
        int[][] matrix = {{1, 5, 9}, {10, 11, 13}, {12, 13, 15}};
        System.out.println(ksm.kthSmallest(matrix, 8));  // 13
    }
}
