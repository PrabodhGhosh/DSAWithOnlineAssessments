package com.prabodh.pattern.nonlinear;

public class NumberOfIslands {

    /**
     * OA Pattern: 2D Matrix Grid DFS (Flood-Fill)
     * Time Complexity: O(M * N) - We visit each cell in the grid exactly once.
     * Space Complexity: O(M * N) - In the worst case of an all-land grid, due to the recursion stack.
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int islandCount = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        // Iterate through every cell in the 2D grid
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // When we discover unvisited land ('1'), it marks the start of a new island
                if (grid[r][c] == '1') {
                    islandCount++;
                    // Trigger DFS to traverse and sink all connected pieces of this island
                    sinkIslandDFS(grid, r, c);
                }
            }
        }
        return islandCount;
    }

    private void sinkIslandDFS(char[][] grid, int r, int c) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Base Case: Out of grid bounds OR cell is water ('0')
        if (r < 0 || r >= rows || c < 0 || c >= cols || grid[r][c] == '0') {
            // Safe termination to prune the exploration branch
            return;
        }

        // Mutation Optimization: Mark this land cell as visited by overwriting it to '0' (water).
        // This eliminates the need for an extra boolean[][] visited array, optimizing space!
        grid[r][c] = '0';

        // Recursively plunge into all 4 cardinal neighboring directions
        sinkIslandDFS(grid, r - 1, c); // Up
        sinkIslandDFS(grid, r + 1, c); // Down
        sinkIslandDFS(grid, r, c - 1); // Left
        sinkIslandDFS(grid, r, c + 1); // Right
    }

    public static void main(String[] args) {
        NumberOfIslands solver = new NumberOfIslands();
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println("--- Matrix Pattern: Counting Connected Grid Components ---");
        System.out.println("Total Distinct Islands: " + solver.numIslands(grid)); // Expected: 3
    }
}