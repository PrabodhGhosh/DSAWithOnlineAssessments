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
| **Merge Intervals** | ⏳ Pending | Sorting and consolidating overlapping coordinates or timeline tracks. | - |
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
* **Applied Focus**: Tracking aggregate values or stable string patterns without repetitive evaluations (e.g., `FindAnagramsFixed` leveraging a static `int[26]` frequency map).

#### **Variable-Size Windows (Elastic Bounds)**
The window extends and retracts dynamically like an accordion based on constraint thresholds to establish optimal boundaries.
* **Numeric Optimization**: Expand the window continuously via a `right` pointer to collect elements. When the constraint threshold is breached, a `while` loop forces the `left` pointer to contract the window (e.g., `MinSizeSubarraySumVariable`). Always catch the "unmet criteria" edge case using a ternary initialization fallback (`minLength == Integer.MAX_VALUE ? 0 : minLength`) to protect against dirty return data.
* **State / Structural Optimization**: When tracking character properties like uniqueness (e.g., `LongestSubstringUniqueVariable`), pair the elastic window with a highly performant, constant-space ASCII frequency tracker array (`int[128]`). When a character frequency passes `1`, contract the `left` boundary to drop elements until the uniqueness invariant is restored.
* **Multi-Variable Constraint Tracking**: Advanced variations require controlling distinct key cardinality thresholds (e.g., `LongestSubstringKDistinct`). Here, tracking a primitive state counter (`distinctCount`) derived from zero-to-one transformations inside the index layout ensures $O(1)$ state evaluations. The window contracts only when the unique key collection breaches the limit, running execution drops until explicit elements are completely evicted (`charCounts[leftChar] == 0`).

### 4. Prefix Sums (Cumulative Slicing & Historical Lookbacks)
Used to precompute values across linear sequences to convert nested scan ranges into instantaneous lookups.

#### **Static Range Optimization**
By converting an input array into a cumulative running total layout of size `N + 1`, any range query between indices `left` and `right` can be resolved in $O(1)$ via simple boundary subtraction: `prefixSums[right + 1] - prefixSums[left]`. Allocating the structural length to `N + 1` seeds index 0 with a `0` value, entirely eliminating conditional branch checking for range evaluations originating at the absolute start of the array.

#### **Historical State Lookbacks (The Map Integration)**
When a linear layout includes zero or negative values, dynamic sliding bounds break. To handle aggregate constraint metrics like identifying continuous slices that sum to a target $K$ (`SubarraySumEqualsKPrefixSum`), we combine the cumulative total with a frequency tracking `HashMap`. 
* **The Identity Formula**: As we traverse, we evaluate `targetLookback = currentSum - k`. Checking our history map for this key in $O(1)$ reveals whether a matching subset slice can be terminated at our current index.
* **Cardially Frequency Jumps**: The tracking map maps `[PrefixSum -> Cumulative Frequency Counts]`. Seeding the map with `(0, 1)` establishes the baseline state before processing elements. If zeros or canceling negative numbers appear, the same cumulative total repeats in history. When looking back, adding the historical frequency value (`count += prefixSumMap.get(targetLookback)`) allows our tracking variable to scale cleanly past multiple structural starting blocks in a single operation.

#### **Prefix / Suffix Product Splitting**
When space constraints rule out auxiliary storage arrays and structural limits ban division operators entirely (e.g., `ProductExceptSelf`), calculations must leverage independent direction passes. The target index configuration is mathematically split into isolated components: the cumulative multiplication of everything to the left (Prefix) combined with everything to the right (Suffix). Passing sequentially forward constructs the baseline prefix states inside the mandatory output layout, while an inverted backward pass scales the existing records via a primitive suffix accumulator tracker, achieving $O(N)$ execution inside a strict $O(1)$ space environment.

### 5. Monotonic Stacks (Eviction Mechanics & Dynamic Trends)
Used to reduce quadratic nested boundary scans $O(N^2)$ into single-pass linear time $O(N)$ by keeping data structured in a strict, sorting trend (strictly increasing or decreasing from bottom to top).

#### **The Eviction Discovery Moment**
Instead of scanning forward into an unknown future, elements sit waiting inside a safe tracking stack. The arrival of an incoming element that breaks the sorted invariant triggers a `while` loop chain-reaction. This eviction loop pop is highly informative: the breaking item is confirmed as the immediate **"Next Greater Element"** (or Next Smaller Element) for all evicted indices simultaneously.

#### **Pointer Mapping vs Ledger Resolution**
* **Value ledgers**: In standard scenarios mapping strict subset lookups (`NextGreaterElementMonotonicStack`), a `HashMap` can be used to capture raw `[Popped Value -> Evicting Value]` associations.
* **Direct index mapping**: To support duplicate input numbers or direct output arrays (`NextGreaterElementIIMonotonicStack`), the stack must store array indices instead of raw values. Evicted elements are directly assigned in-place via `result[poppedIndex] = currentNum`, completely removing extra hash mapping allocations.

#### **Circular Boundaries & Temporal Spans**
* **Virtual array duplication**: Circular coordinate paths are handled smoothly without allocating large duplicated buffers. Running the outer loop up to `2 * N - 1` and evaluating bounds via modulo partitioning (`i % N`) lets unmapped trailing elements safely wrap around to evaluate historical headers.
* **Temporal distance calculation**: For tracking interval spans or delay thresholds (`DailyTempMonotonicStack`), indexing structures evaluate distance dynamically during eviction. The difference between timelines (`i - poppedIndex`) immediately establishes the metric interval without looking up downstream array ranges.

---

## 📉 Architect's Complexity Cheat Sheet

| Category | Operation / Pattern | Time Complexity | Space Complexity | Best Use Case |
| :--- | :--- | :--- | :--- | :--- |
| **Cycle Detection**| Fast & Slow | $O(N)$ | $O(1)$ | Circular verification / Infinite loops |
| **Find Middle** | Fast & Slow | $O(N)$ | $O(1)$ | Merge Sort splits on Linked lists |
| **Fixed Range** | Sliding Window (Fixed) | $O(N)$ | $O(1)$ / $O(K)$ | Moving metrics / Anagram tracking |
| **Dynamic Range**| Sliding Window (Variable) | $O(N)$ | $O(1)$ | Shortest/Longest bounds search |
| **Range Queries** | Prefix Sums | $O(1)$ (Post-compute)| $O(N)$ | Immutable range total operations |
| **Nearest Trend** | Monotonic Stack | $O(N)$ | $O(N)$ | Finding structural spikes or drops |
| **Scheduling** | Merge Intervals | $O(N \log N)$ | $O(N)$ or $O(1)$ | Consolidating overlaps / Calendering |
| **Coordinate Grid**| Matrix Traversal | $O(M \times N)$ | $O(1)$ | Spiral loops / Layered image processing |
| **Dynamic Array** | Access | $O(1)$ | $O(1)$ | Fast random retrieval |
| **Stack** | Push / Pop | $O(1)$ | $O(1)$ | LIFO tracking / Undo-Redo engines |

---

## 📁 Repository Structure

```text
DSAWithOnlineAssessments/
├── .github/workflows/  # CI/CD pipelines (GitHub Actions)
├── src/main/java/com/prabodh/
│   ├── ds/             # Custom implementations (The "Build")
│   ├── patterns/       # Algorithmic logic (The "Analyze")
│   └── challenges/     # LeetCode/OA Practice (The "Apply")
└── src/test/java/com/prabodh/
