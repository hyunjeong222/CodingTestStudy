import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        StringTokenizer st;
        int n;
        while (t-->0) {
            n = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            int sum = 0;
            for (int i = 0; i < n; i++) {
                int num = Integer.parseInt(st.nextToken());
                sum += num;
            }

            sb.append(sum).append("\n");
        }

        System.out.println(sb.toString());


        br.close();
    }
}