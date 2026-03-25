# Code Interview Patterns

Coding interview patterns in Python, Java, and TypeScript, organized by category.

## Navigation

- **[INTERVIEW_LEVEL_GUIDE.md](INTERVIEW_LEVEL_GUIDE.md)** — What to study first by level (Junior → Mid → Senior). Start here.
- **[QUICK_REFERENCE.md](QUICK_REFERENCE.md)** — Pattern cheat sheet with exact file paths.
- **[PROGRESS_TRACKER.md](PROGRESS_TRACKER.md)** — Personal checklist to mark what you've mastered.

---

## Project Structure

```
CodeInterviewHelp/
├── Arrays_Strings/      # Two Pointers, Sliding Window, Prefix Sums, Sorting
├── Hashing/             # Seen Set, Frequency Counting, Subarray Sum K
├── Linked_Structures/   # Fast/Slow Pointers, In-Place Reversal
├── Trees_Graphs/        # BFS, DFS, 1D DP, Heaps, Monotonic Stack,
│                        # Topological Sort, Interval DP, Binary Search on Answer
├── Core_DP/             # Knapsack DP, LCS/Edit Distance
└── Search_Optimization/ # Reference files (canonical implementations in Core_DP/ and Trees_Graphs/)
```

Each pattern folder contains `solution.py`, `solution.java`, and `solution.ts`.

---

## When to Use Each Pattern

**Two Pointers** — Sorted array, finding pairs, in-place modification  
**Sliding Window** — Longest/shortest subarray/substring with a constraint  
**Hashing** — O(1) lookup, frequency counting, complement problems (Two Sum)  
**Prefix Sums** — Range sum queries, subarray sum = k  
**DFS** — Tree/graph traversal, explore all paths, backtracking  
**BFS** — Shortest path unweighted, level-by-level processing, minimum steps  
**1D DP** — Linear decisions with overlapping subproblems  
**Knapsack DP** — Select items with capacity/budget constraints  
**LCS/Edit Distance** — String similarity, sequence alignment  
**Heap** — Top K elements, merging sorted streams  
**Monotonic Stack** — Next/previous greater/smaller element  
**Binary Search on Answer** — "Find minimum X such that..." problems  
**Topological Sort** — Dependency ordering, DAG problems  
**Interval DP** — Optimal cost on intervals (palindromes, balloons, merge stones)

---

## Complexity Quick Reference

| Pattern | Time | Space |
|---------|------|-------|
| Two Pointers | O(n) | O(1) |
| Sliding Window | O(n) | O(k) |
| Hashing | O(n) | O(n) |
| DFS | O(V+E) | O(h) |
| BFS | O(V+E) | O(w) |
| 1D DP | O(n) | O(1)–O(n) |
| Knapsack | O(n×W) | O(W) |
| LCS/Edit Distance | O(m×n) | O(n) |
| Binary Search | O(log n) | O(1) |
| Monotonic Stack | O(n) | O(n) |
| Heap | O(n log k) | O(k) |

---

## Interview Strategy

1. **Clarify** — Constraints, edge cases, expected complexity
2. **Pattern match** — Which pattern applies here?
3. **Explain** — Describe your approach before writing code
4. **Code** — Implement cleanly with clear variable names
5. **Test** — Walk through with a small example
6. **Optimize** — Discuss time/space tradeoffs

---
