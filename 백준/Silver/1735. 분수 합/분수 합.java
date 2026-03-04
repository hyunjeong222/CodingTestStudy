import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb  = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken()); // 분자
        int b = Integer.parseInt(st.nextToken()); // 분모

        st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken()); // 분자
        int d = Integer.parseInt(st.nextToken()); // 분모

        int numerator = (a*d)+(c*b); // 분자
        int denominator = b*d; // 분모

        // 최대 공약수 구하기
        int mod = gcd(numerator, denominator);

        numerator /= mod;
        denominator /= mod;

        sb.append(numerator).append(" ").append(denominator).append("\n");

        System.out.println(sb.toString());

        br.close();
    }

    private static int gcd(int a, int b) {
        if (a%b == 0) {
            return b;
        }

        return gcd(b, a%b);
    }
}