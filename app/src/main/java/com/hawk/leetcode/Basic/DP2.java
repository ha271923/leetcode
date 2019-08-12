package com.hawk.leetcode.Basic;

/**
 * 例題2:編輯距離 ( For Text Compare )
 *  編輯距離，又稱Levenshtein距離（也叫做Edit Distance），是指兩個字串之間，由一個轉成另一個所需的最少編輯操作次數。
 *  許可的編輯操作包括將一個字元替換成另一個字元，插入一個字元，刪除一個字元。
 *  例如將kitten一字轉成sitting：
 *      sitten （k->s）
 *      sittin （e->i）
 *      sitting （->g）
 *  所以kitten和sitting的編輯距離是3。俄羅斯科學家Vladimir Levenshtein在1965年提出這個概念。
 *  給出兩個字串a,b，求a和b的編輯距離。
 *
 *  狀態定義:Fi,j表示第一個字串的前i個字母和第二個字串的前j個字母需要編輯的次數，求Fn,m，n和m分別是兩個字串的長度。
 *
 *  狀態轉移方程：
 *  當Fi,j-1=Fi-1,j時，Fi,j=Fi,j-1；
 *  當Fi,j-1！=Fi-1,j時，Fi,j=min{Fi-1,j-1,Fi,j-1,Fi-1,j} 1.
 */
public class DP2 {
    public static void main(String[] args) {
        int ret = minEditDistance("kitten", "sitting");
        System.out.println("minEditDistance=" + ret);
    }


    /**
     * 计算编辑距离Edit Distance
     * if i == 0 且 j == 0，edit(i, j) = 0
     * if i == 0 且 j  > 0，edit(i, j) = j
     * if i  > 0 且 j == 0，edit(i, j) = i
     * if i >= 1 且 j >= 1 edit(i, j) == min{ edit(i-1, j) + 1, edit(i, j-1) + 1, edit(i-1, j-1) + temp }，
     * 当第word1第i个字符不等于word2的第j个字符时，temp = 1；否则，temp = 0。
     *
     * @param word1 字符串1
     * @param word2 字符串2
     * @return 取二位矩阵的最后一个元素的值, 就是minEditDist    arr[word1.length][word2.length]
     */
    public static int minEditDistance(String word1, String word2) {
        int yLen = word1.length();
        int xLen = word2.length();

        if (yLen == 0 || xLen == 0) {
            return yLen == 0 ? xLen : yLen;
        }

        /*
        * 初始化矩阵
        * yLen = kitten  = 6
        * xLen = sitting = 7
        * 0: all are same
        * 3: 3 char not match
        *           xLen>=x=[0][j] [0][1] [0][2] [0][3] [0][4] [0][5] [0][6] [0][7]  word2
        * yLen>=y=dp[i][0] |  0   |  1   |  2   |  3   |  4   |  5   |  6   |  7   |
        *           [1][0] |  1   |  0   |  0   |  0   |  0   |  0   |  0   |  0   |
        *           [2][0] |  2   |  0   |  0   |  0   |  0   |  0   |  0   |  0   |
        *           [3][0] |  3   |  0   |  0   |  0   |  0   |  0   |  0   |  0   |
        *           [4][0] |  4   |  0   |  0   |  0   |  0   |  0   |  0   |  0   |
        *           [5][0] |  5   |  0   |  0   |  0   |  0   |  0   |  0   |  0   |
        *           [6][0] |  6   |  0   |  0   |  0   |  0   |  0   |  0   |  0   |
        *            word1
        * */
        int[][] dp = new int[yLen + 1][xLen + 1]; // 為什麼要把input陣列XY維度+1? 因為下面的dp算法都是往正上[y-1][x]跟左上[y-1][x-1]跟左[y][x-1], 所以要創造出一開始的數值才不會out-of-bound
        for (int y = 0; y <= yLen; y++) { // 為什麼x一開始是1,2,3,4,5,6,7? 因為一開始都沒字所以完全相同=0, 之後y=1時, word1挑c1, word2還在c0, 所以一定是1個字不相同, 填入1
            dp[y][0] = y;
        }
        for (int x = 0; x <= xLen; x++) { // 為什麼y一開始是1,2,3,4,5,6,7? 理由同上
            dp[0][x] = x;
        }
        // 暴力掃描,填充dp矩阵, 兩個word都從第一個char開始比, char index = (y or x) -1
        for (int y = 1; y <= yLen; y++) {
            for (int x = 1; x <= xLen; x++) { // begin from dp[1][1] to dp [yLen][xLen]
                char w1c = word1.charAt(y-1);
                char w2c = word2.charAt(x-1);
                if (w1c == w2c) {
                    dp[y][x] = dp[y-1][x-1]; // 若相同, 不同字元數依然相同前一組dp(左斜前方)的結果
                } else { // 若不相等时,將前一組dp, 即前次w1c與w2c比較的結果再累加1, 以累加相異字元數
                    // word1:    ABCDEF
                    //          /|\
                    //         //|\\   word1的第一字元A, 一一與word2的DACBE比較
                    // word2:  DACBE
                    // KEY: 題目說許可的編輯操作包括:將一個字元替換成另一個字元，插入一個字元，刪除一個字元。
                    int replace = dp[y-1][x-1] + 1; // 左上+1 = 1.將一個字元替換成另一個字元
                    int  insert = dp[y-1][x  ] + 1; // 正上+1 = 2.插入一個字元
                    int  delete = dp[y  ][x-1] + 1; //   左+1 = 3.刪除一個字
                    // 問題是求最少編輯操作次數, 所以比較三種操作
                    int     min = Math.min(replace, insert);
                            min = Math.min(min, delete);
                    dp[y][x] = min;  // DP:為避免一再地解重複的子問題，一旦解出子問題的解答(solution)，即會將其存在表格(或陣列)中。
                }
            }
        }

        for (int y = 0; y < dp.length; y++) {
            for (int x = 0; x < dp[y].length; x++) {
                System.out.print(dp[y][x] + " ");
            }
            System.out.println();
        }
        return dp[yLen][xLen];
    }

}
