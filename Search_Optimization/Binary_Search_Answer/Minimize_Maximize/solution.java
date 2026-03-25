/**
 * Binary Search - On Answer Space
 * 
 * Time Complexity: O(n log(max - min))
 * Space Complexity: O(1)
 */

public class Solution {
    
    public static int minEatingSpeed(int[] piles, int h) {
        int left = 1, right = 0;
        for (int pile : piles) {
            right = Math.max(right, pile);
        }
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canFinish(piles, h, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return left;
    }
    
    private static boolean canFinish(int[] piles, int h, int speed) {
        int hours = 0;
        for (int pile : piles) {
            hours += (pile + speed - 1) / speed;  // Ceiling division
        }
        return hours <= h;
    }
    
    public static int shipWithinDays(int[] weights, int days) {
        int left = 0, right = 0;
        for (int weight : weights) {
            left = Math.max(left, weight);
            right += weight;
        }
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canShip(weights, days, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return left;
    }
    
    private static boolean canShip(int[] weights, int days, int capacity) {
        int daysNeeded = 1;
        int currentWeight = 0;
        
        for (int weight : weights) {
            if (currentWeight + weight > capacity) {
                daysNeeded++;
                currentWeight = weight;
            } else {
                currentWeight += weight;
            }
        }
        
        return daysNeeded <= days;
    }
    
    public static int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }

    public static void main(String[] args) {
        System.out.println("Min eating speed: " + minEatingSpeed(new int[]{3,6,7,11}, 8));
        System.out.println("Min ship capacity: " + shipWithinDays(new int[]{1,2,3,4,5,6,7,8,9,10}, 5));
        System.out.println("Find peak: " + findPeakElement(new int[]{1,2,3,1}));
    }
}
