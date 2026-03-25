import java.util.Stack;

public class LargestRectangle {
    /**
     * Monotonic Stack - Largest Rectangle in Histogram
     * Time: O(n), Space: O(n)
     */
    public static int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int n = heights.length;
        
        for (int i = 0; i < n; i++) {
            // Pop taller bars and calculate area
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                int heightIdx = stack.pop();
                int height = heights[heightIdx];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        
        // Process remaining bars
        while (!stack.isEmpty()) {
            int heightIdx = stack.pop();
            int height = heights[heightIdx];
            int width = stack.isEmpty() ? n : n - stack.peek() - 1;
            maxArea = Math.max(maxArea, height * width);
        }
        
        return maxArea;
    }
    
    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleArea(heights)); // 10
    }
}
