import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(br.readLine());
        while (t --> 0) {
            int n = Integer.parseInt(br.readLine());

            int cnt = 0;
            for (int i = 1; i <= n; i++) {
                if (n%i == 0) {
                    int x = i;
                    int y = n/i;

                    // (5, 6) -> (6, 5) 바뀌는 순간 멈춤
                    if (x > y) break;

                    int g = gcd(x, y);
                    int l = lcm(x, y);

                    if (g == 1 && l == x*y) cnt++;
                }
            }

            sb.append(cnt).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static int gcd(int x, int y) {
        while (y != 0) {
            int r = x%y;
            x = y;
            y = r;
        }
        return x;
    }

    private static int lcm(int x, int y) {
        return x*y/gcd(x, y);
    }
}