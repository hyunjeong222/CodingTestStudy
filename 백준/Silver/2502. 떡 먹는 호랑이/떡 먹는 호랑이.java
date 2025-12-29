import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int d, k;
    static int A, B;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        d = Integer.parseInt(st.nextToken()); // 할머니가 넘어온 날
        k = Integer.parseInt(st.nextToken()); // 그 날 호랑이에게 준 떡의 개수

        A = 0; // 첫 날에 준 떡의 개수
        B = 0; // 둘째 날에 준 떡의 개수

        // 1 ≤ A ≤ B
        for (int i = 1; i <= k; i++) { // 첫 날 떡을 준 개수
            for (int j = i+1; j <= k-1; j++) { // 둘째 날 떡을 준 개수
                if (getAB(i, j)) {
                    System.out.println(A);
                    System.out.println(B);

                    System.exit(0);
                }
            }
        }

        br.close();
    }

    private static boolean getAB(int a, int b) {
        // 어제 받은 떡의 개수와 그저께 받은 떡의 개수를 더한 만큼의 떡을 받아야함 -> 피보나치 수열
        int[] dp = new int[31];

        // 첫째날, 둘째날에 떡을 준 개수
        dp[1] = a; dp[2] = b;
        for (int i = 3; i <= 30; i++) {
            dp[i] = dp[i-1]+dp[i-2];

            if (dp[i] > k) return false;
            else if (dp[i] == k && i == d) {
                A = a; B = b;
                return true;
            }
        }

        return false;
    }
}