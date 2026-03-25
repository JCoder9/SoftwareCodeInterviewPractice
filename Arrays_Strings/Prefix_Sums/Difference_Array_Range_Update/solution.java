/**
 * Difference Array - Range Update Pattern
 * 
 * Time Complexity: O(1) per update, O(n) to get result
 * Space Complexity: O(n)
 */

import java.util.*;

class DifferenceArray {
    private int[] diff;
    private int size;
    
    public DifferenceArray(int size) {
        this.diff = new int[size + 1];
        this.size = size;
    }
    
    public void rangeAdd(int left, int right, int val) {
        diff[left] += val;
        diff[right + 1] -= val;
    }
    
    public int[] getResult() {
        int[] result = new int[size];
        int current = 0;
        for (int i = 0; i < size; i++) {
            current += diff[i];
            result[i] = current;
        }
        return result;
    }
}

public class Solution {
    
    public static int[] corpFlightBookings(int[][] bookings, int n) {
        DifferenceArray da = new DifferenceArray(n);
        
        for (int[] booking : bookings) {
            int first = booking[0], last = booking[1], seats = booking[2];
            da.rangeAdd(first - 1, last - 1, seats);
        }
        
        return da.getResult();
    }
    
    public static int[] rangeAddition(int length, int[][] updates) {
        int[] diff = new int[length + 1];
        
        for (int[] update : updates) {
            int start = update[0], end = update[1], inc = update[2];
            diff[start] += inc;
            diff[end + 1] -= inc;
        }
        
        int[] result = new int[length];
        int current = 0;
        for (int i = 0; i < length; i++) {
            current += diff[i];
            result[i] = current;
        }
        
        return result;
    }
    
    public static boolean carPooling(int[][] trips, int capacity) {
        int maxLoc = 0;
        for (int[] trip : trips) {
            maxLoc = Math.max(maxLoc, trip[2]);
        }
        
        int[] diff = new int[maxLoc + 1];
        
        for (int[] trip : trips) {
            int passengers = trip[0], start = trip[1], end = trip[2];
            diff[start] += passengers;
            diff[end] -= passengers;
        }
        
        int current = 0;
        for (int delta : diff) {
            current += delta;
            if (current > capacity) {
                return false;
            }
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        DifferenceArray da = new DifferenceArray(5);
        da.rangeAdd(1, 3, 2);
        da.rangeAdd(0, 2, 3);
        System.out.println("Difference Array: " + Arrays.toString(da.getResult()));
        
        int[][] bookings = {{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
        System.out.println("Flight bookings: " + Arrays.toString(corpFlightBookings(bookings, 5)));
        
        int[][] trips = {{2, 1, 5}, {3, 3, 7}};
        System.out.println("Car Pooling (capacity 5): " + carPooling(trips, 5));
    }
}
