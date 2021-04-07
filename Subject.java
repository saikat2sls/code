import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Subject {
    public static void main(String args[]) throws Exception {
        //System.out.println(getCommon("ADDA","BDAA"));
        Scanner in = new Scanner(System.in);
        Map<String, Integer>rank = new LinkedHashMap<>();
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        Map<String, Integer> reverseSortedMap = new LinkedHashMap<String, Integer>();
        int T = in.nextInt();
        in.nextLine();
        for (int i = 0; i < T; i++) {
            String input = in.nextLine();
            String[] Value = input.split(" ");
            String name = Value[0];
            String sid = Value[1];
            int marks = Integer.parseInt(Value[2]);
            rank.put(name+":"+sid, marks);
            //rank.put(name,marks+","+sid);
            //System.out.println(name + ":" + sid + ":" + marks);
        }

        rank.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

        sortedMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

        //System.out.println("Rank   : " + rank);
        System.out.println("Sorted Map   : " + sortedMap);
        System.out.println("Final Sorted Map   : " + reverseSortedMap);


        /*
        for(String user: sortedMap.keySet())
        {
            String[] Value = user.split(":");
            System.out.println(Value[0]+" "+Value[1]+rank.get(user));
        }

         */

    }


}
