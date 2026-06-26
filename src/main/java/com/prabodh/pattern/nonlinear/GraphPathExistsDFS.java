package com.prabodh.pattern.nonlinear;

import java.util.ArrayList;
import java.util.List;

public class GraphPathExistsDFS {

    /**
     * Graph DFS Traversal with Cycle Protection
     * Time Complexity: O(V + E) - We look at every vertex and edge at most once.
     * Space Complexity: O(V + E) - To store the adjacency list and the recursive call stack.
     */
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        // Step 1: Rapidly blueprint the Adjacency List using standard Java lists
        List<Integer>[] adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }

        // Populate the network (Undirected = populate both directions)
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjList[u].add(v);
            adjList[v].add(u);
        }

        // Step 2: Initialize our cycle protection ledger
        boolean[] visited = new boolean[n];

        // Step 3: Trigger the recursive DFS plunge engine
        return dfs(adjList, visited, source, destination);
    }

    private boolean dfs(List<Integer>[] adjList, boolean[] visited, int current, int destination) {
        // Base Case 1: Target discovered!
        if (current == destination) {
            return true;
        }

        // Base Case 2: Mark this airport as visited. If it was already visited, stop to avoid cycles.
        if (visited[current]) {
            return false;
        }
        visited[current] = true; // Log our footprint

        // Recursive Step: Plunge down into all unvisited neighbors branching from this hub
        for (int neighbor : adjList[current]) {
            if (dfs(adjList, visited, neighbor, destination)) {
                return true; // Pass the success signal up the call stack immediately
            }
        }

        return false; // No path found out of this branch
    }

    public static void main(String[] args) {
        GraphPathExistsDFS solver = new GraphPathExistsDFS();

        // Test Case Graph: 0 <-> 1, 1 <-> 2, 2 <-> 0
        int n = 3;
        int[][] edges = {{0, 1}, {1, 2}, {2, 0}};
        int source = 0;
        int destination = 2;

        System.out.println("--- Executing Graph Pattern: DFS Path Verification ---");
        boolean hasPath = solver.validPath(n, edges, source, destination);
        System.out.println("Does a valid path exist from " + source + " to " + destination + "? " + hasPath);
    }
}