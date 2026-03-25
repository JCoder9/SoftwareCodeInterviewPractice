/**
 * Heap/K-Way Merge - K Pairs with Smallest Sums
 * Time: O(k log k), Space: O(k)
 */
interface PairElement {
    sum: number;
    i: number;
    j: number;
}

class MinHeapForPairs {
    private heap: PairElement[] = [];
    
    push(elem: PairElement): void {
        this.heap.push(elem);
        this.bubbleUp(this.heap.length - 1);
    }
    
    pop(): PairElement | undefined {
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
    
    private bubbleUp(idx: number): void {
        while (idx > 0) {
            const parentIdx = Math.floor((idx - 1) / 2);
            if (this.heap[idx].sum >= this.heap[parentIdx].sum) break;
            [this.heap[idx], this.heap[parentIdx]] = [this.heap[parentIdx], this.heap[idx]];
            idx = parentIdx;
        }
    }
    
    private bubbleDown(idx: number): void {
        while (true) {
            let smallest = idx;
            const left = 2 * idx + 1;
            const right = 2 * idx + 2;
            
            if (left < this.heap.length && this.heap[left].sum < this.heap[smallest].sum) {
                smallest = left;
            }
            if (right < this.heap.length && this.heap[right].sum < this.heap[smallest].sum) {
                smallest = right;
            }
            if (smallest === idx) break;
            
            [this.heap[idx], this.heap[smallest]] = [this.heap[smallest], this.heap[idx]];
            idx = smallest;
        }
    }
}

function kSmallestPairs(nums1: number[], nums2: number[], k: number): number[][] {
    if (nums1.length === 0 || nums2.length === 0) return [];
    
    const minHeap = new MinHeapForPairs();
    const visited = new Set<string>();
    const result: number[][] = [];
    
    minHeap.push({ sum: nums1[0] + nums2[0], i: 0, j: 0 });
    visited.add("0,0");
    
    while (minHeap.size() > 0 && result.length < k) {
        const curr = minHeap.pop()!;
        const { i, j } = curr;
        
        result.push([nums1[i], nums2[j]]);
        
        // Add next pairs
        if (i + 1 < nums1.length && !visited.has(`${i + 1},${j}`)) {
            minHeap.push({ sum: nums1[i + 1] + nums2[j], i: i + 1, j });
            visited.add(`${i + 1},${j}`);
        }
        
        if (j + 1 < nums2.length && !visited.has(`${i},${j + 1}`)) {
            minHeap.push({ sum: nums1[i] + nums2[j + 1], i, j: j + 1 });
            visited.add(`${i},${j + 1}`);
        }
    }
    
    return result;
}

console.log(kSmallestPairs([1, 7, 11], [2, 4, 6], 3));  // [[1,2], [1,4], [1,6]]
console.log(kSmallestPairs([1, 1, 2], [1, 2, 3], 2));   // [[1,1], [1,1]]
