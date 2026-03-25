"""
Topological Sort - Sequence Reconstruction

Related LeetCode Problems:
- LC 444: Sequence Reconstruction (Medium) - Premium
- LC 310: Minimum Height Trees (Medium) - related concept

Key Insight: Check if topological sort has unique solution.
- Given original sequence and subsequences
- Verify if original is the ONLY valid topological sort
- Each step must have exactly ONE node with in-degree 0

Time Complexity: O(V + E)
Space Complexity: O(V + E)
"""

from collections import defaultdict, deque
from typing import List


def sequenceReconstruction(org: List[int], seqs: List[List[int]]) -> bool:
    """
    Check if org is the only sequence that can be reconstructed from seqs.
    Each seq is a subsequence of org.
    """
    # Build graph from sequences
    graph = defaultdict(set)
    in_degree = {num: 0 for num in org}
    
    # Also track all numbers that appear in seqs
    all_nums = set()
    for seq in seqs:
        for num in seq:
            all_nums.add(num)
    
    # Check if all numbers in org appear in seqs
    if set(org) != all_nums:
        return False
    
    # Build graph from consecutive pairs in each sequence
    for seq in seqs:
        for i in range(len(seq) - 1):
            u, v = seq[i], seq[i + 1]
            if v not in graph[u]:
                graph[u].add(v)
                in_degree[v] += 1
    
    # Kahn's algorithm - must have unique path
    queue = deque([num for num in org if in_degree[num] == 0])
    result = []
    
    while queue:
        # For unique sequence, queue must have exactly 1 element
        if len(queue) != 1:
            return False
        
        num = queue.popleft()
        result.append(num)
        
        for neighbor in graph[num]:
            in_degree[neighbor] -= 1
            if in_degree[neighbor] == 0:
                queue.append(neighbor)
    
    # Check if reconstruction matches original
    return result == org




if __name__ == "__main__":
    # Test LC 444
    org1 = [1, 2, 3]
    seqs1 = [[1, 2], [1, 3]]
    print("Sequence reconstruction:", sequenceReconstruction(org1, seqs1))  # False (could be [1,2,3] or [1,3,2])
