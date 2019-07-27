package com.cspirat;

/**
 * Created by Edward on 28/07/2017.
 */
public class BestTimetoBuyandSellStock {
    /**
     * 121. Best Time to Buy and Sell Stock
     * Say you have an array for which the ith element is the price of a given stock on day i.

     If you were only permitted to complete at most one transaction (ie,
     buy one and sell one share of the stock), design an algorithm to find the maximum profit.
     Input: [7, 1, 5, 3, 6, 4]
     Output: 5

     max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)

     time : O(n);
     space : O(1);

     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        int min = prices[0];
        int profit = 0;
        for (int price : prices) {
            min = Math.min(min, price);
            profit = Math.max(profit, price - min);
        }
        return profit;
    }

    // https://www.youtube.com/watch?v=KlgkDjwG6no
    public static int maxProfit_BruteForce(int[] prices){
        if (prices.length < 2 ) return 0;
        int maxProfit = 0;

        for (int i=0; i< prices.length; ++i)
            for (int j = i+1; i< prices.length; ++i)
                maxProfit = Math.max(maxProfit, prices[j] - prices[i]);

        return maxProfit;
    }

    public static int maxProfit_OnePass(int[] prices){
        if (prices.length < 2 ) return 0;
        int maxProfit = 0, buy =prices[0];

        for (int i=1; i< prices.length; ++i)
            if(prices[i] < buy)
                buy = prices[i];
            else
                maxProfit = Math.max(maxProfit, prices[i] - buy);

        return maxProfit;
    }
}
