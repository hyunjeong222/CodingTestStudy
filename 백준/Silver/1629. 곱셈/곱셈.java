import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken()); // 숫자
        long b = Long.parseLong(st.nextToken()); // 지수
        long c = Long.parseLong(st.nextToken()); // 나누기

        System.out.println(pow(a, b, c));
    }

    private static long pow(long a, long b, long c) {
        if (b == 1) {
            return a % c;
        }
        else {
            long half = pow(a, b/2, c);

            if (b % 2 == 1) {
                return (half*half%c)*a%c;
            }
            return half*half%c;
        }
    }
}