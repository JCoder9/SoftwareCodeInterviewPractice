/**
 * Contains Duplicate - Boolean Check Pattern
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

import java.util.*;

public class Solution {
    
    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        
        for (int num : nums) {
            if (seen.contains(num)) {
                return true;
            }
            seen.add(num);
        }
        
        return false;
    }
    
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> window = new HashSet<>();
        
        for (int i = 0; i < nums.length; i++) {
            if (window.contains(nums[i])) {
                return true;
            }
            
            window.add(nums[i]);
            
            if (window.size() > k) {
                window.remove(nums[i - k]);
            }
        }
        
        return false;
    }
    
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        if (valueDiff < 0) return false;
        
        long bucketSize = (long) valueDiff + 1;
        Map<Long, Long> buckets = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            long num = nums[i];
            long bucketId = getBucketId(num, bucketSize);
            
            if (buckets.containsKey(bucketId)) {
                return true;
            }
            
            if (buckets.containsKey(bucketId - 1) && 
                Math.abs(num - buckets.get(bucketId - 1)) <= valueDiff) {
                return true;
            }
            
            if (buckets.containsKey(bucketId + 1) && 
                Math.abs(num - buckets.get(bucketId + 1)) <= valueDiff) {
                return true;
            }
            
            buckets.put(bucketId, num);
            
            if (i >= indexDiff) {
                buckets.remove(getBucketId(nums[i - indexDiff], bucketSize));
            }
        }
        
        return false;
    }
    
    private static long getBucketId(long num, long bucketSize) {
        return num < 0 ? (num + 1) / bucketSize - 1 : num / bucketSize;
    }
    
    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        
        for (int num : nums) {
            int index = Math.abs(num) - 1;
            
            if (nums[index] < 0) {
                result.add(Math.abs(num));
            } else {
                nums[index] = -nums[index];
            }
        }
        
        return result;
    }
    
    public static int repeatedNTimes(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        
        for (int num : nums) {
            if (seen.contains(num)) {
                return num;
            }
            seen.add(num);
        }
        
        return -1;
    }
    
    public static void main(String[] args) {
        System.out.println("Contains Duplicate: " + containsDuplicate(new int[]{1,2,3,1}));
        System.out.println("Contains Nearby Duplicate: " + containsNearbyDuplicate(new int[]{1,2,3,1}, 3));
        System.out.println("Contains Nearby Almost Duplicate: " + containsNearbyAlmostDuplicate(new int[]{1,2,3,1}, 3, 0));
        System.out.println("Find Duplicates: " + findDuplicates(new int[]{4,3,2,7,8,2,3,1}));
        System.out.println("Repeated N Times: " + repeatedNTimes(new int[]{1,2,3,3}));
    }
}
