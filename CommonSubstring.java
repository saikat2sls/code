import java.util.Scanner;

public class CommonSubstring {
    public static void main(String args[]) throws Exception {
        /* Sample code to perform I/O:
         * Use either of these methods for input */

        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        in.nextLine();
        String word1 = in.nextLine();
        for(int i=1;i<t;i++)
        {
            String word2 = in.nextLine();
            word1 = findCommonSubString(word1, word2);
        }
        System.out.println(word1);
    }

    public static String findCommonSubString(String word1, String word2)
    {
        String word="";
        int l1= word1.length();
        int l2= word2.length();
        char[] w1 = new char[word1.length()];
        char[] w2 = new char[word2.length()];
        int max=0;
        for (int i = 0; i < word1.length(); i++) {
            w1[i] = word1.charAt(i);
        }

        for (int i = 0; i < word2.length(); i++) {
            w2[i] = word2.charAt(i);
        }
        //System.out.println(l1+" "+l2+" "+word1+" "+word2);

        for(int i=0;i<l1;i++)
        {
            for(int j=0;j<l2;j++)
            {
                int x=i;
                int y=j;
                //System.out.println(w1[x]+":"+w2[y]);
                while(w1[x]==w2[y])
                {
                    //System.out.println("MATCH");
                    x++;
                    y++;
                    if(x==l1 || y==l2)
                    {
                        break;
                    }
                }
                int localMax=x-i-1;
                if(localMax>max)
                {
                    max = localMax ;
                    word = word1.substring(i,x);
                    //System.out.println(word);
                }
            }
        }
        return word;
    }
}
