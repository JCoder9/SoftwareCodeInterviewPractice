"""
Topological Sort - Dependency Resolution Pattern

Problem: Order nodes in directed acyclic graph (DAG) such that for every edge u->v, u comes before v.

Common Applications:
- Course scheduling (prerequisites)
- Build systems (dependencies)
- Task ordering

Two Approaches:
1. Kahn's Algorithm (BFS with in-degree)
2. DFS with post-order

Time Complexity: O(V + E)
Space Complexity: O(V + E)
"""

from typing import List, Dict, Set
from collections import deque, defaultdict


def topological_sort_kahn(num_nodes: int, edges: List[List[int]]) -> List[int]:
    """
    Topological sort using Kahn's algorithm (BFS with in-degree).
    
    Strategy:
    1. Calculate in-degree for all nodes
    2. Start with nodes having in-degree 0
    3. Remove node and decrease in-degree of neighbors
    4. Add neighbors with in-degree 0 to queue
    
    Time: O(V + E), Space: O(V + E)
    """
    # Build adjacency list and in-degree count
    graph = defaultdict(list)
    in_degree = [0] * num_nodes
    
    for u, v in edges:
        graph[u].append(v)
        in_degree[v] += 1
    
    # Start with nodes having no dependencies
    queue = deque([i for i in range(num_nodes) if in_degree[i] == 0])
    result = []
    
    while queue:
        node = queue.popleft()
        result.append(node)
        
        # Remove edges from this node
        for neighbor in graph[node]:
            in_degree[neighbor] -= 1
            if in_degree[neighbor] == 0:
                queue.append(neighbor)
    
    # If we processed all nodes, valid topological order exists
    return result if len(result) == num_nodes else []




if __name__ == "__main__":
    # Test course schedule
    print("Can finish courses [[1,0]]:", can_finish_courses(2, [[1, 0]]))  # True
    print("Can finish courses [[1,0],[0,1]]:", can_finish_courses(2, [[1, 0], [0, 1]]))  # False
    
    # Test course order
