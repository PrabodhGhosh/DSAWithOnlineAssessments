package com.prabodh.pattern;

public class LongestSubstringUniqueVariableSlidingWindow {

    public int findLongestSubstring(String s)
    {
        if(s == null || s.length() ==0)
            return 0;

        int left = 0;
        int maxLength = 0;
        // ASCII frequency tracker
        int[] charCounts = new int[128];

        for (int right = 0; right<s.length();right++)
        {
            char rightChar = s.charAt(right);
            charCounts[rightChar]++;

            // If we found a duplicate, shrink the window from the left
            // until the count of rightChar drops back to 1
            while(charCounts[rightChar]>1)
            {
                char leftChar = s.charAt(left);
                charCounts[leftChar]--;
                left++;
            }

            // Capture the maximum valid window size found so far
            int currentWindowLength = right - left + 1;
            maxLength = Math.max(maxLength,currentWindowLength);

        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(new LongestSubstringUniqueVariableSlidingWindow()
        .findLongestSubstring("abcddbacddfeg"));
    }
}
