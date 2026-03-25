"""Interval DP - Remove Boxes (LC 546)
3D DP: dp[i][j][k] = max points from boxes[i:j+1] with k extra boxes 
of same color as boxes[i] to the left.
Try: remove boxes[i] immediately OR find matching color later to merge.
Time: O(n⁴), Space: O(n³)"""

def removeBoxes(boxes: list[int]) -> int:
    n = len(boxes)
    memo = {}
    
    def dp(i: int, j: int, k: int) -> int:
        if i > j:
            return 0
        if (i, j, k) in memo:
            return memo[(i, j, k)]
        
        # Optimize: merge consecutive same-color boxes at start
        while i < j and boxes[i] == boxes[i + 1]:
            i += 1
            k += 1
        
        # Option 1: Remove boxes[i] with k extra boxes
        result = (k + 1) ** 2 + dp(i + 1, j, 0)
        
        # Option 2: Find matching color m and merge
        for m in range(i + 1, j + 1):
            if boxes[m] == boxes[i]:
                result = max(result, dp(i + 1, m - 1, 0) + dp(m, j, k + 1))
        
        memo[(i, j, k)] = result
        return result
    
    return dp(0, n - 1, 0)

if __name__ == "__main__":
    print(removeBoxes([1, 3, 2, 2, 2, 3, 4, 3, 1]))  # 23
