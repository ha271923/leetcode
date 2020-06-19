package com.cspirat;

import com.utils.Out;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Edward on 28/07/2017.
 */
public class SubsetsII {
    public static void main(String[] args) {
        int[] numbers;
        numbers = new int[] { 1, 2, 1, 2 };
        List<List<Integer>> res = subsetsWithDup(numbers);
        Out.i("Output:");
        Out.println_ListList(res);
    }
    /**
     * 90. Subsets II
     * Given a collection of integers that might contain duplicates, nums, return all startsible subsets.

     Note: The solution set must 'not' contain duplicate subsets.


     For example,
     If nums = [1,2,2], a solution is:

     [
     [2],
     [1],
     [1,2,2],
     [2,2],
     [1,2],
     []
     ]

     test: [1,2,2]


     time : O(2^n);
     space : O(n);
     * @param nums
     * @return
     */

    /*
                   三個元素=[1,2,2]
               元素可以重複, 但組合後不可以重複, 即便順序不同
               Assemble       Answer
               1              1
               1 2            1 2
               1 2 2          1 2 2
               2              2
           dup 2 1      =>
           dup 2 1 2
               2 2            2 2
           dup 2 2 1
     */
    // Tips: 填過的元素要不要回頭拿來填
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return res;
        Arrays.sort(nums); // Sorted!!
        Out.i("sorted nums[]:");
        Out.print_IntArray(nums);
        backtrack(res, new ArrayList<>(), nums, 0);
        return res;
    }

    /*
              -> shift element, never come back
          elements= [ 1 , 2 , 2 ]         backtrack() {
   LOOP_0 iChar=1,2   |                     for i= 0,1,2...
                      1                        backtrack() {
                      |   |                      for i= 1,2...
                      1   2                        backtrack() {
                      |   |   |     ==>              for i= 2...
                      1   2   2
   LOOP_1 iChar=1,2       |
                          2                        }
                          |   |                }
                          2   2           }



                     without filter        with filter(ANSWER)
                     [1]                   [1]
                     [1, 2]                [1, 2]
                     [1, 2, 2]             [1, 2, 2]
                 dup [1, 2]           =>
                     [2]                   [2]
                     [2, 2]                [2, 2]
                 dup [2]


                                            iChar =  0  1  2  ( i是欲取出元素的索引)
                                      nums[iChar] ={ 0, 2, 2 }
            backtrack_a not enter loop yet                                                    listList
                backtrack_a0                    LOOP_A  startElementIdx=0  iChar=0  ADD '1'   [1]
                    backtrack_a0b1              LOOP_B  startElementIdx=1  iChar=1  ADD '2'   [1,2]
                        backtrack_a0b1c2        LOOP_C  startElementIdx=2  iChar=2  ADD '2'   [1,2,2]
                        backtrack_a0b1c2                startElementIdx=2  iChar=2  DEL '2'
                    backtrack_a0b1                      startElementIdx=1  iChar=1  DEL '2'
                    nextloop__a0b2              LOOP_B  startElementIdx=1  iChar=2  continue for remove duplicate
                backtrack_a0                            startElementIdx=0  iChar=0  DEL '1'
                backtrack_a1                    LOOP_A  startElementIdx=0  iChar=1  ADD '2'   [2]
                    backtrack_a1b2              LOOP_E  startElementIdx=2  iChar=2  ADD '2'   [2,2]
                    backtrack_a1b2                      startElementIdx=2  iChar=2  DEL '2'
                backtrack_a1                            startElementIdx=0  iChar=1  DEL '2'
                nextloop__a2                    LOOP_A  startElementIdx=0  iChar=2  continue for remove duplicate
            backtrack_a not exit loop yet
     */

    public static void backtrack(List<List<Integer>> listList, List<Integer> list, int[] elements, int startElementIdx) {
        if(list.size() != 0) // no empty
            listList.add(new ArrayList<>(list)); // 注意寫法! listList = all ANSWERs
        for (int iChar = startElementIdx; iChar < elements.length; iChar++) { // 注意結束掃描條件
            if (iChar != startElementIdx && elements[iChar] == elements[iChar - 1]) { // KEY: Filter list不同於listList, 因為Sorted, 所以只要檢查前一個元素是否相同即可
              Out.i("nextloop     startElementIdx="+startElementIdx+"  iChar="+iChar+"  continue");
              continue;
            }
            list.add(elements[iChar]); // elements[iChar]=one of number, list=one of answer
            Out.i("backtrack   startElementIdx="+startElementIdx+"  iChar="+iChar+"  ADD "+elements[iChar]);
            Out.print_List(list);
            backtrack(listList, list, elements, iChar + 1); // listList = all ANSWERs
            list.remove(list.size() - 1); // pop
            Out.i("backtrack   startElementIdx="+startElementIdx+"  iChar="+iChar+"  DEL "+elements[iChar]);
        }
    }



}
