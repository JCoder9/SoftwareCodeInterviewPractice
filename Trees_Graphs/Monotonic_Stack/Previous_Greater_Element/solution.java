import java.util.Arrays;
import java.util.Stack;

public class PreviousGreater {
    /**
     * Monotonic Stack - Previous Greater Element
     * Time: O(n), Space: O(n)
     */
    public static int[] previousGreaterElement(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        Arrays.fill(result, -1);
        
        for (int i = 0; i < n; i++) {
            // Remove smaller or equal elements
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                stack.pop();
            }
            
            // Stack top is previous greater
            if (!stack.isEmpty()) {
                result[i] = nums[stack.peek()];
            }
            
            stack.push(i);
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        int[] nums = {4, 2, 3, 1, 5};
        System.out.println(Arrays.toString(previousGreaterElement(nums)));
        // [-1, 4, 4, 3, -1]
    }
}
