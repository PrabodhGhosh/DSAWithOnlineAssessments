package com.prabodh.pattern.sequential;

import java.util.Arrays;

/**
 * 2D Matrix Pattern 2 (Grid Crawler Application): Flood Fill Single Step.
 * * Mental Model: "Targeted Color Matching"
 * - Capture original state before mutation.
 * - Mutate the center focal node.
 * - Use the Direction Cheat Sheet to scan neighbors, filtering exclusively
 * for nodes matching the exact original state.
 */
public class FloodFill1Matrix2D {

    // Our static coordinate offset cheat sheet for: [North, South, West, East]
    private static final int[][] DIRECTIONS = {
            {-1, 0}, // North
            {1, 0},  // South
            {0, -1}, // West
            {0, 1}   // East
    };

    public void floodFillSingleStep(int[][] matrix, int row, int col, int newColor) {
        // Safe Guard: Check null first, then dimensions
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        // 1. CAPTURE STATE: Identify the original color of the starting pixel
        int originalColor = matrix[row][col];

        // OPTIMIZATION GUARD: If it's already painted with the new color, exit to avoid redundant work
        if (originalColor == newColor) {
            return;
        }

        // 2. MUTATE CENTER: The starting cell itself must be painted the new color
        matrix[row][col] = newColor;

        int maxRows = matrix.length;
        int maxCols = matrix[0].length;

        // 3. EXPLORE NEIGHBORS: Loop through our direction cheat sheet
        for (int[] dir : DIRECTIONS) {
            int nextNeighborRow = row + dir[0];
            int nextNeighborCol = col + dir[1];

            // CENTRAL SAFETY GATE: Check boundaries first
            if (nextNeighborRow >= 0 && nextNeighborRow < maxRows &&
                    nextNeighborCol >= 0 && nextNeighborCol < maxCols) {

                // CRITICAL FIX: Only change color if neighbor matches the original pixel color
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
        FloodFill1Matrix2D engine = new FloodFill1Matrix2D();

        // 3x3 Test Image Grid
        // Target Center at (1,1) has value 1.
        // Neighbors: North(1), South(2), West(0), East(1)
        int[][] image = {
                {1, 1, 3},
                {0, 1, 1},
                {1, 2, 1}
        };

        System.out.println("--- Executing Flood Fill Single Step at (1,1) ---");
        System.out.println("Original target color was: 1. New color is: 5.");
        engine.floodFillSingleStep(image, 1, 1, 5);

        System.out.println("\nResulting Matrix Grid:");
        // Expected Output:
        // [1, 5, 3] -> North updated because it was 1. Top-Left (0,0) left alone because it's diagonal.
        // [0, 5, 5] -> Center updated to 5. East updated because it was 1. West left alone because it was 0.
        // [1, 2, 1] -> South left alone because it was 2 (didn't match original color 1).
        for (int[] row : image) {
            System.out.println(Arrays.toString(row));
        }
    }
}