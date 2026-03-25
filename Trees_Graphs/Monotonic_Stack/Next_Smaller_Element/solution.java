import java.util.Arrays;
import java.util.Stack;

public class NextSmaller {
    /**
     * Monotonic Stack - Next Smaller Element
     * Time: O(n), Space: O(n)
     */
    public static int[] nextSmallerElement(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        Arrays.fill(result, -1);
        
        for (int i = 0; i < n; i++) {
            // Pop larger elements
            while (!stack.isEmpty() && nums[i] < nums[stack.peek()]) {
                int idx = stack.pop();
                result[idx] = nums[i];
            }
            stack.push(i);
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        int[] nums = {4, 2, 1, 5, 3};
        System.out.println(Arrays.toString(nextSmallerElement(nums)));
        // [2, 1, -1, 3, -1]
    }
}
