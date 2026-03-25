/**
 * Heap/K-Way Merge - Kth Smallest in Sorted Matrix
 * Time: O(k log n), Space: O(n)
 */
interface MatrixElement {
    value: number;
    row: number;
    col: number;
}

class MinHeapForMatrix {
    private heap: MatrixElement[] = [];
    
    push(elem: MatrixElement): void {
        this.heap.push(elem);
        this.bubbleUp(this.heap.length - 1);
    }
    
    pop(): MatrixElement | undefined {
        if (this.heap.length === 0) return undefined;
        if (this.heap.length === 1) return this.heap.pop();
        
        const min = this.heap[0];
        this.heap[0] = this.heap.pop()!;
        this.bubbleDown(0);
        return min;
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

function kthSmallest(matrix: number[][], k: number): number {
    const n = matrix.length;
    const minHeap = new MinHeapForMatrix();
    
    // Add first element from each row
    for (let r = 0; r < Math.min(k, n); r++) {
        minHeap.push({ value: matrix[r][0], row: r, col: 0 });
    }
    
    let result = 0;
    for (let i = 0; i < k; i++) {
        const elem = minHeap.pop()!;
        result = elem.value;
        
        // Add next element from same row
        if (elem.col + 1 < n) {
            minHeap.push({
                value: matrix[elem.row][elem.col + 1],
                row: elem.row,
                col: elem.col + 1
            });
        }
    }
    
    return result;
}

const matrix = [[1, 5, 9], [10, 11, 13], [12, 13, 15]];
console.log(kthSmallest(matrix, 8));  // 13
