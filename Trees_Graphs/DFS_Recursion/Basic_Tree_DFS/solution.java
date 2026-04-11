/**
 * DFS Recursion - Basic Tree DFS Pattern
 * 
 * Problem: Find maximum depth of binary tree (number of nodes along longest path).
 * 
 * Pattern: Recursive DFS is natural for tree problems. Base case: null = 0.
 *          Recursive case: 1 + max(left, right).
 * 
 * Related LeetCode Problems:
 * - LC 104: Maximum Depth, 111: Minimum Depth
 * - LC 110: Balanced Tree, 543: Diameter
 * - LC 112: Path Sum, 100: Same Tree, 101: Symmetric Tree
 * 
 * Time Complexity: O(n) - visit each node once
 * Space Complexity: O(h) - recursion stack (h = height, best O(log n), worst O(n))
 */

// ───────────────────────────────────────────────────────────────────────────
// NAIVE APPROACH (Brute Force) - O(n) time | O(w) space
// ───────────────────────────────────────────────────────────────────────────
// INTERVIEW SCRIPT:
//   1. Describe:   "Brute force uses level-order traversal (BFS) with queue to count
//                   levels — O(n) time but O(w) space where w = max width"
//   2. Problem:    "Queue can be wide; for skewed tree, O(w) can be O(n)"
//   3. Transition: "Recursive DFS is more natural and uses O(h) space, where
//                   h ≤ n but often h = log(n) for balanced trees"
//
// public int maxDepthNaive(TreeNode root) {
//     if (root == null) return 0;
//     
//     Queue<TreeNode> queue = new LinkedList<>();
//     queue.offer(root);
//     int depth = 0;
//     
//     while (!queue.isEmpty()) {
//         int levelSize = queue.size();
//         depth++;
//         
//         for (int i = 0; i < levelSize; i++) {
//             TreeNode node = queue.poll();
//             if (node.left != null) queue.offer(node.left);
//             if (node.right != null) queue.offer(node.right);
//         }
//     }
//     return depth;
// }
// ───────────────────────────────────────────────────────────────────────────

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    /** LC 104: Maximum Depth */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
    
    /** LC 111: Minimum Depth */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        if (root.left == null) return 1 + minDepth(root.right);
        if (root.right == null) return 1 + minDepth(root.left);
        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }
    
    /** LC 110: Balanced Binary Tree */
    public boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }
    
    private int height(TreeNode node) {
        if (node == null) return 0;
        
        int leftHeight = height(node.left);
        if (leftHeight == -1) return -1;
        
        int rightHeight = height(node.right);
        if (rightHeight == -1) return -1;
        
        if (Math.abs(leftHeight - rightHeight) > 1) return -1;
        
        return 1 + Math.max(leftHeight, rightHeight);
    }
    
    /** LC 543: Diameter of Binary Tree */
    private int maxDiameter = 0;
    
    public int diameterOfBinaryTree(TreeNode root) {
        maxDiameter = 0;
        heightDiameter(root);
        return maxDiameter;
    }
    
    private int heightDiameter(TreeNode node) {
        if (node == null) return 0;
        
        int left = heightDiameter(node.left);
        int right = heightDiameter(node.right);
        
        maxDiameter = Math.max(maxDiameter, left + right);
        
        return 1 + Math.max(left, right);
    }
    
    /** LC 112: Path Sum */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return root.val == targetSum;
        return hasPathSum(root.left, targetSum - root.val) || 
               hasPathSum(root.right, targetSum - root.val);
    }
    
    /** LC 100: Same Tree */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        return p.val == q.val && 
               isSameTree(p.left, q.left) && 
               isSameTree(p.right, q.right);
    }
    
    /** LC 101: Symmetric Tree */
    public boolean isSymmetric(TreeNode root) {
        return root == null || isMirror(root.left, root.right);
    }
    
    private boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        return left.val == right.val && 
               isMirror(left.left, right.right) && 
               isMirror(left.right, right.left);
    }
    
    // Test
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        
        System.out.println("Max depth: " + sol.maxDepth(root));  // 3
        System.out.println("Min depth: " + sol.minDepth(root));  // 2
        System.out.println("Is balanced: " + sol.isBalanced(root));  // true
        System.out.println("Diameter: " + sol.diameterOfBinaryTree(root));  // 3
        System.out.println("Has path sum 12: " + sol.hasPathSum(root, 12));  // true
    }
}
