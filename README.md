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

### 1. Sequential & Spatial Structures (Linear)
| Structure | Status | Implementation Notes | Complexity (Amortized) |
| :--- | :--- | :--- | :--- |
| **Dynamic Array** | ✅ Built | Doubling resize strategy | Add: $O(1)$ |
| **LinkedList** | ✅ Built | Head/Tail management | Append: $O(1)$, Insert: $O(N)$ |
| **Stack** | ✅ Built | Array-based (resize) | Push/Pop: $O(1)$ |
| **Queue** | ✅ Built | Circular Buffer (Modulo math) | Enqueue/Dequeue: $O(1)$ |

### 2. Hierarchical & Non-Linear Structures
| Structure | Status | Implementation Notes | Complexity (Amortized) |
| :--- | :--- | :--- | :--- |
| **Binary Search Tree (BST)** | ✅ Built | Custom Node pointer allocation; Left/Right sorting invariants | Search: $O(\log N)$, Insert: $O(\log N)$ |
| **Custom Hash Map** | ⏳ Pending | Bucket array with separate chaining for collision handling | Put/Get: $O(1)$ |
| **Graph (Adjacency List)** | ⏳ Pending | Dynamic vertex-to-edge mapping framework for dependency modeling | Add Vertex/Edge: $O(1)$ |

### 3. Algorithmic Patterns (OA Strategy)
| Pattern | Status | Description | Problems Solved |
| :--- | :--- | :--- | :--- |
| **Two Pointers** | ✅ Built | Bi-directional traversal to reduce complexity from $O(N^2)$ to $O(N)$. | `TwoSum II`, `Valid Palindrome` |
| **Fast & Slow** | ✅ Built | "Tortoise and Hare" strategy for cycle detection and midpoint discovery. | `Linked List Cycle II`, `Happy Number` |
| **Sliding Window** | ✅ Built | Maintaining a running subset loop over continuous data elements. | `MaxSumSubarrayFixed`, `FindAnagramsFixed`, `MinSizeSubarraySumVariable`, `LongestSubstringUniqueVariable`, `LongestSubstringKDistinct` |
| **Prefix Sums** | ✅ Built | Precomputing cumulative running metrics to answer range queries in constant time. | `RangeSumQueryPrefixSum`, `SubarraySumEqualsKPrefixSum`, `ProductExceptSelf` |
| **Monotonic Stack** | ✅ Built | Enforcing a strict directional sort order inside a stack frame to map nearest properties. | `NextGreaterElementMonotonicStack`, `NextGreaterElementIIMonotonicStack`, `DailyTempMonotonicStack` |
| **Merge Intervals** | ✅ Built | Sorting and consolidating overlapping coordinates or timeline tracks. | `MergeInterval`, `MergeInterval2`, `MergeNewInterval` |
| **Matrix Traversal** | ✅ Built | Controlling boundary variables, directional loops, and structural primitives to transform multi-dimensional grids. | `MatrixTransformer2D`, `Matrix2DSpiral`, `RectangularMatrixRotator` |
| **Tree Traversal (DFS/BFS)** | ✅ Built | Executing non-linear searches via recursive depth paths and queue-based level sweeps (Crucial for DOM parsing algorithms). | `TreeMaxDepthDFS`, `TreeInvertDFS`, `TreeSameDFS`, `TreeLevelOrderBFS`, `TreeRightSideViewBFS`, `TreeLeftSideViewBFS` |
| **State Tracking & Graph Maps** | ⏳ Pending | Utilizing tracking maps and visited tables to trace dependency paths and catch cycle deadlocks. | *Placeholders: To be populated* |

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

### 6. Merge Intervals (Chronological Alignment & Boundary Consolidation)
Used to collapse overlapping coordinate spans, calendar logs, or resource timelines into an optimized, unified continuity layout.

