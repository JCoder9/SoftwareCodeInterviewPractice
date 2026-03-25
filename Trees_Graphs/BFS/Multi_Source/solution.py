"""
Multi-Source BFS

Pattern: Start BFS from multiple sources simultaneously

Time Complexity: O(rows × cols) for grids
Space Complexity: O(rows × cols)
"""

from collections import deque

def multi_source_bfs(grid):
    """
    Example: Find time for all oranges to rot
    0 = empty, 1 = fresh orange, 2 = rotten orange
    """
    rows, cols = len(grid), len(grid[0])
    queue = deque()
    fresh_count = 0
    
    # Add ALL rotten oranges to queue at once
    for r in range(rows):
        for c in range(cols):
            if grid[r][c] == 2:
                queue.append((r, c, 0))  # (row, col, time)
            elif grid[r][c] == 1:
                fresh_count += 1
    
    if fresh_count == 0:
        return 0
    
    directions = [(0,1), (1,0), (0,-1), (-1,0)]
    max_time = 0
    
    while queue:
        r, c, time = queue.popleft()
        max_time = max(max_time, time)
        
        for dr, dc in directions:
            nr, nc = r + dr, c + dc
            
            # If fresh orange, make it rotten
            if 0 <= nr < rows and 0 <= nc < cols and grid[nr][nc] == 1:
                grid[nr][nc] = 2
                fresh_count -= 1
                queue.append((nr, nc, time + 1))
    
    return max_time if fresh_count == 0 else -1

# Usage
if __name__ == "__main__":
    grid = [
        [2, 1, 1],
        [1, 1, 0],
        [0, 1, 1]
    ]
    print(multi_source_bfs(grid))  # Output: 4
