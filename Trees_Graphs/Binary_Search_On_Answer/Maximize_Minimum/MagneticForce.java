/**
 * Binary Search on Answer - Maximize the Minimum (LC 1552)
 * 
 * Problem: Magnetic Force - Place m balls in n positions to maximize min distance.
 *          Example: position = [1,2,3,4,7], m = 3 → 3 (positions 1,4,7)
 * 
 * Pattern: If we can place with min_dist=X, we can with X-1. Binary search for max X.
 * 
 * Related LeetCode Problems:
 * - LC 1552: Magnetic Force Between Two Balls (Medium) ⭐⭐⭐
 * - LC 2064: Minimized Maximum of Products (Medium)
 * - LC 875: Koko Eating Bananas (Medium)
 * 
 * Time Complexity: O(n log n + n × log(max_pos)) - sort + binary search
 * Space Complexity: O(1)
 */

// ─────────────────────────────────────────────────────────────────────────────
// NAIVE APPROACH (Brute Force) — O(C(n,m) × m) = exponential time | O(m) space
// ─────────────────────────────────────────────────────────────────────────────
// INTERVIEW SCRIPT:
//   1. Describe:   "Brute force tries all ways to choose m positions from n,
//                  computes min distance for each — C(n,m) combinations"
//   2. Problem:    "For n=100, m=50: ~10^29 combinations to check"
//   3. Transition: "Binary search on min_distance with greedy validation —
//                  O(n log max_dist)"
//
// // Conceptual - would use combinations to generate all selections
// public int maxDistanceNaive(int[] position, int m) {
//     Arrays.sort(position);
//     int maxMinDist = 0;
//     // Try all ways to choose m positions from n
//     // For each combination, compute minimum distance between consecutive chosen
//     // Track maximum of all minimum distances
//     return maxMinDist;
// }
// ─────────────────────────────────────────────────────────────────────────────

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
