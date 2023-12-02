import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String str = br.readLine();

            if (str.equals("0")) break;;

            int left = 0;
            int right = str.length()-1;
            boolean flag = true;

            while (left < right) {
                if (str.charAt(left) != str.charAt(right)) {
                    flag = false;
                }
                left++;
                right--;
            }

            if (flag) sb.append("yes").append("\n");
            else sb.append("no").append("\n");
        }
        System.out.println(sb.toString());
    }
}