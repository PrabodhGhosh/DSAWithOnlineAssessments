package com.prabodh.pattern.sequential;

import java.util.Arrays;

/**
 * 2D Spatial Layout Pattern 3: Grid Transformations (In-Place Transposition).
 * * Mental Model: "The Geometric Building Blocks Engine"
 * - Decouples atomic matrix flips (Transpose, Horizontal Reverse, Vertical Reverse).
 * - Combines these primitives systematically to achieve any rotational degree constraint.
 * - Forces strict O(1) space performance by mutating allocations in-place.
 */
public class MatrixTransformer2D {

    // ==========================================
    // 🧱 ATOMIC PRIMITIVE TRANSFORMS
    // ==========================================

    /**
     * Transposes a square matrix in-place (Flips elements across the main diagonal).
     * Rule: matrix[r][c] <-> matrix[c][r]
     */
    public void transpose(int[][] matrix) {
        // Hardened Guard
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        int n = matrix.length;

        // Fail-safe protection
        if (n != matrix[0].length) {
            throw new IllegalArgumentException("In-place transposition requires a perfectly square NxN matrix.");
        }

        for (int r = 0; r < n; r++) {
            // CRITICAL INVARIANT: Start 'c' at 'r' to process only the upper triangle.
            // This prevents the loop from crossing the line and double-swapping elements back!
            for (int c = r; c < n; c++) {
                int temp = matrix[r][c];
                matrix[r][c] = matrix[c][r];
                matrix[c][r] = temp;
            }
        }
    }

    /**
     * Horizontal Reflection: Reverses each individual row from left to right.
     */
    public void reverseRows(int[][] matrix) {
        int n = matrix.length;
        for (int r = 0; r < n; r++) {
            int left = 0;
            int right = n - 1;
            while (left < right) {
                int temp = matrix[r][left];
                matrix[r][left] = matrix[r][right];
                matrix[r][right] = temp;
                left++;
                right--;
            }
        }
    }

    /**
     * Vertical Reflection: Reverses each individual column from top to bottom.
     */
    public void reverseColumns(int[][] matrix) {
        int n = matrix[0].length;
        for (int c = 0; c < n; c++) {
            int top = 0;
            int bottom = n - 1;
            while (top < bottom) {
                int temp = matrix[top][c];
                matrix[top][c] = matrix[bottom][c];
                matrix[bottom][c] = temp;
                top++;
                bottom--;
            }
        }
    }

    // ==========================================
    // 🔄 DEGREE ROTATION VARIANTS
    // ==========================================

    /**
     * Rotates the matrix 90 Degrees Clockwise.
     * Strategy: Transpose -> Reverse Each Row
     */
    public void rotate90Clockwise(int[][] matrix) {
        transpose(matrix);
        reverseRows(matrix);
    }

    /**
     * Rotates the matrix 90 Degrees Counter-Clockwise (270 Degrees Clockwise).
     * Strategy: Transpose -> Reverse Each Column
     */
    public void rotate90CounterClockwise(int[][] matrix) {
        transpose(matrix);
        reverseColumns(matrix);
    }

    /**
     * Rotates the matrix 180 Degrees.
     * Strategy: Reverse Rows (Horizontal Flip) -> Reverse Columns (Vertical Flip)
     * Alternate approach: You could also execute rotate90Clockwise twice.
     */
    public void rotate180ClokwiseOrCounterClockwise(int[][] matrix) {
        reverseRows(matrix);
        reverseColumns(matrix);
    }

    /**
     * Rotates the matrix 270 Degrees Clockwise.
     * Geometrically identical to a 90-Degree Counter-Clockwise rotation.
     * Strategy: Transpose -> Reverse Each Column
     */
    public void rotate270Clockwise(int[][] matrix) {
        rotate90CounterClockwise(matrix);
    }

    /**
     * Rotates the matrix 270 Degrees Counter-Clockwise.
     * Geometrically identical to a 90-Degree Clockwise rotation.
     * Strategy: Transpose -> Reverse Each Row
     */
    public void rotate270CounterClockwise(int[][] matrix) {
        rotate90Clockwise(matrix);
    }