#### **Upfront Sorting Invariant**
To prevent a nested $O(N^2)$ tracking cross-check between randomized spans, intervals are snapped cleanly to a left boundary anchor. Sorting coordinates by their start index position (`intervals[i][0]`) via an $O(N \log N)$ pre-computation lambda turns multi-layered conflict processing into a sequential, single-pass linear execution loop ($O(N)$).

#### **Active Reference Exploitation**
Instead of copying objects or shifting arrays continuously, the consolidation engine relies on primitive heap mutations in-place. Seeding a tracking collection with the baseline boundary block pointer (`currentClip = intervals[0]`) links its memory reference directly into the output chain. As the linear index loop moves rightward:
* **The Overlap Intersection**: If `nextClip.start <= currentClip.end`, a coordinate collision occurs. The right tracker edge stretches instantly via a maximum boundary selection: `currentClip.end = Math.max(currentClip.end, nextClip.end)`.
* **The Disjoint Gap**: If `nextClip.start > currentClip.end`, a distinct structural pocket of space occurs. The reference engine shifts focus entirely, pointing the active window tracker at the new block (`currentClip = nextClip`) and sliding it into the result collection as a new baseline track.

#### **Pre-Sorted 3-Phase Partitioning**
When an existing track has zero internal overlaps and comes pre-sorted, introducing a new range can be processed in strict $O(N)$ runtime without invoking a sorting method. The timeline is split cleanly across sequential tracking loops using an isolation index pointer:
* **Phase 1 (Pre-Buffer)**: Iteratively pass over and commit all early interval tracks whose end coordinates finish completely before the new interval begins (`intervals[i][1] < newInterval[0]`).
* **Phase 2 (The Melt Zone)**: As long as existing tracks enter before the new interval finishes (`intervals[i][0] <= newInterval[1]`), their structures touch. They are absorbed completely into the new bounds by evaluating `newInterval[0] = Math.min(...)` and `newInterval[1] = Math.max(...)` across the indices before committing the unified element.

### 7. 2D Matrix Traversal (Multi-Dimensional Coordinate Mapping)
Used to process bounded data tables, image pixel layouts, and geographic coordinate grids by swapping, reflecting, or encapsulating directional index trackers.

#### **Pattern 3A: Linear Scanning & Perimeter Fences**
Designed to isolate localized row/column tracks by setting explicit boundary walls (`top`, `bottom`, `left`, `right`). Contracting these boundaries immediately inward upon track exhaustion converts raw grid spaces into organized linear arrays without duplicate coordinate evaluations.
* **Asymmetric Safety Bounds**: On rectangular $M \times N$ layouts, active cross-check conditionals (`if (top <= bottom)` and `if (left <= right)`) must protect backward paths to stop closing walls from duplicate processing.

#### **Pattern 3B: In-Place Diagonal Transformations**
Achieves pure geometric orientation shifts ($90^\circ, 180^\circ, 270^\circ$) inside existing matrix boundaries without consuming extra memory. Operations are broken down into decoupled atomic primitive layers:
1. **The In-Place Transpose**: Converts rows to columns across the main diagonal axis. The inner column iteration must strictly bind its starting pointer to the current row index (`c = r`) to exclusively traverse the upper triangle, completely avoiding the double-swap trap that reverts values.
2. **Horizontal/Vertical Reflections**: Reversing elements within each row buffer independently (Horizontal) or shifting entire row array buffers across matching top/bottom limits (Vertical) anchors the directional compass points. Combining `Transpose + Horizontal Reverse` yields a $90^\circ$ Clockwise rotation, while `Transpose + Vertical Reverse` handles Counter-Clockwise turns.

#### **Pattern 3C: Out-of-Place Rectangular Inversions**
When rotating a non-square grid ($M \times N$), changing dimensions (e.g., $2 \times 3 \rightarrow 3 \times 2$) makes in-place modifications physically impossible due to fixed array allocation constraints. The layout must be mapped out-of-place into a newly allocated inverted destination block (`new int[cols][rows]`) leveraging an offset-adjusted structural mapping index:
$$\text{rotated}[c][\text{maxRows} - 1 - r] = \text{matrix}[r][c]$$

