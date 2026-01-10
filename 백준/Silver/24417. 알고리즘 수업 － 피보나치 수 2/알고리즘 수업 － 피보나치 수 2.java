import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int MOD = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(br.readLine());

        int fir = 1;
        int sec = 1;
        int result = 0;

        if (n == 1 || n == 2) {
            result = 1;
        } else  {
            for (int i = 3; i <= n; i++) {
                result = (fir+sec)%MOD;
                fir = sec;
                sec = result;
            }
        }

        sb.append(result).append(" ").append(n-2);

        System.out.println(sb.toString());

        br.close();
    }
}