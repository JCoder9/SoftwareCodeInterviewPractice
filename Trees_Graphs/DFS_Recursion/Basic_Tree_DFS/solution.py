"""
DFS Recursion - Basic Tree DFS Pattern

Problem: Find maximum depth of binary tree (number of nodes along longest path).

Pattern: Recursive DFS is natural for tree problems. Base case: None = 0.
         Recursive case: 1 + max(left, right).

Time Complexity: O(n) - visit each node once
Space Complexity: O(h) - recursion stack (h = height, best O(log n), worst O(n))
"""

# ───────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) - O(n) time | O(w) space
# ───────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force uses level-order traversal (BFS) with queue to count
#                   levels — O(n) time but O(w) space where w = max width"
#   2. Problem:    "Queue can be wide; for skewed tree, O(w) can be O(n)"
#   3. Transition: "Recursive DFS is more natural and uses O(h) space, where
#                   h ≤ n but often h = log(n) for balanced trees"
#
# from collections import deque
# def maxDepth_naive(root):
#     if not root:
#         return 0
#     
#     queue = deque([root])
#     depth = 0
#     
#     while queue:
#         level_size = len(queue)
#         depth += 1
#         
#         for _ in range(level_size):
#             node = queue.popleft()
#             if node.left:
#                 queue.append(node.left)
#             if node.right:
#                 queue.append(node.right)
#     return depth
# ───────────────────────────────────────────────────────────────────────────

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

def maxDepth(root):
    # Base case: empty tree has depth 0
    if not root:
        return 0
    
    # Recursive case: 1 + max of left and right subtrees
    left_depth = maxDepth(root.left)
    right_depth = maxDepth(root.right)
    
    return 1 + max(left_depth, right_depth)

# Usage
if __name__ == "__main__":
    root = TreeNode(3)
    root.left = TreeNode(9)
    root.right = TreeNode(20)
    root.right.left = TreeNode(15)
    root.right.right = TreeNode(7)
    print(maxDepth(root))  # Output: 3
