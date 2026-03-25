# 🎯 Interview Level Guide - What to Study First

This guide categorizes all algorithm patterns by interview difficulty level to help you focus your preparation efficiently.

---

## 📊 Quick Reference

- **🟢 JUNIOR** = Entry-level, New Grads, 0-2 years experience
- **🟡 MID-LEVEL** = 2-5 years, Most tech companies, Standard interviews
- **🔴 SENIOR/FAANG** = 5+ years, Top-tier companies, Advanced interviews

---

## 🟢 JUNIOR LEVEL - Start Here! (Core Foundations)

### **Priority 1: Must Know** ⭐⭐⭐

#### Arrays & Strings - Two Pointers
- `Two_Pointers/Same_Direction_Remove_Duplicates/` - Remove duplicates from sorted array
- `Two_Pointers/Same_Direction_Move_Zeroes/` - Move zeroes to end
- `Two_Pointers/Opposite_Ends_Palindrome/` - Check if palindrome
- `Two_Pointers/Two_Arrays_Merge_Sorted/` - Merge two sorted arrays

#### Arrays & Strings - Sliding Window
- `Sliding_Window/Fixed_Size_Max_Sum/` - Maximum sum of subarray size k
- `Sliding_Window/Variable_Max_Length_Sum_LTE/` - Max length with sum ≤ target
- `Sliding_Window/At_Most_K_Distinct/` - Substring with at most k distinct chars

#### Hashing
- `Hashing/Seen_Set/` - Two sum, contains duplicate
- `Hashing/Frequency_Counting/` - Character/element counting

#### Linked Lists - Basic Operations
- `Linked_Structures/Fast_Slow_Pointers/Find_Middle/` - Find middle node
- `Linked_Structures/Fast_Slow_Pointers/Detect_Cycle/` - Detect cycle in linked list
- `Linked_Structures/In_Place_Reversal/Reverse_Entire_List/` - Reverse linked list

#### Basic Recursion & Trees
- `Trees_Graphs/DFS_Recursion/Basic_Tree_DFS/` - Tree traversals (inorder, preorder, postorder)
- `Trees_Graphs/BFS/Basic_Tree_BFS/` - Level order traversal

#### 1D Dynamic Programming (Introduction)
- `Trees_Graphs/1D_DP/Climbing_Stairs/` - Fibonacci-style DP
- `Trees_Graphs/1D_DP/House_Robber/` - Non-adjacent selection
- `Trees_Graphs/1D_DP/Maximum_Subarray_Kadane/` - Kadane's algorithm

### **Priority 2: Important**

#### Arrays & Strings - Prefix Sums
- `Arrays_Strings/Prefix_Sums/Range_Sum_Query/` - Subarray sum queries
- `Arrays_Strings/Prefix_Sums/Prefix_HashMap_Subarray_Sum_K/` - Subarray sum equals k

#### Arrays & Strings - Sorting
- `Arrays_Strings/Sorting_Scan/Basic_Sort_Single_Scan/` - Sort and scan patterns
- `Arrays_Strings/Sorting_Scan/Sort_Two_Pointers/` - Two pointers after sorting

#### Linked Lists - More Patterns
- `Linked_Structures/Fast_Slow_Pointers/Remove_Nth_From_End/` - Two-pass or fast/slow
- `Linked_Structures/In_Place_Reversal/Reverse_Between/` - Reverse portion of list

---

## 🟡 MID-LEVEL (Standard Interview Patterns)

### **Priority 1: Core Interview Patterns** ⭐⭐⭐

#### All Sliding Window Variants
- `Sliding_Window/Variable_Min_Length_Sum_GTE/` - Minimum length with sum ≥ target
- `Sliding_Window/Exactly_K_Distinct/` - Exactly k distinct characters
- `Sliding_Window/Anagram_Pattern/` - Find all anagrams (LC 438)
- `Sliding_Window/Minimum_Window_Substring/` - LC 76 (challenging but common)

#### Complete Two Pointers
- `Two_Pointers/Opposite_Ends_Reverse/` - Reverse with conditions
- `Two_Pointers/Fast_Slow_Cycle_Detection/` - Floyd's cycle detection

#### Binary Search Basics
- **Standard Binary Search** on sorted arrays
- `Trees_Graphs/Binary_Search_On_Answer/Capacity_Resource_Allocation/` - Koko bananas (LC 875)

#### Graph Traversals (Critical!)
- `Trees_Graphs/DFS_Recursion/Graph_DFS_Visited/` - DFS with visited set
- `Trees_Graphs/BFS/Graph_BFS/` - BFS on graphs
- `Trees_Graphs/BFS/Grid_BFS/` - Grid traversal (islands, rotting oranges)
- `Trees_Graphs/BFS/Multi_Source/` - Multi-source BFS
- `Trees_Graphs/BFS/Shortest_Path_Variants/` - Shortest path problems

