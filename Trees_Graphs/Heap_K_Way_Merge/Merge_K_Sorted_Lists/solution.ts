/**
 * Heap/K-Way Merge - Merge K Sorted Lists
 * Time: O(N log k), Space: O(k)
 */
class ListNode {
    val: number;
    next: ListNode | null;
    constructor(val?: number, next?: ListNode | null) {
        this.val = val === undefined ? 0 : val;
        this.next = next === undefined ? null : next;
    }
}

class MinHeapForNodes {
    private heap: ListNode[] = [];
    
    push(node: ListNode): void {
        this.heap.push(node);
        this.bubbleUp(this.heap.length - 1);
    }
    
    pop(): ListNode | undefined {
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
            if (this.heap[idx].val >= this.heap[parentIdx].val) break;
            [this.heap[idx], this.heap[parentIdx]] = [this.heap[parentIdx], this.heap[idx]];
            idx = parentIdx;
        }
    }
    
    private bubbleDown(idx: number): void {
        while (true) {
            let smallest = idx;
            const left = 2 * idx + 1;
            const right = 2 * idx + 2;
            
            if (left < this.heap.length && this.heap[left].val < this.heap[smallest].val) {
                smallest = left;
            }
            if (right < this.heap.length && this.heap[right].val < this.heap[smallest].val) {
                smallest = right;
            }
            if (smallest === idx) break;
            
            [this.heap[idx], this.heap[smallest]] = [this.heap[smallest], this.heap[idx]];
            idx = smallest;
        }
    }
}

function mergeKLists(lists: Array<ListNode | null>): ListNode | null {
    const minHeap = new MinHeapForNodes();
    
    for (const node of lists) {
        if (node) {
            minHeap.push(node);
        }
    }
    
    const dummy = new ListNode(0);
    let current = dummy;
    
    while (minHeap.size() > 0) {
        const node = minHeap.pop()!;
        current.next = node;
        current = current.next;
        
        if (node.next) {
            minHeap.push(node.next);
        }
    }
    
    return dummy.next;
}

const l1 = new ListNode(1, new ListNode(4, new ListNode(5)));
const l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
const l3 = new ListNode(2, new ListNode(6));
let result = mergeKLists([l1, l2, l3]);

let output = "";
while (result) {
    output += result.val + (result.next ? " -> " : "");
    result = result.next;
}
console.log(output);
