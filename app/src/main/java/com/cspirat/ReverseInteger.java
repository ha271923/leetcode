package com.cspirat;

import com.utils.Out;

/**
 * Created by Edward on 25/07/2017.
 */
public class ReverseInteger {
    public static void main(String[] args)
    {
        int res = reverse_hawk(1534236469);
        Out.i("res="+res);
    }
    /**
     * 7. Reverse Integer
     * Reverse digits of an integer.

     Example1: x = 123, return 321
     Example2: x = -123, return -321

     int :
     -2147483648 ~ 2147483647

     corner case : 越界
     time : O(n);
     space : O(1);
     * @param x
     * @return
     */

    public static int reverse(int x) {

        long res = 0; // Tips: int to long 避免計算期間 overflow
        while (x != 0) {
            res = res * 10 + x % 10; // KEY: res 的前置計算
            x /= 10;
            if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) return 0;
        }
        return (int)res;
    }

    // ERROR: input=1534236469 , output=-1733610241
    public static int reverse_hawk(int x) {
        int res = 0;
        float f; // ERR: 千萬不要轉float
        int sign=1;
        if(x == 0)
            return res;

        if(x < 0) {
            sign = -1;
            x = x * -1;
        }

        f = x;

        while(f >=1 ) {
            res = res * 10;
            res = res + (int) f % 10; // BUG: float的餘數計算, 1534236469 => 1.53423642E9 => %10 => 6 應該要是9才對!
            f = f /10;
        }

        res = res * sign;
        return res;
    }
}
