import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static final long MOD = 1_000_000_007;
    static long[][] origin = {{1, 1}, {1, 0}}; // 초기값을 갖고있는 행렬
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        /*
         *                n
         *       | 1   1 |    | F(n+1)  F(n)  |
         * A^n = |       |  = |               |
         *       | 1   0 |    |  F(n)  F(n-1) |
         */

        long[][] A = {{1, 1}, {1, 0}};

        long n = Long.parseLong(br.readLine());

        // Fn을 구하려면  A행렬을 n-1제곱 한 뒤 반환된 행렬 A11 원소를 출력
        System.out.println(pow(A, n-1)[0][0]);
    }

    private static long[][] pow(long[][] A, long exp) {
        if (exp == 0 || exp == 1) {
            return A;
        }

        // 지수를 절반으로 분할하여 재귀호출
        long[][] ret = pow(A, exp/2);

        // 하위 재귀에서 얻은 행렬을 제곱
        ret = multiply(ret, ret);

        if (exp % 2 == 1L) {
            return ret = multiply(ret, origin);
        }

        return ret;
    }

    private static long[][] multiply(long[][] o1, long[][] o2) {
        long[][] ret = new long[2][2];

        ret[0][0] = ((o1[0][0]*o2[0][0])+(o1[0][1]*o2[1][0])) % MOD;
        ret[0][1] = ((o1[0][0]*o2[0][1])+(o1[0][1]*o2[1][1])) % MOD;
        ret[1][0] = ((o1[1][0]*o2[0][0])+(o1[1][1]*o2[1][0])) % MOD;
        ret[1][1] = ((o1[1][0]*o2[0][1])+(o1[1][1]*o2[1][1])) % MOD;

        return ret;
    }
}