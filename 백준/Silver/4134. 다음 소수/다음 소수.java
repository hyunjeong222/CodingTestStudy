import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine()); // 테스트 케이스

        while (t --> 0) {
            long n = Long.parseLong(br.readLine());

            while (!isPrime(n)) {
                n++;
            }

            sb.append(n).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static boolean isPrime(long n) {
        if (n < 2) return false;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            // 나누어 떨어지는 것이 있다면 소수 X
            if (n % i == 0) return false;
        }

        return true;
    }
}