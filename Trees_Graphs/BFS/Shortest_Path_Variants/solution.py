"""
Shortest Path in Unweighted Graph

Pattern: Find shortest path from start to target using BFS

Time Complexity: O(V + E)
Space Complexity: O(V)
"""

from collections import deque

def shortest_path(graph, start, target):
    if start == target:
        return 0
    
    visited = set([start])
    queue = deque([(start, 0)])  # (node, distance)
    
    while queue:
        node, distance = queue.popleft()
        
        for neighbor in graph[node]:
            if neighbor == target:
                return distance + 1  # Found it!
            
            if neighbor not in visited:
                visited.add(neighbor)
                queue.append((neighbor, distance + 1))
    
    return -1  # No path found

# Usage
if __name__ == "__main__":
    # Create a graph: 0 -- 1 -- 3
    #                  \  /
    #                   2
    graph = {
        0: [1, 2],
        1: [0, 2, 3],
        2: [0, 1],
        3: [1]
    }
    
    print(shortest_path(graph, 0, 3))  # Output: 2 (path: 0 -> 1 -> 3)
    print(shortest_path(graph, 0, 2))  # Output: 1 (path: 0 -> 2)
    print(shortest_path(graph, 2, 3))  # Output: 2 (path: 2 -> 1 -> 3)
