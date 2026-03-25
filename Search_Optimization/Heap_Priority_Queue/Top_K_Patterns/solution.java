/**
 * Heap / Priority Queue - Top K and K-Way Merge
 * 
 * Time Complexity: O(n log k)
 * Space Complexity: O(k)
 */

import java.util.*;

public class Solution {
    
    public static int kthLargestElement(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        
        return minHeap.peek();
    }
    
    public static List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = 
            new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());
        
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        
        List<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll().getKey());
        }
        
        return result;
    }
    
    public static int[][] kClosestPoints(int[][] points, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> (b[0]*b[0] + b[1]*b[1]) - (a[0]*a[0] + a[1]*a[1])
        );
        
        for (int[] point : points) {
            maxHeap.offer(point);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        
        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll();
        }
        
        return result;
    }
    
    public static int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) return 0;
        
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(intervals[0][1]);
        
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= minHeap.peek()) {
                minHeap.poll();
            }
            minHeap.offer(intervals[i][1]);
        }
        
        return minHeap.size();
    }

    public static void main(String[] args) {
        System.out.println("Kth largest: " + kthLargestElement(new int[]{3,2,1,5,6,4}, 2));
        System.out.println("Top k frequent: " + topKFrequent(new int[]{1,1,1,2,2,3}, 2));
        System.out.println("Min meeting rooms: " + minMeetingRooms(new int[][]{{0,30},{5,10},{15,20}}));
    }
}
