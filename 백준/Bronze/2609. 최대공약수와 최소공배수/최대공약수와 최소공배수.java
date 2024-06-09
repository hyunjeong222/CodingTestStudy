import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int d = gcd(a, b); // 최대 공약수

        System.out.println(d);
        System.out.println(a * b / d); // lcm, 최대 공배수
    }

    private static int gcd(int a, int b) {
        /*
        if (b == 0) return 0;

        // GCD(a, b) = GCD(b, r)이므로 (r = a % b)
        return gcd(b, a % b);
        */
        while (b != 0) {
            int r = a % b;

            // GCD(a, b) = GCD(b, r)이므로 변환
            a = b;
            b = r;
        }

        return a;
    }
}