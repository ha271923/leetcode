package com.hawk.leetcode.Basic.Knapsack;
/**
 * http://www.or.deis.unibo.it/knapsack.html
 * http://www2.lssh.tp.edu.tw/~hlf/class-1/lang-c/DP.pdf
 * 背包問題九講
 * 第一講 01 背包問題 這是最基本的背包問題，每個物品可以選擇放 或不放一次。
 * 第二講 完全背包問題 第二個基本的背包問題模型，每種物品可以放無限多次。
 * 第三講 多重背包問題 每種物品有一個固定的次數上限。
 * 第四講 混合三種背包問題 將前面三種簡單的問題疊加成較複雜的問題。
 *        即有的物品只可以取 一次（01 背包），有的物品可以取無限次（完全背包），有的物品可 以取的次數有一個上限（多重背包）。
 * 第五講 二維費用的背包問題 一個簡單的常見擴展。
 * 第六講 分組的背包問題 一種題目類型，也是一個有用的模型。後兩節的基礎。
 *       些物品被劃分為若干組，每組中的物品互相衝突，最多選 一件。
 * 第七講 有依賴的背包問題 另一種給物品的選取加上限制的方法。
 *        的物品間存在某種「依賴」的關係。也就是說，i 依 賴於 j，表示若選物品 i，則必須選物品 j。
 * 第八講 泛化物品 我自己關於背包問題的思考成果，有一點抽象。
 *       考慮這樣一種物品，它並沒有固定的費用和價值，而是它的價值隨 著你分配給它的費用而變化。這就是泛化物品的概念。
 *       這個定義有一點點抽象，另一種理解是一個泛化物品就是一個數組 h[0..V]，給它費用 v，可得到價值 h[V]。
 * 第九講 背包問題問法的變化 試圖觸類旁通、舉一反三。
 *
 * http://www.csie.ntnu.edu.tw/~u91029/KnapsackProblem.html#3
 *      Knapsack Problem 背包問題:
 *      Fractional Knapsack Problem 分數背包問題。一個物品可以切下一部分、只取幾分之幾放進背包。
 *      0/1 Knapsack Problem   0/1背包問題
 *      Unbounded Knapsack Problem  無限背包問題
 *      Bounded Knapsack Problem  有限背包問題
 *      Money Changing Problem  換錢問題
 *      Change-Making Problem   找錢問題
 *      Partition Problem  分區問題
 */
public class Knapsack {
}
