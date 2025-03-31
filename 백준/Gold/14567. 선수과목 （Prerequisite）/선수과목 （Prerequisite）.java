import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[] dp;
    static ArrayList<ArrayList<Integer>> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 과목의 수
        m = Integer.parseInt(st.nextToken()); // 선수 조건의 수

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

       int a, b; // A번 과목이 B번 과목의 선수과목
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
        }

        dp = new int[n+1];
        Arrays.fill(dp, 1);

        for (int i = 1; i <= n; i++) {
            if (list.get(i).isEmpty()) continue;

            for (int now : list.get(i)) {
                dp[now] = Math.max(dp[now], dp[i]+1);
            }
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(dp[i]).append(" ");
        }
        System.out.println(sb.toString());
    }
}