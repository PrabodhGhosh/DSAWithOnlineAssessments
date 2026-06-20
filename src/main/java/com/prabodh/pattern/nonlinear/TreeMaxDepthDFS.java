package com.prabodh.pattern.nonlinear;

public class TreeMaxDepthDFS {

    /**
     * OA Pattern Core: Depth-First Search (DFS)
     * Recursively sweeps down to the lowest leaf of each branch path.
     */

    public int maxDepth(TreeNode root)
    {
        // Base Case: If the execution pointer hits a dead-end, return depth 0
        if(root==null)
            return 0;

        // Recursive Pass: Hand the baton down to the left and right tracks

        int leftTrackHeight = maxDepth(root.left);
        int rightTrackHeight = maxDepth(root.right);
        // Core Strategy: Take the deeper path and add 1 to count the current node layer
        return Math.max(leftTrackHeight,rightTrackHeight)+1;
    }

    public static void main(String[] args) {
        TreeMaxDepthDFS solution = new TreeMaxDepthDFS();

        // Let's build a tree with a maximum depth of 3 layers
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15); // Layer 3 leaf element

        System.out.println("--- Executing Pattern 8A: Tree DFS Deep-Dive ---");
        int depth = solution.maxDepth(root);

        System.out.println("Expected Max Depth: 3");
        System.out.println("Actual Max Depth:   " + depth);
    }
}
