# Java vs Python - Interview Coding Cheat Sheet

Quick reference for translating patterns between Java and Python during coding interviews.

---

## 📊 Data Structures

### Arrays/Lists

| Operation                         | Java                                          | Python |
|-----------|------|--------|
| **Declaration**                   | `int[] arr = new int[n];`                     |  arr = [] / `arr = [0] * n` |
| **Dynamic list**                  | `List<Integer> list = new ArrayList<>();`     | `lst = []` |
| **Length**                        | `arr.length` or `list.size()`                 | `len(arr)` |
| **Append**                        | `list.add(item)`                              | `lst.append(item)` |
| **Insert at index**               | `list.add(i, item)`                           | `lst.insert(i, item)` |
| **Remove**                        | `list.remove(i)` (by index)<br>`list.remove(Integer.valueOf(x))` (by value) | `lst.pop(i)` (by index)<br>`lst.remove(x)` (by value) |
| **Slice**                         | ❌ No native support                          | `arr[start:end]` |
| **Reverse**                       | `Collections.reverse(list)`                   | `arr.reverse()` or `arr[::-1]` |
| **Sort**                          | `Arrays.sort(arr)` \ `Collections.sort(list)` | `arr.sort()` (in-place)<br>`sorted(arr)` (new list) |
| **Check if empty**                | `list.isEmpty()`                              | `not lst` or `len(lst) == 0` |

### Strings

| Operation                         | Java                                          | Python |
|-----------|------|--------|
| **Length**                        | `s.length()`                                  | `len(s)` |
| **Character at index**            | `s.charAt(i)`                                 | `s[i]` |
| **Substring**                     | `s.substring(start, end)`                     | `s[start:end]` |
| **Check substring**               | `s.contains("abc")`                           | `"abc" in s` |
| **String to array**               | `s.toCharArray()`                             | `list(s)` |
| **Array to string**               | `String.valueOf(arr)` or<br>`new String(arr)` | `"".join(arr)` |
| **Split**                         | `s.split(" ")`                                | `s.split(" ")` |
| **Join**                          | `String.join("-", list)`                      | `"-".join(lst)` |
| **Replace**                       | `s.replace("a", "b")`                         | `s.replace("a", "b")` |
| **Char to ASCII**                 | `(int) c` or `c - 'a'`                        | `ord(c)` or `ord(c) - ord('a')` |
| **ASCII to char**                 | `(char) num`                                  | `chr(num)` |
| **Lowercase/Uppercase**           | `s.toLowerCase()` / `s.toUpperCase()`         | `s.lower()` / `s.upper()` |
| **Immutability**                  | ✅ Immutable (use `StringBuilder`)            | ✅ Immutable (use list for mutations) |

### HashMap/Dictionary

| Operation                         | Java | Python |
|-----------|------|--------|
| **Declaration**                   | `Map<K, V> map = new HashMap<>();`            | `d = {}` or `d = dict()` |
| **Add/Update**                    | `map.put(key, val)`                           | `d[key] = val` |
| **Get**                           | `map.get(key)` (possible null)                | `d.get(key)` (returns None)
                                       /`map.getOrDefault(key, 0)`                  <br>`d.get(key, 0)` |
| **Check key** | `map.containsKey(key)` | `key in d` |
| **Remove** | `map.remove(key)` | `d.pop(key)` or `del d[key]` |
| **Size** | `map.size()` | `len(d)` |
| **Keys** | `map.keySet()` | `d.keys()` |
| **Values** | `map.values()` | `d.values()` |
| **Iterate** | `for (Map.Entry<K,V> e : map.entrySet()) {`<br>`  K key = e.getKey();`<br>`  V val = e.getValue();`<br>`}` | `for key, val in d.items():` |
| **Default dict** | ❌ Must use `getOrDefault()` | `from collections import defaultdict`<br>`d = defaultdict(int)` |
| **Counter** | ❌ Must implement manually | `from collections import Counter`<br>`count = Counter(arr)` |

### Set

