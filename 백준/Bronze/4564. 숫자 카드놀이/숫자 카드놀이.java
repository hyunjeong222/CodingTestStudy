import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // StringTokenizer st = new StringTokenizer(br.readLine());
        while (true) {
            String str = br.readLine();

            if (str.equals("0")) break;

            while (str.length() > 1) {
                sb.append(str).append(" ");

                int res = 1;
                for (char c : str.toCharArray()) {
                    res *= (c-'0');
                }

                str = String.valueOf(res);
            }

            sb.append(str).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }
}