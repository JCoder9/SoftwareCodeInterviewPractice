/**
 * DFS Recursion - Path Tracking in DFS Pattern
 * 
 * Related LeetCode Problems:
 * - LC 257: Binary Tree Paths (Easy)
 * - LC 113: Path Sum II (Medium)
 * - LC 129: Sum Root to Leaf Numbers (Medium)
 * - LC 437: Path Sum III (Medium)
 * 
 * Pattern: Track path during DFS, backtrack when returning
 * Time Complexity: O(n) to visit all nodes
 * Space Complexity: O(h) for path + recursion
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

// LC 257: Binary Tree Paths
export function binaryTreePaths(root: TreeNode | null): string[] {
    const result: string[] = [];
    
    function dfs(node: TreeNode | null, path: string[]): void {
        if (!node) {
            return;
        }
        
        path.push(String(node.val));
        
        if (!node.left && !node.right) {
            result.push(path.join('->'));
        } else {
            dfs(node.left, path);
            dfs(node.right, path);
        }
        
        path.pop();  // Backtrack
    }
    
    if (root) {
        dfs(root, []);
    }
    return result;
}

// LC 113: Path Sum II
export function pathSum(root: TreeNode | null, targetSum: number): number[][] {
    const result: number[][] = [];
    
    function dfs(node: TreeNode | null, path: number[], currentSum: number): void {
        if (!node) {
            return;
        }
        
        path.push(node.val);
        currentSum += node.val;
        
        if (!node.left && !node.right && currentSum === targetSum) {
            result.push([...path]);  // Add copy
        }
        
        dfs(node.left, path, currentSum);
        dfs(node.right, path, currentSum);
        
        path.pop();  // Backtrack
    }
    
    if (root) {
        dfs(root, [], 0);
    }
    return result;
}

// LC 129: Sum Root to Leaf Numbers
export function sumNumbers(root: TreeNode | null): number {
    function dfs(node: TreeNode | null, currentNum: number): number {
        if (!node) {
            return 0;
        }
        
        currentNum = currentNum * 10 + node.val;
        
        if (!node.left && !node.right) {
            return currentNum;
        }
        
        return dfs(node.left, currentNum) + dfs(node.right, currentNum);
    }
    
    return dfs(root, 0);
}

// LC 437: Path Sum III
export function pathSumIII(root: TreeNode | null, targetSum: number): number {
    function dfsFromNode(node: TreeNode | null, currentSum: number): number {
        if (!node) {
            return 0;
        }
        
        currentSum += node.val;
        let count = currentSum === targetSum ? 1 : 0;
        
        count += dfsFromNode(node.left, currentSum);
        count += dfsFromNode(node.right, currentSum);
        
        return count;
    }
    
    if (!root) {
        return 0;
    }
    
    return dfsFromNode(root, 0) + 
           pathSumIII(root.left, targetSum) + 
           pathSumIII(root.right, targetSum);
}

// LC 437: Path Sum III (optimized with prefix sum)
export function pathSumIIIOptimized(root: TreeNode | null, targetSum: number): number {
    function dfs(node: TreeNode | null, currentSum: number, 
                 prefixSums: Map<number, number>): number {
        if (!node) {
            return 0;
        }
        
        currentSum += node.val;
        let count = prefixSums.get(currentSum - targetSum) || 0;
        
        prefixSums.set(currentSum, (prefixSums.get(currentSum) || 0) + 1);
        
        count += dfs(node.left, currentSum, prefixSums);
        count += dfs(node.right, currentSum, prefixSums);
        
        prefixSums.set(currentSum, prefixSums.get(currentSum)! - 1);  // Backtrack
        
        return count;
    }
    
    const prefixSums = new Map<number, number>();
    prefixSums.set(0, 1);
    return dfs(root, 0, prefixSums);
}

// LC 988: Smallest String Starting From Leaf
export function smallestFromLeaf(root: TreeNode | null): string {
    let result: string | null = null;
    
    function dfs(node: TreeNode | null, path: string[]): void {
        if (!node) {
            return;
        }
        
        path.push(String.fromCharCode(97 + node.val));  // 'a' + node.val
        
        if (!node.left && !node.right) {
            const leafToRoot = path.slice().reverse().join('');
            if (result === null || leafToRoot < result) {
                result = leafToRoot;
            }
        }
        
        dfs(node.left, path);
        dfs(node.right, path);
        
        path.pop();  // Backtrack
    }
    
    if (root) {
        dfs(root, []);
    }
    return result || '';
}

// Test cases
if (require.main === module) {
    // Create test tree
    const root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.left.right = new TreeNode(5);
    
    // Test binaryTreePaths
    console.log("Testing binaryTreePaths:");
    console.log(binaryTreePaths(root));  // ["1->2->5", "1->3"]
    
    // Test sumNumbers
    console.log("\nTesting sumNumbers:");
    const root2 = new TreeNode(1);
    root2.left = new TreeNode(2);
    root2.right = new TreeNode(3);
    console.log(sumNumbers(root2));  // 25
    
    // Test pathSumIII
    console.log("\nTesting pathSumIII:");
    const root3 = new TreeNode(10);
    root3.left = new TreeNode(5);
    root3.right = new TreeNode(-3);
    root3.left.left = new TreeNode(3);
    root3.left.right = new TreeNode(2);
    root3.right.right = new TreeNode(11);
    console.log(pathSumIII(root3, 8));  // 3
    console.log(pathSumIIIOptimized(root3, 8));  // 3
}
