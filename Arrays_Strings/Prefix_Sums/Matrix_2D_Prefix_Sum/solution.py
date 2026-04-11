"""
2D Prefix Sum - Matrix Range Sum Query

Problem: Given a matrix, answer multiple queries for sum of rectangular regions efficiently.

Pattern: Build 2D prefix sum where prefix[i][j] = sum of all elements in rectangle
         from (0,0) to (i-1,j-1). Use inclusion-exclusion to query any region in O(1).

Related LeetCode Problems:
- LC 304: Range Sum Query 2D - Immutable (Medium) ⭐⭐⭐
- LC 1074: Number of Submatrices That Sum to Target (Hard)
- LC 1277: Count Square Submatrices with All Ones (Medium)

Time Complexity: O(m×n) to build, O(1) per query
Space Complexity: O(m×n)
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(m×n) time per query | O(1) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force sums all cells in the query rectangle each time
#                  — O(m×n) per query"
#   2. Problem:    "With q queries on 1000×1000 matrix: up to 1 billion operations;
#                  preprocessing once is better"
#   3. Transition: "Build 2D prefix sum in O(m×n), then each query is O(1)
#                  — total O(m×n + q) instead of O(q×m×n)"
#
# def region_sum_naive(matrix, r1, c1, r2, c2):
#     total = 0
#     for i in range(r1, r2 + 1):
#         for j in range(c1, c2 + 1):
#             total += matrix[i][j]
#     return total
# ─────────────────────────────────────────────────────────────────────────────

from typing import List


class Matrix2DPrefixSum:
    """Compute sum of any rectangular submatrix in O(1) time."""
    
    def __init__(self, matrix):
        """Build 2D prefix sum"""
        if not matrix or not matrix[0]:
            self.prefix = []
            return
        
        rows, cols = len(matrix), len(matrix[0])
        # Extra row and column of zeros for easier calculation
        self.prefix = [[0] * (cols + 1) for _ in range(rows + 1)]
        
        for i in range(1, rows + 1):
            for j in range(1, cols + 1):
                self.prefix[i][j] = (matrix[i-1][j-1] + 
                                    self.prefix[i-1][j] + 
                                    self.prefix[i][j-1] - 
                                    self.prefix[i-1][j-1])
    
    def region_sum(self, row1, col1, row2, col2):
        """Sum of rectangle from (row1,col1) to (row2,col2) inclusive"""
        return (self.prefix[row2+1][col2+1] - 
                self.prefix[row1][col2+1] - 
                self.prefix[row2+1][col1] + 
                self.prefix[row1][col1])


# Example
if __name__ == "__main__":
    matrix = [
        [1, 2, 3],
        [4, 5, 6],
        [7, 8, 9]
    ]
    ps2d = Matrix2DPrefixSum(matrix)
    print(ps2d.region_sum(1, 1, 2, 2))  # Sum of bottom-right 2x2 = 5+6+8+9 = 28
    # Maximal square
    square_matrix = [
        ["1", "0", "1", "0", "0"],
        ["1", "0", "1", "1", "1"],
        ["1", "1", "1", "1", "1"],
        ["1", "0", "0", "1", "0"]
    ]
    print("Maximal square area:", maximal_square(square_matrix))  # 4 (2x2)
