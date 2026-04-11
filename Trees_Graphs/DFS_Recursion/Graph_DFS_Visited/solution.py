"""
Graph DFS with Visited Tracking - Number of Islands

Problem: Given m×n 2D grid of '1's (land) and '0's (water), count number of islands.
         An island is surrounded by water and formed by connecting adjacent lands horizontally/vertically.

Pattern: DFS to explore each connected component. Mark visited cells to avoid revisiting.
         Each DFS call sinks an entire island.

Related LeetCode Problems:
- LC 200: Number of Islands (Medium) ⭐⭐⭐
- LC 695: Max Area of Island (Medium) ⭐⭐
- LC 733: Flood Fill (Easy)
- LC 130: Surrounded Regions (Medium)

Time Complexity: O(m × n) - visit each cell at most once
Space Complexity: O(m × n) - worst case recursion depth
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(m × n × cells) time | O(m × n) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force: for each cell, do BFS/DFS to count all its connected
#                  cells, mark them in separate visited set — redundant work"
#   2. Problem:    "Each cell potentially explored multiple times; for dense grid
#                  with one island: O((m×n)²) operations"
#   3. Transition: "Mutate grid as we go (mark '1' → '0') to track visited in-place
#                  — each cell visited exactly once: O(m × n)"
#
# def numIslands_naive(grid):
#     if not grid:
#         return 0
#     rows, cols = len(grid), len(grid[0])
#     islands = 0
#     global_visited = set()
#     
#     def explore_island(r, c):
#         # Separate visited set per island - inefficient!
#         if (r, c) in global_visited:
#             return 0
#         visited = set()
#         stack = [(r, c)]
#         count = 0
#         while stack:
#             cr, cc = stack.pop()
#             if (cr, cc) in visited or grid[cr][cc] == '0':
#                 continue
#             visited.add((cr, cc))
#             global_visited.add((cr, cc))
#             count += 1
#             for dr, dc in [(0,1),(1,0),(0,-1),(-1,0)]:
#                 nr, nc = cr + dr, cc + dc
#                 if 0 <= nr < rows and 0 <= nc < cols:
#                     stack.append((nr, nc))
#         return count
#     
#     for r in range(rows):
#         for c in range(cols):
#             if grid[r][c] == '1' and (r, c) not in global_visited:
#                 if explore_island(r, c) > 0:
#                     islands += 1
#     return islands
# ─────────────────────────────────────────────────────────────────────────────

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
