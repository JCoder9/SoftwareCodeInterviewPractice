/**
 * DFS Recursion - Tree/Graph Pattern
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(h)
 */

class TreeNode {
    val: number;
    left: TreeNode | null;
    right: TreeNode | null;
    constructor(val: number = 0, left: TreeNode | null = null, right: TreeNode | null = null) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

function maxDepth(root: TreeNode | null): number {
    if (!root) return 0;
    return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
}

function isBalanced(root: TreeNode | null): boolean {
    function height(node: TreeNode | null): number {
        if (!node) return 0;
        
        const leftHeight = height(node.left);
        if (leftHeight === -1) return -1;
        
        const rightHeight = height(node.right);
        if (rightHeight === -1) return -1;
        
        if (Math.abs(leftHeight - rightHeight) > 1) return -1;
        
        return 1 + Math.max(leftHeight, rightHeight);
    }
    
    return height(root) !== -1;
}

function hasPathSum(root: TreeNode | null, targetSum: number): boolean {
    if (!root) return false;
    
    if (!root.left && !root.right) {
        return root.val === targetSum;
    }
    
    const remaining = targetSum - root.val;
    return hasPathSum(root.left, remaining) || hasPathSum(root.right, remaining);
}

// Test
if (require.main === module) {
    const root = new TreeNode(3);
    root.left = new TreeNode(9);
    root.right = new TreeNode(20);
    root.right.left = new TreeNode(15);
    root.right.right = new TreeNode(7);
    
    console.log("Max Depth: " + maxDepth(root));
    console.log("Is Balanced: " + isBalanced(root));
    console.log("Has Path Sum (28): " + hasPathSum(root, 28));
}

export { TreeNode, maxDepth, isBalanced, hasPathSum };
