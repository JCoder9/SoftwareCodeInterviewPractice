/**
 * 2D Prefix Sum - Matrix Range Sum Query
 * 
 * Time Complexity: O(m*n) to build, O(1) per query
 * Space Complexity: O(m*n)
 */

public class Solution {
    
    static class Matrix2DPrefixSum {
        private int[][] prefix;
        
        public Matrix2DPrefixSum(int[][] matrix) {
            if (matrix == null || matrix.length == 0) {
                prefix = new int[0][0];
                return;
            }
            
            int rows = matrix.length, cols = matrix[0].length;
            prefix = new int[rows + 1][cols + 1];
            
            for (int i = 1; i <= rows; i++) {
                for (int j = 1; j <= cols; j++) {
                    prefix[i][j] = matrix[i-1][j-1] +
                                  prefix[i-1][j] +
                                  prefix[i][j-1] -
                                  prefix[i-1][j-1];
                }
            }
        }
        
        public int regionSum(int row1, int col1, int row2, int col2) {
            return prefix[row2+1][col2+1] -
                   prefix[row1][col2+1] -
                   prefix[row2+1][col1] +
                   prefix[row1][col1];
        }
    }
    
    public static int countSquareSubmatrices(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        
        Matrix2DPrefixSum ps = new Matrix2DPrefixSum(matrix);
        int rows = matrix.length, cols = matrix[0].length;
        int count = 0;
        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int maxSize = Math.min(rows - r, cols - c);
                for (int size = 1; size <= maxSize; size++) {
                    int area = ps.regionSum(r, c, r + size - 1, c + size - 1);
                    if (area == size * size) {
                        count++;
                    } else {
                        break;
                    }
                }
            }
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        int[][] matrix = {{1,2,3}, {4,5,6}, {7,8,9}};
        Matrix2DPrefixSum ps2d = new Matrix2DPrefixSum(matrix);
        System.out.println("Region sum (1,1) to (2,2): " + ps2d.regionSum(1, 1, 2, 2));
        
        int[][] onesMatrix = {{0,1,1,1}, {1,1,1,1}, {0,1,1,1}};
        System.out.println("Square submatrices: " + countSquareSubmatrices(onesMatrix));
    }
}
