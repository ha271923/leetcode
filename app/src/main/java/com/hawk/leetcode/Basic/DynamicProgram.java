package com.hawk.leetcode.Basic;

/**
 * 6. 動態規劃(dynamic programming)演算法: 多阶段决策过程(multistage decision process)。
 *  態規劃不是某個固定的演算法，而是一種策略
 *  動態規劃的應用最普遍就是最佳化問題~
 *  動態規劃(dynamic programming)演算法籍由將原問題分解成一系列子問題 (subproblems)，並依
 *  序解決子問題來解決原問題。
 *  DP有一個特點，是當原問題題算好之後，其實也一併將所有出現過的子問題都算好了，其答案都可直接從
 *  表格存取。此後當重複提問類似問題時，若提問到的是這些子問題，就可直接從表格取得答案，不需再計算。
 *  為避免一再地解重複的子問題，一旦解出子問題的解答(solution)，即會將其存在表格(或陣列)中。
 *  當需要用到某一子問題的解答時，與其重新計算其解答，演算法會從表格中直接取出其解答以節省計算
 *  時間，是一個「以空間換取時間」的演算法。
 *  一個動態規劃演算法會先從最簡單的子問題先解起，並以一定的程序持續運行直至 求出原問題解答為止。
 *  動態規劃的解題核心主要分為兩步：
 *    第一步：狀態的定義 => 將原問題轉化成另一種數學表述方式的過程
 *    第二步：狀態轉移方程的定義 => 如何能夠用前一項或者前幾項的資訊得到下一項，這種從最優子狀態
 *           轉換為下一個最優狀態的思路
 *    https://codertw.com/%E7%A8%8B%E5%BC%8F%E8%AA%9E%E8%A8%80/78112/
 *
 *
 *  最佳解原則(Principle of optimality): 假設為了解決一個問題，我們必須作出一系列的決策 D1,
 *  D2, …, Dn。若這一系列的決策是最佳解，則針對於前n-k個(或最後n-k個)決策所產生的狀態(子問題)
 *  而言，最後的k個(或前k個)決策(1<= k<=n)必定也是最佳的。
 *   6.1 最長共同子序列(longest common subsequence, LCS or LCSS)演算法
 *   6.2 0/1背包動態規劃演算法(0/1 knapsack dynamic programming algorithm)
 *   6.3 Bellman-Ford最短路徑演算法
 *   6.4 Floyd-Warshall最短路徑演算法
 *
 *  Dynamic Programming
 *  ex: Generate parentheses
 *
 *  Problems:
 *  https://www.zybuluo.com/Yano/note/253649
 *    Best Time to Buy and Sell Stock
 *    Climbing Stairs
 *    Decode Ways
 *    House Robber
 *    House Robber II
 *    Maximal Square
 *    Maximum Product Subarray
 *    Maximum Subarray
 *    Minimum Path Sum
 *    Perfect Squares
 *    Triangle
 *    Ugly Number II
 *    Unique Binary Search Trees
 *    Unique Binary Search Trees II
 *    Unique Paths
 *    Unique Paths II

 * 搜索還是 DP?
 *      在看到一道背包問題時，應該用搜索還是動態規劃呢？
 *      首先，可以從數據範圍中得到命題人意圖的線索。如果一個背包問 題可以
 *      用 DP 解(N 件物品和一個容量為 V 的背包)，V 一定不能很大，否則
 *      O(VN)的算法無法承受，而一 般的搜索解法都是僅與 N 有關，與 V 無關
 *      的。所以，V 很大時（例如 上百萬），命題人的意圖就應該是考察搜索。
 *      另一方面，N 較大時（例 如上百），命題人的意圖就很有可能是考察動態
 *      規劃了。
 *      另外，當想不出合適的動態規划算法時，就只能用搜索了。例如看 到一個
 *      從未見過的背包中物品的限制條件，無法想出 DP 的方程，只好 寫搜索以
 *      謀求一定的分數了
 *
 * http://www.voidcn.com/article/p-terbcauw-ye.html
 * 用Dynamic Programming設計演算法時的步驟
 *   1. 利用Divide and Conquer把原問題遞迴地分成許多更小的問題。（recurrence）
 *   	甲、子問題與原問題的求解方式皆類似。（optimal sub-structure）
 *   	乙、子問題會一而再、再而三的出現。（overlapping sub-problems）
 *   2. 確認每個問題需要哪些子問題來計算答案，並確認總共有哪些子問題。（state space）
 *   3. 決定各個問題的計算先後次序。（computational sequence）
 *   4. 安排好各個問題的答案，要存放在表格的哪個位置。（lookup table）
 *   5. 實做程式，主要有兩種方式：
 *   	甲、Top-down, Recursive.
 *   	乙、Bottom-up, Iterative
 * */
public class DynamicProgram {

    public static void main(String[] args) {

    }

}
