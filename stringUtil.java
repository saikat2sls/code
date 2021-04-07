public class stringUtil {
    public static void main(String args[]) throws Exception {
        String input = "OSPF.v1.ospfv2";
        String output = input.substring(input.lastIndexOf('.') + 1).trim();
        System.out.println(output);
    }
}
