import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        if (a > b) {
            sb.append(a*b/findGCD(a, b)).append("\n");
        } else if (a < b) {
            sb.append(a*b/findGCD(b, a)).append("\n");
        } else sb.append(a).append("\n");

        System.out.println(sb.toString());

        br.close();
    }

    private static long findGCD(long a, long b) {
        while (b != 0) {
            long r = a%b;
            a = b;
            b = r;
        }

        return a;
    }
}