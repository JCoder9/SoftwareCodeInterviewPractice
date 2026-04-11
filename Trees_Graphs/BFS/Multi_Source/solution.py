"""
Multi-Source BFS

Problem: Start BFS from multiple sources simultaneously. Common for problems where
         multiple points spread influence at the same time.
         Example: Rotting Oranges - rotten oranges spread rot in 4 directions each minute.

Pattern: Add ALL source nodes to queue initially with distance/time 0.
         Process level by level; nodes at same level are reached at same time.

Related LeetCode Problems:
- LC 994: Rotting Oranges (Medium) ⭐⭐⭐
- LC 286: Walls and Gates (Medium)
- LC 1162: As Far from Land as Possible (Medium)
- LC 542: 01 Matrix (Medium)

Time Complexity: O(rows × cols) - visit each cell at most once
Space Complexity: O(rows × cols) - queue storage
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(n × rows × cols) time | O(rows×cols) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force: for each rotten orange, do separate BFS to see what
#                  it can reach; merge results — O(n × grid_size)"
#   2. Problem:    "For 100 rotten oranges in 1000×1000 grid: 100M operations;
#                  massive redundant work"
#   3. Transition: "Multi-source BFS starts all sources together; each cell visited
#                  once — O(rows × cols)"
#
# def oranges_rotting_naive(grid):
#     rows, cols = len(grid), len(grid[0])
#     rotten_sources = []
#     for r in range(rows):
#         for c in range(cols):
#             if grid[r][c] == 2:
#                 rotten_sources.append((r, c))
#     
#     if not rotten_sources:
#         return -1
#     
#     # Do BFS from each rotten source separately
#     max_dist = 0
#     for src_r, src_c in rotten_sources:
#         queue = deque([(src_r, src_c, 0)])
#         visited = set([(src_r, src_c)])
#         while queue:
#             r, c, dist = queue.popleft()
#             max_dist = max(max_dist, dist)
#             # ... BFS logic per source ...
#     return max_dist
# ─────────────────────────────────────────────────────────────────────────────

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
