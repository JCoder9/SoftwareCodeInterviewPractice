"""
Topological Sort - Build Order / Project Dependencies

Related LeetCode Problems:
- Similar to Course Schedule but with direct dependencies
- Build order with multi-level dependencies
- Parallel build optimization

Key Insight: Classic topological sort application.
- Projects/tasks have dependencies
- Must complete prerequisites before dependents
- Find valid build order or determine if impossible (cycle)

Time Complexity: O(V + E)
Space Complexity: O(V + E)
"""

from collections import defaultdict, deque
from typing import List, Dict, Tuple


def build_order(projects: List[str], dependencies: List[Tuple[str, str]]) -> List[str]:
    """
    Given projects and dependencies (before, after), return valid build order.
    dependencies[i] = (a, b) means project a must be built before project b.
    """
    graph = defaultdict(list)
    in_degree = {project: 0 for project in projects}
    
    for before, after in dependencies:
        graph[before].append(after)
        in_degree[after] += 1
    
    # Start with projects having no dependencies
    queue = deque([proj for proj in projects if in_degree[proj] == 0])
    build_sequence = []
    
    while queue:
        project = queue.popleft()
        build_sequence.append(project)
        
        # Remove this project, check what can be built next
        for dependent in graph[project]:
            in_degree[dependent] -= 1
            if in_degree[dependent] == 0:
                queue.append(dependent)
    
    # If not all projects built, there's a cycle
    if len(build_sequence) != len(projects):
        return []
    
    return build_sequence




if __name__ == "__main__":
    # Test basic build order
    projects1 = ["a", "b", "c", "d", "e", "f"]
    deps1 = [("a", "d"), ("f", "b"), ("b", "d"), ("f", "a"), ("d", "c")]
    print("Build order:", build_order(projects1, deps1))
