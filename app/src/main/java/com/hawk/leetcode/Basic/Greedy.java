package com.hawk.leetcode.Basic;

import java.util.Scanner;

/**
 * 贪心算法其实本身就跟我们人性一样,看到眼前的好吃的,拿来拿来别客气.但是丝毫不顾忌自己还得燃烧卡路里.
 * 贪心算法也是这样.
 * 贪心算法的本质其实就是总是做出当前最好的选择,也就是说算法总是期望通过局部最优选择从而得到全局最优
 * 的解决方案.
 * 但是大家想想贪心算法其实是很”目光短浅”的,因为"仅仅根据当前眼下的信息"来做出决策,这样就不是从整体来
 * 最优考虑,他做出的选择只能是某种意义上的局部最优.但是,贪心算法可以得到很多问题的整体最优解或者近似
 * 解,在实际生活中还是有一定的意义的,因此贪心算法还是得到了广泛的应用.
 * */
public class Greedy {

    public static void main(String args[])
    {
        int[] arr;
        arr = input();

        arr = selectSort(arr);

        output(arr);
    }

    static int[] selectSort(int[] arr) {
        System.out.print("Selection Sort +++\n");
        int temp;
        for(int i=0; i<arr.length; i++)
        {
            for(int j=i+1; j<arr.length; j++)
            {
                if(arr[i] > arr[j]) // Greedy
                {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.print("Selection Sort ---\n");
        return arr;
    }

    static int[] input() {
        int size, i;
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter Array Size : ");
        size = scan.nextInt();
        int arr[] = new int[size];

        System.out.print("Enter Array Elements(space) : ");
        for( i=0; i<size; i++)
        {
            arr[i] = scan.nextInt();
        }
        System.out.print("Input:\n");
        for( i=0; i<arr.length; i++)
        {
            System.out.print(arr[i]+ "  ");
        }
        System.out.println();
        return arr;
    }

    static void output(int[] arr) {
        System.out.print("Output:\n");
        for(int i=0; i<arr.length; i++)
        {
            System.out.print(arr[i]+ "  ");
        }

        System.out.println();
    }

}
