package com.hawk.leetcode.Basic;

/**
* 本代码运用优先队列式分支限界法解决了最小重量机器设计问题。
* 算法思路：对于在某一个供应商是否购买某一零件，可以将这个过程抽象化为子集树模型。
* 该树的第i层则代表第i个零件的购买情况，每个商家j对应一棵子树。从根节点开始，对于当前讨论的
* 节点我们将之当作扩展节点，遍历该扩展节点的所有子节点，将其中符合条件的子节点全部插入优先
* 队列中（判断条件运用剪枝函数，下面讨论）。当遍历完后，该节点成为死节点，从优先队列中取出
* 在当前情况下重量最小的节点继续向下进行迭代，直到到达某叶子节点后，记录下当前情况下的最小
* 重量，然后继续将优先队列中的活节点依次出队，直到优先队列为空，整个子集树只剩下一条最优路径。
*
* 剪枝函数：在对某个节点是否符合条件的判断中，采用了约束函数和限界函数的双重判断。
* 约束函数：对于该节点到最终叶子节点中所有节点价格的最小值的和超过了要求的节点，直接
* 置为死节点，不再放入优先队列，并且其子节点都不必再进行讨论。
* 限界函数：对于该节点到最终叶子节点中所有节点重量最小值的和已经小于了目前已经计算出的重量的最小值。
* 则直接置为死节点，不放入优先队列，并且其子节点都不必再进行讨论。
* */

/**
 * 设某一机器由n个部件组成，每一个部件都可以从m个不同的供应商处购得。设wij是从供应商j处购得的部件i的重量，cij 是相应的价格。试设计一个算法，给出总价格不超过c的最小重量机器设计。
 *
 * 算法设计：对于给定的机器部件重量和机器部件价格，计算总价格不超过d的最小重量机器设计。
 *
 * 数据输入：有文件input.txt给出输入数据。第1行有3个正整数n，m和d。接下来的2n行，每行n个数。前n行是c，后n行是w。
 */

import android.content.res.Resources;
import android.util.Log;

import com.hawk.leetcode.R;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


public class BranchAndBound6 {

    //定义节点类，分别存储该节点的层数level，该节点的父节点，从根节点到该节点的价值总和，重量总和
//该节点的商家序号。覆盖compareTo方法，优先按照重量对节点进行比较。
    class Node implements Comparable<Node>{
        int value = 0;
        int weight = 0;
        int level = 0;
        int source = 0;
        Node father = null;
        public int compareTo(Node d){
            if(this.weight<d.weight)
                return -1;
            else if(this.weight==d.weight)
                return(this.value-d.value);
            else
                return 1;
        }
    }

