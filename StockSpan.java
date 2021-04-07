/*
https://www.geeksforgeeks.org/the-stock-span-problem/
The stock span problem is a financial problem where we have a series of n daily price quotes for a stock and we need to calculate span of stock’s price for all n days.
The span Si of the stock’s price on a given day i is defined as the maximum number of consecutive days just before the given day, for which the price of the stock on the current day is less than or equal to its price on the given day.
For example, if an array of 7 days prices is given as {100, 80, 60, 70, 60, 75, 85}, then the span values for corresponding 7 days are {1, 1, 1, 2, 1, 4, 6}
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class StockSpan {

    public static class stackNode{
        public int val;
        public int count;
        public stackNode(int value)
        {
            val =value;
        }
    }

    public static void findStockSpan(int[] stocks, List<Integer> output)
    {
        Stack<stackNode> held = new Stack<stackNode>();

        for(int i=0;i<stocks.length;i++)
        {
            int val = stocks[i];
            stackNode cur = new stackNode(val);
            cur.count=1;

            if(held.isEmpty())
            {
                held.push(cur);
            }
            else
            {
                while(held.peek().val<=val)
                {
                    cur.count+=held.pop().count;
                    //held.pop();
                }
                held.push(cur);
            }
            output.add(cur.count);
        }
    }

    public static void main(String args[])
    {
        int[] stockPrice = {100, 80, 60, 70, 60, 75, 85};
        LinkedList<Integer> output = new LinkedList<>();
        findStockSpan(stockPrice, output);
        System.out.println(output);

    }

}
