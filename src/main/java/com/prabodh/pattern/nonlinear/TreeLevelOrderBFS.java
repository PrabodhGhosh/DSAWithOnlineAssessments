package com.prabodh.pattern.nonlinear;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeLevelOrderBFS {

    public List<List<Integer>> levelOrder (TreeNode root)
    {
        List<List<Integer>> result = new ArrayList<>();
        if(root==null)
            return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty())
        {
            int levelSize = queue.size();
            List<Integer> currentLevelData = new ArrayList<>();
            for(int i=0; i<levelSize;i++)
            {
                // 1. Extract the node from the front of the line
                TreeNode currentNode =queue.poll();
                // 2. Log its numeric value into our level list
                currentLevelData.add(currentNode.val);
                // 3. Stage the downstream child elements safely in the back of the queue
                if(currentNode.left!=null)
                    queue.add(currentNode.left);
                if (currentNode.right != null)
                    queue.add(currentNode.right);
            }
            result.add(currentLevelData);
        }

        return result;
    }
    public static void main(String[] args) {
        TreeLevelOrderBFS solution = new TreeLevelOrderBFS();

        // Let's assemble our standard 3-layer verification hierarchy:
        //        10          <- Level 1
        //       /  \
        //      5    20       <- Level 2
        //          /
        //         15         <- Level 3
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);

        System.out.println("--- Executing Pattern 8B: Tree BFS Level-Order Sweep ---");
        List<List<Integer>> structuralLevels = solution.levelOrder(root);

        System.out.println("Expected Groupings: [[10], [5, 20], [15]]");
        System.out.println("Actual Groupings:   " + structuralLevels);
    }

}
