"""
Course Schedule II (Return the Order)

Pattern: Use topological sort to return a valid course ordering

Time Complexity: O(V + E)
Space Complexity: O(V + E)
"""

from collections import defaultdict, deque

def find_order(numCourses, prerequisites):
    """
    LeetCode 210: Course Schedule II
    Return a valid course order, or [] if impossible
    
    numCourses: number of courses labeled 0 to numCourses-1
    prerequisites: list of [course, prerequisite]
    """
    # Build graph
    graph = defaultdict(list)
    in_degree = [0] * numCourses
    
    for course, prereq in prerequisites:
        graph[prereq].append(course)
        in_degree[course] += 1
    
    # Start with courses that have no prerequisites
    queue = deque([i for i in range(numCourses) if in_degree[i] == 0])
    order = []
    
    while queue:
        course = queue.popleft()
        order.append(course)
        
        for next_course in graph[course]:
            in_degree[next_course] -= 1
            if in_degree[next_course] == 0:
                queue.append(next_course)
    
    return order if len(order) == numCourses else []

# Example
if __name__ == "__main__":
    print(find_order(2, [[1, 0]]))  # [0, 1]
    print(find_order(4, [[1, 0], [2, 0], [3, 1], [3, 2]]))  # [0, 1, 2, 3] or [0, 2, 1, 3]
    print(find_order(2, [[1, 0], [0, 1]]))  # [] (cycle)
