public class SplitArray {
    /**
     * Binary Search on Answer - Minimize the Maximum
     * Time: O(n * log(sum - max)), Space: O(1)
     */
    public int splitArray(int[] nums, int k) {
        int left = 0;
        int right = 0;
        
        for (int num : nums) {
            left = Math.max(left, num);
            right += num;
        }
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (canSplit(nums, k, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return left;
    }
    
    private boolean canSplit(int[] nums, int k, int maxSum) {
        int groups = 1;
        int currentSum = 0;
        
        for (int num : nums) {
            if (currentSum + num > maxSum) {
                groups++;
                currentSum = num;
                if (groups > k) {
                    return false;
                }
            } else {
                currentSum += num;
            }
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        SplitArray sa = new SplitArray();
        System.out.println(sa.splitArray(new int[]{7, 2, 5, 10, 8}, 2));  // 18
    }
}
