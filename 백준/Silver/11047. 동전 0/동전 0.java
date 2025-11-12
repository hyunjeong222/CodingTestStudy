import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 동전 종류
        int k = Integer.parseInt(st.nextToken()); // 가치의 합

        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int cnt = 0; // 동전 개수의 최솟값
        for (int i = coins.length-1; i >= 0; i--) {
            if (coins[i] <= k) { // 현재 동전의 가치가 K보다 작거나 같아야지 구성 가능
                cnt += (k/coins[i]);
                k = k%coins[i];
            }
        }

        System.out.println(cnt);

        br.close();
    }
}