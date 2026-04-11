"""
Basic Topological Sort (Kahn's Algorithm - BFS approach)

Problem: Given directed graph, find a valid ordering where for every edge u→v, u comes before v.
         Used for dependency resolution, task scheduling.

Pattern: Start with nodes having no incoming edges (in-degree = 0).
         Process them, remove edges, repeat. If can't process all nodes → cycle exists.

Related LeetCode Problems:
- LC 207: Course Schedule (Medium) ⭐⭐⭐
- LC 210: Course Schedule II (Medium) ⭐⭐⭐
- LC 802: Find Eventual Safe States (Medium)

Time Complexity: O(V + E) - visit each vertex and edge once
Space Complexity: O(V + E) - graph storage
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(V^2 + V×E) time | O(V) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force repeatedly scans for any node that has all its
#                  dependencies satisfied, marks it done, repeats — O(V^2)"
#   2. Problem:    "For V=1000 nodes: scans array 1000 times checking dependencies
#                  each time; very inefficient"
#   3. Transition: "Kahn's algorithm maintains in-degree count; queue tracks ready
#                  nodes — O(V + E) single pass"
#
# def topological_sort_naive(n, edges):
#     graph = defaultdict(list)
#     for src, dst in edges:
#         graph[src].append(dst)
#     
#     visited = set()
#     result = []
#     
#     while len(result) < n:
#         # Find any node with all dependencies satisfied
#         found = False
#         for node in range(n):
#             if node in visited:
#                 continue
#             # Check if all predecessors are visited
#             has_unvisited_dep = False
#             for src in range(n):
#                 if node in graph[src] and src not in visited:
#                     has_unvisited_dep = True
#                     break
#             if not has_unvisited_dep:
#                 result.append(node)
#                 visited.add(node)
#                 found = True
#                 break
#         if not found:  # Cycle detected
#             return []
#     return result
# ─────────────────────────────────────────────────────────────────────────────

from collections import deque, defaultdict

def topological_sort_kahns(n, edges):
    """
    n: number of nodes (0 to n-1)
    edges: list of [from, to] representing directed edges
    Returns: topological order or [] if cycle exists
    """
    # Build graph and count incoming edges
    graph = defaultdict(list)
    in_degree = [0] * n
    
    for src, dst in edges:
        graph[src].append(dst)
        in_degree[dst] += 1
    
    # Start with nodes that have no prerequisites
    queue = deque([i for i in range(n) if in_degree[i] == 0])
    result = []
    
    while queue:
        node = queue.popleft()
        result.append(node)
        
        # Remove this node and decrease in-degree of neighbors
        for neighbor in graph[node]:
            in_degree[neighbor] -= 1
            if in_degree[neighbor] == 0:
                queue.append(neighbor)
    
    # If we processed all nodes, no cycle exists
    return result if len(result) == n else []

# Example: 0 -> 1 -> 2, 0 -> 2
if __name__ == "__main__":
    edges = [[0, 1], [1, 2], [0, 2]]
    print(topological_sort_kahns(3, edges))  # [0, 1, 2]
