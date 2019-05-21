package com.hawk.leetcode.Exams;
import android.util.Log;

import com.hawk.leetcode.BaseClass;

import java.util.HashMap;
import java.util.Map;

import static com.hawk.leetcode.Global.TAG;

public class TwoSum extends BaseClass {
    public static int   target = 9;
    public static int[] input = {2,15,7,19};
    int[] output;

    public TwoSum() {

    }

    @Override
    public Object test() {
        output = twoSum(input, target);
        System.out.println("output index = "+ output[0]+" , " +output[1] );
        return output;
    }

    @Override
    public Object result() {
        System.out.println("output index = "+ output[0]+" , " +output[1] );
        return output;
    }

/*
    @Override
    public Object test(Object... objs) {
        System.out.println("input = "+ objs[0]+" , target = " + objs[1] );
        int[] output = twoSum((int[]) objs[0], (int)objs[1]);
        System.out.println("output index = [ "+ output[0]+" , " +output[1] +" ]");
        return output;
    }
*/

    //  Map
    //  KEY | VALUE (because ANSWER required index, not value.)
    // --------------
    // 差值A |  0
    // 差值B |  1
    // 差值C |  2

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i]; // 算出缺少的數值
            if (map.containsKey(complement)) { // 利用map的優點O(1), 來找出map裡面是否有這個缺少的數值
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i); // 沒找到, 把差值表新增, 讓下一輪的查找有依據
        }
        throw new IllegalArgumentException("No two sum solution");
    }


    // Hawk: my algorithm
    public int[] My_twoSum(int[] nums, int target) {
        for (int i=0; i<nums.length; i++) {
            for ( int j=0; j<nums.length; j++) {
                if(i==j)
                    break;
                int sum = nums[i]+nums[j];
                if(sum == target)
                    return new int[] {i, j};
            }
        }
        return null;
    }


}