package com.prabodh.pattern.sequential;

import java.util.Arrays;

/**
 * 2D Matrix Pattern 2 (Grid Crawler Application): 8-Directional Flood Fill Single Step.
 * * Mental Model: "Expanded Proximity Scanning"
 * - Appends diagonal directional coordinate offsets to the lookup matrix.
 * - The underlying traversal logic scales automatically without changing the execution loop.
 */
public class FloodFill2Matrix2D {

    // Centralized structural coordinate offsets for all 8 surrounding neighbors:
    // [North, South, West, East, North-West, North-East, South-West, South-East]
    private static final int[][] DIRECTIONS = {
            {-1,  0}, // 1. NORTH
            { 1,  0}, // 2. SOUTH
            { 0, -1}, // 3. WEST
            { 0,  1}, // 4. EAST
            {-1, -1}, // 5. NORTH-WEST (Top-Left Diagonal Corner)
            {-1,  1}, // 6. NORTH-EAST (Top-Right Diagonal Corner)
            { 1, -1}, // 7. SOUTH-WEST (Bottom-Left Diagonal Corner)
            { 1,  1}  // 8. SOUTH-EAST (Bottom-Right Diagonal Corner)
    };

    public void floodFill8Way(int[][] matrix, int row, int col, int newColor) {
        // Base Guard Check
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        int originalColor = matrix[row][col];

        if (originalColor == newColor) {
            return;
        }

        // Update the center focal pixel
        matrix[row][col] = newColor;

        int maxRows = matrix.length;
        int maxCols = matrix[0].length;

        // Iterate sequentially through all 8 structural offset directions
        for (int[] dir : DIRECTIONS) {
            int nextNeighborRow = row + dir[0];
            int nextNeighborCol = col + dir[1];

            // Central Safety Gate
            if (nextNeighborRow >= 0 && nextNeighborRow < maxRows &&
                    nextNeighborCol >= 0 && nextNeighborCol < maxCols) {

                // Only change color if the neighbor matches the original source color
                if (matrix[nextNeighborRow][nextNeighborCol] == originalColor) {
                    matrix[nextNeighborRow][nextNeighborCol] = newColor;
                }
            }
        }
    }

    /**
     * Diagnostic Execution Verification
     */
    public static void main(String[] args) {
        FloodFill2Matrix2D engine = new FloodFill2Matrix2D();

        // 3x3 Test Image Grid identical to the 4-way scenario
        int[][] image = {
                {1, 1, 3},
                {0, 1, 1},
                {1, 2, 1}
        };

        System.out.println("--- Executing 8-Directional Flood Fill Single Step at (1,1) ---");
        engine.floodFill8Way(image, 1, 1, 5);

        System.out.println("\nResulting 8-Way Matrix Grid:");
        // Expected Output:
        // [5, 5, 3] -> BOTH row positions updated (North-West diagonal corner is now painted!)
        // [0, 5, 5] -> Center and East updated.
        // [1, 2, 5] -> South-East diagonal corner updated! South (2) left alone because it didn't match.
        for (int[] row : image) {
            System.out.println(Arrays.toString(row));
        }
    }
}