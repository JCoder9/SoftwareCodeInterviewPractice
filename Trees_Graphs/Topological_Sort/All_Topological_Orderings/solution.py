"""
Topological Sort - All Topological Orderings (Backtracking)

Related LeetCode Problems:
- Print all possible topological sorts
- Count all valid topological orderings
- Find lexicographically smallest topological order

Key Insight: Use backtracking to generate all valid orderings.
- Track in-degrees
- At each step, choose any node with in-degree 0
- Recursively explore, then backtrack
- Unlike standard topo sort, explores all possibilities

Time Complexity: O(V! × E) in worst case (factorial orderings)
Space Complexity: O(V)
"""

from collections import defaultdict
from typing import List


def all_topological_sorts(n, edges):
    """
    Find all possible topological orderings using backtracking.
    """
    graph = defaultdict(list)
    in_degree = [0] * n
    
    for u, v in edges:
        graph[u].append(v)
        in_degree[v] += 1
    
    result = []
    current_order = []
    visited = [False] * n
    
    def backtrack():
        # Base case: all nodes processed
        if len(current_order) == n:
            result.append(current_order[:])
            return
        
        # Try all nodes with in-degree 0
        for node in range(n):
            if not visited[node] and in_degree[node] == 0:
                # Choose
                visited[node] = True
                current_order.append(node)
                
                # Decrease in-degrees of neighbors
                for neighbor in graph[node]:
                    in_degree[neighbor] -= 1
                
                # Explore
                backtrack()
                
                # Backtrack
                visited[node] = False
                current_order.pop()
                
                # Restore in-degrees
                for neighbor in graph[node]:
                    in_degree[neighbor] += 1
    
    backtrack()
    return result




if __name__ == "__main__":
    # Test all topological sorts
    edges1 = [[0, 1], [0, 2], [1, 3], [2, 3]]
    all_sorts = all_topological_sorts(4, edges1)
    print(f"All topological sorts ({len(all_sorts)} total):")
    for sort in all_sorts:
