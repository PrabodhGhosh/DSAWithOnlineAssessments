package com.prabodh.pattern.nonlinear;

public class TreeInvertDFS {

    public TreeNode invertTree(TreeNode root)
    {
        // Step 1: Base Case (The Escape Hatch)
        if(root==null)
            return null;

        // Step 2: Core Logic (The Pointer Swap)
        TreeNode temp = root.left;
        root.left=root.right;
        root.right=temp;
        // Step 3: Branching Traversal (Pass the Baton Downwards)
        invertTree(root.left);
        invertTree(root.right);
        // Step 4: Return the modified structural anchor
        return root;

    }

    public static void main(String[] args) {
        TreeInvertDFS solution = new TreeInvertDFS();

        // Let's assemble our standard test tree:
        //        10
        //       /  \
        //      5    20
        //          /
        //         15
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);

        System.out.println("--- Executing Simple DFS Problem: Invert Tree ---");
        TreeNode invertedRoot = solution.invertTree(root);

        // Quick structural verification paths:
        System.out.println("Master Root Value (Should stay 10): " + invertedRoot.val);
        System.out.println("New Left Child (Should be 20):      " + invertedRoot.left.val);
        System.out.println("New Right Child (Should be 5):      " + invertedRoot.right.val);
        System.out.println("New Deep Sub-Child (Should be 15):  " + invertedRoot.left.right.val);
    }
}
