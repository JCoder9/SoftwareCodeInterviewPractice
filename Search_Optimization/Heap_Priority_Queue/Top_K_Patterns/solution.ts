/**
 * Heap / Priority Queue - Top K and K-Way Merge
 * 
 * Time Complexity: O(n log k)
 * Space Complexity: O(k)
 */

class MinHeap<T> {
    private heap: T[] = [];
    
    constructor(private compareFn: (a: T, b: T) => number) {}
    
    push(val: T): void {
        this.heap.push(val);
        this.bubbleUp(this.heap.length - 1);
    }
    
    pop(): T | undefined {
        if (this.heap.length === 0) return undefined;
        if (this.heap.length === 1) return this.heap.pop();
        
        const top = this.heap[0];
        this.heap[0] = this.heap.pop()!;
        this.bubbleDown(0);
        return top;
    }
    
    peek(): T | undefined {
        return this.heap[0];
    }
    
    size(): number {
        return this.heap.length;
    }
    
    private bubbleUp(idx: number): void {
        while (idx > 0) {
            const parent = Math.floor((idx - 1) / 2);
            if (this.compareFn(this.heap[idx], this.heap[parent]) >= 0) break;
            [this.heap[idx], this.heap[parent]] = [this.heap[parent], this.heap[idx]];
            idx = parent;
        }
    }
    
    private bubbleDown(idx: number): void {
        while (true) {
            let smallest = idx;
            const left = 2 * idx + 1;
            const right = 2 * idx + 2;
            
            if (left < this.heap.length && 
                this.compareFn(this.heap[left], this.heap[smallest]) < 0) {
                smallest = left;
            }
            if (right < this.heap.length && 
                this.compareFn(this.heap[right], this.heap[smallest]) < 0) {
                smallest = right;
            }
            
            if (smallest === idx) break;
            
            [this.heap[idx], this.heap[smallest]] = [this.heap[smallest], this.heap[idx]];
            idx = smallest;
        }
    }
}

function kthLargestElement(nums: number[], k: number): number {
    const minHeap = new MinHeap<number>((a, b) => a - b);
    
    for (const num of nums) {
        minHeap.push(num);
        if (minHeap.size() > k) {
            minHeap.pop();
        }
    }
    
    return minHeap.peek()!;
}

function topKFrequent(nums: number[], k: number): number[] {
    const freq = new Map<number, number>();
    for (const num of nums) {
        freq.set(num, (freq.get(num) || 0) + 1);
    }
    
    const minHeap = new MinHeap<[number, number]>((a, b) => a[1] - b[1]);
    
    for (const [num, count] of freq.entries()) {
        minHeap.push([num, count]);
        if (minHeap.size() > k) {
            minHeap.pop();
        }
    }
    
    const result: number[] = [];
    while (minHeap.size() > 0) {
        result.push(minHeap.pop()![0]);
    }
    
    return result;
}

function minMeetingRooms(intervals: number[][]): number {
    if (intervals.length === 0) return 0;
    
    intervals.sort((a, b) => a[0] - b[0]);
    const minHeap = new MinHeap<number>((a, b) => a - b);
    minHeap.push(intervals[0][1]);
    
    for (let i = 1; i < intervals.length; i++) {
        if (intervals[i][0] >= minHeap.peek()!) {
            minHeap.pop();
        }
        minHeap.push(intervals[i][1]);
    }
    
    return minHeap.size();
}

// Test
if (require.main === module) {
    console.log("Kth largest:", kthLargestElement([3, 2, 1, 5, 6, 4], 2));
    console.log("Top k frequent:", topKFrequent([1, 1, 1, 2, 2, 3], 2));
    console.log("Min meeting rooms:", minMeetingRooms([[0, 30], [5, 10], [15, 20]]));
}

export { kthLargestElement, topKFrequent, minMeetingRooms, MinHeap };
