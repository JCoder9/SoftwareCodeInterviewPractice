"""Binary Search on Answer - Kth Smallest in Sorted Matrix (LC 378)
Find kth smallest element in n x n matrix where rows/columns are sorted.
Count elements <= X efficiently. Binary search on value until we find kth.
Time: O(n * log(max - min)), Space: O(1)"""

def kthSmallest(matrix, k):
    def count_less_equal(target):
        # Count how many elements <= target
        count = 0
        row = len(matrix) - 1  # Start from bottom-left
        col = 0
        
        while row >= 0 and col < len(matrix[0]):
            if matrix[row][col] <= target:
                count += row + 1  # All elements in this column up to row
                col += 1
            else:
                row -= 1
        
        return count
    
    # Search space: [smallest element, largest element]
    left = matrix[0][0]
    right = matrix[-1][-1]
    
    while left < right:
        mid = left + (right - left) // 2
        
        if count_less_equal(mid) < k:
            # Not enough elements <= mid, answer is larger
            left = mid + 1
        else:
            # Enough elements, answer could be mid or smaller
            right = mid
    
    return left

if __name__ == "__main__":
    matrix = [[1, 5, 9], [10, 11, 13], [12, 13, 15]]
    print(kthSmallest(matrix, 8))  # 13
