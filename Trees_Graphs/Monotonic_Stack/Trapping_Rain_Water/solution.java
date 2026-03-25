import java.util.Stack;

public class TrappingRainWater {
    /**
     * Monotonic Stack - Trapping Rain Water
     * Time: O(n), Space: O(n)
     */
    public static int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        
        int n = height.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        
        // Build left max array
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i-1], height[i]);
        }
        
        // Build right max array
        rightMax[n-1] = height[n-1];
        for (int i = n-2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i+1], height[i]);
        }
        
        // Calculate water
        int water = 0;
        for (int i = 0; i < n; i++) {
            water += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        
        return water;
    }
    
    // Stack-based solution
    public static int trapStack(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int water = 0;
        
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int bottom = stack.pop();
                
                if (stack.isEmpty()) break;
                
                int distance = i - stack.peek() - 1;
                int boundedHeight = Math.min(height[i], 
                                            height[stack.peek()]) - height[bottom];
                water += distance * boundedHeight;
            }
            stack.push(i);
        }
        
        return water;
    }
    
    public static void main(String[] args) {
        int[] heights = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(heights));       // 6
        System.out.println(trapStack(heights));  // 6
    }
}
