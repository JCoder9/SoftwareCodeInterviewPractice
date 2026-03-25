/**
 * Heap/K-Way Merge - Merge K Sorted Arrays
 * Time: O(N log k), Space: O(k)
 */
interface HeapElement {
    value: number;
    arrayIndex: number;
    elementIndex: number;
}

class MinHeapForArrays {
    private heap: HeapElement[] = [];
    
    push(elem: HeapElement): void {
        this.heap.push(elem);
        this.bubbleUp(this.heap.length - 1);
    }
    
    pop(): HeapElement | undefined {
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
            if (this.heap[idx].value >= this.heap[parentIdx].value) break;
            [this.heap[idx], this.heap[parentIdx]] = [this.heap[parentIdx], this.heap[idx]];
            idx = parentIdx;
        }
    }
    
    private bubbleDown(idx: number): void {
        while (true) {
            let smallest = idx;
            const left = 2 * idx + 1;
            const right = 2 * idx + 2;
            
            if (left < this.heap.length && this.heap[left].value < this.heap[smallest].value) {
                smallest = left;
            }
            if (right < this.heap.length && this.heap[right].value < this.heap[smallest].value) {
                smallest = right;
            }
            if (smallest === idx) break;
            
            [this.heap[idx], this.heap[smallest]] = [this.heap[smallest], this.heap[idx]];
            idx = smallest;
        }
    }
}

function mergeKArrays(arrays: number[][]): number[] {
    const minHeap = new MinHeapForArrays();
    
    // Add first element from each array
    for (let i = 0; i < arrays.length; i++) {
        if (arrays[i].length > 0) {
            minHeap.push({
                value: arrays[i][0],
                arrayIndex: i,
                elementIndex: 0
            });
        }
    }
    
    const result: number[] = [];
    
    while (minHeap.size() > 0) {
        const elem = minHeap.pop()!;
        result.push(elem.value);
        
        // Add next element from same array
        if (elem.elementIndex + 1 < arrays[elem.arrayIndex].length) {
            minHeap.push({
                value: arrays[elem.arrayIndex][elem.elementIndex + 1],
                arrayIndex: elem.arrayIndex,
                elementIndex: elem.elementIndex + 1
            });
        }
    }
    
    return result;
}

const arrays = [[1, 4, 5], [1, 3, 4], [2, 6]];
console.log(mergeKArrays(arrays));  // [1, 1, 2, 3, 4, 4, 5, 6]
