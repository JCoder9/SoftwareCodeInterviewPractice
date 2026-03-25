/**
 * Knapsack DP - Coin Change II (Count Ways)
 * 
 * The Rule: Count HOW MANY ways to make the target amount.
 * 
 * Related LeetCode Problems:
 * - LC 518: Coin Change II (Medium)
 * - LC 377: Combination Sum IV (Medium)
 * 
 * Key Insight: Order matters!
 * - Combinations: Loop coins FIRST
 * - Permutations: Loop amount FIRST
 * 
 * Time Complexity: O(n * amount)
 * Space Complexity: O(amount)
 */

public class CoinChangeCount {
    
    /**
     * LC 518: Coin Change II - count ways (COMBINATIONS).
     * IMPORTANT: Loop coins first!
     */
    public static int change(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        
        // IMPORTANT: Loop coins first for combinations
        for (int coin : coins) {
            for (int a = coin; a <= amount; a++) {
                dp[a] += dp[a - coin];
            }
        }
        
        return dp[amount];
    }
    
    /**
     * LC 377: Combination Sum IV - count ways (PERMUTATIONS).
     * IMPORTANT: Loop amount first!
     */
    public static int combinationSumPermutations(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        
        // IMPORTANT: Loop amount first for permutations
        for (int a = 1; a <= amount; a++) {
            for (int coin : coins) {
                if (coin <= a) {
                    dp[a] += dp[a - coin];
                }
            }
        }
        
        return dp[amount];
    }
    
    /**
     * 2D version for clarity.
     */
    public static int change2D(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        
        for (int i = 1; i <= n; i++) {
            for (int a = 0; a <= amount; a++) {
                dp[i][a] = dp[i-1][a];
                
                if (coins[i-1] <= a) {
                    dp[i][a] += dp[i][a - coins[i-1]];
                }
            }
        }
        
        return dp[n][amount];
    }
    
    public static void main(String[] args) {
        int[] coins1 = {1, 2, 5};
        int amount1 = 5;
        System.out.println("Ways (combinations): " + change(coins1, amount1)); // 4
        
        int[] coins2 = {2};
        int amount2 = 3;
        System.out.println("Ways: " + change(coins2, amount2)); // 0
        
        int[] coins3 = {10};
        int amount3 = 10;
        System.out.println("Ways: " + change(coins3, amount3)); // 1
        
        System.out.println("\nPermutations: " + combinationSumPermutations(coins1, amount1));
        
        System.out.println("\n2D version: " + change2D(coins1, amount1));
    }
}
