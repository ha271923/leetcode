package com.hawk.leetcode.Basic;

import java.util.Collections;
import java.util.LinkedList;
/**
 * 0-1背包问题
 * @author Lican
 *
 */

public class BranchAndBound5 {

    public static void main(String[] args) {
        double MAX_CAPACITY=8; // (kg)
        double pp[]={0,2,1,4,3}; // price(USD)
        double ww[]={0,1,4,2,3}; // weight(kg)
        int n=pp.length-1;
        int[] xx=new int[n+1];
        BranchAndBound5 b=new BranchAndBound5();
        double maxp=b.knapsack(pp, ww, MAX_CAPACITY, xx);
        System.out.println("可装入背包最大重量： "+MAX_CAPACITY+"(kg)");
        System.out.println("装入的物品的序号为：");
        for(int i=1;i<=n;i++){
            System.out.println("item "+i+"("+ww[i]+"kg)"+" : "+xx[i] +" * "+ pp[i] +"(USD)");
        }
        System.out.println("装入背包中物品总价值最大为： "+maxp+"(USD)");
    }

    /**
     * 子集空间树中结点类型为BBnode
     * @author Lican
     *
     */
    class BBnode{
        BBnode parent;//父节点
        boolean leftChild;//左儿子结点标志

        public BBnode(BBnode par,boolean left){
            parent=par;
            leftChild=left;
        }
    }



    /**
     * 优先级队列的结点
     * @author Lican
     *
     */
    class HeapNode implements Comparable{
        BBnode liveNode;//活结点
        double upperProfit;//结点的价值上界
        double profit;//结点所相应的价值
        double weight;//结点所相应的重量
        int level;//活结点在子集树种所处的层序号

        //构造方法
        HeapNode(BBnode node,double up,double pp,double ww,int lev){
            liveNode=node;
            upperProfit=up;
            profit=pp;
            weight=ww;
            level=lev;
        }

        @Override
        public int compareTo(Object x) {//降序排列
            double xs=((HeapNode) x).upperProfit;
            if(upperProfit>xs) return -1;
            if(upperProfit==xs) return 0;
            return 1;
        }
    }

    /**
     * 每个背包的类
     * @author Lican
     *
     */
    class Element implements Comparable{
        int id;//背包编号
        double d;//单位重量价值

        public Element(int id,double d){
            this.id=id;
            this.d=d;
        }

        @Override
        public int compareTo(Object o) {//降序排列
            double xs=((Element) o).d;
            if(d>xs) return -1;
            if(d==xs) return 0;
            return 1;
        }
        public boolean equals(Object o){
            return d==((Element) o).d;
        }
    }

    public double c;//背包容量
    public int n;//物品总数
    public double[] w;//物品重量数组
    public double[] p;//物品价值数组
    public double cw;//当前重量
    public double cp;//当前价值
    public int[] bestx;//最优解
    public LinkedList<HeapNode> heap;//活结点优先队列

    //上界函数bound计算结点所相应价值的上界
    public double bound(int i){
        double cleft=c-cw;//剩余容量
        double b=cp;
        //以物品单位重量价值递减顺序装填剩余容量
        while(i<=n&&w[i]<=cleft){
            cleft-=w[i];
            b+=p[i];
            i++;
        }
        if(i<=n)
            b+=p[i]*cleft/w[i];
        return b;
    }

    public void addLiveNode(double up,double pp,double ww,int lev,BBnode par,boolean ch){
        BBnode b=new BBnode(par,ch);
        HeapNode node=new HeapNode(b,up,pp,ww,lev);
        heap.add(node);
        Collections.sort(heap);
    }
    /**
     * 优先队列式分支限界法，返回最大价值，bestx返回最优解
     * @return
     */
    public double bbKnapsack(){
        //初始化
        BBnode enode=null;
        int i=1;
        double bestp=0.0;//当前最优解
        double up=bound(1);//价值上界

        //搜索子集空间树
        while(i!=n+1){
            //非叶节点
            //检查当前扩展结点的左儿子结点
            double wt=cw+w[i];
            if(wt<=c){//左儿子结点可行
                if(cp+p[i]>bestp){
                    bestp=cp+p[i];
                }
                addLiveNode(up,cp+p[i],cw+w[i],i+1,enode,true);
            }

            up=bound(i+1);
            //检查当前扩展结点的有儿子结点
            if(up>=bestp){//右子树可能含有最优解
                addLiveNode(up,cp,cw,i+1,enode,false);
            }

            HeapNode node=heap.poll();
            enode=node.liveNode;
            cw=node.weight;
            cp=node.profit;
            up=node.upperProfit;
            i=node.level;
        }

        //构造当前最优解
        for(int j=n;j>0;j--){
            bestx[j]=(enode.leftChild)?1:0;
            enode=enode.parent;
        }
        return cp;
    }

    /**
     * 返回最大价值
     * @param pp
     * @param ww
     * @param cc
     * @param xx
     * @return
     */
    public double knapsack(double[] pp,double[] ww,double cc,int[] xx){
        c=cc;
        n=pp.length-1;
        double ps=0;//统计所有背包的价值总量
        double ws=0;//统计所有的背包重量之和
        Element[] q=new Element[n];
        for(int i=1;i<=n;i++){
            q[i-1]=new Element(i,pp[i]/ww[i]);
            ps+=pp[i];
            ws+=ww[i];
        }
        if(ws<=c){//所有物品之和<=最大容量C,即可全部物品装包
            for(int i=1;i<=n;i++){
                xx[i]=1;
            }
            return ps;
        }

        //依物品单位重量价值排序
        java.util.Arrays.sort(q);

        //初始化数据成员
        p=new double[n+1];
        w=new double[n+1];

        for(int i=1;i<=n;i++){
            p[i]=pp[q[i-1].id];
            w[i]=ww[q[i-1].id];
        }

        cw=0;
        cp=0;
        bestx=new int[n+1];

        heap=new LinkedList<HeapNode>();

        //调用bbKnapsack求问题的最优解
        double maxp=bbKnapsack();
        for(int i=1;i<=n;i++){
            xx[q[i-1].id]=bestx[i];
        }
        return maxp;
    }


}