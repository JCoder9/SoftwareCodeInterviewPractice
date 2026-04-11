"""
BFS - BFS on Grid/Matrix (Shortest Path, Flood Fill)

Problem: Find shortest path in a grid from start to end, or simultaneously expand from multiple sources.
         Cells are connected via 4 directions (up/down/left/right).

Pattern: BFS naturally gives shortest path. Use queue with (row, col, distance).
         Track visited cells to avoid reprocessing.

Related LeetCode Problems:
- LC 542: 01 Matrix (Medium) ⭐⭐⭐
- LC 994: Rotting Oranges (Medium) ⭐⭐⭐
- LC 1091: Shortest Path in Binary Matrix (Medium) ⭐⭐
- LC 286: Walls and Gates (Medium)

Time Complexity: O(rows × cols) - visit each cell at most once
Space Complexity: O(rows × cols) - queue and visited set
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O((rows×cols)!) time | O(rows×cols) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force explores all possible paths using DFS recursively
#                  — tries every route, exponential paths"
#   2. Problem:    "For 10×10 grid: trillions of possible paths; doesn't guarantee
#                  shortest path without checking all routes"
#   3. Transition: "BFS naturally finds shortest path in first encounter; explores
#                  cells level-by-level — O(rows × cols)"
#
# def shortest_path_naive(grid, start, end):
#     rows, cols = len(grid), len(grid[0])
#     min_dist = [float('inf')]
#     
#     def dfs(r, c, dist, visited):
#         if (r, c) == end:
#             min_dist[0] = min(min_dist[0], dist)
#             return
#         for dr, dc in [(0,1),(1,0),(0,-1),(-1,0)]:
#             nr, nc = r + dr, c + dc
#             if (0 <= nr < rows and 0 <= nc < cols and 
#                 grid[nr][nc] == 0 and (nr, nc) not in visited):
#                 dfs(nr, nc, dist + 1, visited | {(nr, nc)})
#     
#     dfs(start[0], start[1], 0, {start})
#     return min_dist[0] if min_dist[0] != float('inf') else -1
# ─────────────────────────────────────────────────────────────────────────────

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
