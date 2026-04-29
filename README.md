# 🚀 DSA and System Design Lab

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
| **LinkedList** | ⏳ Pending | - | - |
| **Stack** | ✅ Built | Array-based (resize) | Push/Pop: $O(1)$ |
| **Queue** | ⏳ Pending | Circular Buffer logic | - |

### 2. Associative Structures
| Structure | Status | Notes |
| :--- | :--- | :--- |
| **HashMap** | ⏳ Pending | Chaining strategy |

### 3. Hierarchical Structures
| Structure | Status | Notes |
| :--- | :--- | :--- |
| **Binary Tree** | ⏳ Pending | - |
| **Heap** | ⏳ Pending | - |

---

## 📉 Architect's Complexity Cheat Sheet

| Category | Operation | Time Complexity | Best Use Case |
| :--- | :--- | :--- | :--- |
| **Dynamic Array** | Search | $O(N)$ | General storage |
| **Dynamic Array** | Access | $O(1)$ | Random retrieval |
| **Dynamic Array** | Add (resize)| $O(1)$ | Amortized growth |
| **Stack** | Push / Pop | $O(1)$ | LIFO, Undo/Redo, Parsing |

---

## 📁 Repository Structure

```text
DSAWithOnlineAssessments/
├── src/main/java/com/prabodh/
│   ├── ds/           # Custom implementations (The "Build")
│   │   ├── sequential/ # Arrays, Stacks, Queues
│   ├── patterns/     # Algorithmic logic (The "Analyze")
│   └── challenges/   # LeetCode/OA Practice (The "Apply")
└── src/test/java/com/prabodh/
