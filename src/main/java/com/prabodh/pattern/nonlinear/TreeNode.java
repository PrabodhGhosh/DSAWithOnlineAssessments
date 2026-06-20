package com.prabodh.pattern.nonlinear;

/**
 * Platform-standard binary tree node simulation.
 * This matches the exact class structure provided during Online Assessments.
 */

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}
