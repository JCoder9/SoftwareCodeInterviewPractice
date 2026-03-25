"""
BFS - 0-1 BFS (Deque-based BFS for graphs with 0 and 1 weights)

Related LeetCode Problems:
- LC 1368: Minimum Cost to Make at Least One Valid Path in a Grid (Hard)
- LC 2290: Minimum Obstacle Removal to Reach Corner (Hard)

Key Insight: For graphs with only 0 and 1 edge weights, we can use a deque.
- Add 0-weight edges to the front (process immediately)
- Add 1-weight edges to the back (process later)
This maintains optimal substructure without needing full Dijkstra's algorithm.

Time Complexity: O(V + E) where V is vertices, E is edges
Space Complexity: O(V)
"""

from collections import deque
from typing import List

def zero_one_bfs(graph, start, end):
    """
    0-1 BFS for graph with edge weights of only 0 or 1
    graph: {node: [(neighbor, weight), ...]}
    weight is either 0 or 1
    """
    dist = {start: 0}
    deque_queue = deque([start])
    
    while deque_queue:
        node = deque_queue.popleft()
        
        if node == end:
            return dist[node]
        
        for neighbor, weight in graph[node]:
            new_dist = dist[node] + weight
            
            # Only process if we found a shorter path
            if neighbor not in dist or new_dist < dist[neighbor]:
                dist[neighbor] = new_dist
                
                if weight == 0:
                    deque_queue.appendleft(neighbor)  # Front for 0-weight
                else:
                    deque_queue.append(neighbor)      # Back for 1-weight
    
    return -1 if end not in dist else dist[end]


# LC 1368: Minimum Cost to Make at Least One Valid Path in a Grid
def minCost(grid: List[List[int]]) -> int:
    """
    Grid directions: 1=right, 2=left, 3=down, 4=up
    Cost 0 to follow arrow, cost 1 to change direction.
    """
    rows, cols = len(grid), len(grid[0])
    directions = [(0, 1), (0, -1), (1, 0), (-1, 0)]  # right, left, down, up
    
    dist = [[float('inf')] * cols for _ in range(rows)]
    dist[0][0] = 0
    dq = deque([(0, 0)])
    
    while dq:
        r, c = dq.popleft()
        
        for i, (dr, dc) in enumerate(directions):
            nr, nc = r + dr, c + dc
            
            if 0 <= nr < rows and 0 <= nc < cols:
                # Cost 0 if following grid's arrow, cost 1 otherwise
                cost = 0 if grid[r][c] == i + 1 else 1
                new_dist = dist[r][c] + cost
                
                if new_dist < dist[nr][nc]:
                    dist[nr][nc] = new_dist
                    if cost == 0:
                        dq.appendleft((nr, nc))
                    else:
                        dq.append((nr, nc))
    
    return dist[rows - 1][cols - 1]


# LC 2290: Minimum Obstacle Removal to Reach Corner
def minimumObstacles(grid: List[List[int]]) -> int:
    """
    0 = empty cell, 1 = obstacle
    Cost 0 to move to empty, cost 1 to remove obstacle.
    """
    rows, cols = len(grid), len(grid[0])
    dist = [[float('inf')] * cols for _ in range(rows)]
    dist[0][0] = 0
    dq = deque([(0, 0)])
    
    directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]
    
    while dq:
        r, c = dq.popleft()
        
        if r == rows - 1 and c == cols - 1:
            return dist[r][c]
        
        for dr, dc in directions:
            nr, nc = r + dr, c + dc
            
            if 0 <= nr < rows and 0 <= nc < cols:
                cost = grid[nr][nc]  # 0 or 1
                new_dist = dist[r][c] + cost
                
                if new_dist < dist[nr][nc]:
                    dist[nr][nc] = new_dist
                    if cost == 0:
                        dq.appendleft((nr, nc))
                    else:
                        dq.append((nr, nc))
    
    return dist[rows - 1][cols - 1]


# Test cases
if __name__ == "__main__":
    # Test 0-1 BFS graph
    graph = [
        [(1, 0), (2, 1)],  # 0 -> 1 (cost 0), 0 -> 2 (cost 1)
        [(3, 1)],           # 1 -> 3 (cost 1)
        [(3, 0)],           # 2 -> 3 (cost 0)
        []                  # 3 (end)
    ]
    print("0-1 BFS graph:", zero_one_bfs_graph(graph, 0, 3))  # Expected: 1
    
    # Test LC 1368
    grid1 = [[1, 1, 1, 1], [2, 2, 2, 2], [1, 1, 1, 1], [2, 2, 2, 2]]
    print("Min cost path:", minCost(grid1))  # Expected: 3
    
    # Test LC 2290
    grid2 = [[0, 1, 1], [1, 1, 0], [1, 1, 0]]
    print("Min obstacles:", minimumObstacles(grid2))  # Expected: 2
