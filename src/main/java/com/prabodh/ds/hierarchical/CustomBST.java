package com.prabodh.ds.hierarchical;

/**
 * Custom Binary Search Tree (BST) Implementation.
 * Structural Invariant: For any given node, all elements in the left subtree
 * are strictly smaller, and all elements in the right subtree are strictly greater.
 */
public class CustomBST {

    // Internal hierarchical node layout
    private static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private TreeNode root;
    private int size;

    public CustomBST() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Inserts a new value into the BST following left/right branching rules.
     */
    public void insert(int value) {
        this.root = insertRecursive(this.root, value);
        this.size++;
    }

    private TreeNode insertRecursive(TreeNode current, int value) {
        // Base Case: We found an empty slot in the tree layout, plant the node!
        if (current == null) {
            return new TreeNode(value);
        }

        // Branching Paths: Evaluate the non-linear directions
        if (value < current.value) {
            current.left = insertRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = insertRecursive(current.right, value);
        } else {
            // Duplicate value encountered. To keep invariants strict, we do nothing.
            this.size--;
        }

        return current;
    }

    /**
     * Diagnostic print to verify the non-linear memory structure via an in-order scan.
     */
    public void printInOrder() {
        printInOrderRecursive(this.root);
        System.out.println();
    }

    private void printInOrderRecursive(TreeNode node) {
        if (node != null) {
            printInOrderRecursive(node.left);
            System.out.print(node.value + " ");
            printInOrderRecursive(node.right);
        }
    }

    public int getSize() {
        return this.size;
    }
}