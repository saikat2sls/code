import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/*
https://www.hackerearth.com/codearena/ring/4624b9c/
 */

public class powerbox {
    public static void main(String args[]) throws Exception {
        //System.out.println(getCommon("ADDA","BDAA"));
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {

            List<Long> nums = new ArrayList<>();
            int N = in.nextInt();
            long max = 0;
            for (int j = 0; j < N; j++) {
                long num = in.nextLong();
                nums.add(num);
                if (max < num) {
                    max = num;
                }
            }

            Collections.sort(nums, Collections.reverseOrder());

            int multiplier = N - 1;
            long output = 0;
            for (long num : nums) {
                output = output + (num * multiplier) % 1000000007;
                multiplier -= 2;
            }

            System.out.println("max:"+max+"sum:"+output);
            System.out.println(output*max%1000000007);
        }
    }
}