#### Tree Patterns
- `Trees_Graphs/DFS_Recursion/Common_Tree_Patterns/` - Diameter, height, paths
- `Trees_Graphs/DFS_Recursion/Path_Tracking/` - Root to leaf paths
- `Trees_Graphs/BFS/Level_Order_Patterns/` - Zigzag, right side view

#### Standard Dynamic Programming ⭐
- `Trees_Graphs/1D_DP/Coin_Change/` - Classic DP (LC 322)
- `Trees_Graphs/1D_DP/Longest_Increasing_Subsequence/` - LIS (LC 300)
- `Trees_Graphs/1D_DP/Word_Break/` - LC 139
- `Trees_Graphs/1D_DP/Decode_Ways/` - LC 91

#### Knapsack DP (Very Common)
- `Core_DP/Knapsack/01_Knapsack/` - 0/1 knapsack template
- `Core_DP/Knapsack/Subset_Sum/` - Subset sum check
- `Core_DP/Knapsack/Partition_Equal_Subset/` - Can partition (LC 416)
- `Core_DP/Knapsack/Target_Sum/` - LC 494
- `Core_DP/Knapsack/Coin_Change/` - Minimum coins (LC 322)
- `Core_DP/Knapsack/Coin_Change_II/` - Coin combinations (LC 518)

#### String DP (Common in Mid-Level+)
- `Trees_Graphs/String_DP/Longest_Common_Subsequence/` - LCS (LC 1143)
- `Trees_Graphs/String_DP/Edit_Distance/` - LC 72 (very common!)
- `Trees_Graphs/String_DP/Longest_Common_Substring/` - Continuous match

#### Heap & Priority Queue
- `Trees_Graphs/Heap_K_Way_Merge/Kth_Largest_Element/` - Kth largest (LC 215)
- `Trees_Graphs/Heap_K_Way_Merge/Top_K_Frequent_Elements/` - LC 347
- `Trees_Graphs/Heap_K_Way_Merge/Merge_K_Sorted_Lists/` - LC 23 (classic!)

### **Priority 2: Strengthen Skills**

#### Linked List Advanced
- `Linked_Structures/Fast_Slow_Pointers/Find_Cycle_Start/` - Cycle start position
- `Linked_Structures/Fast_Slow_Pointers/Reorder_List/` - LC 143

#### Topological Sort
- `Trees_Graphs/Topological_Sort/Basic_Kahns_Algorithm/` - BFS topological sort
- `Trees_Graphs/Topological_Sort/DFS_Based_TopoSort/` - DFS topological sort
- `Trees_Graphs/Topological_Sort/Course_Schedule/` - LC 207 (can finish?)
- `Trees_Graphs/Topological_Sort/Course_Schedule_II/` - LC 210 (find order)

#### Prefix Sums Advanced
- `Arrays_Strings/Prefix_Sums/Matrix_2D_Prefix_Sum/` - 2D range sum
- `Arrays_Strings/Prefix_Sums/XOR_Prefix_Patterns/` - XOR subarrays

#### Divide & Conquer
- `Trees_Graphs/DFS_Recursion/Divide_Conquer/` - Merge sort style problems

#### Sorting Patterns
- `Arrays_Strings/Sorting_Scan/Merge_Intervals/` - LC 56, 57
- `Arrays_Strings/Sorting_Scan/Sort_Binary_Search/` - Sort then binary search

---

## 🔴 SENIOR/FAANG LEVEL (Advanced Patterns)

### **Priority 1: Distinguished Candidates**

#### Monotonic Stack (Very Popular in FAANG)
- `Trees_Graphs/Monotonic_Stack/Next_Greater_Element/` - LC 496, 503
- `Trees_Graphs/Monotonic_Stack/Daily_Temperatures/` - LC 739 ⭐
- `Trees_Graphs/Monotonic_Stack/Largest_Rectangle_Histogram/` - LC 84 (classic!)
- `Trees_Graphs/Monotonic_Stack/Trapping_Rain_Water/` - LC 42 (very popular!)
- `Trees_Graphs/Monotonic_Stack/Next_Smaller_Element/` - Building block
- `Trees_Graphs/Monotonic_Stack/Previous_Greater_Element/` - Building block

