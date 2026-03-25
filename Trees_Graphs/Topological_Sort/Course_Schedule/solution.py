"""
Course Schedule (Cycle Detection)

Pattern: Use topological sort to detect if all courses can be completed (no cycles)

Time Complexity: O(V + E)
Space Complexity: O(V + E)
"""

from collections import defaultdict, deque

def can_finish(numCourses, prerequisites):
    """
    LeetCode 207: Course Schedule
    Return True if you can finish all courses
    
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
    completed = 0
    
    while queue:
        course = queue.popleft()
        completed += 1
        
        for next_course in graph[course]:
            in_degree[next_course] -= 1
            if in_degree[next_course] == 0:
                queue.append(next_course)
    
    return completed == numCourses

# Example
if __name__ == "__main__":
    print(can_finish(2, [[1, 0]]))  # True
    print(can_finish(2, [[1, 0], [0, 1]]))  # False (cycle)
