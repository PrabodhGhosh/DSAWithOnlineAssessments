package com.prabodh.pattern.sequential;

import java.util.Arrays;

/**
 * 2D Spatial Layout Pattern 4: Layered Shell Navigation (The Onion Strategy).
 * * Mental Model: "Boundary-Restricted Swapping"
 * - Tracks localized outer tracks using four boundary fences: top, bottom, left, right.
 * - Rotates an isolated ring in-place by cycling 4 corresponding corners in a loop.
 * * * Complexity:
 * - Time Complexity: O(N) linear to the perimeter track length.
 * - Space Complexity: O(1) Absolute In-Place.
 */
public class LayeredRingRotator2D {

    /**
     * Rotates ONLY the outermost ring border of a square matrix 90 degrees clockwise.
     * Leaves all inner elements completely untouched.
     */
    public void rotateOuterRingOnly(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        int n = matrix.length;

        // Boundaries for the absolute outermost layer
        int top = 0;
        int bottom = n - 1;
        int left = 0;
        int right = n - 1;

        // 'i' tracks our offset displacement step down the current ring boundary row/col
        for (int i = 0; i < (right - left); i++) {

            // Step 1: Save the Top-Left element value to a temporary holder
            int temp = matrix[top][left + i];

            // Step 2: Move Bottom-Left cell up to Top-Left
            matrix[top][left + i] = matrix[bottom - i][left];

            // Step 3: Move Bottom-Right cell over to Bottom-Left
            matrix[bottom - i][left] = matrix[bottom][right - i];

            // Step 4: Move Top-Right cell down to Bottom-Right
            matrix[bottom][right - i] = matrix[top + i][right];

            // Step 5: Restore the saved temporary value into Top-Right
            matrix[top + i][right] = temp;
        }
    }

    public static void main(String[] args) {
        LayeredRingRotator2D rotator = new LayeredRingRotator2D();

        // Standard 3x3 Grid
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        System.out.println("--- Original Grid Layout ---");
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }

        rotator.rotateOuterRingOnly(matrix);

        System.out.println("\n--- After Outermost Ring-Only Rotation ---");
        // Expected Output:
        // [7, 4, 1]
        // [8, 5, 2] <-- Notice the center number '5' remains completely untouched!
        // [9, 6, 3]
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}