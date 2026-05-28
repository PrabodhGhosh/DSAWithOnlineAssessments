package com.prabodh.pattern;

public class LongestSubstringKDistinctVariableSlidingWindow {

    public int findLongestSubstringKDistinct(String s, int k)
    {
        if (s ==null || s.length()==0 || k<=0)
            return 0;

        int left = 0;
        int maxLength = 0;
        int distinctCount = 0;
        int[] charCounts = new int[128]; // ASCII frequency tracker

        for(int right = 0; right<s.length(); right++)
        {
            char rightChar = s.charAt(right);
            // If this is a brand new character entering the window, increment distinct count
            if(charCounts[rightChar]==0)
            {
                distinctCount++;
            }
            charCounts[rightChar]++;
            // Shrink the window from the left if we have breached 'k' distinct characters
            while (distinctCount > k) {
                char leftChar = s.charAt(left);
                charCounts[leftChar]--;

                // If a character's count drops to 0, it is completely out of the window
                if (charCounts[leftChar] == 0) {
                    distinctCount--;
                }
                left++;
            }

            // Capture the maximum valid window size
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
        }

    public static void main(String[] args) {
        System.out.println(new LongestSubstringKDistinctVariableSlidingWindow()
                .findLongestSubstringKDistinct("eceab",2));
    }
    }



