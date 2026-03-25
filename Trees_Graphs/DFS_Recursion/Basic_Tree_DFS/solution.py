"""
Basic Tree DFS - Maximum Depth of Binary Tree

Pattern: Any tree traversal problem, finding paths, calculating tree properties

Time Complexity: O(n) - visit each node once
Space Complexity: O(h) - recursion stack depth (h = tree height)
"""

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
