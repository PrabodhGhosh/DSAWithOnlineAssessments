package com.prabodh.pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 2D Spatial Layout Pattern 2: Constant Direction Arrays (The Grid Crawler).
 * * * Mental Model: "The Direction Cheat Sheet"
 * - Decouples coordinate directional offsets from exploration logic.
 * - Replaces messy, individual nested if-statements with a static modifier map.
 * - Leverages a unified loop paired with a single, centralized boundary gate.
 * * * Complexity:
 * - Time Complexity: O(D) where D is the number of fixed directions (here, constant O(4)).
 * - Space Complexity: O(1) auxiliary memory footprint, as storage maps exclusively to the results list.
 */
public class Matrix2DCrawler {

    // Centralized structural coordinate offsets for: [North, South, West, East]
    private static final int[][] DIRECTION = {
            {-1,  0}, // NORTH: Decrements row index by 1; column stays static
            { 1,  0}, // SOUTH: Increments row index by 1; column stays static
            { 0, -1}, // WEST:  Row stays static; decrements column index by 1
            { 0,  1}  // EAST:  Row stays static; increments column index by 1
    };

    /**
     * Safely collects values of valid immediate cardinal neighbors touching a target cell.
     * * @param matrix The 2D grid workspace containing target elements
     * @param row    The source row index position
     * @param col    The source column index position
     * @return A list containing valid neighbor element values inside matrix bounds
     */
    public List<Integer> getCardinalNeighbors(int[][] matrix, int row, int col) {
        List<Integer> neighbors = new ArrayList<>();

        // HARDENED GUARD: Protects against uninitialized references and zero-dimension capacities
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return neighbors;
        }

        int maxRows = matrix.length;
        int maxCols = matrix[0].length;

        // Iterate sequentially through the structural offset directives
        for (int[] dir : DIRECTION) {
            // Compute tentative adjacent neighbor coordinates by applying modifiers
            int neighborRow = row + dir[0];
            int neighborCol = col + dir[1];

            // THE CENTRAL SAFETY GATE:
            // Intercept and drop out-of-bounds indices before they can trigger an exception
            if (neighborRow >= 0 && neighborRow < maxRows && neighborCol >= 0 && neighborCol < maxCols) {
                neighbors.add(matrix[neighborRow][neighborCol]);
            }
        }

        return neighbors;
    }

    /**
     * Diagnostic Verification Entry Point
     */
    public static void main(String[] args) {
        Matrix2DCrawler crawler = new Matrix2DCrawler();

        // Establish a standard 3x3 testing grid
        int[][] grid = {
                {10, 20, 30},
                {40, 50, 60},
                {70, 80, 90}
        };

        // Test Scenario: Target the extreme top-left corner cell (Value: 10, Coordinate: 0, 0)
        // North (-1, 0) and West (0, -1) coordinates will evaluate outside the array bounds.
        // The central safety gate should filter them out silently without throwing an exception.
        System.out.println("--- Testing Corner Cell (0,0) ---");
        List<Integer> cornerNeighbors = crawler.getCardinalNeighbors(grid, 0, 0);

        // Expected output: [40, 20] (South and East values only)
        System.out.println("Expected Neighbors: [40, 20]");
        System.out.println("Actual Neighbors:   " + cornerNeighbors);
    }
}