/**
 * DFS Recursion - Basic Tree DFS Pattern
 * 
 * Related LeetCode Problems:
 * - LC 104, 111, 110, 543, 112, 100, 101
 * 
 * Time: O(n), Space: O(h)
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

/** LC 104: Maximum Depth */
function maxDepth(root: TreeNode | null): number {
    if (!root) return 0;
    return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
}

/** LC 111: Minimum Depth */
function minDepth(root: TreeNode | null): number {
    if (!root) return 0;
    if (!root.left && !root.right) return 1;
    if (!root.left) return 1 + minDepth(root.right);
    if (!root.right) return 1 + minDepth(root.left);
    return 1 + Math.min(minDepth(root.left), minDepth(root.right));
}

/** LC 110: Balanced Binary Tree */
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

/** LC 543: Diameter of Binary Tree */
function diameterOfBinaryTree(root: TreeNode | null): number {
    let maxDiameter = 0;
    
    function height(node: TreeNode | null): number {
        if (!node) return 0;
        
        const left = height(node.left);
        const right = height(node.right);
        
        maxDiameter = Math.max(maxDiameter, left + right);
        
        return 1 + Math.max(left, right);
    }
    
    height(root);
    return maxDiameter;
}

/** LC 112: Path Sum */
function hasPathSum(root: TreeNode | null, targetSum: number): boolean {
    if (!root) return false;
    if (!root.left && !root.right) return root.val === targetSum;
    return hasPathSum(root.left, targetSum - root.val) || 
           hasPathSum(root.right, targetSum - root.val);
}

/** LC 100: Same Tree */
function isSameTree(p: TreeNode | null, q: TreeNode | null): boolean {
    if (!p && !q) return true;
    if (!p || !q) return false;
    return p.val === q.val && 
           isSameTree(p.left, q.left) && 
           isSameTree(p.right, q.right);
}

/** LC 101: Symmetric Tree */
function isSymmetric(root: TreeNode | null): boolean {
    function isMirror(left: TreeNode | null, right: TreeNode | null): boolean {
        if (!left && !right) return true;
        if (!left || !right) return false;
        return left.val === right.val && 
               isMirror(left.left, right.right) && 
               isMirror(left.right, right.left);
    }
    
    return root ? isMirror(root.left, root.right) : true;
}

// Test
if (require.main === module) {
    const root = new TreeNode(3);
    root.left = new TreeNode(9);
    root.right = new TreeNode(20);
    root.right.left = new TreeNode(15);
    root.right.right = new TreeNode(7);
    
    console.log(`Max depth: ${maxDepth(root)}`);  // 3
    console.log(`Min depth: ${minDepth(root)}`);  // 2
    console.log(`Is balanced: ${isBalanced(root)}`);  // true
    console.log(`Diameter: ${diameterOfBinaryTree(root)}`);  // 3
    console.log(`Has path sum 12: ${hasPathSum(root, 12)}`);  // true
}

export { TreeNode, maxDepth, minDepth, isBalanced, diameterOfBinaryTree, hasPathSum, isSameTree, isSymmetric };
