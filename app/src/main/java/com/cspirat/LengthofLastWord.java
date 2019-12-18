package com.cspirat;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : LengthofLastWord
 * Creator : Edward
 * Date : Oct, 2017
 * Description : 58. Length of Last Word
 */
public class LengthofLastWord {
    /**
     * For example,
     Given s = "Hello World",
     return 5.

     time : O(1)
     space : O(1)

     * @param s
     * @return
     */
    // Key: 去除頭尾的空白字元後再開始處理
    public static void main(String[] args) {
        String input = " ";
        // String input = "a";
        // String input = "Hello World";
        // String input = " Hello World ";
        // int ret = lengthOfLastWord(input);
        int ret = lengthOfLastWord_Hawk(input);
        System.out.println(ret);
    }
/*
   Input 1:
   char: "H e l l o _ W o r l d"
   cIdx:  0 1 2 3 4 5 6 7 8 910
                    ^
   cLen:            = 1 2 3 4 5
    out:      (10-5)= 5 <- LastWord

   Input 2:
   char: "a"
   cIdx:  0
   cLen:  1
   out: (1-0)=1

   Input 3:
   char: "_"
   cIdx:  1
   cLen:  1
    out: =0

   Input 4: ???? 在Leetcode online上進行測試得到的答案
   char: "a_"
   cIdx:  1
   cLen:  2
    out: =1
 */
    static public int lengthOfLastWord(String strInput) {
        // trim(): 把字串頭尾的空白字元去除掉, Ex: " Hello World " => "Hello World"
        String strTrimed = strInput.trim();
        int len = strTrimed.length();
        int idx = strTrimed.lastIndexOf(" ");
        System.out.println("len="+len+"  idx= "+idx);
        return len - idx - 1;
    }

    // Hawk: my implement
    // Failed:  Time Complexity over limit.
    static public int lengthOfLastWord_Hawk(String s) {
        int s_len = s.length();

        if(s == null || s.equals("") || s.equals(" ") || s.equals("  "))
            return 0;

        // Remove ' ' at start or end position
        char[] ch = new char[s_len];
        for( int i = 0; i< s_len; i++) {
            ch[i] = s.charAt(i);
        }
        int L = 0;
        do {
            if(ch[L] == ' ') {
                L++;
            }
        } while(L<s_len);

        int R = s_len - 1;
        do {
            if (ch[R] == ' ') {
                R--;
            }
        } while(R > 0);
        System.out.println("L="+L+"  R= "+R);
        int R_space_count = (s_len - 1)  - R;

        int last_space_idx = 0;
        for( int i = L; i<= R; i++) {
            if(ch[i] == ' ')
                last_space_idx = i;
        }
        int last_word_idx = last_space_idx + 1;
        return s_len - last_word_idx - R_space_count;
    }
}
