/**
 * BFS - Level-by-Level BFS
 * 
 * Related LeetCode Problems:
 * - LC 102: Binary Tree Level Order Traversal (Medium)
 * - LC 103: Binary Tree Zigzag Level Order Traversal (Medium)
 * - LC 199: Binary Tree Right Side View (Medium)
 * - LC 515: Find Largest Value in Each Tree Row (Medium)
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

// LC 102: Level Order Traversal
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

// LC 103: Zigzag Level Order
export function zigzagLevelOrder(root: TreeNode | null): number[][] {
    if (!root) return [];
    
    const queue: TreeNode[] = [root];
    const result: number[][] = [];
    let leftToRight = true;
    
    while (queue.length > 0) {
        const levelSize = queue.length;
        const currentLevel: number[] = [];
        
        for (let i = 0; i < levelSize; i++) {
            const node = queue.shift()!;
            
            if (leftToRight) {
                currentLevel.push(node.val);
            } else {
                currentLevel.unshift(node.val);
            }
            
            if (node.left) queue.push(node.left);
            if (node.right) queue.push(node.right);
        }
        
        result.push(currentLevel);
        leftToRight = !leftToRight;
    }
    
    return result;
}

// LC 199: Right Side View
export function rightSideView(root: TreeNode | null): number[] {
    if (!root) return [];
    
    const queue: TreeNode[] = [root];
    const result: number[] = [];
    
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

// LC 515: Largest Values
export function largestValues(root: TreeNode | null): number[] {
    if (!root) return [];
    
    const queue: TreeNode[] = [root];
    const result: number[] = [];
    
    while (queue.length > 0) {
        const levelSize = queue.length;
        let maxVal = -Infinity;
        
        for (let i = 0; i < levelSize; i++) {
            const node = queue.shift()!;
            maxVal = Math.max(maxVal, node.val);
            
            if (node.left) queue.push(node.left);
            if (node.right) queue.push(node.right);
        }
        
        result.push(maxVal);
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
    
    console.log("Level Order:", levelOrder(root));
    console.log("Zigzag:", zigzagLevelOrder(root));
    console.log("Right Side View:", rightSideView(root));
}
