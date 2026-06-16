package com.prabodh.pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Pure 2D Spatial Layout Pattern: Shrinking Security Perimeter.
 * * Mental Model: "The Shrinking Fence"
 * - Establish 4 hard spatial boundaries framing the exploration arena.
 * - Execute a clockwise sweep along the perimeter walls.
 * - Contract each fence line immediately inward as soon as its track is consumed.
 * - Protect trailing loops with cross-checks to ensure closing walls haven't crossed.
 * * * Time Complexity: O(M * N) - Every single element inside the matrix grid is visited exactly once.
 * * Space Complexity: O(1) Amortized - Modifying data in-place; memory footprint is bound exclusively
 * to the mandatory list collection allocated for the return track.
 */
public class Matrix2DSpiral {

    public List<Integer> spiralOrder(int[][] matrix) {
        // HARDENED GUARD: Check null first to prevent NPE before accessing dimensions,
        // then verify if either the row or column dimension contains zero width.
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<>();
        }

        // Initialize the four tracking walls that bound the active exploration viewport
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        List<Integer> result = new ArrayList<>();

        // Loop continuously as long as the perimeter box has a valid, non-zero area
        while (left <= right && top <= bottom) {

            // SWEEP 1: Left to Right along the active 'top' horizontal ceiling line
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            top++; // Consume the ceiling row: Shift the top boundary fence down by 1 unit

            // SWEEP 2: Top to Bottom down along the active 'right' vertical wall line
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--; // Consume the rightmost column: Shift the right boundary fence inward left by 1 unit

            // SWEEP 3: Right to Left along the active 'bottom' horizontal floor line
            // CRITICAL INVARIANT GUARD: Check if the ceiling has crossed past the floor line
            // during previous increments to prevent double-processing rows on asymmetric grids.
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--; // Consume the basement row: Shift the bottom boundary fence up by 1 unit
            }

            // SWEEP 4: Bottom to Top up along the active 'left' vertical wall line
            // CRITICAL INVARIANT GUARD: Check if the left wall has crossed past the right wall line
            // to prevent double-processing columns on asymmetric grids.
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++; // Consume the leftmost column: Shift the left boundary fence inward right by 1 unit
            }
        }

        return result;
    }

    /**
     * Diagnostic Execution Entry Point
     */
    public static void main(String[] args) {
        Matrix2DSpiral engine = new Matrix2DSpiral();

        // Test Scenario 1: Symmetric Square Grid (3x3 Matrix)
        int[][] squareMatrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.println("--- Scenario 1: Symmetric 3x3 Grid ---");
        List<Integer> squareResult = engine.spiralOrder(squareMatrix);
        System.out.println("Expected: [1, 2, 3, 6, 9, 8, 7, 4, 5]");
        System.out.println("Actual:   " + squareResult);
        System.out.println();

        // Test Scenario 2: Asymmetric Rectangular Grid (2x3 Matrix - Severe Boundary Cross Test)
        int[][] rectangularMatrix = {
                {1, 2, 3},
                {4, 5, 6}
        };
        System.out.println("--- Scenario 2: Asymmetric 2x3 Grid ---");
        List<Integer> rectResult = engine.spiralOrder(rectangularMatrix);
        System.out.println("Expected: [1, 2, 3, 6, 5, 4]");
        System.out.println("Actual:   " + rectResult);
        System.out.println();

        // Test Scenario 3: Structural Corner Case (Null Reference Guard Verification)
        System.out.println("--- Scenario 3: Null Safety Check ---");
        List<Integer> nullResult = engine.spiralOrder(null);
        System.out.println("Expected Empty List Size: 0");
        System.out.println("Actual List Size:         " + nullResult.size());
    }
}