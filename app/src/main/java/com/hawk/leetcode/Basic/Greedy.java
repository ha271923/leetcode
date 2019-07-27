package com.hawk.leetcode.Basic;

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

    public static void main(String[] args) {
        int sum;
        greedy(1);
        System.out.println();
    }

    static int greedy(int a){
        if(a<100) {
            System.out.print(a);
            System.out.print(',');
            return greedy(++a);
        }
        else
            return a;
    }

}
