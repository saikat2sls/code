public class sum
{

    public static void main(String[] args) {

        int num1 = 5, num2 = 15, sum;
        sum = num1 + num2;
        String fdn="enterpriseprofile:2c8a9ee6-9965-11ea-bd80-8deea81938ba:multicastlist:2c906b47-9965-11ea-bd80-fb7cb1bad1cd:permission:2d706a52-9965-11ea-bd80-058a96054216";
        int secondLastIndex=fdn.lastIndexOf(':',fdn.lastIndexOf(':')-1);
        String parentFDN = fdn.substring(0, secondLastIndex);

        System.out.println(parentFDN);
    }
}