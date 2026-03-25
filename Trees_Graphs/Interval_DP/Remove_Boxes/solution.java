public class RemoveBoxes {
    private int[][][] memo;
    
    public int removeBoxes(int[] boxes) {
        int n = boxes.length;
        memo = new int[n][n][n];
        return dp(boxes, 0, n - 1, 0);
    }
    
    private int dp(int[] boxes, int i, int j, int k) {
        if (i > j) return 0;
        if (memo[i][j][k] != 0) return memo[i][j][k];
        
        int origI = i;
        while (i < j && boxes[i] == boxes[i + 1]) {
            i++;
            k++;
        }
        
        int result = (k + 1) * (k + 1) + dp(boxes, i + 1, j, 0);
        
        for (int m = i + 1; m <= j; m++) {
            if (boxes[m] == boxes[i]) {
                result = Math.max(result, 
                    dp(boxes, i + 1, m - 1, 0) + dp(boxes, m, j, k + 1));
            }
        }
        
        memo[origI][j][k] = result;
        return result;
    }
}
