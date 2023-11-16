import java.io.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        if (n == 0) {
            bw.append(0 + "\n");
        } else {
            BigInteger[] dp = new BigInteger[n+1];
            dp[0] = new BigInteger("0");
            dp[1] = new BigInteger("1");
            for (int i = 2; i <= n; i++) {
                dp[i] = dp[i-2].add(dp[i-1]);
            }
            bw.append(dp[n] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}