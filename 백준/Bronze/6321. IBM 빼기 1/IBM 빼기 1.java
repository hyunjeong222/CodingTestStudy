import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(br.readLine());

        int idx = 0;
        while (n --> 0) {
            sb.append("String #").append(++idx).append("\n");

            String str = br.readLine();

            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);

                if (c != 'Z') {
                    sb.append((Character.toChars(c+1)));
                } else {
                    sb.append('A');
                }
            }

            sb.append("\n").append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }
}