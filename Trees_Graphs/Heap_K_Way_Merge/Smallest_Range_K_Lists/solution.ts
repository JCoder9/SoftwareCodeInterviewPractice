/**
 * Heap/K-Way Merge - Smallest Range Covering K Lists
 * Time: O(N log k), Space: O(k)
 */
interface RangeElement {
    value: number;
    listIndex: number;
    elementIndex: number;
}

class MinHeapForRange {
    private heap: RangeElement[] = [];
    
    push(elem: RangeElement): void {
        this.heap.push(elem);
        this.bubbleUp(this.heap.length - 1);
    }
    
    pop(): RangeElement | undefined {
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

function smallestRange(nums: number[][]): number[] {
    const minHeap = new MinHeapForRange();
    let currentMax = -Infinity;
    
    // Add first element from each list
    for (let i = 0; i < nums.length; i++) {
        minHeap.push({ value: nums[i][0], listIndex: i, elementIndex: 0 });
        currentMax = Math.max(currentMax, nums[i][0]);
    }
    
    let resultRange = [-Infinity, Infinity];
    
    while (minHeap.size() === nums.length) {
        const curr = minHeap.pop()!;
        const currentMin = curr.value;
        
        // Update result if smaller range found
        if (currentMax - currentMin < resultRange[1] - resultRange[0]) {
            resultRange = [currentMin, currentMax];
        }
        
        // Move to next element in same list
        if (curr.elementIndex + 1 < nums[curr.listIndex].length) {
            const nextVal = nums[curr.listIndex][curr.elementIndex + 1];
            minHeap.push({
                value: nextVal,
                listIndex: curr.listIndex,
                elementIndex: curr.elementIndex + 1
            });
            currentMax = Math.max(currentMax, nextVal);
        }
    }
    
    return resultRange;
}

const nums = [[4, 10, 15, 24, 26], [0, 9, 12, 20], [5, 18, 22, 30]];
console.log(smallestRange(nums));  // [20, 24]
