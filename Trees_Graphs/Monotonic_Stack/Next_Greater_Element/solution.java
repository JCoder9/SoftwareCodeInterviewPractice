import java.util.Arrays;
import java.util.Stack;

public class MonotonicStack {
    /**
     * Monotonic Stack - Next Greater Element
     * Time: O(n), Space: O(n)
     */
    public static int[] nextGreaterElement(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        // Initialize result with -1
        Arrays.fill(result, -1);
        
        for (int i = 0; i < n; i++) {
            // Pop smaller elements - nums[i] is their next greater
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                int idx = stack.pop();
                result[idx] = nums[i];
            }
            stack.push(i);
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        int[] nums = {2, 1, 2, 4, 3};
        int[] result = nextGreaterElement(nums);
        System.out.println(Arrays.toString(result));  // [4, 2, 4, -1, -1]
    }
}
