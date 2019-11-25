package com.cspirat;

/**
 * Created by Edward on 27/07/2017.
 */
public class Pow {
    /**
     * 50. Pow(b, e)
     * Implement pow(b, e).


     eg. 2^2 = 2^1 * 2^1 = (2^0 * 2^0 * 2) * (2^0 * 2^0 * 2) = (1 * 1 * 2) * (1 * 1 * 2) = 4

     eg. 2^3 = 2^1 * 2^1 * 2 = (2^0 * 2^0 * 2) * (2^0 * 2^0 * 2) * 2 = (1 * 1 * 2) * (1 * 1 * 2) * 2 = 8

     time : O(logn);
     space : O(n)/O(1)

     * @param b
     * @param e
     * @return
     */
    // Power Algorithm:
    //    if(b>0)  = base^(exp) = ret
    //    if(b==0) = ret = 1
    //    if(b<0)  = 1.0/(base^(exp)) = ret(一定比1小)
    // Tips: 2^(-1) = 0.5 , 2^(-2) = 0.25
    // KEY: time complexity如何壓到logN
    public static void main(String[] args) {
        double base = 2.0;
        int    exp  = 10; // Ans: 1024
        // double base = 2.1;
        // int    exp  = 3;  // Ans: 9.261
        // double base = 2.0;
        // int    exp  = -2; // Ans: 0.25


        double ret = 0;
        ret = pow_recursive(base, exp);
        // ret = pow_loop(base, exp);
        // ret = pow_hawk(base, exp); // too slow
        System.out.println("ret="+ret);
    }

    /*
         %2 = 0   1   0   1   0   1   0   1   0   1    0
         exp= 0   1   2   3   4   5   6   7   8   9   10
        2^10= 1 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2  * 2
         Ans= 1   2   4   8  16  32  64 128 256 512 1024
              |   |   |           |                    |
              1   |   |           |                    |  => recur5= 1
            1*1*2=2   |           |                    |  => recur4= r*r*b
                  2*2=4           |                    |  => recur3= r*r
                           4*4*2=32                    |  => recur2= r*r*b
                                              32*32=1024  => recur1= r*r

     */
    // KEY: 利用2分法與遞迴, 將次方的運算, 降低time complexity至 logN,
    //     ex: 2^10 => log10,base2 => 3.3219280949 => 4次遞迴即可算出
    // Tip: 32*32=1024,  (b^e)*(b^e)=b^2e
    public static double pow_recursive(double b, int e) {
        if (e > 0) {
            return pow_math(b, e);
        } else {
            return  1.0 / pow_math(b, e); // 任何數的0次方 == 1, 負數次方的值一定比1小, ex: 2^-2 = 1/(2*2) = 0.25
        }
    }
    public static double pow_math(double b, int e) {
        if (e == 0) {
            // System.out.println( "1.0");
            return 1;
        }
        double r = pow_math(b, e / 2);
        if (e % 2 == 0) {
            // System.out.println( r + " * " + r + " = " + r*r);
            return r * r;
        } else {
            // System.out.println( r + " * " + r + " * " + b + " = " + r*r*b);
            return r * r * b;
        }
    }

    // -------------------------------------------------------------

    public static double pow_loop(double b, int e) {
        if (e == 0) return 1;
        double res = 1;
        // int : -6.. ~ +6..  -2^32 ~ 2 ^32-1 Integer.MIN_VALUE
        long abs = Math.abs((long)e);
        while (abs > 0) {
            if (abs % 2 != 0) {
                res *= b;
            }
            b *= b;
            abs /= 2;
        }
        if (e < 0) {
            return 1.0 / res;
        }
        return res;
    }

    // -------------------------------------------------------------
    // Hawk: time complexity is too slow.
    public static double pow_hawk(double b, int e) {
        double ret = 1.0;

        if(e == 0) {
            ret = 1;
        } else if(e == 1) {
            ret = b;
        } else if(e>1) {
            while (e > 0) {
                ret = ret * b;
                e--;
            }
        } else if (e<1) {
            e = e*-1;
            while (e > 0) {
                ret = ret * b;
                e--;
            }
            ret = 1/ret;
        }
        return ret;
    }
}
