
import java.lang.*;
//Swap the bits in the given number

//int num = 0x01  // bit numbering  is 0..31
// after reverse num = 0x80000000  // bit number 32 - i -1
// 0th bit goes to 31st and 31st comes to 0th
// 1st bit goes to 30th and 30th bit comes to 1st bit
//  111000(56)  -> 000111(7)
//  000000000000000000000001110101  -> 10101110000000000000000
//  111000 -111000 = b =0
//   000111
//   101110 -> 010111 -> 101011 -> 110101 -> 111010 -> 011101

// max number = 2^32
//reverse  = max-given

//step 1: find the length of bits involved from right (l)
// step 2: calculate th reverse for l bits => max(l) - input
//step 3: reverse<<(32-l)


public class BitReverse
{

    static int swap_bits (int num)
    {
        //find out the startbit  (s)
        int output=0;
        int count=0;
        while(num!=0)
        {
            int i = num&1;
            output = (output<<1) + i;
            num=num>>1;
            count++;
        }
        return output<<(32-count);
    }


    public static void main(String arg[])
    {
        int input = 123;
        System.out.println(Integer.toBinaryString(input));
        System.out.println(Integer.toBinaryString(swap_bits(input)));
    }

}
