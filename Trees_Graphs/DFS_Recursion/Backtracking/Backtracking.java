/**
 * DFS Recursion - Backtracking Pattern
 * 
 * Problem: Generate all possible subsets, permutations, or combinations.
 *          Example: subsets([1,2,3]) → [[], [1], [2], [3], [1,2], [1,3], [2,3], [1,2,3]]
 * 
 * Pattern: Choose → Explore → Un-choose (backtrack)
 * 
 * Related LeetCode Problems:
 * - LC 78: Subsets (Medium) ⭐⭐⭐
 * - LC 46: Permutations (Medium) ⭐⭐⭐
 * - LC 77: Combinations (Medium)
 * - LC 39: Combination Sum (Medium)
 * - LC 17: Letter Combinations of a Phone Number (Medium)
 * 
 * Time Complexity: O(n × 2^n) for subsets, O(n × n!) for permutations
 * Space Complexity: O(n) for recursion depth
 */

// ─────────────────────────────────────────────────────────────────────────────
// NAIVE APPROACH (Brute Force) — O(2^n × n) time | O(2^n × n) space
// ─────────────────────────────────────────────────────────────────────────────
// INTERVIEW SCRIPT:
//   1. Describe:   "Brute force uses bit manipulation for 2^n subsets, checks each bit
//                  to include/exclude elements — O(2^n × n)"
//   2. Problem:    "For n=20: 1M subsets; creates all upfront; no pruning for
//                  constraints"
//   3. Transition: "Backtracking explores incrementally with pruning — same O(2^n)
//                  but more flexible and memory-efficient"
//
// public List<List<Integer>> subsetsNaive(int[] nums) {
//     List<List<Integer>> result = new ArrayList<>();
//     int n = nums.length;
//     // Generate all 2^n bit patterns
//     for (int mask = 0; mask < (1 << n); mask++) {
//         List<Integer> subset = new ArrayList<>();
//         for (int i = 0; i < n; i++) {
//             if ((mask & (1 << i)) != 0) {
//                 subset.add(nums[i]);
//             }
//         }
//         result.add(subset);
//     }
//     return result;
// }
// ─────────────────────────────────────────────────────────────────────────────

import java.util.*;

public class Backtracking {
    
    // LC 78: Subsets
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackSubsets(result, new ArrayList<>(), nums, 0);
        return result;
    }
    
    private void backtrackSubsets(List<List<Integer>> result, List<Integer> path, int[] nums, int start) {
        result.add(new ArrayList<>(path));  // Add copy
        
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);  // Choose
            backtrackSubsets(result, path, nums, i + 1);  // Explore
            path.remove(path.size() - 1);  // Un-choose
        }
    }
    
    // LC 46: Permutations (in-place with swapping)
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackPermute(result, nums, 0);
        return result;
    }
    
    private void backtrackPermute(List<List<Integer>> result, int[] nums, int start) {
        if (start == nums.length) {
            List<Integer> perm = new ArrayList<>();
            for (int num : nums) {
                perm.add(num);
            }
            result.add(perm);
            return;
        }
        
        for (int i = start; i < nums.length; i++) {
            swap(nums, start, i);  // Choose
            backtrackPermute(result, nums, start + 1);  // Explore
            swap(nums, start, i);  // Un-choose
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    // LC 77: Combinations
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackCombine(result, new ArrayList<>(), n, k, 1);
        return result;
    }
    
    private void backtrackCombine(List<List<Integer>> result, List<Integer> path, int n, int k, int start) {
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }
        
        for (int i = start; i <= n; i++) {
            path.add(i);
            backtrackCombine(result, path, n, k, i + 1);
            path.remove(path.size() - 1);
        }
    }
    
    // LC 39: Combination Sum
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackCombSum(result, new ArrayList<>(), candidates, target, 0, 0);
        return result;
    }
    
    private void backtrackCombSum(List<List<Integer>> result, List<Integer> path, 
                                   int[] candidates, int target, int total, int start) {
        if (total == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        if (total > target) {
            return;
        }
        
        for (int i = start; i < candidates.length; i++) {
            path.add(candidates[i]);
            backtrackCombSum(result, path, candidates, target, total + candidates[i], i);  // i, not i+1
            path.remove(path.size() - 1);
        }
    }
    
    // LC 17: Letter Combinations of a Phone Number
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        
        Map<Character, String> phone = new HashMap<>();
        phone.put('2', "abc");
        phone.put('3', "def");
        phone.put('4', "ghi");
        phone.put('5', "jkl");
        phone.put('6', "mno");
        phone.put('7', "pqrs");
        phone.put('8', "tuv");
        phone.put('9', "wxyz");
        
        backtrackLetters(result, new StringBuilder(), digits, phone, 0);
        return result;
    }
    
    private void backtrackLetters(List<String> result, StringBuilder path, String digits,
                                   Map<Character, String> phone, int index) {
        if (index == digits.length()) {
            result.add(path.toString());
            return;
        }
        
        String letters = phone.get(digits.charAt(index));
        for (char letter : letters.toCharArray()) {
            path.append(letter);
            backtrackLetters(result, path, digits, phone, index + 1);
            path.deleteCharAt(path.length() - 1);
        }
    }
    
    // Test
    public static void main(String[] args) {
        Backtracking solution = new Backtracking();
        
        // Test subsets
        System.out.println("Testing subsets:");
        System.out.println(solution.subsets(new int[]{1, 2, 3}));
        
        // Test permute
        System.out.println("\nTesting permute:");
        System.out.println(solution.permute(new int[]{1, 2, 3}));
        
        // Test combine
        System.out.println("\nTesting combine:");
        System.out.println(solution.combine(4, 2));
        
        // Test combinationSum
        System.out.println("\nTesting combinationSum:");
        System.out.println(solution.combinationSum(new int[]{2, 3, 6, 7}, 7));
        
        // Test letterCombinations
        System.out.println("\nTesting letterCombinations:");
        System.out.println(solution.letterCombinations("23"));
    }
}
