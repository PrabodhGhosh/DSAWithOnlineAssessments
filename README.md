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
| **Sliding Window** | ⏳ Pending | Maintaining a data subset window to track running metrics. | - |

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

---

## 📉 Architect's Complexity Cheat Sheet

| Category | Operation | Time Complexity | Best Use Case |
| :--- | :--- | :--- | :--- |
| **Cycle Detection**| Fast & Slow | $O(N)$ Time / $O(1)$ Space | Detecting infinite loops |
| **Find Middle** | Fast & Slow | $O(N)$ Time / $O(1)$ Space | Merge Sort on LinkedList |
| **Dynamic Array** | Access | $O(1)$ | Random retrieval |
| **Stack** | Push / Pop | $O(1)$ | LIFO, DFS, Undo/Redo |

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
