/**
 * Heap/K-Way Merge - Kth Largest Element
 * Time: O(n log k), Space: O(k)
 */
class MinHeap {
    private heap: number[] = [];
    
    push(val: number): void {
        this.heap.push(val);
        this.bubbleUp(this.heap.length - 1);
    }
    
    pop(): number | undefined {
        if (this.heap.length === 0) return undefined;
        if (this.heap.length === 1) return this.heap.pop();
        
        const min = this.heap[0];
        this.heap[0] = this.heap.pop()!;
        this.bubbleDown(0);
        return min;
    }
    
    peek(): number | undefined {
        return this.heap[0];
    }
    
    size(): number {
        return this.heap.length;
    }
    
    private bubbleUp(idx: number): void {
        while (idx > 0) {
            const parentIdx = Math.floor((idx - 1) / 2);
            if (this.heap[idx] >= this.heap[parentIdx]) break;
            [this.heap[idx], this.heap[parentIdx]] = [this.heap[parentIdx], this.heap[idx]];
            idx = parentIdx;
        }
    }
    
    private bubbleDown(idx: number): void {
        while (true) {
            let smallest = idx;
            const left = 2 * idx + 1;
            const right = 2 * idx + 2;
            
            if (left < this.heap.length && this.heap[left] < this.heap[smallest]) {
                smallest = left;
            }
            if (right < this.heap.length && this.heap[right] < this.heap[smallest]) {
                smallest = right;
            }
            if (smallest === idx) break;
            
            [this.heap[idx], this.heap[smallest]] = [this.heap[smallest], this.heap[idx]];
            idx = smallest;
        }
    }
}

function findKthLargest(nums: number[], k: number): number {
    const minHeap = new MinHeap();
    
    for (const num of nums) {
        minHeap.push(num);
        if (minHeap.size() > k) {
            minHeap.pop();
        }
    }
    
    return minHeap.peek()!;
}

console.log(findKthLargest([3, 2, 1, 5, 6, 4], 2));  // 5
