import com.sun.jmx.remote.internal.ArrayQueue;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

enum RouletteBidType {
    EVEN,
    ODD,
    RED,
    BLACK,
    FIRST_HALF,
    SECOND_HALF,
    FIRST_TIERCE,
    SECOND_TIERCE,
    THIRD_TIERCE,
    FIRST_ROW,
    SECOND_ROW,
    THIRD_ROW;

}

class RouletteNumber {
    int value;
    char color;

    RouletteNumber(int value, char color) {
        this.value = value;
        this.color = color;
    }
}

public class RoulettePredictor {
    static int MAX_THRESHOLD_2 = 2;
    static int MAX_THRESHOLD_3 = 5;
    static int MAX_INDIVIDUAL_BID = 7;
    public static Random randomGenerator = new Random();
    static int evenCount;
    static int oddCount;
    static int firstHalfCount;
    static int secondHalfCount;
    static int firstTierceCount;
    static int secondTierceCount;
    static int thirdTierceCount;
    static int redCount;
    static int blackCount;
    static int firstRowCount;
    static int secondRowCount;
    static int thirdRowCount;
    static int longestEvenCount;
    static int longestOddCount;
    static int currentEvenCount;
    static int currentOddCount;
    static int longestRedCount;
    static int longestBlackCount;
    static int currentRedCount;
    static int currentBlackCount;

    static Map<RouletteBidType,Integer> longestCount;
    static Map<RouletteBidType,Integer> currentCount;
    static Map<RouletteBidType,Integer> totalCount;
    static HashMap numberCount = new HashMap<Integer, Integer>();
    static List<RouletteNumber> inputList = new ArrayList();
    static Map<RouletteBidType,Integer> previousBid;

    public static void main(String args[] ) throws Exception {

        int MAX_BET =10;
        /** input format: sum min turn
         * sum: the sum with which user starts
         * unit: minimum unit that can be bet
         * turn: number of times the roulette should spin
         */
        Scanner in = new Scanner(System.in);
        int turns = in.nextInt();
        int balance = in.nextInt();

        RandomGeneratorFeed feed = new RandomGeneratorFeed();
        feed.init();

        int count = 0;
        while (count < turns) {
            //Roulette spin random numbers
            //Point number = randomNumberGenerator();

            //Take from actual feed Server streaming
            RouletteNumber number = feed.getNumberNext();


            Map bidList = predictorProcess(balance);
            //validate bidList;
            int profit= calculateProfit(bidList,number);
            balance+=profit;
            printCurrentTransaction(count,profit, number, bidList);

            //For Statistics purpose
            implementCount(number.value, number.color);
            count++;
        }
        printNumberStatistics();
        System.out.print("BALANCE:"+balance);
    }

    public static void printCurrentTransaction(int roundNumber, int profit, RouletteNumber numberSelected, Map<RouletteBidType,Integer> bid)
    {
        String num;
        if(numberSelected.color=='R')
            num="R"+numberSelected.value;
        else
            num="B"+numberSelected.value;
        System.out.println("Round        Number      Profit        Bid");
        System.out.println("-------------------------------------------");
        System.out.println(roundNumber+"           "+num+"            "+profit+"        "+bid.toString());
        System.out.println("--------------------------------------------------------------------------");
    }
    public static int calculateProfit(Map<RouletteBidType,Integer> bidList, RouletteNumber number)
    {
        int value=number.value;
        char color=number.color;  //R->RED  B->BLACK
        int amountLoss=0;
        int amountGain=0;

        for(RouletteBidType bidType: bidList.keySet())
        {
            int count=bidList.get(bidType);
            switch(bidType)
            {
                case ODD:
                    if(value%2==1)
                    {
                        amountGain+=count*2;
                    }
                    else
                    {
                        amountLoss+=count;
                    }
                    break;
                case EVEN:
                    if(value%2==0)
                    {
                        amountGain+=count*2;
                    }
                    else
                    {
                        amountLoss+=count;
                    }
                    break;
                case RED:
                    if(color=='R')
                    {
                        amountGain+=count*2;
                    }
                    else
                    {
                        amountLoss+=count;
                    }
                    break;
                case BLACK:
                    if(color=='B')
                    {
                        amountGain+=count*2;
                    }
                    else
                    {
                        amountLoss+=count;
                    }
                    break;
                case FIRST_TIERCE:
                    if(value<=12 && value>0)
                    {
                        amountGain+=count*2;
                    }
                    else
                    {
                        amountLoss+=count;
                    }
                    break;
                case SECOND_TIERCE:
                    if(value<=24 && value>12)
                    {
                        amountGain+=count*2;
                    }
                    else
                    {
                        amountLoss+=count;
                    }
                    break;
                case THIRD_TIERCE:
                    if(value<=36 && value>24)
                    {
                        amountGain+=count*2;
                    }
                    else
                    {
                        amountLoss+=count;
                    }
                    break;
                case FIRST_ROW:
                    if(value%3==0)
                    {
                        amountGain+=count*2;
                    }
                    else
                    {
                        amountLoss+=count;
                    }
                    break;
                case SECOND_ROW:
                    if(value%3==1)
                    {
                        amountGain+=count*2;
                    }
                    else
                    {
                        amountLoss+=count;
                    }
                    break;
                case THIRD_ROW:
                    if(value%3==2)
                    {
                        amountGain+=count*2;
                    }
                    else
                    {
                        amountLoss+=count;
                    }
                    break;
                case FIRST_HALF:
                    if(value<=18)
                    {
                        amountGain+=count*2;
                    }
                    else
                    {
                        amountLoss+=count;
                    }
                    break;
                case SECOND_HALF:
                    if(value>18 && value<=36)
                    {
                        amountGain+=count*2;
                    }
                    else
                    {
                        amountLoss+=count;
                    }
                    break;

            }
        }
        return amountGain-amountLoss;
    }

