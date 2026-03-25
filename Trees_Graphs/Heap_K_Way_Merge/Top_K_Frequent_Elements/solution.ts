/**
 * Heap/K-Way Merge - Top K Frequent Elements
 * Time: O(n log k), Space: O(n)
 */
class MinHeapForFrequency {
    private heap: [number, number][] = [];  // [frequency, number]
    
    push(item: [number, number]): void {
        this.heap.push(item);
        this.bubbleUp(this.heap.length - 1);
    }
    
    pop(): [number, number] | undefined {
        if (this.heap.length === 0) return undefined;
        if (this.heap.length === 1) return this.heap.pop();
        
        const min = this.heap[0];
        this.heap[0] = this.heap.pop()!;
        this.bubbleDown(0);
        return min;
    }
    
    size(): number {
        return this.heap.length;
    }
    
    getAll(): [number, number][] {
        return [...this.heap];
    }
    
    private bubbleUp(idx: number): void {
        while (idx > 0) {
            const parentIdx = Math.floor((idx - 1) / 2);
            if (this.heap[idx][0] >= this.heap[parentIdx][0]) break;
            [this.heap[idx], this.heap[parentIdx]] = [this.heap[parentIdx], this.heap[idx]];
            idx = parentIdx;
        }
    }
    
    private bubbleDown(idx: number): void {
        while (true) {
            let smallest = idx;
            const left = 2 * idx + 1;
            const right = 2 * idx + 2;
            
            if (left < this.heap.length && this.heap[left][0] < this.heap[smallest][0]) {
                smallest = left;
            }
            if (right < this.heap.length && this.heap[right][0] < this.heap[smallest][0]) {
                smallest = right;
            }
            if (smallest === idx) break;
            
            [this.heap[idx], this.heap[smallest]] = [this.heap[smallest], this.heap[idx]];
            idx = smallest;
        }
    }
}

function topKFrequent(nums: number[], k: number): number[] {
    // Count frequencies
    const count = new Map<number, number>();
    for (const num of nums) {
        count.set(num, (count.get(num) || 0) + 1);
    }
    
    // Min heap of size k
    const minHeap = new MinHeapForFrequency();
    
    for (const [num, freq] of count.entries()) {
        minHeap.push([freq, num]);
        if (minHeap.size() > k) {
            minHeap.pop();
        }
    }
    
    // Extract numbers
    return minHeap.getAll().map(item => item[1]);
}

console.log(topKFrequent([1, 1, 1, 2, 2, 3], 2));  // [2, 1] or [1, 2]
console.log(topKFrequent([4, 4, 4, 2, 2, 3], 2));  // [2, 4] or [4, 2]
