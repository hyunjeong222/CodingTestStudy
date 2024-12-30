import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int min = Math.min(s, k);
        min = Math.min(min, h);

        int sum = s + k + h;

        StringBuilder sb = new StringBuilder();
        if (sum >= 100) {
            sb.append("OK").append("\n");
        } else {
            if (min == s) sb.append("Soongsil").append("\n");
            else if (min == k) sb.append("Korea").append("\n");
            else sb.append("Hanyang").append("\n");
        }

        System.out.println(sb.toString());
    }
}