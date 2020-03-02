package com.cspirat;

import com.utils.Out;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : WordSearch
 * Creator : Edward
 * Date : Oct, 2017
 * Description : 79. Word Search
 */
public class WordSearch {
    /**
     * 意思就是從任意字符出發，然後它可以向左向右向上向下移動，走過的路徑構成一個字符串，
     * 判斷是否能走出給定字符串的單詞，還有一個條件就是走過過的字符不能夠走第二次。
     *
     * 例如: SEE，從第二行最後一列的S出發，向下移動，再向左移動，就走出了SEE。
     * 例如: ABCB，從第一行第一列的A出發，向右移動，再向右移動，到達C以後，不能向左移動回到B，
     *      並且也沒有其他的路徑走出ABCB所以返回false。
     */
    public static void main(String[] args) {
        // test1
        char[][] inputs = {
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
        };
        String word = "ABCCED"; // ans: true
        // test2
        char[][] inputs2 = {
            {'C','A','A'},
            {'A','A','A'},
            {'B','C','D'}
        };
        String word2 = "AAB"; // ans: true

        boolean res = wordSearch(inputs, word);
        System.out.println(res);
    }
    /**
     * For example,
     Given board =

     [
       ['A','B','C','E'],
       ['S','F','C','S'],
       ['A','D','E','E']
     ]
     word = "ABCCED", -> returns true,
     word = "SEE", -> returns true,
     word = "ABCB", -> returns false.


     time : 不知道
     space : 不知道

     * @param board
     * @param word
     * @return
     */

    // Tips: 第一個char要用暴力法掃出來, 之後的char只要掃鄰近四個方向(上下左右)的字元
    static public boolean wordSearch(char[][] board, String word) {
        // LOOP: 暴力法掃出第一個char
        for (int y = 0; y < board.length; y++)
            for (int x = 0; x < board[0].length; x++)
                if (isExist_recur(board, y, x, word, 0))
                    return true;

        return false;
    }

    static private boolean isExist_recur(char[][] board, int y, int x, String word, int shift) {
        if (shift >= word.length()) // 掃完了
            return true;

        // pos出界?
        if (y < 0 || y >= board.length ||
            x < 0 || x >= board[0].length)
            return false;
        Out.i("  board["+y+"]["+x+"]="+board[y][x]+"  shift="+shift);
        if (board[y][x] == word.charAt(shift++)) { // 找到符合字元
            char c = board[y][x];
            Out.i("c="+c);
            board[y][x] = '#'; // 使用過的字元不得重複使用,所以標註為'#'
            boolean res = // RECURSIVE: 四方向的任一組為true即為true,一層層往下找
                    isExist_recur(board,y+1 , x    , word, shift) || // 上
                    isExist_recur(board,y-1 , x    , word, shift) || // 下
                    isExist_recur(board, y     ,x+1, word, shift) || // 右
                    isExist_recur(board, y     ,x-1, word, shift);   // 左
            board[y][x] = c; // 要把table還原, 供下次[y2][x2]的起始點使用
            return res;
        } else { // 沒找到符合字元
            return false;
        }
    }
}
