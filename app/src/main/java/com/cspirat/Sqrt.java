package com.cspirat;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : Sqrt
 * Creator : Edward
 * Date : Oct, 2017
 * Description : 69. Sqrt(x)
 */
public class Sqrt {
    // 367	Valid Perfect Square

    /**
     * time : O(logn) space : O(1)
     * @param x
     * @return
     */
    public static void main(String[] args) {
        System.out.println(mySqrt(4)); // Ans: 2
        System.out.println(mySqrt(8)); // Ans: 2
        System.out.println(mySqrt(9)); // Ans: 3
    }
/*
      x   = y*y                               , Integer(y)=Ans
 sqrt(1)  =	1                                 , Integer(y)= 1
 sqrt(2)  =	1.414213                          , Integer(y)= 1
 sqrt(3)  =	1.732050                          , Integer(y)= 1
 sqrt(4)  =	2        <= 4=2*2 代表將4開根號後   , Integer(y)= 2
 sqrt(5)  =	2.236067                          , Integer(y)= 2
 sqrt(6)  =	2.449489                          , Integer(y)= 2
 sqrt(7)  =	2.645751                          , Integer(y)= 2
 sqrt(8)  =	2.828427                          , Integer(y)= 2
 sqrt(9)  =	3        <= 9=3*3 代表將9開根號後   , Integer(y)= 3
 sqrt(10) =	3.162277                          , Integer(y)= 3
 sqrt(11) =	3.316624                          , Integer(y)= 3
 sqrt(12) =	3.464101                          , Integer(y)= 3

 Q: 題目提到non-negative integer, 所以不要負數與浮點數的答案, 只要正整數的答案, 所以
     sqrt(8)  =	2.828427 => 2 (正整數)
 */
    // Q: 數學開根號演算法(整數=非精確)的實作
    // Tips: 如何搜尋? 2分逼近法
    static public int mySqrt(int x) { // x=y*y=> x開根號(sqrt)後==y <= Ans: y
        if (x <= 0)
            return 0;
        int L = 1;
        int R = x; // 1*1=1, 2*2=4, y*y=x, 所以x是欲求值的上限

        /*
          if x=9
                                          x
               0  1  2  3  4  5  6  7  8  9  10
                  L           m           R
                  L  m     R
                        Lm R

         */
        while (L <= R) { // LOOP:
            long mid = (R - L) / 2 + L;
            System.out.println("  L="+L +"  mid="+mid+"  R="+R);

            if (mid * mid == x) // y*y=x , 找到了完全正解!
                return (int) mid;
            else if (mid * mid < x) // 太小, +1逼近, 並設為左邊界
                L  = (int) mid + 1; // 因為此題要求整數, 故以"整數"+1為步進單位,而非小數
            else                    // 太大, -1逼近, 並設為右邊界
                R = (int) mid - 1;  // 因為此題要求整數, 故以"整數"-1為步進單位,而非小數

        }
        // 找近似解
        System.out.println("  L="+L +"         R="+R);
        if (R * R < x) // 逼近出的近似解, 不得超過 x
            return (int) R;
        else
            return (int) L;
    }

    // Newton Method time : 不知道 space : O(1);
    public int mySqrt2(int x) {
        long res = x;
        while (res * res > x) {
            res = (res + x / res) / 2;
        }
        return (int) res;
    }
}
