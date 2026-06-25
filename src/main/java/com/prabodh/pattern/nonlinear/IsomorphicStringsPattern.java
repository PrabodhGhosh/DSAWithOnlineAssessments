package com.prabodh.pattern.nonlinear;

import java.util.HashMap;
import java.util.Map;

public class IsomorphicStringsPattern {

    /**
     * OA Pattern: Dual-Mapping Identity Tracking
     * Time Complexity: O(N) where N is the length of the string.
     * Space Complexity: O(N) or O(1) amortized since character sets are bounded (e.g., ASCII).
     */
    public boolean isIsomorphic(String s, String t) {
        // Base structure check: if sizes don't match, they cannot be structurally identical
        if (s.length() != t.length()) {
            return false;
        }

        // Establish the dual synchronization channels
        Map<Character, Character> mapST = new HashMap<>();
        Map<Character, Character> mapTS = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);

            // Cross-Check 1: Does s already map to a different character in t?
            if (mapST.containsKey(charS)) {
                if (mapST.get(charS) != charT) {
                    return false; // Structural mismatch found
                }
            }

            // Cross-Check 2: Has t already been claimed by a different character in s?
            if (mapTS.containsKey(charT)) {
                if (mapTS.get(charT) != charS) {
                    return false; // Structural collision found
                }
            }

            // If clean, establish the reciprocal 1-to-1 bond
            mapST.put(charS, charT);
            mapTS.put(charT, charS);
        }

        return true; // The entire sequence holds up to structural tracking invariants
    }

    public static void main(String[] args) {
        IsomorphicStringsPattern solver = new IsomorphicStringsPattern();

        System.out.println("--- Executing Pattern 9B: Dual-Mapping Synchronization ---");

        // Test Case 1: Valid structure
        String s1 = "egg", t1 = "add";
        System.out.println("Is '" + s1 + "' and '" + t1 + "' isomorphic? (Expected: true):  " + solver.isIsomorphic(s1, t1));

        // Test Case 2: Broken mapping collision target
        String s2 = "foo", t2 = "bar";
        System.out.println("Is '" + s2 + "' and '" + t2 + "' isomorphic? (Expected: false): " + solver.isIsomorphic(s2, t2));

        // Test Case 3: The Cross-Claim Trap
        String s3 = "badc", t3 = "baba";
        System.out.println("Is '" + s3 + "' and '" + t3 + "' isomorphic? (Expected: false): " + solver.isIsomorphic(s3, t3));
    }
}