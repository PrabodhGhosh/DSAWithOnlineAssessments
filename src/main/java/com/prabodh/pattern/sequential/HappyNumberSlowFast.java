package com.prabodh.pattern.sequential;

public class HappyNumberSlowFast {

    public boolean isHappy(int n)
    {
        int slow = n;
        int fast = getNext(n);

        while(fast!=1 && fast!=slow)
        {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        }

        return fast == 1;
    }


    private int getNext(int n)
    {
        int sum = 0;
        while (n>0)
        {
           int digit = n%10;
           sum +=digit*digit;
           n/=10;

        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new HappyNumberSlowFast().isHappy(18));
    }
}
