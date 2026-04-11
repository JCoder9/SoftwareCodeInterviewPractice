/**
 * BFS - Basic BFS on Binary Tree (Level Order Traversal)
 * 
 * Problem: Traverse tree level by level, visiting all nodes at depth 0, then 1, then 2, etc.
 * 
 * Pattern: Use a queue. Add root, then repeatedly: remove front node, process it, add children.
 * 
 * Related LeetCode Problems:
 * - LC 102: Binary Tree Level Order Traversal (Medium)
 * - LC 107: Binary Tree Level Order Traversal II (Medium)
 * - LC 637: Average of Levels in Binary Tree (Easy)
 * 
 * Time Complexity: O(n) - visit each node once
 * Space Complexity: O(w) - w = max width of tree (worst: all bottom level in queue)
 */

// ───────────────────────────────────────────────────────────────────────────
// NAIVE APPROACH (Brute Force) - O(n²) time | O(n) space
// ───────────────────────────────────────────────────────────────────────────
// INTERVIEW SCRIPT:
//   1. Describe:   "Brute force uses recursive DFS to find max depth, then for each
//                   level 0..depth calls DFS again to collect nodes at that level"
//   2. Problem:    "Visits each node multiple times — O(n × h) can be O(n²) for
//                   skewed trees"
//   3. Transition: "BFS with queue visits each node exactly once — O(n) time and
//                   naturally processes level by level"
//
// public List<List<Integer>> levelOrderNaive(TreeNode root) {
//     List<List<Integer>> result = new ArrayList<>();
//     int depth = maxDepth(root);
//     
//     for (int level = 0; level < depth; level++) {
//         List<Integer> currentLevel = new ArrayList<>();
//         collectLevel(root, level, currentLevel);
//         result.add(currentLevel);
//     }
//     return result;
// }
// 
// private void collectLevel(TreeNode node, int level, List<Integer> result) {
//     if (node == null) return;
//     if (level == 0) {
//         result.add(node.val);
//     } else {
//         collectLevel(node.left, level - 1, result);
//         collectLevel(node.right, level - 1, result);
//     }
// }
// 
// private int maxDepth(TreeNode node) {
//     if (node == null) return 0;
//     return 1 + Math.max(maxDepth(node.left), maxDepth(node.right));
// }
// ───────────────────────────────────────────────────────────────────────────

import java.util.*;

public class BasicTreeBFS {
    
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    // Basic BFS traversal
    public List<Integer> bfsTree(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            result.add(node.val);
            
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        
        return result;
    }
    
    // LC 102: Binary Tree Level Order Traversal
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);
                
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            
            result.add(currentLevel);
        }
        
        return result;
    }
    
    // LC 107: Binary Tree Level Order Traversal II
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = levelOrder(root);
        Collections.reverse(result);
        return result;
    }
    
    // LC 637: Average of Levels in Binary Tree
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            double levelSum = 0;
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                levelSum += node.val;
                
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            
            result.add(levelSum / levelSize);
        }
        
        return result;
    }
    
    // Test
    public static void main(String[] args) {
        BasicTreeBFS solution = new BasicTreeBFS();
        
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        
        System.out.println("Basic BFS: " + solution.bfsTree(root));
        System.out.println("Level Order: " + solution.levelOrder(root));
        System.out.println("Average of Levels: " + solution.averageOfLevels(root));
    }
}
