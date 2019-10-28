package com.cspirat;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SudokuSolver
 * Creator : Edward
 * Date : Oct, 2017
 * Description : 37. Sudoku Solver
 */
public class SudokuSolver {

    // time : 不知道 space : 不知道

    public static void main(String[] args) {
        char[][] board1 = {
                // Ans: true, empty_num=51
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
        // board1與board2差異處在於第一個元素5與8
        char[][] board2 = {
                // Ans: false , empty_num=51
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

        boolean ret = solveSudoku(board1);
        System.out.println("solveSudoku ret=" + ret);
    }

    // Tips: [9行x9列 x 填值9x檢查9(行&列&宮)]^(empty_num遞迴)
    //      = 6561^51空格 = 6561的51次方 = 4.6288374031887636999947808580971e+194
    public static boolean solveSudoku(char[][] board) {
        if (board == null || board.length == 0)
            return false;
        // Tips: 2+1個LOOP = 9x9x9= 729次填值
        for (int y = 0; y < board[0].length; y++) {  // LOOP1: 0~8
            for (int x = 0; x < board[0].length; x++) {  // LOOP2: 0~8
                if (board[y][x] == '.') { // 掃到未知數
                    for (char c = '1'; c <= '9'; c++) { // LOOP3:  '1'~'9', 一次一次地把數字 1~9 輸入驗證
                        if (isValid(board, y, x, c)) { // LOOP4: 填入這個值, 產生新的題目
                            board[y][x] = c; // 驗證OK!這個位置填入此值, 確實可以產生新的題目
                            if (solveSudoku(board)) // Recursive1: 再用多填了一個值產生的新題目, 遞迴下去, 新題目將會empty-1, 直到empty==0, 代表都填滿了
                                return true; // 最上層solveSudoku()->...->最底層solveSudoku()->ret=true只要一次結果就會一直true
                            else
                                board[y][x] = '.';  // Recursive1: 遞迴, 找不到答案, 依然是未知數'.'
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
static int recursive_count=0;
    // Tips: 驗證,非填入值
    public static boolean isValid(char[][] board, int row, int col, char c ) {
        for (int i = 0; i < 9; i++) { // LOOP4: 0~8的檢驗須完全通過
            // x: 掃行,檢查新題目的col
            if (board[row][i] != '.' && board[row][i] == c) // 已知數 && 填入c掃到與已知數重覆
                return false; // 填這個c值是重複的!
            // y: 掃列,檢查新題目的row
            if (board[i][col] != '.' && board[i][col] == c) // 已知數 && 填入c掃到與已知數重覆
                return false; // 填這個c值是重複的!
            // xy: 檢查新題目的col&row, 掃9x9宮
            if (    board[3 * (row / 3) + i / 3][3 * (col / 3) + i / 3] != '.' // xy: 檢查新題目的col&row
                 && board[3 * (row / 3) + i / 3][3 * (col / 3) + i / 3] ==  c ) {
                return false; // 填這個c值是重複的!
            }
        }
        // System.out.println(recursive_count++);  // recursive_count = 3145435
        return true; // 所有欄位都填滿了! 獲得解答
    }
}
