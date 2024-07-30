import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BigDecimal a = new BigDecimal(st.nextToken());
        BigDecimal b = new BigDecimal(st.nextToken());
        BigDecimal ans = divide(a, b);
        System.out.println(ans);
    }

    private static BigDecimal divide(BigDecimal a, BigDecimal b) {
        return a.divide(b, 1001, BigDecimal.ROUND_DOWN);
    }
}