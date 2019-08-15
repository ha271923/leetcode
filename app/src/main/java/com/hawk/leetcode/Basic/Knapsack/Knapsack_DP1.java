package com.hawk.leetcode.Basic.Knapsack;

/**
 *
 * http://www2.lssh.tp.edu.tw/~hlf/class-1/lang-c/DP.pdf
 * 背包問題九講
 * 第一講   01背包問題 這是最基本的背包問題，每個物品可以選擇放 或不放一次。
 *
 0/1 Knapsack Problem
 「 0/1 」的意思是：每種物品只會放進背包零個或一個。一個物品要嘛整個不放進背包、要嘛整個放進背包。物品無法切割。
 大家看到這個問題，第一個直覺通常是貪心法：
 優先挑選價值最高的物品。然而，價值高的物品，放入背包之後，有可能留下很大的空隙，浪費背包耐重量；反而是狂塞
 一些零零碎碎的不值錢東西，才能獲得最多的利益。
 聰明的人會想：
 優先挑選價值與重量比值最大的物品。不過這個方法也有問題，仍然有可能出現方才提到的現象。你能舉例嗎？這有助於
 了解 0/1 背包問題的關鍵點。
 0/1 背包問題的關鍵點:
 在於如何有效利用背包的剩餘重量，找出最好的物品組合方式。
 0/1 背包問題是經典的 NP-complete 問題，數值範圍大時無法快速求得精確解，只能折衷求得近似解。
 然而，當數值範圍不大時，得以用動態規劃快速求得精確解。

 http://www.csie.ntnu.edu.tw/~u91029/KnapsackProblem.html#3
 本篇文章打算藉由 0/1 背包問題的各種細節，介紹動態規劃的各種技巧。

 一件物品不放進背包，背包價值不變、背包耐重不變；一件物品放進背包，背包價值上升、背包耐重下降。遞迴公式為：
 c(n, w) = max( c(n-1, w), c(n-1, w-weight[n]) + cost[n] )

 */

public class Knapsack_DP1 {
    public static void main(String args[]) {
        //                        A是CP值最高, 60/10=6(元/公斤), 答案卻是 B+C
        // object=                A,   B,   C
        int val[]  = new int[] { 60, 100, 120 };
        int wt[]   = new int[] { 10,  20,  30 };
        // int val[]  = new int[] { 60, 100, 120, 300, 1000 };
        // int wt[]   = new int[] { 10,  20,  30, 49, 50 };
        int W = 50; // capacity of weight
        int n = val.length; // number of object

        System.out.println(knapSack(W, wt, val, n));
    }

    static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    /**
     *     A. 物件數量少時, 分支單純
     *              0          0      <W
     *            / | \        A      <W
     *           /  |  \       B      <W
     *          A   B   C      C      <W
     *        / |    \         A+B    <W
     *       B  C  A C  B  A   A+C    <W
     *               ^         B+C    <W
     *                         A+B+C  <W
     *    B. 物件數量多時, 分支複雜度+50
     *           W= 50
     *       wt[i]=             開始滿足wt[0]>=10kg             開始滿足wt[1]>=20kg                      開始滿足wt[2]>=30kg
     *                                    |                              |                                       |
     *           w=  0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19  20  21  22  23  24  25  26  27  28  29  30  31  32  33  34  35  36  37  38  39  40  41  42  43  44  45  46  47  48  49  50(kg)
     *              -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     *        i= 0 | 0 0 0 0 0 0 0 0 0 0  0  0  0  0  0  0  0  0  0  0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0
     * val[0]=A= 1 | 0 0 0 0 0 0 0 0 0 0 60 60 60 60 60 60 60 60 60 60  60  60  60  60  60  60  60  60  60  60  60  60  60  60  60  60  60  60  60  60  60  60  60  60  60  60  60  60  60  60  60
     * val[1]=B= 2 | 0 0 0 0 0 0 0 0 0 0 60 60 60 60 60 60 60 60 60 60 100 100 100 100 100 100 100 100 100 100 160 160 160 160 160 160 160 160 160 160 160 160 160 160 160 160 160 160 160 160 160
     * val[2]=C= 3 | 0 0 0 0 0 0 0 0 0 0 60 60 60 60 60 60 60 60 60 60 100 100 100 100 100 100 100 100 100 100 160 160 160 160 160 160 160 160 160 160 180 180 180 180 180 180 180 180 180 180 220
     *   n= 3(item)
     */
    static int knapSack(int W, int[] wt, int[] val, int n) {
        int dp[][] = new int[n + 1][W + 1];  // 考量不放入的情況, 所以XY增加空行列
        // 初始化空行列
        for (int i = 0; i < n + 1; i++)
            dp[i][0] = 0;
        for (int w = 0; w < W + 1; w++)
            dp[0][w] = 0;
        // Build table dp[][] in bottom up manner
        for (int i = 1; i < n + 1; i++) { // 列舉所有物件,本例是3個物件, 但可以增加更多
            for (int w = 1; w < W + 1; w++) { // 慢慢增加背包容量直至上限W=50
                if (w >= wt[i - 1]) { // 容量w加大到可以放下所指i物件==> wt[i-1]
                    //当物品为i件重量为w时，如果容量w加大到到可以放下第i件的重量(wt[i-1])时，dp[i][w]为下列两种情况之一：
                    //  (1)物品i放入背包中，则背包剩余重量为w-wt[i-1],所以dp[i][w]为dp[i-1][]的值加上当前物品i的价值
                    //     KEY: dp[i - 1][w - wt[i - 1]]是當前紀錄時光回溯的算法, 一次退回(w-wt[i-1])到可以容納當前慾放入新物件wt[i-1]的可用空間的紀錄點
                    //     (當初是w+wt[i-1]一直維持到w++遞增到可以容納下去下個物件的重量, 所以退回去的算法是dp[i - 1][w - wt[i - 1]])
                    //     退到當前最佳紀錄前的前一個Node或Checkpoint, 為了算出換成此物是否比較划算
                    // dp[i][w] = max(selectObjVal + dp[i - 1][w - wt[i - 1]], dp[i - 1][w]); // KEY:
                    //  若維持目前的紀錄 < (退到可以放下新物件的紀錄前的舊紀錄+改選新物件=產生的新記錄)
                    if (dp[i - 1][w] < (dp[i - 1][w - wt[i - 1]] + val[i - 1]))
                        dp[i    ][w] =  dp[i - 1][w - wt[i - 1]] + val[i - 1]; // 更新最新紀錄!
                    else  //  (2)物品i不放入背包中，所以dp[i][w]为dp[i-1][w]的值
                        dp[i][w] = dp[i - 1][w]; // 不放, 目前記錄比較好, 不要退一步把目前紀錄的最後物件換掉, 因為紀錄會變差
                } else // 放不下, 依然維持之前紀錄
                    dp[i][w] = dp[i - 1][w];
            }
        }

        printResult(W, dp, n);

        return dp[n][W];
    }

    static void printResult(int W, int[][] dp, int n) {
        // Result
        for (int w = 0; w<= W; w++)
            System.out.print(w + " ");
        System.out.println();
        for (int i = 0; i<= n; i++) {
            for (int w = 0; w<= W; w++) {
                System.out.print(dp[i][w] + " ");
            }
            System.out.println();
        }
    }

}