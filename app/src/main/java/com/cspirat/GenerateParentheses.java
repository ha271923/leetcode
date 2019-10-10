package com.cspirat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edward on 27/07/2017.
 */
public class GenerateParentheses {
    /**
     * 22. Generate Parentheses
     * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

     For example, given n = 3, a solution set is:
     [
     "((()))",
     "(()())",
     "(())()",
     "()(())",
     "()()()"
     ]


     time : O(n!) (2^n)
     space : O(n)

     卡特兰数：
     (0,n-1) (1,n-2) (2,n-3) ... (n-1,0)

     * @param n
     * @return
     */
/*
A. 無剪枝條件下, 組合為 2^(n*2), ex: n=3, 組合=2^(3*2)=64
    void helper(String strCur, int L, int R){
            if (L > 0) {
                helper(res, s + "(", L - 1, R);
            }
            if (R > 0) {
                helper(res, s + ")", L, R - 1);
            }
    }

B. 剪枝:
     簡化: L='('=0 ,  R=')'=1 ]
     組合條件: 0=( , 1=) 為何不是 2^6個符號(階) = 64種, 而是20種, 因為1跟0各只能且必須出現三次

剪枝條件1: 當n=3時,挑出L==0&&R==0, 也就是1跟0均滿足出現三次
T [00]=000000
T [01]=000001
T [02]=000010
T [03]=000011
T [04]=000100
T [05]=000101
T [06]=000110
T [07]=000111 = [00]
T [08]=001000
T [09]=001001
T [10]=001010
T [11]=001011 = [01]
T [12]=001100
T [13]=001101 = [02]
T [14]=001110 = [03]
T [15]=001111
T [16]=010000
T [17]=010001
T [18]=010010
T [19]=010011 = [04]
T [20]=010100
T [21]=010101 = [05]
T [22]=010110 = [06]
T [23]=010111
T [24]=011000
T [25]=011001 = [07]
T [26]=011010 = [08]
T [27]=011011
T [28]=011100 = [09]
T [29]=011101
T [30]=011110
T [31]=011111
T [32]=100000
T [33]=100001
T [34]=100010
T [35]=100011 = [10]
T [36]=100100
T [37]=100101 = [11]
T [38]=100110 = [12]
T [39]=100111
T [40]=101000
T [41]=101001 = [13]
T [42]=101010 = [14]
T [43]=101011
T [44]=101100 = [15]
T [45]=101101
T [46]=101110
T [47]=101111
T [48]=110000
T [49]=110001 = [16]
T [50]=110010 = [17]
T [51]=110011
T [52]=110100 = [18]
T [53]=110101
T [54]=110110
T [55]=110111
T [56]=111000 = [19]
T [57]=111001
T [58]=111010
T [59]=111011
T [60]=111100
T [61]=111101
T [62]=111110
T [63]=111111

     剪枝條件2: "))("這種'不符合'的pattern有一個特色, 也就是L>R,需剪枝(排除)
                                          剩下L<R
                [00]= "000111" = "000111" = A[0] = "((()))"
                [01]= "  1011" = "001011" = A[1] = "(()())"
                [02]= "   101" = "001101" = A[2] = "(())()"
                [03]= "    10" = "001110"
                [04]= " 10011" = "010011" = A[3] = "()(())"
                [05]= "   101" = "010101" = A[4] = "()()()"
                [06]= "    10" = "010110"
                [07]= "  1001" = "011001"
                [08]= "    10" = "011010"
                [09]= "   100" = "011100"
                [10]= "100011" = "100011"
                [11]= "   101" = "100101"
                [12]= "    10" = "100110"
                [13]= "  1001" = "101001"
                [14]= "    10" = "101010"
                [15]= "   100" = "101100"
                [16]= " 10001" = "110001"
                [17]= "    10" = "110010"
                [18]= "   100" = "110100"
                [19]= "  1000" = "111000"

*/

    // Tips: pair數字大小, 越大代表越上層, 越小代表越下層
    // Tips: 未填入左括号數超过右括号數，它肯定不是合法序列
    public static List<String> generateParenthesis(int pair) {
        List<String> res = new ArrayList<>();
        if (pair == 0)
            return res;
        helper(res, "", pair, pair);
        return res;
    }
    // Backtracking是一種窮舉搜尋的演算法，目標是找尋所有可能的答案，可分為兩個概念，分別是enumerate(枚舉)與pruning(剪枝)
    //  (1)enumerate(枚舉):每一步列出所有可能的下一步一一測試
    //  (2)pruning(剪枝):遇到不符合條件的下一步便省略，不再繼續枚舉
    public static void helper(List<String> res, String s, int L, int R) {
        System.out.println(L+","+R);

        if (L > R) // 剪枝條件2: 如果未填入左括号數超过右括号數，它肯定不是合法序列, ex: ())不合法, (()合法
            return;

        if (L == 0 && R == 0) { // 剪枝條件1: 左右括号均填畢
            System.out.println(s);
            res.add(s);
            return;
        }

        if (L > 0)
            helper(res, s + "(", L - 1, R);  // 注意!遞迴函數無分左右 helper

        if (R > 0)
            helper(res, s + ")", L, R - 1); // 注意!遞迴函數無分左右 helper
    }

    public static void main(String[] args) {
        List<String>  out = generateParenthesis(3); // Pass
        // List<String>  out = generateParenthesis_ALL_result(3); // Fail
        // for(String o : out)
        //     System.out.println("o = " + o);
    }
/*
    // generateParenthesis_ALL_result() 這個實作列出所有未截枝組合
    // Output: 20 種組合,
    //   0:( , 1:) 為何不是 2^6 = 64種, 而是20種, 因為1跟0各只能且必須出現三次
    // ["000111","001011","001101","001110","010011",
    //  "010101","010110","011001","011010","011100",
    //  "100011","100101","100110","101001","101010",
    //  "101100","110001","110010","110100","111000"]
    // Expected: 5 種組合, 因為要把L>R的都剪枝拿掉, 例如: ))(
    // ["000111","001011","001101","010011","010101"]
    // ["((()))","(()())","(())()","()(())","()()()"]

    static List<String> out = new ArrayList<String>();
    static public List<String> generateParenthesis_ALL_result(int n) {
        helper(new String(""), n, n);
        return out;
    }
    static private void helper(String res, int L, int R) {
        System.out.println(L+","+R);
        if(L == 0 && R == 0) {
            System.out.println(res);
            out.add(res);
        }
        if(L>0)
            helper(res+"(", L-1, R  );
        if(R>0)
            helper(res+")", L  , R-1);
    }
*/
}