#### Binary Search on Answer (FAANG Favorite)
- `Trees_Graphs/Binary_Search_On_Answer/Minimize_Maximum/` - Split array (LC 410)
- `Trees_Graphs/Binary_Search_On_Answer/Maximize_Minimum/` - Magnetic force (LC 1552)
- `Trees_Graphs/Binary_Search_On_Answer/Kth_Smallest_Sorted_Matrix/` - LC 378
- `Trees_Graphs/Binary_Search_On_Answer/Binary_Search_With_Constraints/` - LC 1870

#### Advanced DP - Interval DP
- `Trees_Graphs/Interval_DP/Palindrome_Problems/` - Longest palindromic substring
- `Trees_Graphs/Interval_DP/Matrix_Chain_Merge_Stones/` - LC 1000
- `Trees_Graphs/Interval_DP/Burst_Balloons/` - LC 312 (hard!)
- `Trees_Graphs/Interval_DP/Min_Insertions_Palindrome/` - LC 1312
- `Trees_Graphs/Interval_DP/Remove_Boxes/` - LC 546 (very hard)

#### Advanced String DP
- `Trees_Graphs/String_DP/Shortest_Common_Supersequence/` - LC 1092
- `Trees_Graphs/String_DP/Delete_Operations/` - LC 583
- `Trees_Graphs/String_DP/Distinct_Subsequences/` - LC 115
- `Trees_Graphs/String_DP/Wildcard_Matching/` - LC 44
- `Trees_Graphs/String_DP/Regular_Expression_Matching/` - LC 10 (very hard!)

#### K-Way Merge Advanced
- `Trees_Graphs/Heap_K_Way_Merge/Merge_K_Sorted_Arrays/` - Multiple arrays
- `Trees_Graphs/Heap_K_Way_Merge/Kth_Smallest_Matrix/` - LC 378
- `Trees_Graphs/Heap_K_Way_Merge/K_Pairs_Smallest_Sums/` - LC 373
- `Trees_Graphs/Heap_K_Way_Merge/Smallest_Range_K_Lists/` - LC 632

### **Priority 2: Expert Level**

#### Advanced Graph Algorithms
- `Trees_Graphs/BFS/Bidirectional_BFS/` - Two-way BFS optimization
- `Trees_Graphs/BFS/Zero_One_BFS/` - 0-1 weighted graphs
- `Trees_Graphs/DFS_Recursion/Backtracking/` - N-queens, sudoku
- `Trees_Graphs/DFS_Recursion/Memoization_DFS/` - DFS with memoization

#### Advanced Topological Sort
- `Trees_Graphs/Topological_Sort/Alien_Dictionary/` - LC 269 (hard!)
- `Trees_Graphs/Topological_Sort/Sequence_Reconstruction/` - LC 444
- `Trees_Graphs/Topological_Sort/All_Topological_Orderings/` - Generate all orderings
- `Trees_Graphs/Topological_Sort/DAG_Ordering/` - Complex dependency graphs

#### Advanced Knapsack
- `Core_DP/Knapsack/Unbounded_Knapsack/` - Unlimited items
- `Core_DP/Knapsack/Bounded_Knapsack/` - Limited counts

#### Advanced Sliding Window
- `Arrays_Strings/Sliding_Window/Monotonic_Deque_Sliding_Max/` - LC 239 (hard!)

#### Advanced Linked List
- `Linked_Structures/In_Place_Reversal/Reverse_K_Group/` - LC 25 (reverse in k groups)
- `Linked_Structures/In_Place_Reversal/Reverse_Alternating_K_Group/` - Complex reversals
- `Linked_Structures/In_Place_Reversal/Reorder_Zigzag/` - Zigzag patterns
- `Linked_Structures/In_Place_Reversal/Palindrome_Using_Reversal/` - Palindrome check

#### Advanced Prefix Patterns
- `Arrays_Strings/Prefix_Sums/Difference_Array_Range_Update/` - Range updates
- `Arrays_Strings/Prefix_Sums/Product_Arrays_Sliding_Window/` - Product calculations

#### Greedy & Selection
- `Arrays_Strings/Sorting_Scan/Greedy_Selection/` - Greedy algorithms
- `Arrays_Strings/Sorting_Scan/Custom_Sort_Criteria/` - Custom comparators

---

## ⚠️ Known Gap: Union-Find (Disjoint Set)

This pattern is **not in the project** but appears in real interviews, especially mid-level+.

Used for:
- Detecting cycles in undirected graphs
- Counting connected components with dynamic updates
- "Number of Islands" variants with online queries
- Redundant Connection (LC 684), Friend Circles (LC 547)

Worth adding or studying separately before interviews at mid-level+.

---

## 📚 Study Strategy by Level

### 🟢 **JUNIOR LEVEL PATH** (2-3 months)

