package com.cspirat;

import java.util.HashSet;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ValidSudoku
 * Creator : Edward
 * Date : Sep, 2017
 * Description : 36. Valid Sudoku
 */
// QUESTION: 這題只是求'驗證'出這樣的題目是否違反sudoku的規則,
//           並非解出此題, 也非要求一定藥可以解出來
// KEY: HashSet
public class ValidSudoku {

    public static void main(String[] args) {
        char[][] board1 = {
                // Ans: true
                {'5','3','.',  '.','7','.',  '.','.','.'},
                {'6','.','.',  '1','9','5',  '.','.','.'},
                {'.','9','8',  '.','.','.',  '.','6','.'},

                {'8','.','.',  '.','6','.',  '.','.','3'},
                {'4','.','.',  '8','.','3',  '.','.','1'},
                {'7','.','.',  '.','2','.',  '.','.','6'},

                {'.','6','.',  '.','.','.',  '2','8','.'},
                {'.','.','.',  '4','1','9',  '.','.','5'},
                {'.','.','.',  '.','8','.',  '.','7','9'}
        };

        char[][] board2 = {
                // Ans: false
                {'8','3','.',  '.','7','.',  '.','.','.'},
                {'6','.','.',  '1','9','5',  '.','.','.'},
                {'.','9','8',  '.','.','.',  '.','6','.'},

                {'8','.','.',  '.','6','.',  '.','.','3'},
                {'4','.','.',  '8','.','3',  '.','.','1'},
                {'7','.','.',  '.','2','.',  '.','.','6'},

                {'.','6','.',  '.','.','.',  '2','8','.'},
                {'.','.','.',  '4','1','9',  '.','.','5'},
                {'.','.','.',  '.','8','.',  '.','7','9'}
        };

        boolean ret = isValidSudoku_HashSet(board2);
        // boolean ret = isValidSudoku_BruteForce(board1);
        System.out.println("Valided ret=" + ret);
    }

    // time : O(n^2) space : O(n)
    public static boolean isValidSudoku_HashSet(char[][] board) {
        char curDigital;
        // board.length == board[0].length == 9
        // Tips: 2個LOOP = 9x9= 81次填值
        for (int i = 0; i < board[0].length; i++) { // xy1=i LOOP1: 9: Try to fill num 1~9 to field by len = 1~9
            // KEY: 利用Set資料結構
            // KEY: add(E)的返回值, ret = true if this set did not already contain the specified element
            HashSet<Character> rows = new HashSet<>();
            HashSet<Character> cols = new HashSet<>();
            HashSet<Character> cube = new HashSet<>();

            for (int j = 0; j < board[0].length; j++) { // xy2=j LOOP2: 9: Try to fill num 1~9 to field by len = 1~9
                // x: 掃行
                curDigital = board[j][i];
                if (curDigital != '.' && !cols.add(curDigital)) // COL: 已知數 且 此數值不存在於Set
                    return false;

                // y: 掃列
                curDigital = board[i][j];
                if (curDigital != '.' && !rows.add(curDigital)) // ROW: 已知數 且 此數值不存在於Set
                    return false;

                // 掃9x9宮
                int rowIndex = 3 * (i / 3);
                int colIndex = 3 * (i % 3);
                curDigital = board[rowIndex + j / 3][colIndex + j % 3];
                if (curDigital != '.' && !cube.add(curDigital)) // 9x9: 已知數 且 此數值不存在於Set
                    return false;
            }
        }
        return true;
    }

    // No HashSet
    // Tips: 9行x9列 x 檢查(9行x9列x9宮) = 81次填值 x 729次填後比對 = 共 59049 次Loop
    public static boolean isValidSudoku_BruteForce(char[][] board) {
        for (int y = 0; y < board.length; y++) {  // LOOP1: 0~8
            for (int x = 0; x < board[0].length; x++) {  // LOOP2: 0~8
                if (board[y][x] == '.') // 掃到未知數
                    continue;
                if (!valid(board, y, x)) // 一定是已知數
                    return false;
            }
        }
        return true;
    }
    // 驗證
    public static boolean valid(char[][] board, int y, int x) {
        char curDigital = board[y][x];

        // x: 掃行
        for (int col = 0; col < board[0].length; col++) { // LOOP3x: 0~8
            if (col == x)
                continue;
            if (board[y][col] == curDigital)
                return false;
        }
        // y: 掃列
        for (int row = 0; row < board[0].length; row++) { // LOOP3y: 0~8
            if (row == y)
                continue;
            if (board[row][x] == curDigital)
                return false;
        }
        // 掃9x9宮
        for (int row = (y / 3) * 3; row < (y / 3 + 1) * 3; row++) { // LOOP3xy: x,y
            for (int col = (x / 3) * 3; col < (x / 3 + 1) * 3; col++) {
                if (row == y && col == x)
                    continue;
                if (board[row][col] == curDigital)
                    return false;
            }
        }
        return true;
    }
}