    /**
     * Rotates the matrix 360 Degrees.
     * Strategy: An identity operation. The matrix remains completely unchanged.
     */
    public void rotate360(int[][] matrix) {
        // Geometrically, 360 degrees loops back onto itself. No operations needed.
    }

    // ==========================================
    // 🧪 DIAGNOSTIC VERIFICATION TEST SUITE
    // ==========================================

    public static void main(String[] args) {
        MatrixTransformer2D transformer = new MatrixTransformer2D();

        // Template blueprint grid used to reset state between test cases
        int[][] seedGrid = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        System.out.println("=========================================");
        System.out.println("🎯 ORIGINAL BLUEPRINT MATRIX");
        System.out.println("=========================================");
        printMatrix(seedGrid);

        // ------------------------------------------------------
        // Test Case 1: Pure Transpose
        // ------------------------------------------------------
        int[][] testGrid = cloneMatrix(seedGrid);
        transformer.transpose(testGrid);
        System.out.println("\n--- 1. PURE TRANSPOSE (Rows become Columns) ---");
        // Expected: [1,4,7], [2,5,8], [3,6,9]
        printMatrix(testGrid);

        // ------------------------------------------------------
        // Test Case 2: 90 Degrees Clockwise
        // ------------------------------------------------------
        testGrid = cloneMatrix(seedGrid);
        transformer.rotate90Clockwise(testGrid);
        System.out.println("\n--- 2. ROTATE 90° CLOCKWISE ---");
        // Expected: [7,4,1], [8,5,2], [9,6,3]
        printMatrix(testGrid);

        // ------------------------------------------------------
        // Test Case 3: 90 Degrees Counter-Clockwise (270° Clockwise)
        // ------------------------------------------------------
        testGrid = cloneMatrix(seedGrid);
        transformer.rotate90CounterClockwise(testGrid);
        System.out.println("\n--- 3. ROTATE 90° COUNTER-CLOCKWISE (or 270° Clockwise) ---");
        // Expected: [3,6,9], [2,5,8], [1,4,7]
        printMatrix(testGrid);

        // ------------------------------------------------------
        // Test Case 4: 180 Degrees
        // ------------------------------------------------------
        testGrid = cloneMatrix(seedGrid);
        transformer.rotate180ClokwiseOrCounterClockwise(testGrid);
        System.out.println("\n--- 4. ROTATE 180° (Upside-Down & Inverted) ---");
        // Expected: [9,8,7], [6,5,4], [3,2,1]
        printMatrix(testGrid);

        // ------------------------------------------------------
        // Test Case 5: 360 Degrees
        // ------------------------------------------------------
        testGrid = cloneMatrix(seedGrid);
        transformer.rotate360(testGrid);
        System.out.println("\n--- 5. ROTATE 360° (Identity State) ---");
        // Expected: Matches original blueprint [1,2,3], [4,5,6], [7,8,9]
        printMatrix(testGrid);

        // ------------------------------------------------------
        // Test Case 6: 270 Degrees Clockwise
        // ------------------------------------------------------
        testGrid = cloneMatrix(seedGrid);
        transformer.rotate270Clockwise(testGrid);
        System.out.println("\n--- 6. ROTATE 270° CLOCKWISE (Identical to 90° Counter-Clockwise) ---");
        // Expected: [3,6,9], [2,5,8], [1,4,7]
        printMatrix(testGrid);
    }



    // ==========================================
    // 🛠️ UTILITY LAB METHODS
    // ==========================================

    /**
     * Iterates and cleanly prints the matrix configuration to console layout rows
     */
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    /**
     * Deep-clones the source array to prevent side-effect pollution between tests
     */
    private static int[][] cloneMatrix(int[][] source) {
        int[][] clone = new int[source.length][source[0].length];
        for (int r = 0; r < source.length; r++) {
            clone[r] = source[r].clone();
        }
        return clone;
    }
}