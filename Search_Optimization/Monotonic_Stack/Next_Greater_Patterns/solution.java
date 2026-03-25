/**
 * Monotonic Stack - Next Greater/Smaller Elements
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

import java.util.*;

public class Solution {
    
    public static int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                result[stack.pop()] = nums[i];
            }
            stack.push(i);
        }
        
        return result;
    }
    
    public static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int prevIdx = stack.pop();
                result[prevIdx] = i - prevIdx;
            }
            stack.push(i);
        }
        
        return result;
    }
    
    public static int largestRectangleHistogram(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        
        for (int i = 0; i <= heights.length; i++) {
            int h = (i == heights.length) ? 0 : heights[i];
            
            while (!stack.isEmpty() && heights[stack.peek()] > h) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            
            stack.push(i);
        }
        
        return maxArea;
    }
    
    public static int trapRainWater(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int water = 0;
        
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int bottom = stack.pop();
                
                if (stack.isEmpty()) break;
                
                int distance = i - stack.peek() - 1;
                int boundedHeight = Math.min(height[stack.peek()], height[i]) - height[bottom];
                water += distance * boundedHeight;
            }
            
            stack.push(i);
        }
        
        return water;
    }

    public static void main(String[] args) {
        System.out.println("Next greater: " + Arrays.toString(nextGreaterElements(new int[]{2,1,2,4,3})));
        System.out.println("Daily temperatures: " + Arrays.toString(dailyTemperatures(new int[]{73,74,75,71,69,72,76,73})));
        System.out.println("Largest rectangle: " + largestRectangleHistogram(new int[]{2,1,5,6,2,3}));
        System.out.println("Trap rain water: " + trapRainWater(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}
