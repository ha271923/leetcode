package com.cspirat;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : PermutationSequence
 * Creator : Edward
 * Date : Nov, 2017
 * Description : 60. Permutation Sequence
 */
public class PermutationSequence {
    /**
     * The set [1,2,3,…,n] contains a total of n! unique permutations.

     By listing and labeling all of the permutations in order,
     We get the following sequence (ie, for n = 3):

     "123"
     "132"
     "213"
     "231"
     "312"
     "321"
     Given n and k, return the kth permutation sequence.

     Note: Given n will be between 1 and 9 inclusive.

     1， 2， 3， 4:

     1 + {2, 3, 4}
     2 + {1, 3, 4}
     3 + {1, 2, 4}
     4 + {1, 2, 3}

     18 : 3421

     res  :
     fact : 1 1 2 6

     k = 17

     i = 4    index = 17 / 6 = 2 k = 17 % 6 = 5
     i = 3    index = 5 / 2 = 2 k = 5 % 2 = 1
     i = 2    index = 1 / 1 = 1 k = 1 % 1 = 0

     4 3 2 1
     3 4 2 1

     time : O(n)
     space : O(n)

     * @param element
     * @param kth
     * @return
     */
    /*
    A. 從結果推數字規則 -> 每一個數的區間factor:
                                        factor=一個digital下面的總變化數
       1 element  = 1*1   condition   -> 1
       2 elements = 2*1   conditions  -> 1 (因為數字不可重複,所以只有01,10兩種情況,沒有00,11)
       3 elements = 3*2   conditions  -> 2
       4 elements = 4*6   conditions  -> 6  <-- 第(5-1)層的組合數量
       5 elements = 5*24  conditions  -> 24 <-- 第 5   層的組合數量
       ...
       n elements = n*[(n-1)*(n-2)*(n-3)...*1] == n! <-- 第n層的組合數量
       n elements = n*[factor]
    B. 導出最佳化規則 -> 最適合的區間:
     f=2     f=1  f=1 (注意! 當只有一層或兩層時,間隔都是1 )
                          i
          /-- 2 -- 3  == [1] --\
      1 <                       >= 1+{2,3} = 數字1的全排列有2組
          \-- 3 -- 2  == [2] --/

          /-- 1 -- 3  == [3] --\
      2 <                       >= 2+{1,3} = 數字2的全排列有2組, 若要求第3組數組的話, 可知會落在數字2的區間
          \-- 3 -- 1  == [4] --/

          /-- 1 -- 2  == [5] --\
      3 <                       >= 3+{1,2} = 數字3的全排列有2組
          \-- 2 -- 1  == [6] --/
    C. 算法:
        n個數時, 每一個數都有[(n-1)!] 階層個組合, 所以Loop的n loop起算點, 最好是(Kth-[(n-1)!]*minN) < [(n-1)!]
        在此(n-1)前的所有loop都可以跳過
     */
    // Tips: 一定是從1開始, 直到n的數列
    public static void main(String[] args) {
        // System.out.println(getPermutation(3, 1)); // 123
        // System.out.println(getPermutation(3, 2)); // 132
//        System.out.println(getPermutation(3, 3)); // 213 , leetcode example:
        // System.out.println(getPermutation(3, 4)); // 231
        // System.out.println(getPermutation(3, 5)); // 312
        // System.out.println(getPermutation(3, 6)); // 321

//         System.out.println(getPermutation(4, 4)); // 1342 , leetcode example:

//        System.out.println(getPermutation(5, 44)); // 25143

        System.out.println(getPermutation(12, 2888880)); // test factor

//        System.out.println(getPermutation_recursive(12, 2888880)); // test factor

    }

    /**
     * Initialize a list of digits to build the result
     * Build from first character
     */
    public static String getPermutation(int n, int pos) {
        System.out.println("QUESTION:   pos="+pos+"  n="+n);

        // 計算出每一個數為root時的間隔(factor)
        pos = pos - 1;
        int factor = 1;
        for (int i = 1; i < n; i++) {
            factor *= i; // factor=n!
        }
        // 需要digits的原因:
        // 1. 因為要找的數組從1開始到n,而非從0, 避免用(n+1)來推算, digits[0]='1'
        // 2. 因為需要remove已找到, 下次不需要列舉的數
        List<Integer> digits = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            digits.add(i + 1);
        }
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) { // LOOP: 一層一層的經由index的計算來找出所要append的值
            int index = pos / factor; // KEY: 觀看上述SAMPLE資料, 可知每一個數一定都是該層factor與pos經由公式=> index = (pos / factor)求出!
            int digital = digits.get(index);
            sb.append(digital); // Ans:把結果輸出到sb
            // 那下一個數如何列舉呢? 重新定義題目的pos
            digits.remove(index); // KEY: 因為Answer數組沒有重複性, 所以移除
            // 下一個數的pos需要重新計算,不是從第n層的array index(max=n!), 而是經由餘數算法後的第(n-1)層array index(max=(n-1)!)
            // 一路往上一層使用餘數算法計算出新pos
            /*
            KEY: algorithm算出新的pos
       factor= 1 <--   2 <--   3 <--   4 <--   5 <-- ....... <-- (n-1) <-- n 種變化情況
            i= n <--(n-1)<--(n-2)<--(n-3)<--(n-4)<-- ....... <--    1  <-- 0 層,往上一層掃描
            ex: pos=2, 最多 3層, 利用迴圈往上遞增
                         i=0層, factor=n!=1*2*3=6, pos=2
                   i=1層, factor=(n-1)!=1*2=2, (pos=2) >= (i=2),
             i=2層, factor=(n-2)!=1=1, pos=2
             */
            pos = pos % factor; // KEY: algorithm算出新的pos
            if (i < (n-1)) {  // 因為從最底一層(i=0),往最root(i=11)一層,逐漸前進(i++),當到達最root那一層時, factor必定為1(無分支)
                factor = factor / ((n - 1) - i);  // 非root層, 的factor由此算法推算
            } else {
                System.out.println("抵達最root層,factor必定為1, 不須套演算法!!");
            }
            System.out.println("pos="+pos+"  index="+index+"  digital="+digital+"  n="+n+"   i="+i+"  factor="+factor);
        }
        return sb.toString();
    }

    /**
     * Divide into subgroups and locate it
     */
    public static String getPermutation_recursive(int n, int k) {
        ArrayList<Integer> digits = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            digits.add(i + 1);
        }
        return helper(digits, n, k - 1); // note it's k - 1 here, start from 0
    }

    /**
     * Get relative digit from list
     * First digit's index in list is k / factorial(n-1)
     * Get the digit, remove that digit from list and update k
     * Concatenate digit with following digits
     */
    public static String helper(ArrayList<Integer> nums, int n, int k) {
        if (n == 1) {
            return nums.get(0).toString();
        }
        int index = k / factorial(n - 1);
        String digit = nums.get(index).toString();
        nums.remove(index);
        k = k % factorial(n - 1);
        return digit + helper(nums, n - 1, k);
    }

    private static int factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorial(n - 1); // can be dp
    }
}
