import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
https://www.hackerearth.com/codearena/ring/80f8b03/

 */

public class restuarant {
    public static void main(String args[]) throws Exception {
        //System.out.println(getCommon("ADDA","BDAA"));
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        in.nextLine();
        String input = in.nextLine();
        //System.out.println(input);
        Map<Character, Integer> specialize = new HashMap<>();
        specialize.put('V', 0);
        specialize.put('N', 0);
        specialize.put('G', 0);
        specialize.put('T', 0);
        specialize.put('S', 0);
        specialize.put('B', 0);
        specialize.put('P', 0);


        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            {

                int count = specialize.get(c);
                specialize.put(c, ++count);

            }
        }

        //System.out.println(specialize.keySet().toString());
        //System.out.println(specialize.values().toString());


        int T = in.nextInt();
        in.nextLine();
        long ways = 1;
        for (int i = 0; i < T; i++) {
            ways=1;
            String order = in.nextLine();
            int isPossible=1;
            for (int j = 0; j < order.length(); j++) {
                char c = order.charAt(j);
                {
                    int temp = specialize.get(c);
                    if (temp == 0)
                    {
                        isPossible=0;
                    }
                }
            }

            if(isPossible==1) {
                for (int j = 0; j < order.length(); j++) {
                    char c = order.charAt(j);
                    {
                        int temp = specialize.get(c);
                        ways = ways * temp;
                        ways= ways%1000000007;
                        if (temp > 0)
                            specialize.put(c, --temp);
                    }
                }
            }
            else
            {
                ways=0;
            }

            System.out.println(ways);

        }
    }

}