| Operation | Java | Python |
|-----------|------|--------|
| **Declaration** | `Set<T> set = new HashSet<>();` | `s = set()` or `s = {1, 2, 3}` |
| **Add** | `set.add(item)` | `s.add(item)` |
| **Remove** | `set.remove(item)` | `s.remove(item)` or `s.discard(item)` |
| **Check** | `set.contains(item)` | `item in s` |
| **Size** | `set.size()` | `len(s)` |
| **Union** | ❌ Use loops | `s1 \| s2` or `s1.union(s2)` |
| **Intersection** | ❌ Use loops | `s1 & s2` or `s1.intersection(s2)` |

### Queue/Deque

| Operation | Java | Python |
|-----------|------|--------|
| **Queue (FIFO)** | `Queue<T> q = new LinkedList<>();` | `from collections import deque`<br>`q = deque()` |
| **Add to end** | `q.offer(item)` or `q.add(item)` | `q.append(item)` |
| **Remove from front** | `q.poll()` (returns null if empty)<br>`q.remove()` (throws exception) | `q.popleft()` |
| **Peek front** | `q.peek()` | `q[0]` |
| **Stack (LIFO)** | `Stack<T> stack = new Stack<>();`<br>or use `Deque` | Use list: `stack = []` |
| **Push** | `stack.push(item)` | `stack.append(item)` |
| **Pop** | `stack.pop()` | `stack.pop()` |
| **Peek** | `stack.peek()` | `stack[-1]` |

### Heap/PriorityQueue

| Operation | Java | Python |
|-----------|------|--------|
| **Min Heap** | `PriorityQueue<Integer> pq = new PriorityQueue<>();` | `import heapq`<br>`heap = []` |
| **Max Heap** | `PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());` | `heap = []`<br>Push negative values: `heapq.heappush(heap, -val)` |
| **Add** | `pq.offer(item)` | `heapq.heappush(heap, item)` |
| **Remove min** | `pq.poll()` | `heapq.heappop(heap)` |
| **Peek min** | `pq.peek()` | `heap[0]` |
| **Size** | `pq.size()` | `len(heap)` |
| **Custom comparator** | `PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]);` | Use tuple: `(priority, item)` |

---

## 🔧 Common Patterns

### Two Pointers

**Java:**
```java
int left = 0, right = nums.length - 1;
while (left < right) {
    // ...
    left++;
    right--;
}
```

**Python:**
```python
left, right = 0, len(nums) - 1
while left < right:
    # ...
    left += 1
    right -= 1
```

### Sliding Window

**Java:**
```java
int left = 0;
for (int right = 0; right < nums.length; right++) {
    // Add nums[right] to window
    while (/* window invalid */) {
        // Remove nums[left] from window
        left++;
    }
}
```

**Python:**
```python
left = 0
for right in range(len(nums)):
    # Add nums[right] to window
    while # window invalid:
        # Remove nums[left] from window
        left += 1
```

### Frequency Counter (Anagram example)

**Java:**
```java
int[] count = new int[26];
for (int i = 0; i < s.length(); i++) {
    count[s.charAt(i) - 'a']++;
    count[t.charAt(i) - 'a']--;
}
return Arrays.stream(count).allMatch(c -> c == 0);
```

**Python:**
```python
count = [0] * 26
for c1, c2 in zip(s, t):
    count[ord(c1) - ord('a')] += 1
    count[ord(c2) - ord('a')] -= 1
return all(c == 0 for c in count)
```

### DFS Recursion

**Java:**
```java
public void dfs(int node, Set<Integer> visited) {
    if (visited.contains(node)) return;
    visited.add(node);
    
    for (int neighbor : graph.get(node)) {
        dfs(neighbor, visited);
    }
}
```

**Python:**
```python
def dfs(node, visited):
    if node in visited:
        return
    visited.add(node)
    
    for neighbor in graph[node]:
        dfs(neighbor, visited)
```

### BFS

**Java:**
```java
Queue<Integer> queue = new LinkedList<>();
queue.offer(start);
Set<Integer> visited = new HashSet<>();
visited.add(start);

while (!queue.isEmpty()) {
    int node = queue.poll();
    for (int neighbor : graph.get(node)) {
        if (!visited.contains(neighbor)) {
            visited.add(neighbor);
            queue.offer(neighbor);
        }
    }
}
```

