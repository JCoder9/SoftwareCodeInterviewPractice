/**
 * 2D DP - String/Sequence Matching
 * 
 * Time Complexity: O(m * n)
 * Space Complexity: O(n) optimized
 */

public class Solution {
    
    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[] prev = new int[n + 1];
        
        for (int i = 1; i <= m; i++) {
            int[] curr = new int[n + 1];
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    curr[j] = prev[j - 1] + 1;
                } else {
                    curr[j] = Math.max(prev[j], curr[j - 1]);
                }
            }
            prev = curr;
        }
        
        return prev[n];
    }
    
    public static int editDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[] prev = new int[n + 1];
        
        for (int j = 0; j <= n; j++) {
            prev[j] = j;
        }
        
        for (int i = 1; i <= m; i++) {
            int[] curr = new int[n + 1];
            curr[0] = i;
            
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    curr[j] = prev[j - 1];
                } else {
                    curr[j] = 1 + Math.min(Math.min(prev[j], curr[j - 1]), prev[j - 1]);
                }
            }
            
            prev = curr;
        }
        
        return prev[n];
    }
    
    public static int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        int[] prev = new int[n + 1];
        prev[0] = 1;
        
        for (int i = 1; i <= m; i++) {
            int[] curr = new int[n + 1];
            curr[0] = 1;
            
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    curr[j] = prev[j - 1] + prev[j];
                } else {
                    curr[j] = prev[j];
                }
            }
            
            prev = curr;
        }
        
        return prev[n];
    }

    public static void main(String[] args) {
        System.out.println("LCS('abcde', 'ace'): " + longestCommonSubsequence("abcde", "ace"));
        System.out.println("Edit distance('horse', 'ros'): " + editDistance("horse", "ros"));
        System.out.println("Num distinct('rabbbit', 'rabbit'): " + numDistinct("rabbbit", "rabbit"));
    }
}
