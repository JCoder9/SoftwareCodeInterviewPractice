public class MinimumSpeed {
    /**
     * Binary Search on Answer - Binary Search with Constraints
     * Time: O(n * log(max_speed)), Space: O(1)
     */
    public int minSpeedOnTime(int[] dist, double hour) {
        // Edge case
        if (dist.length > Math.ceil(hour)) {
            return -1;
        }
        
        int left = 1;
        int right = 10_000_000;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (canArrive(dist, hour, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return left;
    }
    
    private boolean canArrive(int[] dist, double hour, int speed) {
        double time = 0;
        
        for (int i = 0; i < dist.length - 1; i++) {
            time += Math.ceil((double) dist[i] / speed);
        }
        
        time += (double) dist[dist.length - 1] / speed;
        
        return time <= hour;
    }
    
    public static void main(String[] args) {
        MinimumSpeed ms = new MinimumSpeed();
        System.out.println(ms.minSpeedOnTime(new int[]{1, 3, 2}, 6.0));    // 1
        System.out.println(ms.minSpeedOnTime(new int[]{1, 3, 2}, 2.7));    // 3
    }
}