---

### 8. Tree Traversal Mechanics (Hierarchical Navigation)
Tree traversal involves navigating non-linear, branching memory networks. Under live Online Assessment pressure, complex problems collapse easily when split into either vertical (DFS) or horizontal (BFS) execution models.

#### **Pattern 8A: Depth-First Search (DFS / Vertical Plunge)**
DFS leverages the internal runtime **Call Stack** via recursion to plunge down a single track until it reaches a dead end, processing elements deeply before backtracking. Bug-free node manipulation is achieved by organizing logic into a strict **3-Step DFS Engine Architecture**:

1. **The Base Case (The Escape Hatch)**: The critical boundary guard checking for `null` pointer targets. This stops further descent and shields subsequent layers from throwing fatal `NullPointerException` errors.
   * *Example*: `if (root == null) return null;`
2. **The Core Logic / Functionality Layer**: The precise point where data is analyzed, updated, or validated within the active node frame.
   * *Inversion (LeetCode 226)*: Swapping child reference paths with a temporary pointer holder variable (`TreeNode temp = root.left; root.left = root.right; root.right = temp;`).
   * *Identity Verification (LeetCode 100)*: Running an immediate equality check on primitive values (`if (p.val != q.val) return false;`).
3. **The Recursive Call (Passing the Baton)**: Branching execution paths further into the left and right downward hierarchies (`node.left`, `node.right`). The final result is assembled by merging the booleans or metrics returned from both sub-trees.
   * *Combination Check*: `return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);`

#### **Pattern 8B: Breadth-First Search (BFS / Level-Order Traversal)**
Unlike DFS, BFS sweeps horizontally across each tier layout. Because it cannot track horizons implicitly using recursion, it establishes structural layer fences using an explicit **FIFO Queue** runway.

##### **The 3-Step BFS Level-Isolation Framework**
1. **Conveyor Seed**: Initialize an explicit `Queue<TreeNode>` container and push the root node onto the track to act as the baseline horizon anchor.
2. **Horizon Snapshotting**: Encapsulate processing within a `while (!queue.isEmpty())` loop. At the immediate entry point of each layer sequence, freeze the queue's boundary footprint via `int levelSize = queue.size();`. This snapshots precisely how many nodes occupy the current horizontal tier, insulating the loop calculation from downstream child elements accumulating in the back of the queue.
3. **Flush and Queue Cycles**: Execute an isolated `for` loop exactly `levelSize` times. Extract the front node via `queue.poll()`, log its value, and push its valid left and right children into the back of the queue to stage the subsequent tier horizon.
   * *Perspective Modification (LeetCode 199)*: Adding horizontal perspective metrics becomes simple by checking index coordinates during the flush cycle. Capturing the node when `i == levelSize - 1` isolates the **Right Side View**, whereas matching `i == 0` captures the **Left Side View**.

---

### 9. State Tracking & Graph Maps (Dependency Architecture)
*Placeholders: Microservice dependency resolution and verification tracking patterns to be populated along the learning journey.*

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
| **Grid Boundary** | Matrix Spiral Fence Run | $O(M \times N)$ | $O(1)$ Amortized | Sequential boundary-enclosed reading |
| **Square Rotation**| Matrix In-Place Transformation| $O(N^2)$ | $O(1)$ Absolute | In-place $90^\circ/180^\circ$ matrix rotations |
| **Rect. Rotation** | Matrix Out-of-Place Rotation | $O(M \times N)$ | $O(M \times N)$ | Transforming non-square grids ($M \neq N$) |
| **Tree Traversal**| Depth-First Search (DFS) | $O(N)$ | $O(H)$ Call Stack | Deep path verification / Hierarchical mutations |
| **Tree Traversal**| Breadth-First Search (BFS) | $O(N)$ | $O(W)$ FIFO Queue | Level grouping / Horizontal layer perspective views |
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
