"""
BFS - BFS on Graph with Visited Tracking

Related LeetCode Problems:
- LC 133: Clone Graph (Medium)
- LC 127: Word Ladder (Hard)
- LC 847: Shortest Path Visiting All Nodes (Hard)

Pattern:
- Need to track visited nodes to avoid cycles!
- Use set to mark visited nodes
- Common for general graph traversal

Time Complexity: O(V + E) - V vertices, E edges
Space Complexity: O(V) - visited set and queue
"""

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
