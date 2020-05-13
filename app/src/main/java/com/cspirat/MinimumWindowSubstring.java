package com.cspirat;

import com.utils.Out;

import java.util.HashMap;
import java.util.Map;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MinimumWindowSubstring
 * Creator : Edward
 * Date : Nov, 2017
 * Description : 76. Minimum Window Substring
 */
public class MinimumWindowSubstring {
    public static void main(String[] args) {
        String ret = minWindow("ADOBECODEBANC", "ABC");
        Out.i("ret=" + ret); // Output: "BANC"
    }

    /**
     * sliding window
     * Given a string S and a string T, find the minimum window in S which will contain all the characters
     * in T in complexity O(n).

     For example,
     S = "ADOBECODEBANC"
     T = "ABC"
     Minimum window is "BANC".

     Note:
     If there is no such window in S that covers all characters in T, return the empty string "".

     If there are multiple such windows, you are guaranteed that there will always be only one unique
     minimum window in S.

     test case:
     S = "ADOBECODEBANC"
     T = "ABC"

     ADOBEC
     DOBECODEBA
     OBECODEBA
     BECODEBA
     ECODEBA
     CODEBA
     ODEBANC
     DEBANC
     EBANC
     BANC

            A B C D E F G H    O
     count: 0 0 0 -1-1         -1
            0 1 2 3 4 5 6 7 ...

     A D O B E C O D E B A N C
               i
     CODEBA
     total = 1

     time : O(n)
     space : O(1)

     * @param source
     * @param target
     * @return
     */
    // Tips: 字元出現次序不重要, 出現次數重要
    // Tips: 因為題目未限定A~Z或a~z; 所以將所有ASCII的128個字元均列入,次序ID代表ASCII code, 陣列存的數值代表count
    // Tips: T = "ABC", 字元'A'要出現count=1次, 'B''C'字元'亦然
    /*
    思路：
    1. 如果S[i:j]是min window，那么S[i], S[j]必然也在T中。
    2. 对于任意S[i]要检查是否也在T中，需要将T的所有字符建立一个hash table(因全部ASCII也才128個,所以也可用陣列)。
       T中可能有重复字符，所以key = T[i], val = freq of (T[i])。
    3. 先找到第一个window包含T
    4. 扫描S[i]
    若S[i]不在T中，跳过。
    若S[i]在T中，freq of (S[i]) - 1，并且match的长度+1
     */
    static final int ASCII_chars = 128;
    public static String minWindow(String srcStr, String tgtStr) {
        // ASCII table共有128個字元  http://www.asciitable.com/

        int[] countByChar = new int[ASCII_chars];
        for (char ch : tgtStr.toCharArray()) {  // 先把target出現的字完次數記錄在陣列
            countByChar[ch]++;
        }
        int from = 0;
        int total = tgtStr.length();
        int range = Integer.MAX_VALUE;
        /**
         sliding window:
                           L   R
                        ->|range|<-
                   0123456789012
         srcStr = "ADOBECODEBANC"
         tgtStr = "ABC"

         match1= "ADOBEC"        <-- Len=6
         match2=    "BECODEBA"   <-- Len=8 , 'B'*2
         match3=      "CODEBA"   <-- Len=6
         match4=          "BANC" <-- Len=4 , smallest
                  0123456789012

         */
        for (int R = 0, L = 0; R < srcStr.length(); R++) { // LOOP1:
            char asciiChar = srcStr.charAt(R); // KEY: type char val= ASCII code 當作 index
            Out.i("R="+R+"    asciiChar="+asciiChar);
            if (countByChar[asciiChar]-- > 0) // 掃到Target char, 該字元須出現次數與總數均減一
                total--;

            // ???
            while (total == 0) { // LOOP2: 每當全部的Target的字元都有掃到時, 當時srcCharIdx指到哪? 計算sliding window範圍起點與寬度
                Out.i("R=" + R + "  L=" + L + "  range=" + range + "  from=" + from);
                if (R - L + 1 < range) {
                    range = R - L + 1;
                    from = L;
                }
                asciiChar = srcStr.charAt(L++); // L++
                if (++countByChar[asciiChar] > 0)
                    total++; // break LOOP
            }
        }

        // LR可以丟棄了, 從字元的起點from與數目range, 得知result字串內容
        // substring() 代表 Out.i("0123456".substring(2,2+3)); 會印出 "234"
        return (range == Integer.MAX_VALUE) ? "" : srcStr.substring(from, from + range);
    }


/*
    https://www.cnblogs.com/grandyang/p/4340948.html
    這道題給了我們一個原字符串S，還有一個目標字符串T，讓在S中找到一個最短的子串，使得其包含了T中的所
    有的字母，並且限制了時間複雜度為O (n)。這道題的要求是要在O(n) 的時間度裡實現找到這個最小窗口字串
    ，暴力搜索Brute Force 肯定是不能用的，因為遍歷所有的子串的時間複雜度是平方級的。那麼來想一下，時
    間複雜度卡的這麼嚴，說明必須在一次遍歷中完成任務，當然遍歷若干次也是O(n)，但不一定有這個必要，嘗
    試就一次遍歷拿下！那麼再來想，既然要包含T中所有的字母，那麼對於T中的每個字母，肯定要快速查找是否
    在子串中，既然總時間都卡在了O(n)，肯定不想在這裡還浪費時間，就用空間換時間（也就算法題中可以這麼
    乾了，七老八十的富翁就算用大別野也換不來時間啊。依依東望，望的就是時間吶TT），使用HashMap，建立T
    中每個字母與其出現次數之間的映射，那麼你可能會有疑問，為啥不用HashSet 呢，別急，講到後面你就知道
    用HashMap 有多妙，簡直妙不可言～

    目前在腦子一片漿糊的情況下，我們還是從簡單的例子來分析吧，題目例子中的S有點長，換個短的S = "ADBANC"
    ，T = "ABC"，那麼肉眼遍歷一遍S唄，首先第一個是A，嗯很好，T中有，第二個是D，T中沒有，不理它，第三個
    是B，嗯很好，T中有，第四個又是A，多了一個，禮多人不怪嘛，收下啦，第五個是N，一邊涼快去，第六個終於是
    C了，那麼貌似好像需要整個S串，其實不然，注意之前有多一個A ，就算去掉第一個A，也沒事，因為第四個A可以
    代替之，第二個D也可以去掉，因為不在T串中，第三個B就不能再去掉了，不然就沒有B了。所以最終的答案就"BANC"
    了。通過上面的描述，你有沒有發現一個有趣的現象，先擴展，再收縮，就好像一個窗口一樣，先擴大右邊界，然後
    再收縮左邊界​​，上面的例子中右邊界無法擴大了後才開始收縮左邊界，實際上對於復雜的例子，有可能是擴大右邊
    界，然後縮小一下左邊界，然後再擴大右邊界等等。這就很像一個不停滑動的窗口了，這就是大名鼎鼎的滑動窗口
    Sliding Window 了，簡直是神器啊，能解很多子串，子數組，子序列等等的問題，是必須要熟練掌握的啊！

    下面來考慮用代碼來實現，先來回答一下前面埋下的伏筆，為啥要用HashMap，而不是HashSet，現在應該很顯而易
    見了吧，因為要統計T串中字母的個數，而不是僅僅看某個字母是否在T串中出現。統計好T串中字母的個數了之後，
    開始遍歷S串，對於S中的每個遍歷到的字母，都在HashMap 中的映射值減1，如果減1後的映射值仍大於等於0，說
    明當前遍歷到的字母是T串中的字母，使用一個計數器cnt，使其自增1。當cnt 和T串字母個數相等時，說明此時的
    窗口已經包含了T串中的所有字母，此時更新一個minLen 和結果res，這裡的minLen 是一個全局變量，用來記錄出
    現過的包含T串所有字母的最短的子串的長度，結果res 就是這個最短的子串。然後開始收縮左邊界​​，由於遍歷的
    時候，對映射值減了1，所以此時去除字母的時候，就要把減去的1加回來，此時如果加1後的值大於0了，說明此時少
    了一個T中的字母，那麼cnt 值就要減1了，然後移動左邊界left。你可能會疑問，對於不在T串中的字母的映射值也
    這麼加呀減呀的，真的大丈夫（帶膠布）嗎？其實沒啥事，因為對於不在T串中的字母，減1後，變-1，cnt 不會增加
    ，之後收縮左邊界​​的時候，映射值加1後為0，cnt 也不會減少，所以並沒有什麼影響啦，下面是具體的步驟啦：
    - 先掃描一遍T，把對應的字符及其出現的次數存到HashMap 中。
    - 然後開始遍歷S，就把遍歷到的字母對應的HashMap 中的value 減一，如果減1後仍大於等於0，cnt 自增1。
    - 如果cnt 等於T串長度時，開始循環，紀錄一個字串並更新最小字串值。然後將子窗口的左邊界向右移，如果某個移
      除掉的字母是T串中不可缺少的字母，那麼cnt 自減1，表示此時T串並沒有完全匹配。
 */
/*
    static public String minWindow_hashmap(String s, String t) {
        String res = "";
        int[] countByChar = new int[ASCII_chars];
        int left = 0, cnt = 0, minLen = Integer.MAX_VALUE;
        for (char c : t.toCharArray())
            ++countByChar[c];

        for (int i = 0; i < s.length(); ++i) {
            if (--countByChar[s.charAt([i])] >= 0)
                ++cnt;

            while (cnt == t.length()) {
                if (minLen > i - left + 1) {
                    minLen = i - left + 1;
                    res = s.substring(left, minLen);
                }
                if (++countByChar[s[left]] > 0)
                    --cnt;
                ++left;
            }
        }
        return res;
    }
*/
    /*
        這道題也可以不用HashMap，直接用個int的數組來代替，因為ASCII只有256個字符，所以用個大小
        為256的int數組即可代替HashMap，但由於一般輸入字母串的字符只有128個，所以也可以只用128
        ，其餘部分的思路完全相同，雖然只改了一個數據結構，但是運行速度提高了一倍，說明數組還是比
        HashMap快啊。在熱心網友chAngelts的提醒下，還可以進一步的優化，沒有必要每次都計算子串，
        只要有了起始位置和長度，就能唯一的確定一個子串。這裡使用一個全局變量minLeft來記錄最終結
        果子串的起始位置，初始化為-1，最終配合上minLen，就可以得到最終結果了。注意在返回的時候要
        檢測一下若minLeft仍為初始值-1，需返回空串，
     */
/*
    static public String minWindow( String s, String t) {
        vector < int > letterCnt( 128 , 0 );
        int left = 0 , cnt = 0 , minLeft = - 1 , minLen = INT_MAX;
        for ( char c : t) ++ letterCnt[c];
        for ( int i = 0 ; i < s.size(); ++ i) {
            if (--letterCnt[s[i]] >= 0 ) ++ cnt;
            while (cnt == t.size()) {
                if (minLen > i - left + 1 ) {
                    minLen = i - left + 1 ;
                    minLeft = left;
                }
                if (++letterCnt[s[left]] > 0 ) -- cnt;
                ++ left;
            }
        }
        return minLeft == - 1 ? "" : s.substr(minLeft, minLen);
    }
*/
}
