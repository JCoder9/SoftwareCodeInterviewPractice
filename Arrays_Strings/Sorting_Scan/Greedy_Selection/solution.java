/**
 * Sort + Greedy Selection Pattern
 * 
 * Time Complexity: O(n log n)
 * Space Complexity: O(1) to O(n)
 */

import java.util.*;

public class Solution {
    
    public static int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        
        int totalUnits = 0;
        
        for (int[] box : boxTypes) {
            if (truckSize <= 0) break;
            
            int boxesToTake = Math.min(box[0], truckSize);
            totalUnits += boxesToTake * box[1];
            truckSize -= boxesToTake;
        }
        
        return totalUnits;
    }
    
    public static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        
        int child = 0, cookie = 0;
        
        while (child < g.length && cookie < s.length) {
            if (s[cookie] >= g[child]) {
                child++;
            }
            cookie++;
        }
        
        return child;
    }
    
    public static int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        
        int left = 0, right = people.length - 1;
        int boats = 0;
        
        while (left <= right) {
            if (people[left] + people[right] <= limit) {
                left++;
            }
            right--;
            boats++;
        }
        
        return boats;
    }
    
    public static boolean carPooling(int[][] trips, int capacity) {
        List<int[]> events = new ArrayList<>();
        
        for (int[] trip : trips) {
            events.add(new int[]{trip[1], trip[0]});
            events.add(new int[]{trip[2], -trip[0]});
        }
        
        events.sort((a, b) -> a[0] - b[0]);
        
        int currentPassengers = 0;
        for (int[] event : events) {
            currentPassengers += event[1];
            if (currentPassengers > capacity) return false;
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        System.out.println("Max Units: " + maximumUnits(new int[][]{{1,3},{2,2},{3,1}}, 4));
        System.out.println("Assign Cookies: " + findContentChildren(new int[]{1,2,3}, new int[]{1,1}));
        System.out.println("Boats: " + numRescueBoats(new int[]{1,2}, 3));
        System.out.println("Car Pooling: " + carPooling(new int[][]{{2,1,5},{3,3,7}}, 4));
    }
}
