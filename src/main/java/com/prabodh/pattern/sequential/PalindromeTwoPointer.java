package com.prabodh.pattern.sequential;

public class PalindromeTwoPointer {

    public boolean isPalindrome(String input)
    {
        // 1. Convert to lowercase to make checking case-insensitive
        String cleanInput = input.toLowerCase();
        //Use regex to get rid of non-alphanumeric chars
        cleanInput=cleanInput.replaceAll("[^a-zA-Z0-9]","");
        char[] cleanInputArr = cleanInput.toCharArray();
        int begin = 0;
        int end = cleanInputArr.length-1;
        while(begin<end)
        {
            if(cleanInputArr[begin]!=cleanInputArr[end])
                return false;
            begin++;
            end--;
        }
        return true;
    }

    public boolean isPalindromeOptimized(String s) {
        int begin = 0;
        int end = s.length() - 1;

        while (begin < end) {
            char left = s.charAt(begin);
            char right = s.charAt(end);

            // Skip non-alphanumeric from the left
            if (!Character.isLetterOrDigit(left)) {
                begin++;
            }
            // Skip non-alphanumeric from the right
            else if (!Character.isLetterOrDigit(right)) {
                end--;
            }
            // Both are alphanumeric, so compare
            else {
                if (Character.toLowerCase(left) != Character.toLowerCase(right)) {
                    return false;
                }
                begin++;
                end--;
            }
        }
        return true;
    }
}
