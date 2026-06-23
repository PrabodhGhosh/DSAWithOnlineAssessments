package com.prabodh.pattern.nonlinear;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RightAndLeftSideViewDFS {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> rightViewElements = new ArrayList<>();
        if (root == null) return rightViewElements;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();

                // Boundary Condition: Isolate the absolute right-most element of this horizon
                if (i == levelSize - 1) {
                    rightViewElements.add(currentNode.val);
                }

                if (currentNode.left != null) queue.add(currentNode.left);
                if (currentNode.right != null) queue.add(currentNode.right);
            }
        }
        return rightViewElements;
    }

    public List<Integer> leftSideView(TreeNode root) {
        List<Integer> leftViewElements = new ArrayList<>();
        if (root == null) return leftViewElements;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();

                // Boundary Condition: Isolate the absolute left-most element of this horizon
                if (i == 0) {
                    leftViewElements.add(currentNode.val);
                }

                if (currentNode.left != null) queue.add(currentNode.left);
                if (currentNode.right != null) queue.add(currentNode.right);
            }
        }
        return leftViewElements;
    }

    public static void main(String[] args) {
        RightAndLeftSideViewDFS solution = new RightAndLeftSideViewDFS();

        // Assembling an asymmetric test hierarchy to stress-test boundary selection:
        //        10
        //       /  \
        //      5    20
        //          /
        //         15
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);

        // 1. Right Side View Verification (Expected: [10, 20, 15])
        System.out.println("Right Side View:  " + solution.rightSideView(root));

        // 2. Left Side View Verification (Expected: [10, 5, 15])
        System.out.println("Left Side View:   " + solution.leftSideView(root));
    }
}