    public static void printNumberStatistics()
    {
        System.out.println("Item          Count              Deviation");
        System.out.println("--------------------------------------------");
        System.out.println("1-18         " +firstHalfCount);
        System.out.println("19-36        "+secondHalfCount);
        System.out.println("1st 12       "+firstTierceCount);
        System.out.println("2nd 12       "+secondTierceCount);
        System.out.println("3rd 12       "+thirdTierceCount);
        System.out.println("Red          "+redCount);
        System.out.println("Black        "+blackCount);
        System.out.println("Odd          "+oddCount);
        System.out.println("Even         "+evenCount);
        System.out.println("Top Row      "+firstRowCount);
        System.out.println("Mid Row      "+secondRowCount);
        System.out.println("Bot Row      "+thirdRowCount);
        System.out.println("---------------------------------------------");
        System.out.println("Longest Red streak: "+longestRedCount);
        System.out.println("Longest Black streak: "+longestBlackCount);
        System.out.println("Longest Odd streak: "+longestOddCount);
        System.out.println("Longest Even streak: "+longestEvenCount);

    }

    public static Map<RouletteBidType, Integer> predictorProcess(int account)
    {
       int currentBid=0;
       Map<RouletteBidType, Integer> currentBidding = new HashMap<>();

       if(currentRedCount > MAX_THRESHOLD_2)
       {
           if(null!=previousBid.get(RouletteBidType.BLACK))
           {currentBid=previousBid.get(RouletteBidType.BLACK)*2+1;}
           else
           {currentBid=1;}

           if(currentBid>MAX_INDIVIDUAL_BID && account-currentBid<MAX_INDIVIDUAL_BID)
           {
               currentBid = 1;
           }
           currentBidding.put(RouletteBidType.BLACK, currentBid);
       }
       else if(currentBlackCount > MAX_THRESHOLD_2)
       {
           if(null!=previousBid.get(RouletteBidType.RED))
           {currentBid=previousBid.get(RouletteBidType.RED)*2+1;}
           else
           {currentBid=1;}

           if(currentBid>MAX_INDIVIDUAL_BID && account-currentBid<MAX_INDIVIDUAL_BID)
           {
               currentBid = 1;
           }
           currentBidding.put(RouletteBidType.RED, currentBid);
       }

        if(currentEvenCount > MAX_THRESHOLD_2)
        {
            if(null!=previousBid.get(RouletteBidType.ODD))
            {currentBid=previousBid.get(RouletteBidType.ODD)*2+1;}
            else
            {currentBid=1;}

            if(currentBid>MAX_INDIVIDUAL_BID && account-currentBid<MAX_INDIVIDUAL_BID)
            {
                currentBid = 1;
            }
            currentBidding.put(RouletteBidType.ODD, currentBid);
        }
        else if(currentOddCount > MAX_THRESHOLD_2)
        {
            if(null!=previousBid.get(RouletteBidType.EVEN))
            {currentBid=previousBid.get(RouletteBidType.EVEN)*2+1;}
            else
            {currentBid=1;}

            if(currentBid>MAX_INDIVIDUAL_BID && account-currentBid<MAX_INDIVIDUAL_BID)
            {
                currentBid = 1;
            }
            currentBidding.put(RouletteBidType.EVEN, currentBid);
        }
       previousBid = currentBidding;
       return currentBidding;
    }


