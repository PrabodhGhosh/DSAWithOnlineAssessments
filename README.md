# 🚀 DSA and System Design Lab
![Build Status](https://github.com/prabodh-ghosh/DSAWithOnlineAssessments/actions/workflows/main.yml/badge.svg)

This repository is my personal laboratory for mastering Data Structures, Algorithms, and System Design patterns.

## 🏗 The Build-Analyze-Apply Methodology
I approach every topic using a three-stage loop to ensure depth of understanding:
1. **Build**: Custom implementation from scratch (no Java Collections).
2. **Analyze**: Benchmarking memory layout and time complexity.
3. **Apply**: Solving real-world LeetCode/Online Assessment challenges using my custom implementations.

---

## 🗺 Roadmap & Progress

### 1. Sequential Structures
| Structure | Status | Implementation Notes | Complexity (Amortized) |
| :--- | :--- | :--- | :--- |
| **Dynamic Array** | ✅ Built | Doubling resize strategy | Add: $O(1)$ |
| **LinkedList** | ✅ Built | Head/Tail management | Append: $O(1)$, Insert: $O(N)$ |
| **Stack** | ✅ Built | Array-based (resize) | Push/Pop: $O(1)$ |
| **Queue** | ✅ Built | Circular Buffer (Modulo math) | Enqueue/Dequeue: $O(1)$ |

### 2. Algorithmic Patterns (OA Strategy)
| Pattern | Status | Description | Problems Solved |
| :--- | :--- | :--- | :--- |
| **Two Pointers** | ✅ Built | Bi-directional traversal to reduce complexity from $O(N^2)$ to $O(N)$. | `TwoSum II`, `Valid Palindrome` |
| **Fast & Slow** | ✅ Built | "Tortoise and Hare" strategy for cycle detection and midpoint discovery. | `Linked List Cycle II`, `Happy Number` |
| **Sliding Window** | ✅ Built | Maintaining a running subset loop over continuous data elements. | `MaxSumSubarrayFixed`, `FindAnagramsFixed`, `MinSizeSubarraySumVariable`, `LongestSubstringUniqueVariable`, `LongestSubstringKDistinct` |
| **Prefix Sums** | ✅ Built | Precomputing cumulative running metrics to answer range queries in constant time. | `RangeSumQueryPrefixSum`, `SubarraySumEqualsKPrefixSum`, `ProductExceptSelf` |
| **Monotonic Stack** | ✅ Built | Enforcing a strict directional sort order inside a stack frame to map nearest properties. | `NextGreaterElementMonotonicStack`, `NextGreaterElementIIMonotonicStack`, `DailyTempMonotonicStack` |
| **Merge Intervals** | ✅ Built | Sorting and consolidating overlapping coordinates or timeline tracks. | `MergeInterval` (Object-based), `MergeInterval2` (Primitive 2D Matrix), `MergeNewInterval` (3-Phase Insertion) |
| **Matrix Traversal** | ⏳ Pending | Controlling boundary variables to navigate multi-dimensional index coordinates. | - |

---

## 🧠 Pattern Deep Dive

### 1. Two Pointers (Meeting in the Middle)
Used on **sorted** structures to find pairs or symmetries. By moving pointers from both ends toward the center, we eliminate entire branches of unnecessary comparisons.
* **Key Logic**: If `sum > target`, move `end--`. If `sum < target`, move `begin++`.

### 2. Fast & Slow Pointers (The Tortoise and the Hare)
This pattern uses two pointers moving at different speeds (usually 1 step vs 2 steps). It is the gold standard for detecting cycles in linked structures or finding midpoints in $O(N)$ time with $O(1)$ space.

#### **Cycle Entry Point Discovery**
In a "Lollipop" shaped list (Linear path leading into a circle), we use a two-phase approach:
1. **Phase 1**: Both pointers start at `head`. If they meet, a cycle exists.
2. **Phase 2**: Reset `fast` to `head`. Move both at a 1:1 speed. They are mathematically guaranteed to meet at the **Start of the Cycle**.
* **Mathematical Proof**: $L_1$ (distance to cycle) is equivalent to the distance from the meeting point back to the cycle start.

#### **The "Hidden" Pattern: Happy Number**
The pattern extends beyond Linked Lists. In numerical problems like "Happy Number," the "next" pointer is the result of a function (sum of squares of digits). If the sequence enters a loop that doesn't include 1, the Fast/Slow pointers will collide, signaling a "Not Happy" state.

### 3. Sliding Window (Resource Reuse Optimization)
Used to optimize performance when evaluating continuous subarrays or substrings. Instead of scanning nested bounds from scratch, it transitions a tracking frame across elements, reusing calculated historical data to reduce complexity from $O(N^2)$ to $O(N)$.

#### **Fixed-Size Windows**
The boundaries of the frame maintain a static length `K`. 
* **Mechanics**: Construct the initial state up to index `K - 1`. Then slide the window step-by-step by adding the incoming element at the right edge and subtracting the discarded element falling off the left edge (`i - k`).
* **Applied Focus**: Tracking aggregate values or stable string patterns without repetitive evaluations (e.g., `FindAnagramsFixed
