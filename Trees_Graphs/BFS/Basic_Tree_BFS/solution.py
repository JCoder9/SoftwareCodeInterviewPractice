"""
BFS - Basic BFS on Binary Tree (Level Order Traversal)

Problem: Traverse tree level by level, visiting all nodes at depth 0, then 1, then 2, etc.

Pattern: Use a queue. Add root, then repeatedly: remove front node, process it, add children.

Related LeetCode Problems:
- LC 102: Binary Tree Level Order Traversal (Medium)
- LC 107: Binary Tree Level Order Traversal II (Medium)
- LC 637: Average of Levels in Binary Tree (Easy)

Time Complexity: O(n) - visit each node once
Space Complexity: O(w) - w = max width of tree (worst: all bottom level in queue)
"""

# ───────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) - O(n²) time | O(n) space
# ───────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force uses recursive DFS to find max depth, then for each
#                   level 0..depth calls DFS again to collect nodes at that level"
#   2. Problem:    "Visits each node multiple times — O(n × h) can be O(n²) for
#                   skewed trees"
#   3. Transition: "BFS with queue visits each node exactly once — O(n) time and
#                   naturally processes level by level"
#
# def level_order_naive(root):
#     result = []
#     depth = max_depth(root)
#     
#     for level in range(depth):
#         current_level = []
#         collect_level(root, level, current_level)
#         result.append(current_level)
#     return result
# 
# def collect_level(node, level, result):
#     if not node:
#         return
#     if level == 0:
#         result.append(node.val)
#     else:
#         collect_level(node.left, level - 1, result)
#         collect_level(node.right, level - 1, result)
# 
# def max_depth(node):
#     if not node:
#         return 0
#     return 1 + max(max_depth(node.left), max_depth(node.right))
# ───────────────────────────────────────────────────────────────────────────

from collections import deque


def bfs_tree(root):
    """Basic BFS traversal"""
    if not root:
        return []
    
    queue = deque([root])  # Start with root
    result = []
    
    while queue:
        node = queue.popleft()  # Remove from front
        result.append(node.val)  # Process node
        
        # Add children to back of queue
        if node.left:
            queue.append(node.left)
        if node.right:
            queue.append(node.right)
    
    return result




if __name__ == "__main__":
    # Create test tree:     3
    #                      / \
    #                     9  20
    #                       /  \
    #                      15   7
