import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean[] prime;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());

        prime = new boolean[n+1];
        getPrime();

        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int i = m; i <= n; i++) {
            if (!prime[i]) {
                sum += i;
                if (min == Integer.MAX_VALUE) {
                    min = i;
                }
            }
        }

        if (sum == 0) System.out.println(-1);
        else sb.append(sum).append("\n").append(min);

        System.out.println(sb.toString());

        br.close();
    }

    private static void getPrime() {
        // 소수 X true
        prime[0] = true;
        prime[1] = true;

        for (int i = 2; i <= Math.sqrt(prime.length); i++) {
            for (int j = i*i; j < prime.length; j+=i) {
                prime[j] = true;
            }
        }
    }
}