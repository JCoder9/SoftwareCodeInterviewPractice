/**
 * Binary Search on Answer - Kth Smallest in Sorted Matrix
 * Time: O(n * log(max - min)), Space: O(1)
 */
function kthSmallest(matrix: number[][], k: number): number {
    function countLessEqual(target: number): number {
        let count = 0;
        let row = matrix.length - 1;
        let col = 0;
        
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
    
    let left = matrix[0][0];
    let right = matrix[matrix.length - 1][matrix[0].length - 1];
    
    while (left < right) {
        const mid = Math.floor(left + (right - left) / 2);
        
        if (countLessEqual(mid) < k) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    
    return left;
}

const matrix = [[1, 5, 9], [10, 11, 13], [12, 13, 15]];
console.log(kthSmallest(matrix, 8));  // 13
