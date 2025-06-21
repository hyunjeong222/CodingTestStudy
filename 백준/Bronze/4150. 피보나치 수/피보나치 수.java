import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        BigInteger[] dp = new BigInteger[n+3];
        dp[1] = BigInteger.valueOf(1);
        dp[2] = BigInteger.valueOf(1);
        for (int i = 3; i < n+1; i++) {
            dp[i] = dp[i-1].add(dp[i-2]);
        }
        System.out.println(dp[n]);
        
        br.close();
    }
}