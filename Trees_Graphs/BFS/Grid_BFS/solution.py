"""
BFS - BFS on Grid/Matrix (Shortest Path, Flood Fill)

Related LeetCode Problems:
- LC 542: 01 Matrix (Medium) - distance to nearest 0
- LC 994: Rotting Oranges (Medium) - multi-source BFS
- LC 1091: Shortest Path in Binary Matrix (Medium)
- LC 286: Walls and Gates (Medium)

Pattern:
- BFS where "neighbors" are adjacent cells (up/down/left/right)
- Common for shortest path in maze, island problems, flood fill
- Use visited set or modify grid to track visited cells

Time Complexity: O(rows × cols)
Space Complexity: O(rows × cols)
"""

from collections import deque


def bfs_grid_shortest_path(grid, start, end):
    """
    Find shortest path in grid
    grid: 0 = walkable, 1 = wall
    start: (row, col)
    end: (row, col)
    """
    if grid[start[0]][start[1]] == 1 or grid[end[0]][end[1]] == 1:
        return -1
    
    rows, cols = len(grid), len(grid[0])
    visited = set([start])
    queue = deque([(start[0], start[1], 0)])  # (row, col, distance)
    
    directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]  # right, down, left, up
    
    while queue:
        r, c, dist = queue.popleft()
        
        if (r, c) == end:
            return dist
        
        for dr, dc in directions:
            nr, nc = r + dr, c + dc
            
            if (0 <= nr < rows and 0 <= nc < cols and 
                grid[nr][nc] == 0 and (nr, nc) not in visited):
                visited.add((nr, nc))
                queue.append((nr, nc, dist + 1))
    
    return -1




if __name__ == "__main__":
    # Test shortest path
    print("Testing shortest path in grid:")
    grid = [[0, 0, 0], [1, 1, 0], [0, 0, 0]]
    print(bfs_grid_shortest_path(grid, (0, 0), (2, 2)))  # 4