**Week 1-2: Arrays & Basic Patterns**
1. Two Pointers (same direction) - 3 days
2. Two Pointers (opposite ends) - 2 days
3. Sliding Window (fixed size) - 2 days
4. Hash maps/sets - 2 days

**Week 3-4: Linked Lists & Basic Trees**
1. Fast/slow pointers - 3 days
2. Reverse linked list - 2 days
3. Basic tree DFS - 3 days
4. Basic tree BFS - 2 days

**Week 5-6: Introduction to DP**
1. Climbing stairs pattern - 2 days
2. House robber - 2 days
3. Maximum subarray - 2 days
4. Practice problems - 4 days

**Week 7-8: Solidify & Practice**
1. Prefix sums - 3 days
2. More practice - 5 days
3. Mock interviews - 2 days

**Target LeetCode Problems:** 50-70 easy, 10-20 medium

---

### 🟡 **MID-LEVEL PATH** (3-4 months)

**Month 1: Advanced Arrays & Graphs**
- Week 1: All sliding window patterns
- Week 2: Graph DFS/BFS thoroughly
- Week 3: Grid problems & multi-source BFS
- Week 4: Tree patterns & level order

**Month 2: Dynamic Programming (Critical!)**
- Week 1: 1D DP (all variants)
- Week 2: Knapsack patterns (0/1, subset sum)
- Week 3: String DP (LCS, edit distance)
- Week 4: Coin change variations

**Month 3: Heaps & Advanced Structures**
- Week 1: Heap operations & kth element problems
- Week 2: K-way merge patterns
- Week 3: Topological sort
- Week 4: Binary search variants

**Month 4: Practice & Polish**
- Week 1-2: Linked list advanced
- Week 3: Prefix sums advanced
- Week 4: Mock interviews & weak areas

**Target LeetCode Problems:** 30-40 easy, 100-120 medium, 10-20 hard

---

### 🔴 **SENIOR/FAANG PATH** (4-6 months)

Assumes you know all mid-level patterns. Focus on:

**Month 1-2: Advanced Patterns**
- Monotonic stack/deque (2 weeks)
- Binary search on answer (1 week)
- Interval DP (2 weeks)
- Advanced string DP (1 week)

**Month 3: Hard Problems**
- Backtracking optimization
- Bidirectional BFS
- Advanced topological sort
- Memoization patterns

**Month 4-5: Company-Specific Prep**
- Practice hard problems from target companies
- System design (if applicable)
- Study company-specific patterns

**Month 6: Final Polish**
- Mock interviews weekly
- Review weak areas
- Speed optimization

**Target LeetCode Problems:** 200+ total, 50+ hard

---

## 🎯 Quick Priority Summary

### Must Do (Everyone)
1. Two Pointers (all)
2. Sliding Window (fixed + variable)
3. Hash maps
4. Fast/slow pointers
5. Basic BFS/DFS
6. 1D DP basics

### Mid-Level Focus
1. All graph algorithms
2. Knapsack DP
3. String DP (LCS, edit distance)
4. Heap/Priority Queue
5. Topological Sort
6. Binary Search variations

### Senior/FAANG Focus
1. Monotonic stack ⭐⭐⭐
2. Binary search on answer ⭐⭐
3. Interval DP ⭐⭐
4. Advanced string DP
5. Bidirectional BFS
6. Backtracking with memoization
7. Hard graph problems

---

## 💡 Pro Tips

1. **Don't skip junior patterns even if you're senior** - They're building blocks
2. **Master one pattern before moving to next** - Depth over breadth
3. **Do 3-5 problems per pattern minimum** -Pattern recognition needs repetition
4. **Start with Python for learning** - Switch to Java/C++ for optimization later
5. **Time yourself** - 30 min for easy, 45 min for medium, 60 min for hard
6. **Review mistakes** - Keep an error log

---

## 📊 Pattern Frequency in Real Interviews

**Most Common (80% of interviews):**
- Two Pointers
- Sliding Window
- Hash Maps
- BFS/DFS on Trees
- Graph traversal
- 1D DP
- Knapsack DP

**Common (50% of interviews):**
- String DP
- Heap/Priority Queue
- Binary Search
- Topological Sort
- Fast/Slow Pointers

**Less Common but High-Signal (20% of interviews):**
- Monotonic Stack
- Binary Search on Answer
- Interval DP
- Bidirectional BFS
- Advanced backtracking

---

## 🚀 Getting Started

1. **Assess your level honestly**
2. **Start with the appropriate priority 1 section**
3. **Do 3-5 problems per pattern**
4. **Move to next pattern when comfortable**
5. **Review weekly**

Good luck with your preparation! 🎉
