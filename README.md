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
| **Two Pointers** | 🏃 In Progress | Using two indices to traverse from ends to middle or at different speeds. Reduces $O(N^2)$ to $O(N)$. | `TwoSum II`, `Valid Palindrome` |
| **Sliding Window** | ⏳ Pending | Maintaining a subset of data within a range to track running metrics. | - |
| **Fast & Slow** | ⏳ Pending | "Tortoise and Hare" approach for cycle detection and mid-point finding. | - |

---

## 🧠 Pattern Deep Dive

### Two Pointers (Meeting in the Middle)
This pattern is used on **sorted** arrays or strings to find pairs or symmetries. By moving pointers from the `begin` and `end` towards the center, we eliminate unnecessary comparisons, effectively optimizing time complexity from **$O(N^2)$** to **$O(N)$**.
* **Key Logic**: If `sum > target`, move `end--`. If `sum < target`, move `begin++`.

---

## 📉 Architect's Complexity Cheat Sheet

| Category | Operation | Time Complexity | Best Use Case |
| :--- | :--- | :--- | :--- |
| **Dynamic Array** | Access | $O(1)$ | Random retrieval |
| **Stack** | Push / Pop | $O(1)$ | LIFO, DFS, Undo/Redo |
| **Queue** | Enqueue / Dequeue | $O(1)$ | FIFO, BFS, Buffering |
| **LinkedList** | Insert (Head/Tail) | $O(1)$ | Frequent growth/shrink |

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
