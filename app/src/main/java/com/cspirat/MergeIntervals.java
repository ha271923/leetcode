package com.cspirat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MergeIntervals
 * Creator : Edward
 * Date : Oct, 2017
 * Description : 56. Merge Intervals
 */

/*
Input:
              0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
i1= { 1, 3}=    -----
i2= { 2, 6}=      ---------
i3= { 8,10}=                  ------
i4= {15,18}=                                     -----------



Answer:
              0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
o1= { 1, 6}=    --===-------
                 merge
o2= { 8,10}=                  ------
o3= {15,18}=                                     -----------

 */
/*
    public class Interval {
        int start;
        int end;
        Interval() {
            start = 0;
            end = 0;
        }
        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }
 */
public class MergeIntervals {
    public static void main(String[] args) {
        List<Interval> inputs = new ArrayList<Interval>();
        Interval i1 = new Interval(1,3);
        Interval i2 = new Interval(2,6);
        Interval i3 = new Interval(8,10);
        Interval i4 = new Interval(15,18);

        inputs.add(i3);
        inputs.add(i4);
        inputs.add(i1);
        inputs.add(i2);

        List<Interval> r = merge(inputs);

        for(Interval i : r)
        {
            System.out.println(i.start+" "+i.end);
        }
    }
    /**
     * For example,
     Given [1,3],[2,6],[8,10],[15,18],
     return [1,6],[8,10],[15,18].

                sta     end
     |___|       |____|
       |_____|       |___|

     time : O(nlogn) space : O(n)

     * @param intervals
     * @return
     */
    static public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1)
            return intervals;
        /*
            Lambda語法: https://magiclen.org/java-8-lambda/
            輸入多個參數(省略型態) = (a, b)
            輸出單行回傳值 = a.start - b.start
            Lambda寫法: (a, b) -> a.start - b.start
            Java寫法:
            int func(int a, int b) {
                return (a.start - b.start)
            }
            目的: 作Comparator<? super T>
            @Override
                public int compare(Animal obj1, Animal obj2) {
                return obj1.size - obj22.size;
            }
            obj1 和 obj2 是要進行比較的對象。
            return    0: obj1 = obj2
                   +Val: obj1 > obj2
                   -Val: obj1 < obj2
        */
        // public static <T> void Collections.sort(List<T> list, Comparator<? super T> c)
        // 上面API會將list的內容一一比較, 至於比較方式是依照 c參數, 返回的 +Val, -Val, 0 三種情況, 作為排序sort
        // 為什麼用 "super"
        // 如果直接用Comparator <T> c作為參數，第二個參數是 Comparator <? super T> c , <? super T>意味這類型可以是T或者它的父類型。
        // 為什麼准許父類型。答案是：
        //    這種方法准許為所有的子類使用相同的比較器。Ex： https://www.jianshu.com/p/ebf037d54661
        Collections.sort(intervals, (a, b) -> a.start - b.start); // 寫法KEY: 把inputs[]裡的元素, 全依照該元素的start大小排列
        // inputs = [8,10],[15,18],[1,3],[2,6],
        // 經由Collections.sort()排列處理後
        // list   = [1,3],[2,6],[8,10],[15,18],
        int start = intervals.get(0).start;
        int   end = intervals.get(0).end;
        List<Interval> res = new ArrayList<>();

        for (Interval interval : intervals) { // LOOP: scan all
            //  Case0: 合併群
            //  s1 --- e1
            //  s2 --- e2         Ans= s1~e1
            //
            //  Case1: 合併群
            //  s1 --- e1
            //    s2 =-- e2       Ans= s1~e2
            //
            //  Case2: 合併群
            //  s1 --- e1
            //     s2 --- e2      Ans= s1~e2
            //
            //  Case3: 合併群
            //  s1 ----- e1
            //   s2 --- e2        Ans= s1~e1
            //  觀察上例,合併群的重點在於 s1與e2, 算法為 s1 <= e2 ,
            //
            //  Case4: 獨立群
            //  s1 --- e1
            //         s2 --- e2  Ans= s1~e1 , s2~e2

            if (interval.start <= end) { // 合併群 = Case0: Case1: Case2: Case3:
                end = Math.max(end, interval.end);
            } else { // 合併群 = Case4:
                res.add(new Interval(start, end)); // 獨立
                start = interval.start;
                end = interval.end;
            }
        }
        res.add(new Interval(start, end));
        return res;
    }
}
