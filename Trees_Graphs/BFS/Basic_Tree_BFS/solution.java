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
