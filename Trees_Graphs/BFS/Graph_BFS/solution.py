"""
BFS - BFS on Graph with Visited Tracking

Problem: Traverse a graph level-by-level starting from a source node.
         Find shortest path or explore all reachable nodes.

Pattern: Use queue for BFS. Maintain visited set to avoid cycles.
         Essential for shortest path in unweighted graphs.

Related LeetCode Problems:
- LC 133: Clone Graph (Medium) ⭐⭐
- LC 127: Word Ladder (Hard) ⭐⭐⭐
- LC 841: Keys and Rooms (Medium)
- LC 847: Shortest Path Visiting All Nodes (Hard)

Time Complexity: O(V + E) - V vertices, E edges
Space Complexity: O(V) - visited set and queue
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(V!) time | O(V) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force explores all possible paths using DFS recursively
#                  — exponential paths without visited tracking"
#   2. Problem:    "In graphs with cycles: infinite loops! Even with cycle detection,
#                  explores exponential number of paths"
#   3. Transition: "BFS with visited set explores each node once, processes level
#                  by level — O(V + E) linear time"
#
# def bfs_naive(graph, start):
#     # DFS without proper visited tracking - explores many redundant paths
#     result = []
#     def dfs(node, path):
#         if node in path:  # Cycle detection only
#             return
#         result.append(node)
#         for neighbor in graph[node]:
#             dfs(neighbor, path + [node])
#     dfs(start, [])
#     return result
# ─────────────────────────────────────────────────────────────────────────────

from collections import deque


def bfs_graph(graph, start):
    """
    Basic BFS on graph
    graph: dict like {0: [1, 2], 1: [0, 3], 2: [0], 3: [1]}
    """
    visited = set([start])
    queue = deque([start])
    result = []
    
    while queue:
        node = queue.popleft()
        result.append(node)
        
        # Visit all neighbors
        for neighbor in graph[node]:
            if neighbor not in visited:
                visited.add(neighbor)  # Mark as visited
                queue.append(neighbor)
    
    return result




if __name__ == "__main__":
    # Test basic BFS
    print("Testing basic BFS:")
    graph = {0: [1, 2], 1: [0, 3], 2: [0], 3: [1]}
    print(bfs_graph(graph, 0))  # [0, 1, 2, 3]
