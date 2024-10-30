import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int n = 11;
    static int c;
    static int[][] map;
    static boolean[] checked;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        c = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (c --> 0) {
            map = new int[n][n];
            checked = new boolean[n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            ans = 0;
            dfs(0, 0);
            sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }

    private static void dfs(int idx, int sum) {
        if (idx == n) {
            ans = Math.max(ans, sum);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!checked[i] && map[idx][i] != 0) {
                checked[i] = true;

                dfs(idx+1, sum+map[idx][i]);

                checked[i] = false;
            }
        }
    }
}