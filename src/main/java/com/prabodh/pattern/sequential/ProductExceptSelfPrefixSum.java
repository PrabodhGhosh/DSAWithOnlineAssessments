package com.prabodh.pattern.sequential;

/**
 * Prefix/Suffix Accumulator Variation: Product of Array Except Self.
 * Time Complexity: O(N) - Two sequential linear passes.
 * Space Complexity: O(1) - Modifies the mandatory output array in-place.
 */

public class ProductExceptSelfPrefixSum {

    public int[] findProductExceptSelf(int[] arr)
    {
        int[] answer = new int[arr.length];
        int n = arr.length;
        if(arr.length==0 || arr==null)
        {
            return new int[0];
        }

        // =========================================================================
        // PASS 1: CUMULATIVE LEFT (PREFIX) PRODUCTS
        // =========================================================================
        // Base state: index 0 has absolutely no elements to its left, so its prefix is 1.

        answer[0]=1;
        int leftProduct = 1;

        for (int i=1;i<n;i++)
        {
            // The left product up to index i is the product of elements up to i-1
            leftProduct*=arr[i-1];
            answer[i] = leftProduct;
        }

        // =========================================================================
        // PASS 2: CUMULATIVE RIGHT (SUFFIX) PRODUCTS
        // =========================================================================
        // Step backward from right to left, multiplying the existing left products
        // by the accumulating right products.

        int rightProduct = 1;
        for (int i = n-1; i>=0;i--)
        {
            // Multiply the stored prefix product by the running suffix product
            answer[i] *= rightProduct;
            // Incorporate the current element into our running suffix total for the next leftward step
            rightProduct *= arr[i];
        }

        return answer;
    }

    public static void main(String[] args) {
        ProductExceptSelfPrefixSum engine = new ProductExceptSelfPrefixSum();
        int[] result = engine.findProductExceptSelf(new int[]{1, 2, 3, 4});

        System.out.print("Output Ledger: [");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + (i < result.length - 1 ? ", " : ""));
        }
        System.out.println("]"); // Prints: [24, 12, 8, 6]
    }
    }


