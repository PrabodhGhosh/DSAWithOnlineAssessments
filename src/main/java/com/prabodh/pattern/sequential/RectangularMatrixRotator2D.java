package com.prabodh.pattern.sequential;

import java.util.Arrays;

/**
 * 2D Spatial Layout Pattern 3 (Rectangular Variation): Out-of-Place Rotation.
 * * Mental Model: "Dimensional Inversion Mapping"
 * - Handles grids where row count != column count (M != N).
 * - Allocates a new target matrix with inverted bounds: new int[cols][rows].
 * * * Complexity:
 * - Time Complexity: O(M * N) - Every cell is visited exactly once.
 * - Space Complexity: O(M * N) - Required to house the new shape configuration.
 */
public class RectangularMatrixRotator2D {

    /**
     * Rotates a rectangular matrix 90 degrees clockwise into a new array container.
     */
    public int[][] rotate90Rectangular(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0][0];
        }

        int maxRows = matrix.length;
        int maxCols = matrix[0].length;

        // Allocate inverted matrix dimensions (Rows become Columns, Columns become Rows)
        int[][] rotated = new int[maxCols][maxRows];

        for (int r = 0; r < maxRows; r++) {
            for (int c = 0; c < maxCols; c++) {
                // The Row position in the new matrix matches the old Column position.
                // The Column position in the new matrix maps backward from the old right margin.
                rotated[c][maxRows - 1 - r] = matrix[r][c];
            }
        }

        return rotated;
    }

    public static void main(String[] args) {
        RectangularMatrixRotator2D rotator = new RectangularMatrixRotator2D();

        // 2 Rows x 3 Columns Matrix
        int[][] rectangularMatrix = {
                {1, 2, 3},
                {4, 5, 6}
        };

        System.out.println("--- Original Rectangular Matrix (2x3) ---");
        for (int[] row : rectangularMatrix) {
            System.out.println(Arrays.toString(row));
        }

        int[][] result = rotator.rotate90Rectangular(rectangularMatrix);

        System.out.println("\n--- Rotated Rectangular Matrix (3x2) ---");
        // Expected Output:
        // [4, 1]
        // [5, 2]
        // [6, 3]
        for (int[] row : result) {
            System.out.println(Arrays.toString(row));
        }
    }
}