package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 格雷碼（循環二進位單位距離碼/最小差異碼）
 * 是任意兩個相鄰數的代碼只有一位二進位數不同的編碼，它與奇偶校驗碼同屬可靠性編碼。
 *
 *     KEY: 畫出表格找出規則！！
 *                                  bit
 *                                  210
 *    十進位 二進位          十進位　 格雷碼
 *   i= 0     000       Ans= 0      000  init
 *      1     001            1      001  [0]+1
 *
 *      2     010            3      011  [1]+2
 *      3     011            2      010  [0]+2
 *
 *      4     100            6      110  [3]+4
 *      5     101            7      111  [2]+4
 *      6     110            5      101  [1]+4
 *      7     111            4      100  [0]+4
 *                                      algorithm = (results.size()-1)+inc  <== KEY
 *
 *  input: n=3 , 3 bits
 *  output: GrayCode=[0, 1, 3, 2, 6, 7, 5, 4]
 *
 * The gray code is a binary numeral system where two successive values differ
 * in only one bit.
 * <p>
 * Given a non-negative integer n representing the total number of bits in the
 * code, print the sequence of gray code. A gray code sequence must begin with
 * 0.
 * <p>
 * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 * <p>
 * Note:
 * For a given n, a gray code sequence is not uniquely defined.
 * <p>
 * For example, [0,2,3,1] is also a valid gray code sequence according to the
 * above definition.
 * For now, the judge is able to judge based on one instance of gray code
 * sequence. Sorry about that.
 * <p>
 * Tags: Backtracking
 */
class Graycode {
    public static void main(String[] args) {
        System.out.println(
                grayCode(3)
        );
    }

    /**
     * generate 0, 1 then add 10 from back to get 11, 10
     * same goes for 00, 01, 11, 10, add 100 to get 110, 111, 101, 100
     */
    static public List<Integer> grayCode(int n) {
        List<Integer> results = new ArrayList<Integer>();
        results.add(0); // 只有一開始沒有數字1, 所以不要在算法裏面
        // Algorithm
        for (int bit = 0; bit < n; bit++) {
            int inc = 1 << bit; // increase bit
            for (int i = results.size() - 1; i >= 0; i--) { // KEY: 畫出表格找出規則 bit=2, i={[3],[2],[1],[0]}
                results.add(results.get(i) + inc);
            }
        }
        return results;
    }
}