package com.prabodh.pattern.nonlinear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagramsPattern {

    /**
     * OA Pattern: Identity Tracking via Hashing
     * Time Complexity: O(N * K log K) where N is number of words, K is max length of a word.
     * Space Complexity: O(N * K) to store the groups in the map.
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        // Base case guard clause
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        // We use a Map where the Key is the sorted "signature" and the Value is the matching group list
        Map<String, List<String>> anagramGroups = new HashMap<>();

        for (String word : strs) {
            // 1. Convert the string to a character array and sort it alphabetically
            char[] charArray = word.toCharArray();
            Arrays.sort(charArray);
            String sortedKey = new String(charArray); // e.g., "tea" becomes "aet"

            // 2. If this sorted signature hasn't been seen yet, seed a new empty list inside our map
            if (!anagramGroups.containsKey(sortedKey)) {
                anagramGroups.put(sortedKey, new ArrayList<>());
            }

            // 3. Retrieve the matching bucket list and add our original word to it
            anagramGroups.get(sortedKey).add(word);
        }

        // 4. Extract all the value lists from our map layout and return the final collection
        return new ArrayList<>(anagramGroups.values());
    }

    public static void main(String[] args) {
        GroupAnagramsPattern solver = new GroupAnagramsPattern();

        String[] testCase = {"eat", "tea", "tan", "ate", "nat", "bat"};

        System.out.println("--- Executing Pattern 9A: Identity Hashing ---");
        List<List<String>> groupedResult = solver.groupAnagrams(testCase);

        System.out.println("Input Sequence: " + Arrays.toString(testCase));
        System.out.println("Grouped Output: " + groupedResult);
    }
}