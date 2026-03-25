"""
BFS - Level-Order Traversal Pattern

Problem: Explore tree/graph level by level using queue.

Common Applications:
- Level order traversal
- Shortest path in unweighted graph
- Zigzag traversal
- Right side view

Time Complexity: O(n) - visit each node once
Space Complexity: O(w) - width of tree (max nodes at any level)
"""

from typing import Optional, List
from collections import deque


def level_order(root: Optional[TreeNode]) -> List[List[int]]:
    """
    Return level-order traversal of tree.
    
    Strategy: Use queue, process each level completely before next.
    
    Time: O(n), Space: O(w)
    """
    if not root:
        return []
    
    result = []
    queue = deque([root])
    
    while queue:
        level_size = len(queue)
        level = []
        
        # Process all nodes at current level
        for _ in range(level_size):
            node = queue.popleft()
            level.append(node.val)
            
            if node.left:
                queue.append(node.left)
            if node.right:
                queue.append(node.right)
        
        result.append(level)
    
    return result




if __name__ == "__main__":
    # Create tree:      3
    #                  / \
    #                 9  20
    #                   /  \
    #                  15   7
