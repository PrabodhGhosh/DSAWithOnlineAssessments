package com.prabodh.pattern.nonlinear;

public class NumberOfProvinces {

    /**
     * OA Pattern: Adjacency Matrix Connected Component Tracking
     * Time Complexity: O(N^2) - We traverse the entire matrix elements.
     * Space Complexity: O(N) - Used by the boolean tracking array.
     */
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int provinceCount = 0;

        // Check every city in the network
        for (int i = 0; i < n; i++) {
            // If the city hasn't been visited, it means it belongs to an entirely new group
            if (!visited[i]) {
                provinceCount++;
                // Explore and mark every city connected within this group
                exploreProvinceDFS(isConnected, visited, i);
            }
        }
        return provinceCount;
    }

    private void exploreProvinceDFS(int[][] isConnected, boolean[] visited, int currentCity) {
        // Mark the current city as visited
        visited[currentCity] = true;

        // Scan all possible connection mappings for the current city
        for (int neighborCity = 0; neighborCity < isConnected.length; neighborCity++) {
            // If there is a connection match AND the neighbor city hasn't been visited yet, explore it
            if (isConnected[currentCity][neighborCity] == 1 && !visited[neighborCity]) {
                exploreProvinceDFS(isConnected, visited, neighborCity);
            }
        }
    }

    public static void main(String[] args) {
        NumberOfProvinces solver = new NumberOfProvinces();
        // City 0 and City 1 are connected. City 2 is isolated.
        int[][] connections = {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };
        System.out.println("\n--- Graph Pattern: Counting Disconnected Components ---");
        System.out.println("Total Isolated Provinces: " + solver.findCircleNum(connections)); // Expected: 2
    }
}