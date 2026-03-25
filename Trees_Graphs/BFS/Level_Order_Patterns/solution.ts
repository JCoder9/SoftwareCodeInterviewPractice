/**
 * BFS - Level-Order Traversal Pattern
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(w)
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

function levelOrder(root: TreeNode | null): number[][] {
    if (!root) return [];
    
    const result: number[][] = [];
    const queue: TreeNode[] = [root];
    
    while (queue.length > 0) {
        const levelSize = queue.length;
        const level: number[] = [];
        
        for (let i = 0; i < levelSize; i++) {
            const node = queue.shift()!;
            level.push(node.val);
            
            if (node.left) queue.push(node.left);
            if (node.right) queue.push(node.right);
        }
        
        result.push(level);
    }
    
    return result;
}

function rightSideView(root: TreeNode | null): number[] {
    if (!root) return [];
    
    const result: number[] = [];
    const queue: TreeNode[] = [root];
    
    while (queue.length > 0) {
        const levelSize = queue.length;
        
        for (let i = 0; i < levelSize; i++) {
            const node = queue.shift()!;
            
            if (i === levelSize - 1) {
                result.push(node.val);
            }
            
            if (node.left) queue.push(node.left);
            if (node.right) queue.push(node.right);
        }
    }
    
    return result;
}

// Test
if (require.main === module) {
    const root = new TreeNode(3);
    root.left = new TreeNode(9);
    root.right = new TreeNode(20);
    root.right.left = new TreeNode(15);
    root.right.right = new TreeNode(7);
    
    console.log("Level Order: " + JSON.stringify(levelOrder(root)));
    console.log("Right Side View: " + JSON.stringify(rightSideView(root)));
}

export { TreeNode, levelOrder, rightSideView };
