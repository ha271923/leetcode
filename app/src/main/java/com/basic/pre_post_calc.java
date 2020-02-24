package com.basic;

public class pre_post_calc {
    public static void main(String args[]){
        post_calc();
        pre_calc();
    }


    static public void post_calc(){
        System.out.println("=============== post =========");

        int a=1;
        System.out.println("0. a="+a);

        if(a-- > 0)
            System.out.println("1. a="+a); // print 0

        if(a++ > 0)
            System.out.println("2. a="+a);

        a--;
        if(a > 0)
            System.out.println("3. a="+a);

        a++;
        if(a > 0)
            System.out.println("4. a="+a);

        System.out.println("5. a="+a);
    }

    static public void pre_calc(){
        System.out.println("=============== pre =========");

        int a=1;
        System.out.println("0. a="+a);

        if(--a > 0)
            System.out.println("1. a="+a);

        if(++a > 0)
            System.out.println("2. a="+a);

        --a;
        if(a > 0)
            System.out.println("3. a="+a);

        ++a;
        if(a > 0)
            System.out.println("4. a="+a);

        System.out.println("5. a="+a);
    }
}
