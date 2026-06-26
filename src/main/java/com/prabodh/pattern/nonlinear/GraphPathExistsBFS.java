package com.prabodh.pattern.nonlinear;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphPathExistsBFS {

    /**
     * OA Pattern: Graph BFS Traversal (Concentric Level Sweeping)
     * Time Complexity: O(V + E)
     * Space Complexity: O(V + E)
     */
    public boolean validPathBFS(int n, int[][] edges, int source, int destination) {
        // Step 1: Fast blueprinting of the Adjacency List
        List<Integer>[] adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adjList[edge[0]].add(edge[1]);
            adjList[edge[1]].add(edge[0]);
        }

        // Step 2: Set up the FIFO Queue conveyor belt and visited array
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];

        // Seed the starting airport
        queue.add(source);
        visited[source] = true;

        // Step 3: Standard 3-Step BFS Level-Isolation Framework
        while (!queue.isEmpty()) {
            int current = queue.poll();

            // Target condition check
            if (current == destination) {
                return true;
            }

            // Flush out all neighbors of the current hub
            for (int neighbor : adjList[current]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true; // Mark footprint immediately on discovery
                    queue.add(neighbor);      // Stage for the next horizon tier
                }
            }
        }

        return false; // Queue exhausted without tracking down the destination
    }

    public static void main(String[] args) {
        GraphPathExistsBFS solver = new GraphPathExistsBFS();

        int n = 3;
        int[][] edges = {{0, 1}, {1, 2}, {2, 0}};
        int source = 0;
        int destination = 2;

        System.out.println("--- Executing Graph Pattern: BFS Horizon Sweeping ---");
        boolean hasPath = solver.validPathBFS(n, edges, source, destination);
        System.out.println("Does a valid path exist from " + source + " to " + destination + "? " + hasPath);
    }
}