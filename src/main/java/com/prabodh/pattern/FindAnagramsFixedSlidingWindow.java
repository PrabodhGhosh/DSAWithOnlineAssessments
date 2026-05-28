package com.prabodh.pattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAnagramsFixedSlidingWindow {

    public List<Integer> findAnagrams(String s, String p)
    {
        List<Integer> indices = new ArrayList<>();
        if(s.length()<p.length() || s == null || p == null)
            return indices;
        int k = p.length();
        int[] pCounts = new int[26];
        int[] windowCounts = new int[26];

        // Phase 1: Populate target frequencies and the first window

        for(int i=0;i<k;i++)
        {
            pCounts[p.charAt(i)-'a']++;
            windowCounts[s.charAt(i)-'a']++;
        }
        // Check if the very first window matches
        if(Arrays.equals(pCounts,windowCounts))
            indices.add(0);

        // Phase 2: Slide the window of fixed size K across string s

        for(int i=k;i<s.length();i++)
        {
            // Add the character entering the window (Right edge)
            windowCounts[s.charAt(i)- 'a']++;
            // Remove the character leaving the window (Left edge)
            windowCounts[s.charAt(i-k)- 'a']--;
            // If the frequencies match perfectly, we found an anagram start index
            if(Arrays.equals(windowCounts, pCounts))
            {
                indices.add(i-k+1);
            }
        }
        return indices;
    }

    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        System.out.println(new FindAnagramsFixedSlidingWindow().findAnagrams(s,p));
    }
}
