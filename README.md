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
| **Stack** | ⏳ Pending | - | - |
| **Queue** | ⏳ Pending | - | - |

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

---

## 📁 Repository Structure

```text
DSAWithOnlineAssessments/
├── src/main/java/com/prabodh/
│   ├── ds/           # Custom implementations (The "Build")
│   ├── patterns/     # Algorithmic logic (The "Analyze")
│   └── challenges/   # LeetCode/OA Practice (The "Apply")
└── src/test/java/com/prabodh/