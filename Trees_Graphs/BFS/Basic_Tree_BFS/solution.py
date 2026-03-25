"""
BFS - Basic BFS on Binary Tree (Level Order Traversal)

Related LeetCode Problems:
- LC 102: Binary Tree Level Order Traversal (Medium)
- LC 107: Binary Tree Level Order Traversal II (Medium)
- LC 637: Average of Levels in Binary Tree (Easy)

Pattern:
- Use a queue! Add root, then repeatedly: remove front node, process it, add its children
- Visit all nodes at depth 0, then depth 1, then depth 2, etc.

Time Complexity: O(n) - visit each node once
Space Complexity: O(w) - where w is max width of tree (worst case: all bottom level nodes in queue)
"""

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
