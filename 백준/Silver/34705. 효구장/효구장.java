import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int N = 5;
    static int x, y;
    static int[] weight;
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        StringTokenizer st;
        while (t --> 0) {
            st = new StringTokenizer(br.readLine());
            // x 이상 y 이하
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            weight = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                weight[i] = Integer.parseInt(st.nextToken());
            }

            flag = false;
            dfs(0, 0);

            sb.append(flag ? "YES" : "NO").append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static void dfs(int idx, int sum) {
        if (idx == N) {
            if (sum >= x && sum <= y) {
                flag = true;
            }

            return;
        }

        dfs(idx+1, sum+weight[idx]);
        dfs(idx+1, sum);
    }
}