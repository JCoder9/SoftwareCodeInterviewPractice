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

import java.util.*;

public class PathTracking {
    
    // TreeNode definition
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    // LC 257: Binary Tree Paths
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        dfsPaths(root, new ArrayList<>(), result);
        return result;
    }
    
    private void dfsPaths(TreeNode node, List<String> path, List<String> result) {
        if (node == null) {
            return;
        }
        
        path.add(String.valueOf(node.val));
        
        if (node.left == null && node.right == null) {
            result.add(String.join("->", path));
        } else {
            dfsPaths(node.left, path, result);
            dfsPaths(node.right, path, result);
        }
        
        path.remove(path.size() - 1);  // Backtrack
    }
    
    // LC 113: Path Sum II
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        dfsPathSum(root, targetSum, new ArrayList<>(), 0, result);
        return result;
    }
    
    private void dfsPathSum(TreeNode node, int targetSum, List<Integer> path, 
                             int currentSum, List<List<Integer>> result) {
        if (node == null) {
            return;
        }
        
        path.add(node.val);
        currentSum += node.val;
        
        if (node.left == null && node.right == null && currentSum == targetSum) {
            result.add(new ArrayList<>(path));  // Add copy
        }
        
        dfsPathSum(node.left, targetSum, path, currentSum, result);
        dfsPathSum(node.right, targetSum, path, currentSum, result);
        
        path.remove(path.size() - 1);  // Backtrack
    }
    
    // LC 129: Sum Root to Leaf Numbers
    public int sumNumbers(TreeNode root) {
        return dfsSumNumbers(root, 0);
    }
    
    private int dfsSumNumbers(TreeNode node, int currentNum) {
        if (node == null) {
            return 0;
        }
        
        currentNum = currentNum * 10 + node.val;
        
        if (node.left == null && node.right == null) {
            return currentNum;
        }
        
        return dfsSumNumbers(node.left, currentNum) + dfsSumNumbers(node.right, currentNum);
    }
    
    // LC 437: Path Sum III
    public int pathSumIII(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        
        return dfsFromNode(root, targetSum, 0) + 
               pathSumIII(root.left, targetSum) + 
               pathSumIII(root.right, targetSum);
    }
    
    private int dfsFromNode(TreeNode node, int targetSum, long currentSum) {
        if (node == null) {
            return 0;
        }
        
        currentSum += node.val;
        int count = currentSum == targetSum ? 1 : 0;
        
        count += dfsFromNode(node.left, targetSum, currentSum);
        count += dfsFromNode(node.right, targetSum, currentSum);
        
        return count;
    }
    
    // LC 437: Path Sum III (optimized with prefix sum)
    public int pathSumIIIOptimized(TreeNode root, int targetSum) {
        Map<Long, Integer> prefixSums = new HashMap<>();
        prefixSums.put(0L, 1);
        return dfsOptimized(root, targetSum, 0, prefixSums);
    }
    
    private int dfsOptimized(TreeNode node, int targetSum, long currentSum, 
                              Map<Long, Integer> prefixSums) {
        if (node == null) {
            return 0;
        }
        
        currentSum += node.val;
        int count = prefixSums.getOrDefault(currentSum - targetSum, 0);
        
        prefixSums.put(currentSum, prefixSums.getOrDefault(currentSum, 0) + 1);
        
        count += dfsOptimized(node.left, targetSum, currentSum, prefixSums);
        count += dfsOptimized(node.right, targetSum, currentSum, prefixSums);
        
        prefixSums.put(currentSum, prefixSums.get(currentSum) - 1);  // Backtrack
        
        return count;
    }
    
    // Test
    public static void main(String[] args) {
        PathTracking solution = new PathTracking();
        
        // Create test tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        
        // Test binaryTreePaths
        System.out.println("Testing binaryTreePaths:");
        System.out.println(solution.binaryTreePaths(root));  // ["1->2->5", "1->3"]
        
        // Test sumNumbers
        System.out.println("\nTesting sumNumbers:");
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        System.out.println(solution.sumNumbers(root2));  // 25
        
        // Test pathSumIII
        System.out.println("\nTesting pathSumIII:");
        TreeNode root3 = new TreeNode(10);
        root3.left = new TreeNode(5);
        root3.right = new TreeNode(-3);
        root3.left.left = new TreeNode(3);
        root3.left.right = new TreeNode(2);
        root3.right.right = new TreeNode(11);
        System.out.println(solution.pathSumIII(root3, 8));  // 3
        System.out.println(solution.pathSumIIIOptimized(root3, 8));  // 3
    }
}