**Python:**
```python
from collections import deque
queue = deque([start])
visited = {start}

while queue:
    node = queue.popleft()
    for neighbor in graph[node]:
        if neighbor not in visited:
            visited.add(neighbor)
            queue.append(neighbor)
```

---

## ⚠️ Common Gotchas

### 1. Array Indexing
- **Both**: 0-indexed, but some LeetCode problems (like LC 167) require 1-indexed output
- **Python**: Supports negative indexing: `arr[-1]` = last element

### 2. Integer Division
- **Java**: `/` is integer division for ints → `5 / 2 = 2`
- **Python 3**: `/` is float division → `5 / 2 = 2.5`, use `//` for integer division → `5 // 2 = 2`

### 3. Comparisons
| Type | Java | Python |
|------|------|--------|
| **String equality** | `s1.equals(s2)` ⚠️ NOT `==` | `s1 == s2` |
| **Object equality** | `obj1.equals(obj2)` | `obj1 == obj2` |
| **Reference equality** | `obj1 == obj2` | `obj1 is obj2` |

### 4. Integer Overflow
- **Java**: `int` is 32-bit, can overflow. Use `long` for large numbers
- **Python**: Integers have unlimited precision (no overflow!)

### 5. Null vs None
- **Java**: `null` (for objects), no value for primitives
- **Python**: `None`

### 6. Boolean
- **Java**: `true` / `false` (lowercase)
- **Python**: `True` / `False` (capitalized)

### 7. Infinity
- **Java**: `Integer.MAX_VALUE` / `Integer.MIN_VALUE`
- **Python**: `float('inf')` / `float('-inf')`

### 8. Sorting with Custom Comparator
**Java:**
```java
Arrays.sort(arr, (a, b) -> a[0] - b[0]);
Collections.sort(list, (a, b) -> a - b);
```

**Python:**
```python
arr.sort(key=lambda x: x[0])
sorted(lst, key=lambda x: x)
```

### 9. Range/Loop
**Java:**
```java
for (int i = 0; i < n; i++) { }
for (int num : nums) { }
```

**Python:**
```python
for i in range(n):  # 0 to n-1
for num in nums:
```

### 10. Multiple Return Values
- **Java**: Must use array/list/object → `return new int[]{a, b};`
- **Python**: Can use tuple → `return a, b`

---

## 💡 Quick Tips

### When to Use What

**Java Benefits:**
- Explicit type checking at compile time
- Better performance for large-scale problems
- More interview environments support Java

**Python Benefits:**
- Faster to write (less boilerplate)
- Rich built-in functions (`zip`, `enumerate`, `map`, `filter`)
- List comprehensions: `[x*2 for x in arr if x > 0]`
- Cleaner syntax for complex operations

### Space Complexity Notes

| Structure | Java | Python |
|-----------|------|--------|
| **Array of 26 chars** | `int[26]` = O(1) | `[0]*26` = O(1) |
| **HashMap/Dict** | O(n) | O(n) |
| **Recursion stack** | O(depth) | O(depth) |

### Common Interview Imports

**Java:**
```java
import java.util.*;  // Most common
```

**Python:**
```python
from collections import defaultdict, Counter, deque
import heapq
from typing import List, Optional  # For type hints
```

---

## 🎯 Pro Tips for Interviews

1. **Java strings are immutable** → Use `StringBuilder` for string concatenation in loops
2. **Python has no char type** → Use `s[0]` (single-char string)
3. **Java requires explicit types** → Declare return type and parameter types
4. **Python is dynamic** → Can reassign variables to different types
5. **Both**: Prefer `for-each` style loops when not needing indices
6. **Sorting stability**: Both Java and Python use stable sorts (equal elements maintain order)
7. **List slicing in Python** is powerful but creates new lists (O(k) space for k elements)

---

## 📝 Interview Template

### Java:
```java
class Solution {
    public ReturnType methodName(ParamType param) {
        // Your solution here
        return result;
    }
}
```

### Python:
```python
class Solution:
    def methodName(self, param: ParamType) -> ReturnType:
        # Your solution here
        return result
```

---

**Remember**: Focus on the algorithm, not the syntax. Both languages can solve any interview problem efficiently!
