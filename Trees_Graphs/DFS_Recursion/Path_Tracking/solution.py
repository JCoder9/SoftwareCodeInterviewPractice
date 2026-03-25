"""
Path Tracking in DFS - All Root-to-Leaf Paths

Pattern: Finding all paths, specific paths, or path properties

Time Complexity: O(n) - visit each node once
Space Complexity: O(h) - height of tree for recursion stack
"""

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

def binaryTreePaths(root):
    if not root:
        return []
    
    result = []
    
    def dfs(node, path):
        # Add current node to path
        path.append(str(node.val))
        
        # If leaf node, add path to result
        if not node.left and not node.right:
            result.append("->".join(path))
        else:
            # Continue exploring
            if node.left:
                dfs(node.left, path)
            if node.right:
                dfs(node.right, path)
        
        # Backtrack: remove current node
        path.pop()
    
    dfs(root, [])
    return result

# Usage
if __name__ == "__main__":
    root = TreeNode(1)
    root.left = TreeNode(2)
    root.right = TreeNode(3)
    root.left.right = TreeNode(5)
    print(binaryTreePaths(root))  # Output: ["1->2->5", "1->3"]