    public static RouletteNumber randomNumberGenerator()
    {
        // Generate random integers in range 0 to 37
        int rand_int1 = randomGenerator.nextInt(37);

        String color= getColor(((Integer) rand_int1).toString());
        char rand_color = color.charAt(0);


        RouletteNumber number = new RouletteNumber(rand_int1, rand_color);
        return number;
    }

    public static void implementCount(int currentValue, char currentColor)
    {
        if(currentOddCount>longestOddCount)
            longestOddCount=currentOddCount;

        if(currentEvenCount>longestEvenCount)
            longestEvenCount=currentEvenCount;

        if(currentRedCount>longestRedCount)
            longestRedCount=currentRedCount;

        if(currentBlackCount>longestBlackCount)
            longestBlackCount=currentBlackCount;

        if(currentValue%2==0)
        {
            evenCount++;
            currentEvenCount++;
            currentOddCount=0;

        }
        else
        {
            oddCount++;
            currentOddCount++;
            currentEvenCount=0;
        }

        if(currentColor=='R')
        {
            redCount++;
            currentRedCount++;
            currentBlackCount=0;
        }
        else if(currentColor=='B')
        {
            blackCount++;
            currentBlackCount++;
            currentRedCount=0;
        }

        if(currentValue<13)
        {
            firstTierceCount++;
        }
        else if(currentValue<25)
        {
            secondTierceCount++;
        }
        else
        {
            thirdTierceCount++;
        }

        if(currentValue<=18)
        {
            firstHalfCount++;
        }
        else
        {
            secondHalfCount++;
        }

        int rem = currentValue%3;
        if(rem==0)
        {
            firstRowCount++;
        }
        else if(rem==1)
        {
            secondRowCount++;
        }
        else
        {
            thirdRowCount++;
        }
    }

    public static String getColor(final String number) {

        if (number.equals("1"))
            return ("R");
        if (number.equals("2"))
            return ("B");
        if (number.equals("3"))
            return ("R");
        if (number.equals("4"))
            return ("B");
        if (number.equals("5"))
            return ("R");
        if (number.equals("6"))
            return ("B");
        if (number.equals("7"))
            return ("R");
        if (number.equals("8"))
            return ("B");
        if (number.equals("9"))
            return ("R");
        if (number.equals("10"))
            return ("B");
        if (number.equals("11"))
            return ("B");
        if (number.equals("12"))
            return ("R");
        if (number.equals("13"))
            return ("B");
        if (number.equals("14"))
            return ("R");
        if (number.equals("15"))
            return ("B");
        if (number.equals("16"))
            return ("R");
        if (number.equals("17"))
            return ("B");
        if (number.equals("18"))
            return ("R");
        if (number.equals("19"))
            return ("R");
        if (number.equals("20"))
            return ("B");
        if (number.equals("21"))
            return ("R");
        if (number.equals("22"))
            return ("B");
        if (number.equals("23"))
            return ("R");
        if (number.equals("24"))
            return ("B");
        if (number.equals("25"))
            return ("R");
        if (number.equals("26"))
            return ("B");
        if (number.equals("27"))
            return ("R");
        if (number.equals("28"))
            return ("B");
        if (number.equals("29"))
            return ("B");
        if (number.equals("30"))
            return ("R");
        if (number.equals("31"))
            return ("B");
        if (number.equals("32"))
            return ("R");
        if (number.equals("33"))
            return ("B");
        if (number.equals("34"))
            return ("R");
        if (number.equals("35"))
            return ("B");
        if (number.equals("36"))
            return ("R");
        if (number.equals("0"))
            return ("G");
        if (number.equals("00"))
            return ("G");

        return("P");
    }

}

class RandomGeneratorFeed
{


    static Queue<Integer> numberFeed=new LinkedList<>();
    static String Feed="17 13 10 16 5 17 33 13 23 11 5 28 34 19 26 34 26 25 18 13 25 18 23 3 30 25 6 25 22 35 13 4 25 31 0 23 25 13 13 24 34 18 19 17 26 22 5 9 24 22 16 29 5 9 15 17 17 10 16 4 31 13 34 12 23 20 26 36 35 32 36 19 22 13 20 29 16 33 29 22 34 16 28 29 23 13 0 33 15 18 19 25 23 22 31 32 0 30 21 25";

    public static void init() {
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(Feed);
            while (m.find()) {
                numberFeed.add(Integer.parseInt(m.group()));
            }
    }

    public static RouletteNumber getNumberNext()
    {
        int value = numberFeed.poll();
        String color = RoulettePredictor.getColor(((Integer)value).toString());
        char r_color = color.charAt(0);

        RouletteNumber p = new RouletteNumber(value, r_color);
        return p;
    }
}



