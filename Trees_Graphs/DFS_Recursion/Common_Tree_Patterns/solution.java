/**
 * DFS Recursion - Tree/Graph Pattern
 * 
 * Time Complexity: O(n) to visit all nodes
 * Space Complexity: O(h) for recursion stack
 */

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

public class Solution {
    
    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
    
    public static boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }
    
    private static int height(TreeNode node) {
        if (node == null) return 0;
        
        int leftHeight = height(node.left);
        if (leftHeight == -1) return -1;
        
        int rightHeight = height(node.right);
        if (rightHeight == -1) return -1;
        
        if (Math.abs(leftHeight - rightHeight) > 1) return -1;
        
        return 1 + Math.max(leftHeight, rightHeight);
    }
    
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }
        
        int remaining = targetSum - root.val;
        return hasPathSum(root.left, remaining) || hasPathSum(root.right, remaining);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        
        System.out.println("Max Depth: " + maxDepth(root));
        System.out.println("Is Balanced: " + isBalanced(root));
        System.out.println("Has Path Sum (28): " + hasPathSum(root, 28));
    }
}
