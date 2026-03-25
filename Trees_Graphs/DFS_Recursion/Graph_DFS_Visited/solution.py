"""
Graph DFS with Visited Tracking - Number of Islands (2D Grid DFS)

Pattern: Graph traversal, finding connected components, cycle detection

Time Complexity: O(m × n) - visit each cell at most once
Space Complexity: O(m × n) - worst case recursion depth
"""

def numIslands(grid):
    if not grid:
        return 0
    
    rows, cols = len(grid), len(grid[0])
    islands = 0
    
    def dfs(r, c):
        # Base cases: out of bounds or water or already visited
        if (r < 0 or r >= rows or c < 0 or c >= cols or 
            grid[r][c] == '0'):
            return
        
        # Mark as visited by changing to water
        grid[r][c] = '0'
        
        # Explore all 4 directions
        dfs(r + 1, c)  # down
        dfs(r - 1, c)  # up
        dfs(r, c + 1)  # right
        dfs(r, c - 1)  # left
    
    # Check every cell
    for r in range(rows):
        for c in range(cols):
            if grid[r][c] == '1':
                islands += 1
                dfs(r, c)  # Sink the entire island
    
    return islands

# Usage
if __name__ == "__main__":
    grid = [
        ["1","1","0","0","0"],
        ["1","1","0","0","0"],
        ["0","0","1","0","0"],
        ["0","0","0","1","1"]
    ]
    print(numIslands(grid))  # Output: 3
