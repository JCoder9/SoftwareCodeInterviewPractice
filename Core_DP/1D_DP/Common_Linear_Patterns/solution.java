/**
 * 1D Dynamic Programming - Core Patterns
 * 
 * Time Complexity: O(n) for most
 * Space Complexity: O(1) optimized
 */

public class Solution {
    
    public static int climbStairs(int n) {
        if (n <= 2) return n;
        
        int prev2 = 1, prev1 = 2;
        for (int i = 3; i <= n; i++) {
            int curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        
        return prev1;
    }
    
    public static int robHouses(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        
        int prev2 = 0, prev1 = 0;
        for (int num : nums) {
            int curr = Math.max(prev1, num + prev2);
            prev2 = prev1;
            prev1 = curr;
        }
        
        return prev1;
    }
    
    public static boolean canJump(int[] nums) {
        int maxReach = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > maxReach) return false;
            maxReach = Math.max(maxReach, i + nums[i]);
        }
        return true;
    }
    
    public static int minJumps(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;
        
        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;
        
        for (int i = 0; i < n - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
                
                if (currentEnd >= n - 1) break;
            }
        }
        
        return jumps;
    }
    
    public static int decodeWays(String s) {
        if (s.isEmpty() || s.charAt(0) == '0') return 0;
        
        int n = s.length();
        int prev2 = 1, prev1 = 1;
        
        for (int i = 1; i < n; i++) {
            int curr = 0;
            
            if (s.charAt(i) != '0') {
                curr += prev1;
            }
            
            int twoDigit = Integer.parseInt(s.substring(i - 1, i + 1));
            if (twoDigit >= 10 && twoDigit <= 26) {
                curr += prev2;
            }
            
            prev2 = prev1;
            prev1 = curr;
        }
        
        return prev1;
    }

    public static void main(String[] args) {
        System.out.println("Climb stairs (5): " + climbStairs(5));
        System.out.println("Rob houses [1,2,3,1]: " + robHouses(new int[]{1, 2, 3, 1}));
        System.out.println("Can jump [2,3,1,1,4]: " + canJump(new int[]{2, 3, 1, 1, 4}));
        System.out.println("Min jumps [2,3,1,1,4]: " + minJumps(new int[]{2, 3, 1, 1, 4}));
        System.out.println("Decode ways '226': " + decodeWays("226"));
    }
}
