"""
Course Schedule (Cycle Detection with Topological Sort)

Problem: Given numCourses and prerequisites, determine if you can finish all courses.
         If course A depends on B, you must take B before A. Detect if cycle exists.
         Example: [[1,0],[0,1]] → false (circular dependency)

Pattern: Use Kahn's algorithm (topological sort). If can process all nodes → no cycle.
         Count how many nodes processed; if < total → cycle exists.

Related LeetCode Problems:
- LC 207: Course Schedule (Medium) ⭐⭐⭐
- LC 210: Course Schedule II (Medium)
- LC 630: Course Schedule III (Hard)

Time Complexity: O(V + E) - visit each course and prerequisite once
Space Complexity: O(V + E) - graph storage
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(V!) time | O(V) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force tries all V! permutations of courses, checks each
#                  if valid ordering — factorial time"
#   2. Problem:    "For 10 courses: 3.6 million permutations; for 15: 1.3 trillion;
#                  completely impractical"
#   3. Transition:  "Topological sort with in-degree tracking processes each course/edge
#                  once — O(V + E)"
#
# from itertools import permutations
# def can_finish_naive(numCourses, prerequisites):
#     # Build prerequisite map
#     prereq_map = defaultdict(set)
#     for course, prereq in prerequisites:
#         prereq_map[course].add(prereq)
#     
#     # Try all permutations
#     for perm in permutations(range(numCourses)):
#         valid = True
#         taken = set()
#         for course in perm:
#             # Check if all prerequisites are taken
#             if not prereq_map[course].issubset(taken):
#                 valid = False
#                 break
#             taken.add(course)
#         if valid:
#             return True
#     return False
# ─────────────────────────────────────────────────────────────────────────────

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
