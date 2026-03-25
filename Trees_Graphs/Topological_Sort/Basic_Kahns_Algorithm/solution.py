"""
Basic Topological Sort (Kahn's Algorithm - BFS approach)

Pattern: Start with nodes that have no prerequisites, process them, then remove them and repeat

Time Complexity: O(V + E)
Space Complexity: O(V + E)
"""

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
