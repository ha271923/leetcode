package com.cspirat;

import com.utils.Out;

import java.util.HashMap;

public class RomantoInteger {
    public static void main(String[] args) {
        // 羅馬數字字串=左大右小
        String in = "XLIV"; // 44
        // String in = "MCMXCIV"; // 1994
        // String in = "MDCXCV";  // MDCXCV = M D C XC V = 1000+500+100+90+5 = 1695
        System.out.println("res="+romanToInt(in));  // Ans= 1994
        // System.out.println("res="+romanToInt_hawk(in));
    }
    /**
     * 13. Roman to Integer
     * Given a roman numeral, convert it to an integer.

     Input is guaranteed to be within the range from 1 to 3999.

     规律：左边的数字如果小于右边的数字 = 右 - 左

     time : O(n);
     space : O(1);
     * @param s
     * @return
     */
    public static int romanToInt(String s) { // 記住兩個KEY, 這是羅馬數字的規律性
        if (s == null || s.length() == 0)
            return 0;
        // 1000, 900,500,400 ,100,  90, 50,  40, 10,   9,  5,   4, 1
        // "M" ,"CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"
        int res = toNumber(s.charAt(0)); // KEY: 開頭為I從1開始,開頭為X從10開始, 先取出charAt(0)
        for (int i = 1; i < s.length(); i++) {
            if (toNumber(s.charAt(i)) > toNumber(s.charAt(i - 1))) { // 每次先框兩個字分析, 遇後大於前時, 使用減法 Ex: IV=1+5-2*1=4; IX=1+10-2*1=9; XC=10+100-2*10=90 = charAt(1)-charAt(0) = 當前比後小時,觸動減法
                Out.i("i="+i+"  "+s.charAt(i) +"="+toNumber(s.charAt(i)) +"  "+ s.charAt(i-1)+"*2="+toNumber(s.charAt(i-1))*2+"  old_res="+res);
                res += toNumber(s.charAt(i)) - 2 * toNumber(s.charAt(i - 1)); // KEY: 因為(i - 1),所以從 i=1 開始, *2是因為回圈下一輪補償+1, 所以會是 -1 , Ex: VI= (5-2*1)+(1)=4
            } else {
                res += toNumber(s.charAt(i));
            }
        }
        return res;
    }

    public static int toNumber(char c) {
        int res = 0;
        switch (c) {
            case 'I' : return 1;
            case 'V' : return 5;
            case 'X' : return 10;
            case 'L' : return 50;
            case 'C' : return 100;
            case 'D' : return 500;
            case 'M' : return 1000;
        }
        return res;
    }
    // Hawk: HashMap 使用get()前, 要先用containsKey()確認KEY是否存在, 否則會Exception
    // 效能較romanToInt() 差
    public static int romanToInt_hawk(String s) {
        int res = 0;
        HashMap<String, Integer> map = new HashMap<>();
        // Tips: 羅馬數字 高位數的在左邊, 低位數在右邊, IV=4, VI=6=V+I可由5+1組合,
        // KEY: 組合數字 4,9 無法用加法算出, 需定義符號直接查表
        map.put("M", 1000);
        map.put("CM", 900); // 查表 C+M
        map.put("D",  500);
        map.put("CD", 400); // 查表 C+D
        map.put("C",  100);
        map.put("XC",  90); // 查表 X+C
        map.put("L",   50);
        map.put("XL",  40); // 查表 X+L
        map.put("X",   10);
        map.put("IX",   9); // 查表 I+X
        map.put("V",    5);
        map.put("IV",   4); // 查表 I+V
        map.put("I",    1);
        int len = s.length();
        int val = 0;
        int start = 0;
        String Char = null;
        while (len>0 ){
            if(len >= 2) {
                Char = s.substring(start,start+2);
                if(map.containsKey(Char))
                    val = map.get(Char); // NULL exception
            }
            if(val!=0) {
                start += 2;
                len -= 2;
            } else { // try one char
                Char = s.substring(start,start+1);
                if(map.containsKey(Char))
                    val = map.get(Char);
                start++;
                len--;
            }
            res += val;
            Out.i("start="+start+"   len="+len+"  Char="+Char+"  val="+val+"   res="+res);
            val = 0;
        }
        return res;
    }
}
