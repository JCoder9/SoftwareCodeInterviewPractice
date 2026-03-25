public class KokoEatingBananas {
    /**
     * Binary Search on Answer - Capacity/Resource Allocation
     * Time: O(n * log(max_pile)), Space: O(1)
     */
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 0;
        
        for (int pile : piles) {
            right = Math.max(right, pile);
        }
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (canFinish(piles, h, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return left;
    }
    
    private boolean canFinish(int[] piles, int h, int speed) {
        long hours = 0;
        
        for (int pile : piles) {
            hours += (pile + speed - 1) / speed;  // Ceiling division
            if (hours > h) {
                return false;
            }
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        KokoEatingBananas keb = new KokoEatingBananas();
        System.out.println(keb.minEatingSpeed(new int[]{3, 6, 7, 11}, 8));  // 4
    }
}
