/**
 * 2D Prefix Sum - Matrix Range Sum Query
 * 
 * Time Complexity: O(m*n) to build, O(1) per query
 * Space Complexity: O(m*n)
 */

class Matrix2DPrefixSum {
    private prefix: number[][];
    
    constructor(matrix: number[][]) {
        if (!matrix || matrix.length === 0) {
            this.prefix = [];
            return;
        }
        
        const rows = matrix.length, cols = matrix[0].length;
        this.prefix = Array(rows + 1).fill(0)
            .map(() => Array(cols + 1).fill(0));
        
        for (let i = 1; i <= rows; i++) {
            for (let j = 1; j <= cols; j++) {
                this.prefix[i][j] = matrix[i-1][j-1] +
                                   this.prefix[i-1][j] +
                                   this.prefix[i][j-1] -
                                   this.prefix[i-1][j-1];
            }
        }
    }
    
    regionSum(row1: number, col1: number, row2: number, col2: number): number {
        return this.prefix[row2+1][col2+1] -
               this.prefix[row1][col2+1] -
               this.prefix[row2+1][col1] +
               this.prefix[row1][col1];
    }
}

function countSquareSubmatrices(matrix: number[][]): number {
    if (!matrix || matrix.length === 0) return 0;
    
    const ps = new Matrix2DPrefixSum(matrix);
    const rows = matrix.length, cols = matrix[0].length;
    let count = 0;
    
    for (let r = 0; r < rows; r++) {
        for (let c = 0; c < cols; c++) {
            const maxSize = Math.min(rows - r, cols - c);
            for (let size = 1; size <= maxSize; size++) {
                const area = ps.regionSum(r, c, r + size - 1, c + size - 1);
                if (area === size * size) {
                    count++;
                } else {
                    break;
                }
            }
        }
    }
    
    return count;
}

// Test
if (require.main === module) {
    const matrix = [[1,2,3], [4,5,6], [7,8,9]];
    const ps2d = new Matrix2DPrefixSum(matrix);
    console.log("Region sum (1,1) to (2,2):", ps2d.regionSum(1, 1, 2, 2));
    
    const onesMatrix = [[0,1,1,1], [1,1,1,1], [0,1,1,1]];
    console.log("Square submatrices:", countSquareSubmatrices(onesMatrix));
}

export { Matrix2DPrefixSum, countSquareSubmatrices };
