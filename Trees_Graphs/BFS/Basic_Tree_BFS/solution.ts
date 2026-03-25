/**
 * BFS - Basic BFS on Binary Tree (Level Order Traversal)
 * 
 * Related LeetCode Problems:
 * - LC 102: Binary Tree Level Order Traversal (Medium)
 * - LC 107: Binary Tree Level Order Traversal II (Medium)
 * - LC 637: Average of Levels in Binary Tree (Easy)
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(w) - max width of tree
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

// Basic BFS traversal
export function bfsTree(root: TreeNode | null): number[] {
    if (!root) return [];
    
    const queue: TreeNode[] = [root];
    const result: number[] = [];
    
    while (queue.length > 0) {
        const node = queue.shift()!;
        result.push(node.val);
        
        if (node.left) queue.push(node.left);
        if (node.right) queue.push(node.right);
    }
    
    return result;
}

// LC 102: Binary Tree Level Order Traversal
export function levelOrder(root: TreeNode | null): number[][] {
    if (!root) return [];
    
    const queue: TreeNode[] = [root];
    const result: number[][] = [];
    
    while (queue.length > 0) {
        const levelSize = queue.length;
        const currentLevel: number[] = [];
        
        for (let i = 0; i < levelSize; i++) {
            const node = queue.shift()!;
            currentLevel.push(node.val);
            
            if (node.left) queue.push(node.left);
            if (node.right) queue.push(node.right);
        }
        
        result.push(currentLevel);
    }
    
    return result;
}

// LC 107: Binary Tree Level Order Traversal II
export function levelOrderBottom(root: TreeNode | null): number[][] {
    return levelOrder(root).reverse();
}

// LC 637: Average of Levels in Binary Tree
export function averageOfLevels(root: TreeNode | null): number[] {
    if (!root) return [];
    
    const queue: TreeNode[] = [root];
    const result: number[] = [];
    
    while (queue.length > 0) {
        const levelSize = queue.length;
        let levelSum = 0;
        
        for (let i = 0; i < levelSize; i++) {
            const node = queue.shift()!;
            levelSum += node.val;
            
            if (node.left) queue.push(node.left);
            if (node.right) queue.push(node.right);
        }
        
        result.push(levelSum / levelSize);
    }
    
    return result;
}

// Test cases
if (require.main === module) {
    const root = new TreeNode(3);
    root.left = new TreeNode(9);
    root.right = new TreeNode(20);
    root.right.left = new TreeNode(15);
    root.right.right = new TreeNode(7);
    
    console.log("Basic BFS:", bfsTree(root));
    console.log("Level Order:", levelOrder(root));
    console.log("Average of Levels:", averageOfLevels(root));
}
