package com.prabodh.pattern.nonlinear;

public class TreeSameDFS {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Guard 1: Both tracks hit a dead end together -> Structural match found!
        if (p == null && q == null) {
            return true;
        }

        // Guard 2: Structural mismatch (one track hit a dead end, but the other still has data)
        if ((p == null && q != null) || (p != null && q == null)) {
            return false;
        }

        // Core Logic: Value mismatch check
        if (p.val != q.val) {
            return false;
        }

        // Branching Traversal: Pass the baton down both tracks simultaneously
        boolean leftMatches = isSameTree(p.left, q.left);
        boolean rightMatches = isSameTree(p.right, q.right);

        return leftMatches && rightMatches;
    }

    public static void main(String[] args) {
        TreeSameDFS solution = new TreeSameDFS();

        // Tree P:
        //      10
        //     /  \
        //    5    20
        TreeNode p = new TreeNode(10);
        p.left = new TreeNode(5);
        p.right = new TreeNode(20);

        // Tree Q (Identical to P):
        TreeNode q = new TreeNode(10);
        q.left = new TreeNode(5);
        q.right = new TreeNode(20);

        // Tree R (Structural mismatch via an extra node):
        TreeNode r = new TreeNode(10);
        r.left = new TreeNode(5);
        r.right = new TreeNode(20);
        r.right.left = new TreeNode(15);

        System.out.println("--- Executing Multi-Track DFS: Same Tree Verification ---");
        System.out.println("Comparing P and Q (Expected: true):  " + solution.isSameTree(p, q));
        System.out.println("Comparing P and R (Expected: false): " + solution.isSameTree(p, r));
    }
}