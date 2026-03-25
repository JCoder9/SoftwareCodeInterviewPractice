import java.util.Arrays;

public class MagneticForce {
    /**
     * Binary Search on Answer - Maximize the Minimum
     * Time: O(n log n + n * log(max_pos - min_pos)), Space: O(1)
     */
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        
        int left = 1;
        int right = position[position.length - 1] - position[0];
        
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            
            if (canPlace(position, m, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        
        return left;
    }
    
    private boolean canPlace(int[] position, int m, int minDist) {
        int count = 1;
        int lastPos = position[0];
        
        for (int i = 1; i < position.length; i++) {
            if (position[i] - lastPos >= minDist) {
                count++;
                lastPos = position[i];
                if (count == m) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public static void main(String[] args) {
        MagneticForce mf = new MagneticForce();
        System.out.println(mf.maxDistance(new int[]{1, 2, 3, 4, 7}, 3));  // 3
    }
}
