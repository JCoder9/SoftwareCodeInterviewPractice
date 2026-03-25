"""
Topological Sort (DFS approach)

Pattern: Do DFS, when done with a node, add it to result. Then reverse the result.

Time Complexity: O(V + E)
Space Complexity: O(V + E)
"""

from collections import defaultdict

def topological_sort_dfs(n, edges):
    """DFS-based topological sort"""
    graph = defaultdict(list)
    for src, dst in edges:
        graph[src].append(dst)
    
    visited = [0] * n  # 0: unvisited, 1: visiting, 2: visited
    result = []
    
    def dfs(node):
        if visited[node] == 1:  # Currently visiting - cycle detected!
            return False
        if visited[node] == 2:  # Already visited
            return True
        
        visited[node] = 1  # Mark as visiting
        
        for neighbor in graph[node]:
            if not dfs(neighbor):
                return False
        
        visited[node] = 2  # Mark as visited
        result.append(node)  # Add to result AFTER visiting all children
        return True
    
    # Try DFS from each unvisited node
    for i in range(n):
        if visited[i] == 0:
            if not dfs(i):
                return []  # Cycle detected
    
    return result[::-1]  # Reverse because we added children first

# Example
if __name__ == "__main__":
    edges = [[0, 1], [1, 2], [0, 2]]
    print(topological_sort_dfs(3, edges))  # [0, 1, 2]
