/**
 * Fast/Slow Pointers - Happy Number Pattern
 * 
 * Related LeetCode Problems:
 * - LC 202: Happy Number (Easy)
 * - LC 258: Add Digits (Easy)
 * - LC 263, 264: Ugly Number I & II
 * 
 * Time: O(log n), Space: O(1)
 */

class Solution {
    /**
     * LC 202: Happy Number
     */
    public boolean isHappy(int n) {
        int slow = n;
        int fast = n;
        
        while (true) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
            
            if (fast == 1) {
                return true;
            }
            
            if (slow == fast) {
                return false;
            }
        }
    }
    
    private int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int digit = n % 10;
            totalSum += digit * digit;
            n /= 10;
        }
        return totalSum;
    }
    
    /**
     * LC 258: Add Digits
     */
    public int addDigits(int num) {
        while (num >= 10) {
            num = getSum(num);
        }
        return num;
    }
    
    private int getSum(int n) {
        int total = 0;
        while (n > 0) {
            total += n % 10;
            n /= 10;
        }
        return total;
    }
    
    /**
     * LC 258: Mathematical solution
     */
    public int addDigitsMath(int num) {
        if (num == 0) return 0;
        if (num % 9 == 0) return 9;
        return num % 9;
    }
    
    /**
     * LC 263: Ugly Number
     */
    public boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }
        
        for (int factor : new int[]{2, 3, 5}) {
            while (n % factor == 0) {
                n /= factor;
            }
        }
        
        return n == 1;
    }
    
    /**
     * LC 264: Ugly Number II
     */
    public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        
        int i2 = 0, i3 = 0, i5 = 0;
        
        for (int i = 1; i < n; i++) {
            int next2 = ugly[i2] * 2;
            int next3 = ugly[i3] * 3;
            int next5 = ugly[i5] * 5;
            
            int nextUgly = Math.min(next2, Math.min(next3, next5));
            ugly[i] = nextUgly;
            
            if (nextUgly == next2) i2++;
            if (nextUgly == next3) i3++;
            if (nextUgly == next5) i5++;
        }
        
        return ugly[n - 1];
    }
    
    /**
     * LC 1812: Determine Color of a Chessboard Square
     */
    public boolean squareIsWhite(String coordinates) {
        int col = coordinates.charAt(0) - 'a' + 1;
        int row = coordinates.charAt(1) - '0';
        
        return (col + row) % 2 == 1;
    }
    
    // Test
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // Test isHappy
        System.out.println("Testing isHappy:");
        System.out.println("19 is happy: " + sol.isHappy(19));  // true
        System.out.println("2 is happy: " + sol.isHappy(2));    // false
        
        // Test addDigits
        System.out.println("\nTesting addDigits:");
        System.out.println("addDigits(38): " + sol.addDigits(38));  // 2
        System.out.println("addDigits(38) math: " + sol.addDigitsMath(38));  // 2
        
        // Test isUgly
        System.out.println("\nTesting isUgly:");
        System.out.println("6 is ugly: " + sol.isUgly(6));    // true
        System.out.println("14 is ugly: " + sol.isUgly(14));  // false
        
        // Test nthUglyNumber
        System.out.println("\nTesting nthUglyNumber:");
        System.out.println("10th ugly number: " + sol.nthUglyNumber(10));  // 12
    }
}