    //以下讨论中，商品的重量存储在w[][]中，价格存储在c[][]中。角标全部从1开始。
    //共有n个零件需要购买，同一个零件共有m个商家在销售，消费的上限为d(maxValue)。
    // Q:在d的經費下, 能買到的最輕getMinWeight 的n種商品組合, 使得
    //   (c1+...cn)<d, (w1+...wn)=是最低重量
    public void getMinWeight(int[][] c,int[][] w,int m,int n,int d){
//		int minValue = Integer.MAX_VALUE;
        int minWeight = Integer.MAX_VALUE;
        //定义购买的路径数组。
        int[] way = new int[n+1];
        //定义优先队列。
        PriorityQueue<Node> heap = new PriorityQueue<Node>();
        //初始化一个全为0的节点，放入优先队列，开始循环。
        Node initial = new Node();
        heap.add(initial);
        //只要优先队列非空，循环就继续下去，依次继续取出优先队列中的节点。
        while(!heap.isEmpty()){
            Node fatherNode = heap.poll();
            //当取出的节点已经到达叶子节点时，如果所找到的这条路径的零件总重量小于之前的重量，则更新
            //购买路径和当前已发现的最小重量。
            if(fatherNode.level == n){
                if(fatherNode.weight < minWeight){
                    minWeight = fatherNode.weight;
//					minValue = fatherNode.value;
                    for(int i=n;i>=1;i--){
                        way[i] = fatherNode.source;
                        fatherNode = fatherNode.father;
                    }
                }
            }
            //否则就对于取出的节点先进行剪枝判断。
            else{
                int min_weight = fatherNode.weight;
                int min_value = fatherNode.value;
                //遍历该节点到叶子节点后面的最优路径的所有节点。
                for(int i = fatherNode.level+1;i<n+1;i++){
                    int temp_min_value = Integer.MAX_VALUE;
                    int temp_min_weight = Integer.MAX_VALUE;
                    //选取下面每一层中的重量最小的节点和价值最小的节点。
                    for(int j=1;j<m+1;j++){
                        if(c[i][j]<temp_min_value)
                            temp_min_value = c[i][j];
                        if(w[i][j]<temp_min_weight)
                            temp_min_weight = c[i][j];
                    }
                    //将能够获取到的理想最优值取出，看是否符合限界函数和约束函数。
                    //注意：此时的最优值不一定能同时取到，因为重量最优的点和价值最优的点不一定是同一个节点。
                    min_weight += temp_min_weight;
                    min_value += temp_min_value;
                }
                //对于宽约束都不能满足的节点，直接置为死节点，不再讨论其剩余节点。
                if(min_weight > minWeight || min_value > d){
                    continue;
                }
                //剩下的能够符合要求的所有子节点全部放入到优先队列中去。
                for(int i=1;i<m+1;i++){
                    if(fatherNode.value+c[fatherNode.level+1][i] <=d &&
                            fatherNode.weight+w[fatherNode.level+1][i]<minWeight){
                        Node newNode = new Node();
                        newNode.father = fatherNode;
                        newNode.level = fatherNode.level+1;
                        newNode.source = i;
                        newNode.value = fatherNode.value+c[fatherNode.level+1][i];
                        newNode.weight = fatherNode.weight+w[fatherNode.level+1][i];
                        heap.add(newNode);
                    }
                }
            }
        }
        //输出能够取得的最小重量和购买路径。
        System.out.println("minWeight="+minWeight+"");
        for(int i = 1;i<n+1;i++)
            System.out.print("w["+i+"]["+way[i]+"]\n");
    }

    //主函数用于对各项数值进行初始化。从E盘input.txt文件下读取初始数值。
    // 商品的重量存储在w[][]中，价格存储在c[][]中。角标全部从1开始。
    // 共有n个零件需要购买，同一个零件共有m个商家在销售，消费的上限为d。
    // Q:在d的經費下, 能買到的最輕getMinWeight 的n種商品組合, 使得
    //   (c1+...cn)<d, (w1+...wn)=是最低重量
    public static void main(String[] args) throws IOException{
        int m,n,d;
        // get params from input.txt file
        File initialFile = new File("app/src/main/res/raw/input.txt");
        String absolutePath = initialFile.getAbsolutePath();
        InputStream inputStream = new FileInputStream(initialFile);
        BufferedReader buf = new BufferedReader(new InputStreamReader(inputStream));
        String[] str = buf.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);
        d = Integer.parseInt(str[2]);
        int[][] c = new int[n+1][m+1];
        int[][] w = new int[n+1][m+1];
        for(int i=1;i<n+1;i++){
            str = buf.readLine().split(" ");
            for(int j=1;j<m+1;j++)
                c[i][j] = Integer.parseInt(str[j-1]);
        }
        for(int i=1;i<n+1;i++){
            str = buf.readLine().split(" ");
            for(int j=1;j<m+1;j++)
                w[i][j] = Integer.parseInt(str[j-1]);
        }
        //调用该函数得到结果。
        new BranchAndBound6().getMinWeight(c,w,m,n,d);
        /*
         n零件=2 ,m廠商=3 ,d預算=8
        n1=c[1][1]=1  ,c[1][2]=2  ,c[1][3]=3   , c[1][0]=0
        n2=c[2][1]=6  ,c[2][2]=5  ,c[2][3]=4   , c[2][0]=0
        n1=w[1][1]=31 ,w[1][2]=22 ,w[1][3]=13  , w[1][0]=0
        n2=w[2][1]=41 ,w[2][2]=52 ,w[2][3]=63  , w[2][0]=0
         */
        //用于测试输入数据。
        //for(int k : w[1])
        //	System.out.println(k);
        buf.close();
    }

}