import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 총 밀가루
        int m = Integer.parseInt(st.nextToken()); // 만두 종류
        int c0 = Integer.parseInt(st.nextToken()); // 스페셜 만두에 필요한 밀가루
        int d0 = Integer.parseInt(st.nextToken()); // 스페셜 만두 판매 금액

        int[][] dumplings = new int[m+1][4];
        // 스페셜 만두
        dumplings[0][0] = n/c0;
        dumplings[0][1] = 1;
        dumplings[0][2] = c0;
        dumplings[0][3] = d0;

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            dumplings[i][0] = Integer.parseInt(st.nextToken()); // 남아있는 만두 속
            dumplings[i][1] = Integer.parseInt(st.nextToken()); // 하나의 만두를 위해 필요한 만두 속
            dumplings[i][2] = Integer.parseInt(st.nextToken()); // 피를 만드는데 필요한 밀가루 양
            dumplings[i][3] = Integer.parseInt(st.nextToken()); // 판매 금액
        }

        int[] dp = new int[n+1];
        for (int[] dumpling : dumplings) {
            for (int i = n; i >= dumpling[2]; i--) {
                int max = dumpling[0]/dumpling[1]; // 남아있는 만두 속으로 만들 수 있는 만두 개수
                for (int j = 1; j <= max; j++) {
                    if (i-dumpling[2]*j < 0) continue;
                    dp[i] = Math.max(dp[i], dp[i-dumpling[2]*j]+dumpling[3]*j);
                }
            }
        }

        System.out.println(dp[n]);
    }
}