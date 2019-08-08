package com.hawk.leetcode.Basic;

// https://blog.csdn.net/ljmingcom304/article/details/50324007

import com.hawk.leetcode.Basic.data.Knapsack;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * 一个旅行者有一个最多能装m公斤的背包，现在有n中物品，每件的重量分别
 * 是W1、W2、……、Wn，每件物品的价值分别为C1、C2、……、Cn， 需要将物
 * 品放入背包中，要怎么样放才能保证背包中物品的总价值最大？
 *
 */
public class BranchAndBound7 {
    public static void main(String[] args) {
        Knapsack[] bags = new Knapsack[] {
            new Knapsack(2, 13),
            new Knapsack(1, 10),
            new Knapsack(3, 24),
            new Knapsack(2, 15),
            new Knapsack(4, 28),
            new Knapsack(5, 33),
            new Knapsack(3, 20),
            new Knapsack(1, 8)
        };

        BranchAndBound7 problem = new BranchAndBound7(bags);
        problem.solve();
        System.out.println(problem.getBestValue());
    }

    // 准备放入背包中的物品
    private Knapsack[] bags;
    // 背包的总承重m
    private int maxWeight = 12;
    // 给定的物品数n
    private int n;
    // 物品放入背包可以获得的最大价值
    private int bestValue;

    public BranchAndBound7(Knapsack[] bags) {
        super();
        this.bags = bags;
        this.n = bags.length;
        // 物品依据单位重量价值进行排序
        Arrays.sort(bags, Collections.reverseOrder());
    }

    // 队列式分支限界法
    public void solve() {
        LinkedList<Node> nodeList = new LinkedList<Node>();

        // 起始节点当前重量和当期价值均为0
        nodeList.add(new Node(0, 0, 0));

        while (!nodeList.isEmpty()) {
            // 取出放入队列中的第一个节点
            Node node = nodeList.pop();

            if (node.upboundValue >= bestValue && node.index < n) {
                // 左节点：该节点代表物品放入背包中，上个节点的价值+本次物品的价值为当前价值
                int leftWeight = node.currWeight + bags[node.index].getWeight();
                int leftValue = node.currValue + bags[node.index].getValue();
                Node left = new Node(leftWeight, leftValue, node.index + 1);

                // 放入当前物品后可以获得的价值上限
                left.upboundValue = getUpboundValue(left);

                // 当物品放入背包中左节点的判断条件为保证不超过背包的总承重
                if (left.currWeight <= maxWeight
                        && left.upboundValue > bestValue) {
                    // 将左节点添加到队列中
                    nodeList.add(left);
                    if (left.currValue > bestValue) {
                        // 物品放入背包不超重，且当前价值更大，则当前价值为最大价值
                        bestValue = left.currValue;
                    }
                }

                // 右节点：该节点表示物品不放入背包中，上个节点的价值为当前价值
                Node right = new Node(node.currWeight, node.currValue,node.index + 1);

                // 不放入当前物品后可以获得的价值上限
                right.upboundValue = getUpboundValue(right);

                if (right.upboundValue >= bestValue) {
                    // 将右节点添加到队列中
                    nodeList.add(right);
                }
            }
        }
    }

    // 当前操作的节点，放入物品或不放入物品
    class Node {
        private int currWeight; // 当前放入物品的重量
        private int currValue; // 当前放入物品的价值
        private int upboundValue; // 不放入当前物品可能得到的价值上限
        private int index; // 当前操作的索引

        public Node(int currWeight, int currValue, int index) {
            this.currWeight = currWeight;
            this.currValue = currValue;
            this.index = index;
        }
    }

    // 价值上限=节点现有价值+背包剩余容量*剩余物品的最大单位重量价值
    // 当物品由单位重量的价值从大到小排列时，计算出的价值上限大于所有物品的总重量，否则小于物品的总重量
    // 当放入背包的物品越来越来越多时，价值上限也越来越接近物品的真实总价值
    private int getUpboundValue(Node n) {

        // 获取背包剩余容量
        int surplusWeight = maxWeight - n.currWeight;
        int value = n.currValue;
        int i = n.index;

        while (i < this.n && bags[i].getWeight() <= surplusWeight) {
            surplusWeight -= bags[i].getWeight();
            value += bags[i].getValue();
            i++;
        }

        // 当物品超重无法放入背包中时，可以通过背包剩余容量*下个物品单位重量的价值计算出物品的价值上限
        if (i < this.n) {
            value += bags[i].getUnitValue() * surplusWeight;
        }

        return value;
    }

    public int getBestValue() {
        return bestValue;
    }

}
