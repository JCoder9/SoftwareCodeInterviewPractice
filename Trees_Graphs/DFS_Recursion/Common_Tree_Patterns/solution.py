"""
DFS Recursion - Tree/Graph Pattern

Problem: Use recursive DFS to explore trees/graphs and compute properties.

Common Patterns:
1. Subtree properties (height, diameter, balanced)
2. Path problems (sum, max path, lowest common ancestor)
3. Backtracking (all paths, combinations)

Time Complexity: O(n) to visit all nodes
Space Complexity: O(h) for recursion stack (h = height)
"""

from typing import Optional, List


def lowest_common_ancestor(root: TreeNode, p: TreeNode, q: TreeNode) -> TreeNode:
    """
    Find lowest common ancestor of two nodes in BST.
    
    Strategy: 
    - If both nodes are in left subtree, LCA is in left
    - If both nodes are in right subtree, LCA is in right
    - Otherwise, current node is LCA
    
    Time: O(h), Space: O(h)
    """
    if not root or root == p or root == q:
        return root
    
    left = lowest_common_ancestor(root.left, p, q)
    right = lowest_common_ancestor(root.right, p, q)
    
    # If both sides found nodes, current is LCA
    if left and right:
        return root
    
    # Return whichever side found something
    return left if left else right




if __name__ == "__main__":
    # Create tree:      3
    #                  / \
    #                 9  20
    #                   /  \
    #                  15   7
    root = TreeNode(3)
